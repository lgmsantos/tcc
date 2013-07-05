package graph.algorithms.gui;

import static java.awt.Color.GRAY;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
import graph.algorithms.task.GraphInput;
import graph.algorithms.task.Task;

import javax.swing.JList;

public class TaskList extends JList<Task<GraphInput>>{

    private static final long serialVersionUID = 1L;

    public TaskList() {
        setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
        setBorder(createLineBorder(GRAY, 1, true));
    }
    
}
