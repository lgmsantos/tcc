package graph.algorithms.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class ExecutionPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;

    public ExecutionPanel(ExecutionList executionList) {
        setLayout(new MigLayout("fill", "", ""));
        JScrollPane scrollPane = new JScrollPane(executionList);
        add(scrollPane, "grow");
    }
}
