package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetValues extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField etValue;
    private JTextField etRow;
    private JTextField etColumn;
    private JLabel textValue;
    private JLabel textRow;
    private JLabel textColumn;
    private JLabel text1;

    private int type;
    private int[] result;

    public SetValues(int type) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.type = type;
        result = null;

        Utils.setButtons(buttonOK);
        Utils.setButtons(buttonCancel);
        buttonOK.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/bottonOKError.png")));
        buttonCancel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/buttonCancel.png")));

        if (type == 1) {
            textValue.setVisible(true);
            etValue.setVisible(true);
            textRow.setVisible(false);
            etRow.setVisible(false);
            textColumn.setVisible(false);
            etColumn.setVisible(false);
        } else {
            textValue.setVisible(false);
            etValue.setVisible(false);
            textRow.setVisible(true);
            etRow.setVisible(true);
            textColumn.setVisible(true);
            etColumn.setVisible(true);
        }
        Utils.loadFonts();
        text1.setFont(Utils.roboto);
        textValue.setFont(Utils.roboto.deriveFont(16f));
        textRow.setFont(Utils.roboto.deriveFont(16f));
        textColumn.setFont(Utils.roboto.deriveFont(16f));
        etValue.setFont(Utils.roboto.deriveFont(16f));
        etRow.setFont(Utils.roboto.deriveFont(16f));
        etColumn.setFont(Utils.roboto.deriveFont(16f));
        etValue.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        etRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        etColumn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

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

    private void onOK() {
        if (type == 1) {
            result = new int[1];
            result[0] = Integer.valueOf(etValue.getText());
        } else {
            result = new int[2];
            result[0] = Integer.valueOf(etRow.getText());
            result[1] = Integer.valueOf(etColumn.getText());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public int[] drawSetValues() {
        pack();
        setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width) / 2 - getSize().width / 2;
        int y = (screenSize.height) / 2 - getSize().height / 2;
        setLocation(x, y);
        setVisible(true);
        return result;
    }

    public static void main(String[] args) {
        SetValues dialog = new SetValues(1);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
}
