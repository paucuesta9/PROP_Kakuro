package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

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
        } else if (type == 2) {
            textValue.setVisible(false);
            etValue.setVisible(false);
            textRow.setVisible(true);
            etRow.setVisible(true);
            textColumn.setVisible(true);
            etColumn.setVisible(true);
        } else if (type == 3) {
            textValue.setVisible(false);
            etValue.setVisible(false);
            textRow.setVisible(true);
            etRow.setVisible(true);
            textColumn.setVisible(false);
            etColumn.setVisible(false);
        } else {
            textValue.setVisible(false);
            etValue.setVisible(false);
            textRow.setVisible(false);
            etRow.setVisible(false);
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

        etValue.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() > '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    etValue.setEditable(true);
                } else {
                    etValue.setEditable(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        etRow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    etRow.setEditable(true);
                } else {
                    etRow.setEditable(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        etColumn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    etColumn.setEditable(true);
                } else {
                    etColumn.setEditable(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

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
            if (etValue.getText().isEmpty() || etValue.getText().length() > 1)
                Utils.showError("El valor debe estar entre 1 y 9");
            else {
                dispose();
                result = new int[1];
                result[0] = Integer.valueOf(etValue.getText());
            }
        } else if (type == 2) {
            if (etRow.getText().isEmpty() || Integer.parseInt(etRow.getText()) > 45)
                Utils.showError("El valor debe estar entre 0 y 45");
            else if (etColumn.getText().isEmpty() || Integer.parseInt(etColumn.getText()) > 45)
                Utils.showError("El valor debe estar entre 0 y 45");
            else {
                dispose();
                result = new int[2];
                result[0] = Integer.valueOf(etRow.getText());
                result[1] = Integer.valueOf(etColumn.getText());
            }
        } else if (type == 3) {
            if (etRow.getText().isEmpty() || Integer.parseInt(etRow.getText()) > 45)
                Utils.showError("El valor debe estar entre 0 y 45");
            else {
                dispose();
                result = new int[2];
                result[0] = Integer.valueOf(etRow.getText());
            }
        } else if (type == 4) {
            if (etColumn.getText().isEmpty() || Integer.parseInt(etColumn.getText()) > 45)
                Utils.showError("El valor debe estar entre 0 y 45");
            else {
                dispose();
                result = new int[2];
                result[0] = Integer.valueOf(etColumn.getText());
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        result = new int[1];
        result[0] = -1;
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 2, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setBorderPainted(false);
        buttonOK.setContentAreaFilled(false);
        buttonOK.setFocusPainted(false);
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 40), new Dimension(100, 40), new Dimension(100, 40), 0, false));
        buttonCancel = new JButton();
        buttonCancel.setBorderPainted(false);
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setFocusPainted(false);
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 40), new Dimension(100, 40), new Dimension(100, 40), 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        text1 = new JLabel();
        text1.setText("Indique los valores:");
        panel3.add(text1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel3.add(spacer2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), null, null, 0, false));
        textValue = new JLabel();
        textValue.setText("Valor de la celda:");
        panel3.add(textValue, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        etValue = new JTextField();
        etValue.setHorizontalAlignment(0);
        etValue.setOpaque(false);
        panel3.add(etValue, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textRow = new JLabel();
        textRow.setText("Valor fila");
        panel3.add(textRow, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        etRow = new JTextField();
        etRow.setHorizontalAlignment(0);
        etRow.setOpaque(false);
        panel3.add(etRow, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textColumn = new JLabel();
        textColumn.setText("Valor columna");
        panel3.add(textColumn, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        etColumn = new JTextField();
        etColumn.setHorizontalAlignment(0);
        etColumn.setOpaque(false);
        panel3.add(etColumn, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel3.add(spacer3, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(20, -1), null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel3.add(spacer4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 5), null, new Dimension(-1, 5), 0, false));
        final Spacer spacer5 = new Spacer();
        panel3.add(spacer5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        contentPane.add(spacer6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(20, -1), null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
