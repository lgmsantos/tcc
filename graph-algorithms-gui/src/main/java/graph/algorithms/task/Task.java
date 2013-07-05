package graph.algorithms.task;

import graph.algorithms.task.execution.FASResult;


public interface Task<T extends TaskInput<?>> {
    
    public int iterationCount();

    public FASResult execute(T input);
}
