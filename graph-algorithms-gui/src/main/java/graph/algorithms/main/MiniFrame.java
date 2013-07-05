package graph.algorithms.main;

import static java.awt.BorderLayout.CENTER;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class MiniFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JButton button;

    public MiniFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        button = new JButton("Rebuild");
        add(button, CENTER);
        invokeLater(new Runnable() {

            @Override
            public void run() {
                pack();
                setVisible(true);
            }
        });

        setupGlobalHotkey();
    }

    private void setupGlobalHotkey() {
        JIntellitype.getInstance();
        if (JIntellitype.checkInstanceAlreadyRunning("MyApp")) {
            JOptionPane.showMessageDialog(null, "Rodando!", "Oeeeee", ERROR_MESSAGE);
            System.exit(1);
        }
        JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_WIN, (int) 'A');
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {

            @Override
            public void onHotKey(int identifier) {
                if (identifier == 1)
                    button.doClick();
            }
        });
    }
    
    public void addActionListener(ActionListener actionListener){
        button.addActionListener(actionListener);
    }

}
