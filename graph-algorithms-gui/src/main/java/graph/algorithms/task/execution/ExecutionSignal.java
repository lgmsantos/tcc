package graph.algorithms.task.execution;

import static javax.swing.SwingUtilities.invokeLater;

import java.util.ArrayList;
import java.util.List;

public class ExecutionSignal {
    
    private List<ExecutionListener> listeners;

    public ExecutionSignal() {
        listeners = new ArrayList<>();
    }

    public void addExecutionListener(ExecutionListener executionListener){
        listeners.add(executionListener);
    }
    
    public void asyncSendStep(final int executionPointer, final Execution execution) {
        invokeLater(new Runnable() {            
            @Override
            public void run() {
                sendStep(executionPointer, execution);
            }
        });
    }
    
    public void asyncSendFinished(final int executionPointer, final Execution execution) {
        invokeLater(new Runnable() {            
            @Override
            public void run() {
                sendFinished(executionPointer, execution);
            }
        });
    }
    
    public void sendStep(int executionPointer, Execution execution) {
        for(ExecutionListener listener: listeners)
            listener.executed(executionPointer, execution);
    }

    public void sendFinished(int executionPointer, Execution execution) {
        for(ExecutionListener listener: listeners)
            listener.finished(executionPointer, execution);        
    }
}
