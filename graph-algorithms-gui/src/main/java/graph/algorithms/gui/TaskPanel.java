package graph.algorithms.gui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class TaskPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public TaskPanel(TaskList taskList) {
        setLayout(new MigLayout("fill", "", "0[]0"));
        add(taskList, "grow");
    }
    
    

}
