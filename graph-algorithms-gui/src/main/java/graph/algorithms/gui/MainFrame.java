package graph.algorithms.gui;

import static javax.swing.SwingUtilities.invokeLater;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import org.picocontainer.Startable;

public class MainFrame extends JFrame implements Startable {

    private static final long serialVersionUID = 1L;

    public MainFrame(TaskPanel taskPanel, ExecutionPanel executionPanel, ControlPanel controlPanel) {
        setLayout(new MigLayout("fill", "", "10[100!]10[60!]10[]10"));
        setTitle("Algorithm Execution Status");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(taskPanel, "growy, h 100!, w 300!, wrap, span 2 1");
        add(controlPanel, "grow, w 300!, wrap");
        add(executionPanel, "growy, w 300!, h 100:400:, span 2 1");
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
