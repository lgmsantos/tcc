package graph.algorithms.gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ControlPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    
    private JButton btnCreate;
    private JButton btnRun;
    private JButton btnStop;

    private ExecutionBatchControl batchControl;

    public ControlPanel(final ExecutionBatchControl batchControl) {
        this.batchControl = batchControl;
        setLayout(new MigLayout("fill", "", "0[]0[]0"));        
        initializeButtons();
        add(btnCreate, "grow");
        add(btnRun, "grow");
        add(btnStop, "grow, wrap");
        JButton left = new JButton("<<");
        final JLabel label = new JLabel(batchControl.label());
        left.addActionListener(new InvokeLaterActionListener() {
            
            @Override
            void invokeLaterActionPerformed(ActionEvent e) {
                batchControl.left();
                label.setText(batchControl.label());
                label.repaint();
            }
        });
        JButton right = new JButton(">>");
        right.addActionListener(new InvokeLaterActionListener() {
            
            @Override
            void invokeLaterActionPerformed(ActionEvent e) {
                batchControl.right();
                label.setText(batchControl.label());
            }
        });
        
        add(left, "grow");
        add(label, "center");
        add(right, "grow");
    }
        

    private void initializeButtons() {
        btnCreate = new JButton("create");
        btnCreate.addActionListener(new InvokeLaterActionListener() {
            
            @Override
            void invokeLaterActionPerformed(ActionEvent e) {
                batchControl.createBatch();
            }
        });
        btnRun = new JButton("run");
        btnRun.addActionListener(new InvokeLaterActionListener() {
            
            @Override
            void invokeLaterActionPerformed(ActionEvent e) {
                batchControl.runBatch();
            }
        });
        btnStop = new JButton("stop");
        btnStop.addActionListener(new InvokeLaterActionListener() {
            
            @Override
            void invokeLaterActionPerformed(ActionEvent e) {
                batchControl.stopBatch();
            }
        });
    }
}
