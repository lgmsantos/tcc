package graph.algorithms.gui;

import graph.algorithms.task.execution.ExecutionBatch;
import graph.algorithms.task.execution.ExecutionBatchFactory;
import graph.algorithms.task.execution.Executor;

import java.util.ArrayList;
import java.util.List;

public class ExecutionBatchControl {

    private List<ExecutionBatch> executionBatches;
    private ExecutionBatchFactory executionBatchFactory;
    private ExecutionList executionList;
    private int batchPointer;
    private Executor executor;
    private ExecutionChartPanel chartPanel;

    public ExecutionBatchControl(ExecutionList executionList, ExecutionBatchFactory executionBatchFactory,
            Executor executor, ExecutionChartPanel chartPanel) {
        this.executionList = executionList;
        this.executionBatchFactory = executionBatchFactory;
        this.executor = executor;
        this.chartPanel = chartPanel;
        executionBatches = new ArrayList<ExecutionBatch>();
        batchPointer = 0;
    }

    public void createBatch() {
        executionBatches.add(executionBatchFactory.createBatch());
        batchPointer = executionBatches.size() - 1;
        refreshExecutionList();
    }

    private void refreshExecutionList() {
        executionList.setListData(currentBatch().getExecutions());
        executionList.repaint();
        chartPanel.plot(currentBatch().getExecutions());
    }

    public void runBatch() {
        executor.executeBatch(currentBatch());
    }

    public void stopBatch() {
        executor.stop();
    }

    private ExecutionBatch currentBatch() {
        return executionBatches.get(batchPointer);
    }

    public void left() {
        if (executionBatches.size() == 0)
            return;
        batchPointer += -1 + executionBatches.size();
        batchPointer %= executionBatches.size();
        refreshExecutionList();
    }

    public void right() {
        if (executionBatches.size() == 0)
            return;
        batchPointer += 1;
        batchPointer %= executionBatches.size();
        refreshExecutionList();
    }

    public String label() {
        if (executionBatches.size() == 0)
            return "00/00";
        return String.format("%02d/%02d", batchPointer + 1, executionBatches.size());
    }

    public List<ExecutionBatch> getExecutionBatches() {
        return executionBatches;
    }

    public void setExecutionBatches(List<ExecutionBatch> executionBatches) {
        this.executionBatches = executionBatches;
    }

}
