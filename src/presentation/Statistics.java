package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** @file Statistics.java
 @brief Clase  <em>Statistics</em>.
 @class Statistics
 */

/** @brief Pantalla Statistics que muestra un Dialog con las estadísticas del jugador
 * @author Alvaro Armada Ruiz
 */

public class Statistics extends JDialog {
    private JPanel panel;
    private JPanel stats;
    private JLabel acabadosT;
    private JLabel empezadosT;
    private JLabel ayudasT;
    private JLabel puntosT;
    private JLabel creadosT;
    private JLabel acabadosI;
    private JLabel empezadosI;
    private JLabel ayudasI;
    private JLabel puntosI;
    private JLabel creadosI;
    private JPanel Panel2;
    private JButton volver;

    private CtrlUI ctrlUI;

    public Statistics() {
        $$$setupUI$$$();
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(volver);

        Utils.loadFonts();

        ctrlUI = CtrlUI.getInstance();
        ArrayList<Integer> a = ctrlUI.getStatsList();

        acabadosT.setFont(Utils.roboto.deriveFont(18f));
        acabadosT.setForeground(Color.BLACK);

        empezadosT.setFont(Utils.roboto.deriveFont(18f));
        empezadosT.setForeground(Color.BLACK);

        ayudasT.setFont(Utils.roboto.deriveFont(18f));
        ayudasT.setForeground(Color.BLACK);

        puntosT.setFont(Utils.roboto.deriveFont(18f));
        puntosT.setForeground(Color.BLACK);

        creadosT.setFont(Utils.roboto.deriveFont(18f));
        creadosT.setForeground(Color.BLACK);

        acabadosI.setFont(Utils.roboto.deriveFont(18f));
        acabadosI.setForeground(Color.BLACK);
        acabadosI.setText(String.valueOf(a.get(0)));

        empezadosI.setFont(Utils.roboto.deriveFont(18f));
        empezadosI.setForeground(Color.BLACK);
        empezadosI.setText(String.valueOf(a.get(1)));

        ayudasI.setFont(Utils.roboto.deriveFont(18f));
        ayudasI.setForeground(Color.BLACK);
        ayudasI.setText(String.valueOf(a.get(2)));

        puntosI.setFont(Utils.roboto.deriveFont(18f));
        puntosI.setForeground(Color.BLACK);
        puntosI.setText(String.valueOf(a.get(3)));

        creadosI.setFont(Utils.roboto.deriveFont(18f));
        creadosI.setForeground(Color.BLACK);
        creadosI.setText(String.valueOf(a.get(4)));

        Utils.setButtons(volver);
        volver.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    public void drawStatistics() {
        pack();
        setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width) / 2 - getSize().width / 2;
        int y = (screenSize.height) / 2 - getSize().height / 2;
        setLocation(x, y);
        setVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-1973022));
        stats = new JPanel();
        stats.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        stats.setBackground(new Color(-1973532));
        panel.add(stats, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(400, -1), new Dimension(400, -1), new Dimension(400, -1), 0, false));
        Panel2 = new JPanel();
        Panel2.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        Panel2.setBackground(new Color(-1973022));
        stats.add(Panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        acabadosT = new JLabel();
        acabadosT.setText("Acabados:");
        Panel2.add(acabadosT, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        empezadosT = new JLabel();
        empezadosT.setText("Empezados:");
        Panel2.add(empezadosT, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ayudasT = new JLabel();
        ayudasT.setText("Ayudas utilizadas:");
        Panel2.add(ayudasT, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        puntosT = new JLabel();
        puntosT.setText("Puntos:");
        Panel2.add(puntosT, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        creadosT = new JLabel();
        creadosT.setText("Creados:");
        Panel2.add(creadosT, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        acabadosI = new JLabel();
        acabadosI.setText("Label");
        Panel2.add(acabadosI, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        empezadosI = new JLabel();
        empezadosI.setText("Label");
        Panel2.add(empezadosI, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ayudasI = new JLabel();
        ayudasI.setText("Label");
        Panel2.add(ayudasI, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        puntosI = new JLabel();
        puntosI.setText("Label");
        Panel2.add(puntosI, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        creadosI = new JLabel();
        creadosI.setText("Label");
        Panel2.add(creadosI, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        Panel2.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(60, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        Panel2.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(60, -1), null, 0, false));
        final Spacer spacer3 = new Spacer();
        Panel2.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(60, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        Panel2.add(spacer4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(60, -1), null, 0, false));
        final Spacer spacer5 = new Spacer();
        Panel2.add(spacer5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(60, -1), null, 0, false));
        final Spacer spacer6 = new Spacer();
        stats.add(spacer6, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        volver = new JButton();
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setFocusable(false);
        volver.setText("Volver");
        stats.add(volver, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        stats.add(spacer7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        final Spacer spacer8 = new Spacer();
        stats.add(spacer8, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
