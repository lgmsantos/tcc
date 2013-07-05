package graph.algorithms.task.execution;

import java.util.List;
import java.util.TreeMap;

public class ResultDAO implements ExecutionListener {

    private TreeMap<Integer, List<FASResult>> resultMap;
    private int currentSet;
    
    public ResultDAO(ExecutionSignal signal) {
        signal.addExecutionListener(this);
        resultMap = new TreeMap<>();
        currentSet = 0;
    }
    
    @Override
    public void executed(int executionPointer, Execution execution) {
    }

    @Override
    public void finished(int executionPointer, Execution execution) {
        resultMap.get(currentSet).add(execution.bestResult());
    }

    @Override
    public void started() {
    }

}
