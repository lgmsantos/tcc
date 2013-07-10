package graph.algorithms.task.execution;

import static java.lang.String.format;
import static java.util.Collections.min;
import graph.algorithms.task.Task;
import graph.algorithms.task.TaskInput;

import java.util.ArrayList;
import java.util.List;

public class Execution<T extends TaskInput<?>> implements Comparable<Execution<T>>{

    private Task<T> task;
    private T input;
    private int executionCount;
    private List<FASResult> results;

    public Execution(final Task<T> task, final T input) {
        this.task = task;
        this.input = input;
        executionCount = 0;
        results = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        String bestResult = results.isEmpty() ? "--" : String.valueOf(bestResult());
        return format("%s (%s) = %s (%s/%s)", task, input, bestResult, executionCount, task.iterationCount());
    }
    
    public Task<T> getTask() {
        return task;
    }

    public FASResult bestResult() {
        if(results.isEmpty())
            return null;
        return min(results);
    }

    public boolean finished() {
        return executionCount >= task.iterationCount();  
    }

    public void step() {
        executionCount++;
        results.add(task.execute(input));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public int compareTo(Execution other) {
        int inputComp = ((TaskInput<TaskInput>)input).compareTo(other.input);
        if(inputComp != 0)
            return inputComp;
        return task.toString().compareTo(other.task.toString());
    }
}
