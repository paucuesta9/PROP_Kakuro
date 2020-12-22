package presentation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import data.CtrlData;
import domain.classes.Exceptions.NoTypeCellException;
import domain.controllers.CtrlDomain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/** @file Records.java
 @brief Clase  <em>Records</em>.
 @class Records
 */

/**
 * @brief Clase Records que representa el reporitorio de kakuros y los records. Contiene las funciones y atributos necesarios para poder hacerlo.
 * @author Pol Vallespí Soro
 */

public class Records {
    private JPanel panel;
    private JPanel panel2;
    private JPanel menu;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton volver;
    private JButton config;
    private JLabel logo;
    private JLabel text;
    private JButton mostrar;
    private CtrlUI ctrlUI = CtrlUI.getInstance();

    private String[][] content;
    private int fila = -1;

    public Records() {
        $$$setupUI$$$();
        menu.setBackground(null);
        panel2.setBackground(null);

        table.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));
        logo.setIcon(Utils.getLogo());

        String[] titles = {"Dificultad", "tamaño", "Kakuro"};
        //ctrlUI.getListOfKakuros();


        JsonArray kak = ctrlUI.getKakuroRecords();
        content = new String[kak.size()][3];
        for (int i = 0; i < kak.size(); ++i) {
            JsonObject kakuro = kak.get(i).getAsJsonObject();
            content[i][0] = kakuro.get("diff").getAsString();
            content[i][1] = kakuro.get("size").getAsString();
            String s = kakuro.get("id").getAsString();
            content[i][2] = s.substring(0, s.indexOf("."));
        }

        table = new JTable(content, titles) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        //table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.setFont(new Font("Verdana", Font.BOLD, 20));
        scrollPane.setViewportView(table);
        scrollPane.setVisible(true);

        mostrar.setFont(Utils.roboto);
        mostrar.setForeground(Color.WHITE);
        mostrar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        mostrar.setBorderPainted(false);
        mostrar.setBackground(null);
        mostrar.setHorizontalTextPosition(JButton.CENTER);
        mostrar.setVerticalTextPosition(JButton.CENTER);

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode("#00204A"));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10, 0, 0, 10));

        volver.setFont(Utils.roboto);
        volver.setForeground(Color.WHITE);
        volver.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        volver.setBorderPainted(false);
        volver.setBackground(null);
        volver.setHorizontalTextPosition(JButton.CENTER);
        volver.setVerticalTextPosition(JButton.CENTER);

        text.setFont(Utils.roboto);
        text.setForeground(Color.BLACK);
        text.setBackground(null);

        listeners();
    }

    public JPanel getDefaultPanel() {
        return panel;
    }

    private void listeners() {

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                fila = table.getSelectedRow();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        mostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fila != -1) {
                    String dif = content[fila][0];
                    String size = content[fila][1];
                    String id = content[fila][2];
                    String path = "data/diff" + dif + "/" + size + "/" + id + ".txt";
                    ctrlUI.toShowKakuro(path, fila);

                }
            }
        });

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toMain();
            }
        });

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config conf = new Config();
                conf.drawConfig();
            }
        });
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
        panel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-1973532));
        menu = new JPanel();
        menu.setLayout(new GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
        menu.setBackground(new Color(-1973532));
        panel.add(menu, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 700), null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        menu.add(spacer1, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        volver = new JButton();
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setFocusPainted(false);
        volver.setFocusable(false);
        volver.setText("Volver");
        menu.add(volver, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, -1), new Dimension(252, -1), 0, false));
        final Spacer spacer2 = new Spacer();
        menu.add(spacer2, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 35), new Dimension(-1, 10), 0, false));
        final Spacer spacer3 = new Spacer();
        menu.add(spacer3, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        text = new JLabel();
        text.setForeground(new Color(-16761405));
        text.setText("Indique el kakuro a mostrar");
        menu.add(text, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        config = new JButton();
        config.setBackground(new Color(-852481));
        config.setBorderPainted(false);
        config.setContentAreaFilled(false);
        config.setFocusPainted(false);
        config.setFocusable(false);
        config.setText("\uF013");
        menu.add(config, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(40, -1), new Dimension(40, -1), new Dimension(40, -1), 0, false));
        logo = new JLabel();
        logo.setText("");
        menu.add(logo, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
        final Spacer spacer4 = new Spacer();
        menu.add(spacer4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 79), new Dimension(-1, 79), new Dimension(-1, 79), 0, false));
        final Spacer spacer5 = new Spacer();
        menu.add(spacer5, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(60, -1), new Dimension(60, -1), new Dimension(60, -1), 0, false));
        mostrar = new JButton();
        mostrar.setBorderPainted(false);
        mostrar.setContentAreaFilled(false);
        mostrar.setFocusable(false);
        mostrar.setText("Mostrar");
        menu.add(mostrar, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, -1), new Dimension(252, -1), 0, false));
        final Spacer spacer6 = new Spacer();
        menu.add(spacer6, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1973532));
        panel2.setEnabled(true);
        panel.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(800, -1), new Dimension(800, -1), new Dimension(800, -1), 0, false));
        scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(-328193));
        scrollPane.setForeground(new Color(-1973532));
        panel2.add(scrollPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(320, 128), null, 0, false));
        table = new JTable();
        table.setBackground(new Color(-1973532));
        table.setMaximumSize(new Dimension(800, 40));
        table.setMinimumSize(new Dimension(800, 40));
        table.setPreferredSize(new Dimension(800, 40));
        scrollPane.setViewportView(table);
        final Spacer spacer7 = new Spacer();
        panel2.add(spacer7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
        final Spacer spacer8 = new Spacer();
        panel.add(spacer8, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(20, -1), new Dimension(20, -1), new Dimension(20, -1), 0, false));
        final Spacer spacer9 = new Spacer();
        panel.add(spacer9, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(54, -1), new Dimension(54, -1), new Dimension(54, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
