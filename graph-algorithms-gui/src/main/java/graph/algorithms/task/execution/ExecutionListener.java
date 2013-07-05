package graph.algorithms.task.execution;

public interface ExecutionListener {
    
    void started();

    void executed(int executionPointer, Execution execution);

    void finished(int executionPointer, Execution execution);

}
