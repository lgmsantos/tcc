package graph.algorithms.task.execution;

public interface ExecutionListener {

    void executionStepped(Execution<?> execution);

    void executionFinished(Execution<?> execution);

}
