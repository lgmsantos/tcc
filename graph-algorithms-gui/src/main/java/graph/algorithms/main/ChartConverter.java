package graph.algorithms.main;

import graph.algorithms.task.execution.Execution;

import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartConverter {

    public CategoryDataset toDataSet(List<Execution<?>> executions) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Execution<?> ex : executions)
            if (ex.bestResult() != null) {
                dataSet.addValue(ex.bestResult().getFasSize(), ex.getTask().toString(), new Integer(ex.bestResult()
                        .getEdgeSetSize()));
            }
        return dataSet;
    }

}
