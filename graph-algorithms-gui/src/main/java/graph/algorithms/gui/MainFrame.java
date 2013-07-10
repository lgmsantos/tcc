package graph.algorithms.gui;

import static javax.swing.SwingUtilities.invokeLater;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import org.picocontainer.Startable;

public class MainFrame extends JFrame implements Startable {

    private static final long serialVersionUID = 1L;

    public MainFrame(TaskPanel taskPanel, ExecutionPanel executionPanel, ControlPanel controlPanel, ExecutionChartPanel charPanel) {
        setLayout(new MigLayout("fill", "[300!][]", "10[100!]10[60!]10[]10"));
        setTitle("Algorithm Execution Status");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(taskPanel, "grow");
        add(charPanel, "grow, center, w 400:800:, span 1 3, wrap");
        add(controlPanel, "grow, wrap");
        add(executionPanel, "grow, h 100:400:");
    }

    public void start() {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                pack();
                setVisible(true);
            }
        });
    }

    public void stop() {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                dispose();
            }
        });
    }

}
