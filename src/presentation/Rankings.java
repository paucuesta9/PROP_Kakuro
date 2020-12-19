package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domain.classes.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** @file Rankings.java
 @brief Clase  <em>Rankings</em>.
 */

/**
 * @author Pol Vallespí Soro
 * @brief Clase Ranking que muestra un ranking según el criterio del usuario. Contiene las funciones y atributos necesarios para poder hacerlo.
 */

public class Rankings implements ListSelectionListener {

    private JPanel menu;
    private JButton volver;
    private JButton config;
    private JPanel panel;
    private JLabel text;
    private JScrollPane scrollPane;
    private JButton creadas;
    private JButton wins;
    private JButton puntos;
    private JPanel panel2;
    private JPanel ButtonPan;
    private JTable table;
    private JLabel logo;

    private CtrlUI ctrlUI = CtrlUI.getInstance();

    public Rankings() {
        menu.setBackground(null);
        panel2.setBackground(null);

        table.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setIcon(Utils.getLogo());

        String[] titles = {"Posición", "Nombre", "Puntos"}; //Nombre de cada columna
        String[][] t = ctrlUI.getListOfPlayers("puntos");
        showTable(t, titles);

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

        ButtonPan.setBackground(null);

        puntos.setFont(Utils.roboto);
        puntos.setForeground(Color.WHITE);
        puntos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));
        puntos.setBorderPainted(false);
        puntos.setBackground(null);
        puntos.setText("Puntos");
        puntos.setHorizontalTextPosition(JButton.CENTER);
        puntos.setVerticalTextPosition(JButton.CENTER);

        wins.setFont(Utils.roboto);
        wins.setForeground(Color.BLACK);
        wins.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Partidas_ganadas.png")));
        wins.setBorderPainted(false);
        wins.setBackground(null);
        wins.setHorizontalTextPosition(JButton.CENTER);
        wins.setVerticalTextPosition(JButton.CENTER);

        creadas.setFont(Utils.roboto);
        creadas.setForeground(Color.BLACK);
        creadas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
        creadas.setBorderPainted(false);
        creadas.setBackground(null);
        creadas.setText("Creados");
        creadas.setHorizontalTextPosition(JButton.CENTER);
        creadas.setVerticalTextPosition(JButton.CENTER);

        text.setFont(Utils.roboto);
        text.setForeground(Color.BLACK);
        text.setBackground(null);


        puntos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.setForeground(Color.WHITE);
                puntos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));
                wins.setForeground(Color.BLACK);
                wins.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Partidas_ganadas.png")));
                creadas.setForeground(Color.BLACK);
                creadas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                String[][] t = ctrlUI.getListOfPlayers("puntos");
                String[] titles = {"Posición", "Nombre", "Puntos"};
                showTable(t, titles);
            }
        });
        wins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.setForeground(Color.BLACK);
                puntos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                wins.setForeground(Color.WHITE);
                wins.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ganadas-azul.png")));
                creadas.setForeground(Color.BLACK);
                creadas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                String[] titles = {"Posición", "Nombre", "Acabados"};
                String[][] t = ctrlUI.getListOfPlayers("wins");
                showTable(t, titles);
            }
        });
        creadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.setForeground(Color.BLACK);
                puntos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                wins.setForeground(Color.BLACK);
                wins.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Partidas_ganadas.png")));
                creadas.setForeground(Color.WHITE);
                creadas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 8.png")));
                String[] titles = {"Posición", "Nombre", "Creados"};
                String[][] t = ctrlUI.getListOfPlayers("creadas");
                showTable(t, titles);
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

    void showTable(String[][] t, String[] titles) {

        table = new JTable(t, titles) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            ;
        };
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.setFont(new Font("Verdana", Font.BOLD, 20));
        scrollPane.setViewportView(table);
        scrollPane.setVisible(true);
    }

    public JPanel getDefaultPanel() {
        return panel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

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
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-1973532));
        menu = new JPanel();
        menu.setLayout(new GridLayoutManager(8, 4, new Insets(0, 0, 0, 0), -1, -1));
        menu.setBackground(new Color(-1973532));
        panel.add(menu, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 700), null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        menu.add(spacer1, new GridConstraints(5, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        volver = new JButton();
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setFocusPainted(false);
        volver.setFocusable(false);
        volver.setText("Volver");
        menu.add(volver, new GridConstraints(6, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, -1), new Dimension(252, -1), 0, false));
        final Spacer spacer2 = new Spacer();
        menu.add(spacer2, new GridConstraints(7, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 35), new Dimension(-1, 10), 0, false));
        final Spacer spacer3 = new Spacer();
        menu.add(spacer3, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        text = new JLabel();
        text.setForeground(new Color(-16761405));
        text.setText("Indique el ranking que desea ver:");
        menu.add(text, new GridConstraints(3, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonPan = new JPanel();
        ButtonPan.setLayout(new BorderLayout(0, 0));
        menu.add(ButtonPan, new GridConstraints(4, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        puntos = new JButton();
        puntos.setBorderPainted(false);
        puntos.setContentAreaFilled(false);
        puntos.setFocusPainted(false);
        puntos.setFocusable(false);
        puntos.setMaximumSize(new Dimension(120, 56));
        puntos.setMinimumSize(new Dimension(120, 56));
        puntos.setPreferredSize(new Dimension(120, 56));
        puntos.setText("Puntos");
        ButtonPan.add(puntos, BorderLayout.WEST);
        wins = new JButton();
        wins.setBorderPainted(false);
        wins.setContentAreaFilled(false);
        wins.setFocusPainted(false);
        wins.setFocusable(false);
        wins.setHorizontalTextPosition(11);
        wins.setMaximumSize(new Dimension(120, 56));
        wins.setMinimumSize(new Dimension(120, 56));
        wins.setPreferredSize(new Dimension(120, 56));
        wins.setText("");
        ButtonPan.add(wins, BorderLayout.CENTER);
        creadas = new JButton();
        creadas.setBorderPainted(false);
        creadas.setContentAreaFilled(false);
        creadas.setFocusPainted(false);
        creadas.setFocusable(false);
        creadas.setMaximumSize(new Dimension(120, 56));
        creadas.setMinimumSize(new Dimension(120, 56));
        creadas.setPreferredSize(new Dimension(120, 56));
        creadas.setText("");
        ButtonPan.add(creadas, BorderLayout.EAST);
        config = new JButton();
        config.setBackground(new Color(-852481));
        config.setBorderPainted(false);
        config.setContentAreaFilled(false);
        config.setFocusPainted(false);
        config.setFocusable(false);
        config.setText("\uF013");
        menu.add(config, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(40, -1), new Dimension(40, -1), new Dimension(40, -1), 0, false));
        logo = new JLabel();
        logo.setText("");
        menu.add(logo, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
        final Spacer spacer4 = new Spacer();
        menu.add(spacer4, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 79), new Dimension(-1, 79), new Dimension(-1, 79), 0, false));
        final Spacer spacer5 = new Spacer();
        menu.add(spacer5, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(40, -1), new Dimension(40, -1), new Dimension(40, -1), 0, false));
        final Spacer spacer6 = new Spacer();
        menu.add(spacer6, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(40, -1), new Dimension(40, -1), new Dimension(40, -1), 0, false));
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
        panel.add(spacer8, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(20, -1), new Dimension(20, -1), new Dimension(20, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
