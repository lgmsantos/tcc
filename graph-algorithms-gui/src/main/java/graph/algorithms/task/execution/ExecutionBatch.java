package graph.algorithms.task.execution;

import static java.util.Collections.unmodifiableList;

import java.util.List;

public class ExecutionBatch {

    private List<Execution<?>> executionList;

    public ExecutionBatch(List<Execution<?>> executions) {
        executionList = executions;
    }

    public List<Execution<?>> getExecutions() {
        return unmodifiableList(executionList);
    }
}
