package graph.algorithms.task.execution;

import static graph.algorithms.task.execution.State.FINISHED;
import static graph.algorithms.task.execution.State.NEW;
import static java.util.Collections.unmodifiableList;

import java.util.List;

public class ExecutionBatch {

    private int executionPointer;
    private Thread executionThread;
    private State state;
    private List<Execution> executionList;
    private ExecutionSignal executionSignal;

    public ExecutionBatch(List<Execution> executions, ExecutionSignal executionSignal) {
        executionList = executions;
        this.executionSignal = executionSignal;
        executionPointer = -1;
        state = NEW;
        executionThread = new Thread(new Runnable() {

            @Override
            public void run() {
                runLoop();
            }
        }, "ExecutionBatch");
    }

    protected void newRun() {
        executionPointer = 0;
        executionThread.start();
    }

    private void runLoop() {
        try {
            while (!isFinished()) {
                while (!isRunning())
                    Thread.sleep(500);
                if (executionPointer == executionList.size())
                    synchronized (this) {
                        state = FINISHED;
                    }
                else
                    step();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void step() {
        currentExecution().step();
        executionSignal.asyncSendStep(executionPointer, currentExecution());
        if (currentExecution().finished()) {
            executionSignal.asyncSendFinished(executionPointer, currentExecution());
            executionPointer++;
        }
    }

    private boolean isRunning() {
        synchronized (this) {
            return state.isRunning();
        }
    }

    private boolean isFinished() {
        synchronized (this) {
            return state.isFinished();
        }
    }

    private Execution currentExecution() {
        return executionList.get(executionPointer);
    }

    public void run() {
        synchronized (this) {
            state = state.run(this);
        }
    }

    public void stop() {
        synchronized (this) {
            state = state.stop(this);
        }
    }

    public List<Execution> getExecutions() {
        return unmodifiableList(executionList);
    }
}
