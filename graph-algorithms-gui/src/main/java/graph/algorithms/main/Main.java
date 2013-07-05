package graph.algorithms.main;

import graph.algorithms.gui.ControlPanel;
import graph.algorithms.gui.ExecutionBatchControl;
import graph.algorithms.gui.ExecutionList;
import graph.algorithms.gui.ExecutionPanel;
import graph.algorithms.gui.MainFrame;
import graph.algorithms.gui.TaskList;
import graph.algorithms.gui.TaskPanel;
import graph.algorithms.task.EadesTask;
import graph.algorithms.task.GraphInput;
import graph.algorithms.task.KobylanskiTask;
import graph.algorithms.task.SaabTask;
import graph.algorithms.task.Task;
import graph.algorithms.task.execution.ExecutionBatchFactory;
import graph.algorithms.task.execution.ExecutionSignal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.Startable;
import org.picocontainer.behaviors.Caching;

public class Main implements Startable {

    private TaskList taskList;
    private static DefaultPicoContainer container;

    public Main(TaskList taskList) {
        this.taskList = taskList;
    }

    public static void main(String[] args) {
        setNimbusLookAndFeel();
        setupMiniframe();
        startContainer();
    }

    private static void setupMiniframe() {
        MiniFrame miniFrame = new MiniFrame();
        miniFrame.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                startContainer();
            }
        });
    }

    private static void dispose() {
        container.dispose();
    }

    private static void startContainer() {
        container = createContainer();
        container.start();
    }

    private static DefaultPicoContainer createContainer() {
        final DefaultPicoContainer container = new DefaultPicoContainer(new Caching());
        container.addComponent(Main.class);
        container.addComponent(MainFrame.class);
        container.addComponent(TaskPanel.class);
        container.addComponent(TaskList.class);
        container.addComponent(ExecutionPanel.class);
        container.addComponent(ExecutionList.class);
        container.addComponent(ControlPanel.class);
        container.addComponent(ExecutionBatchFactory.class);
        container.addComponent(ExecutionBatchControl.class);
        container.addComponent(ExecutionSignal.class);
        return container;
    }

    public static void setNimbusLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start() {
        List<Task<GraphInput>> listData = Arrays.asList(new EadesTask(),  new KobylanskiTask(), new SaabTask());        
        taskList.setListData(listData.toArray(new Task[0]));
    }

    @Override
    public void stop() {
    }
}
