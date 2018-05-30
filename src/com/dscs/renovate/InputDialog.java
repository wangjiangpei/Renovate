package com.dscs.renovate;

import javax.swing.*;
import java.awt.event.*;

public class InputDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private JEditorPane editorPane1;
    private JTextField textField1;
    private JTextField textField2;
    private Listener listener;

    public InputDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    public void start() {
        pack();
        setLocationRelativeTo(null);
        textField1.grabFocus();
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        String text1 = textField1.getText();
        String text2 = textField2.getText();
        String text3= editorPane1.getText();
        Info info = new Info(text1, text2, text3);

        dispose();
        if (listener != null) {
            listener.info(info);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        InputDialog dialog = new InputDialog();
        System.exit(0);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        public void info(Info info);
    }

}
