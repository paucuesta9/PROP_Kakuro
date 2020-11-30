package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Error extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel text;

    public Error(String textError) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        text.setText(textError);

        //buttonOK.setFont(roboto);
        buttonOK.setForeground(Color.WHITE);
        buttonOK.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/bottonOKError.png")));
        buttonOK.setBorderPainted(false);
        buttonOK.setBackground(null);
        buttonOK.setHorizontalTextPosition(JButton.CENTER);
        buttonOK.setVerticalTextPosition(JButton.CENTER);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        String textError = "Pau es supercalifragilisticoespialidoso";
        Error dialog = new Error(textError);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setVisible(true);
        System.exit(0);
    }


}
