package graph.algorithms.gui;

import static java.awt.Color.GRAY;
import static javax.swing.BorderFactory.createLineBorder;
import static org.jfree.chart.ChartFactory.createLineChart;
import static org.jfree.chart.plot.PlotOrientation.VERTICAL;
import graph.algorithms.main.ChartConverter;
import graph.algorithms.task.execution.Execution;

import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

public class ExecutionChartPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private ChartConverter chartConverter;
    
    public ExecutionChartPanel(ChartConverter chartConverter) {
        this.chartConverter = chartConverter;
        setLayout(new MigLayout("fill", "", ""));
        setBorder(createLineBorder(GRAY, 1, true));
    }

    public void plot(List<Execution<?>> executions) {
        CategoryDataset dataset = chartConverter.toDataSet(executions);
        removeAll();
        JFreeChart chart = createLineChart("", "Edges Size", "FAS Size", dataset, VERTICAL, true, false, false);
        add(new ChartPanel(chart), "grow");
    }
}
