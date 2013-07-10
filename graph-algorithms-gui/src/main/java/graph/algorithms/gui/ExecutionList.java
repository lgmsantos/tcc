package graph.algorithms.gui;

import static java.awt.Color.GRAY;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
import static javax.swing.SwingUtilities.invokeLater;
import graph.algorithms.task.execution.Execution;
import graph.algorithms.task.execution.ExecutionListener;
import graph.algorithms.task.execution.ExecutionSignal;

import java.util.List;

import javax.swing.JList;

public class ExecutionList extends JList<Execution<?>> {

    private static final long serialVersionUID = 1L;

    public ExecutionList(ExecutionSignal signal) {
        signal.addExecutionListener(new ExecutionListener() {

            @Override
            public void executionStepped(Execution<?> execution) {
                invokeLater(new Runnable() {
                    public void run() {
                        repaint();                        
                    }
                });
            }

            @Override
            public void executionFinished(Execution<?> execution) {
            }
        });
        setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
        setBorder(createLineBorder(GRAY, 1, true));
    }

    public void setListData(List<Execution<?>> executions) {
        setListData(executions.toArray(new Execution[0]));
    }

}
