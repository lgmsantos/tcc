package graph.algorithms.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public abstract class InvokeLaterActionListener implements ActionListener{

    @Override
    public void actionPerformed(final ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                invokeLaterActionPerformed(e);
            }

        });
    }

    abstract void invokeLaterActionPerformed(ActionEvent e);
}
