package graph.algorithms.main;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultKeyedValues2DDataset;

public class Bah {
    
    public static void main(String[] args) throws InterruptedException {
        Main.setNimbusLookAndFeel();
        DefaultKeyedValues2DDataset data = new DefaultKeyedValues2DDataset();
        data.addValue(10, new Integer(1), new Integer(1));
        data.addValue(2, new Integer(1), new Integer(2));
        data.addValue(3, new Integer(1), new Integer(3));
        data.addValue(4, new Integer(1), new Integer(4));
        
        data.addValue(3, new Integer(2), new Integer(1));
        data.addValue(4, new Integer(2), new Integer(2));
        data.addValue(5, new Integer(2), new Integer(3));
        data.addValue(10, new Integer(2), new Integer(4));
        
        JFreeChart line = ChartFactory.createLineChart("bah", "X", "Y", data, PlotOrientation.VERTICAL, true, false, false);
        ChartFrame frame = new ChartFrame("bag", line);
        frame.pack();
        frame.setVisible(true);
    }
}
