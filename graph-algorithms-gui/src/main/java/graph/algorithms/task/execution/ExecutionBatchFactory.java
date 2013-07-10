package graph.algorithms.task.execution;

import static java.util.Collections.sort;
import graph.algorithms.gui.TaskList;
import graph.algorithms.task.GraphInput;
import graph.algorithms.task.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExecutionBatchFactory {

    private TaskList taskList;
    private List<GraphInput> inputs;

    public ExecutionBatchFactory(TaskList taskList) {
        this.taskList = taskList;
    }

    public ExecutionBatch createBatch() {
        return new ExecutionBatch(executions());
    }

    private List<Execution<?>> executions() {
        List<Execution<?>> executions = new ArrayList<>();
        for (Task<GraphInput> task : taskList.getSelectedValuesList())
            for (GraphInput input : inputs())
                executions.add(new Execution<>(task, input));
        sort(executions);
        return executions;
    }

    private List<GraphInput> inputs() {
        if (inputs == null) {
            inputs = new ArrayList<>();
            String fileName = getClass().getResource("/input").getFile();
            for (File file : new File(fileName).listFiles())
                inputs.add(new GraphInput(file));
         }
        return inputs;
    }

}
