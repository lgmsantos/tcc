package graph.algorithms.task.execution;

import static java.lang.String.format;
import static java.util.Collections.max;
import graph.algorithms.task.Task;
import graph.algorithms.task.TaskInput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Execution implements Comparable<Execution>{

    private Task<?> task;
    private TaskInput<?> input;
    private int executionCount;
    private List<FASResult> results;
    private Callable<FASResult> step;

    public <E extends TaskInput<?>> Execution(final Task<E> task, final E input) {
        this.task = task;
        this.input = input;
        executionCount = 0;
        results = new ArrayList<>();
        step = new Callable<FASResult>() {

            @Override
            public FASResult call() throws Exception {
                return task.execute(input);
            }
        };
    }
    
    @Override
    public String toString() {
        String bestResult = results.isEmpty() ? "--" : String.valueOf(bestResult());
        return format("%s (%s) = %s (%s/%s)", task, input, bestResult, executionCount, task.iterationCount());
    }

    public FASResult bestResult() {
        return max(results);
    }

    public boolean finished() {
        return executionCount >= task.iterationCount();  
    }

    public void step() {
        executionCount++;
        try {
            results.add(step.call());
        } catch(RuntimeException e){
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
