package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Locale;

/** @file Generate.java
 @class Generate
 */

/** @brief Pantalla para generar kakuros
 * @author ------------
 */

public class Generate {
    private JPanel panel1;
    private JLabel label;
    private JButton config;
    private JButton easy;
    private JButton medium;
    private JButton hard;
    private JButton generateButton;
    private JButton exit;
    private JPanel rowSize;
    private JPanel columnSize;
    private JTextField numColumn;
    private JLabel textSize;
    private JLabel rowSizeText;
    private JLabel columnSizeText;
    private JTextField numRow;
    private JLabel difficulty;
    private JLabel logo;
    private JPanel panelGenerate;
    private JPanel buttonDiff;

    private CtrlUI ctrlUI;
    private JFrame frame;

    private boolean g = false;

    int diff = 1;

    public Generate() {
        $$$setupUI$$$();
        ctrlUI = CtrlUI.getInstance();

        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setIcon(Utils.getLogo());

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10, 0, 0, 54));

        textSize.setFont(Utils.roboto);
        textSize.setForeground(Color.BLACK);

        rowSizeText.setForeground(Color.BLACK);
        rowSizeText.setFont(Utils.roboto.deriveFont(18f));

        numRow.setForeground(Color.BLACK);
        numRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        numRow.setFont(Utils.roboto.deriveFont(18f));

        columnSizeText.setForeground(Color.BLACK);
        columnSizeText.setFont(Utils.roboto.deriveFont(18f));

        numColumn.setForeground(Color.BLACK);
        numColumn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        numColumn.setFont(Utils.roboto.deriveFont(18f));

        difficulty.setFont(Utils.roboto);
        difficulty.setForeground(Color.BLACK);

        Utils.setButtons(easy);
        easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));

        Utils.setButtons(medium);
        medium.setForeground(Color.BLACK);
        medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));

        Utils.setButtons(hard);
        hard.setForeground(Color.BLACK);
        hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));

        Utils.setButtons(generateButton);
        generateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(exit);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config config = new Config();
                config.drawConfig();
            }
        });

        numRow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                numRow.setEditable(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        numColumn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                numColumn.setEditable(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!g) {
                    easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));
                    medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
                    hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                    easy.setForeground(Color.WHITE);
                    medium.setForeground(Color.BLACK);
                    hard.setForeground(Color.BLACK);
                    diff = 1;
                }
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!g) {
                    easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                    medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -4.png")));
                    hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                    easy.setForeground(Color.BLACK);
                    medium.setForeground(Color.WHITE);
                    hard.setForeground(Color.BLACK);
                    diff = 2;
                }
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!g) {
                    easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                    medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
                    hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 8.png")));
                    easy.setForeground(Color.BLACK);
                    medium.setForeground(Color.BLACK);
                    hard.setForeground(Color.WHITE);
                    diff = 3;
                }
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!g) {
                    if (!numRow.getText().equals("") && !numColumn.getText().equals("") && Integer.parseInt(numRow.getText()) >= 3 && Integer.parseInt(numColumn.getText()) >= 3) {
                        g = true;
                        exit.setVisible(false);
                        URL url = Generate.class.getClassLoader().getResource("images/load.gif");
                        ImageIcon imageIcon = new ImageIcon(url);
                        generateButton.setText("");
                        generateButton.setIcon(imageIcon);
                        exit.setEnabled(false);
                        Thread t = new Thread() {
                            public void run() {
                                ctrlUI.generate(Integer.parseInt(numRow.getText()), Integer.parseInt(numColumn.getText()), diff);
                                ctrlUI.resolve();
                                ctrlUI.toAskSave("¿Desea guardar el kakuro generado?", ctrlUI.getKakuro(), 1);
                            }
                        };
                        t.start();
                    } else Utils.showError("Tamaño inválido");
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!g) {
                    ctrlUI.toMain();
                }
            }
        });
    }

    public JPanel getDefaultPanel() {
        return panel1;
    }

    private void createUIComponents() {
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
        label.requestFocusInWindow();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973022));
        panel1.setDoubleBuffered(true);
        panel1.setMaximumSize(new Dimension(1200, 800));
        panel1.setMinimumSize(new Dimension(1200, 800));
        panel1.setPreferredSize(new Dimension(1200, 800));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(13, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setAutoscrolls(false);
        panel2.setBackground(new Color(-12828863));
        panel2.setDoubleBuffered(false);
        panel2.setForeground(new Color(-4473925));
        panel2.setOpaque(false);
        panel1.add(panel2, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rowSize = new JPanel();
        rowSize.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        rowSize.setBackground(new Color(-1973022));
        panel2.add(rowSize, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rowSizeText = new JLabel();
        rowSizeText.setText("Número de filas:");
        rowSize.add(rowSizeText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numRow = new JTextField();
        numRow.setHorizontalAlignment(0);
        numRow.setOpaque(false);
        rowSize.add(numRow, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        columnSize = new JPanel();
        columnSize.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        columnSize.setBackground(new Color(-1973022));
        panel2.add(columnSize, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        columnSizeText = new JLabel();
        columnSizeText.setText("Número de columnas:");
        columnSize.add(columnSizeText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(187, 16), null, 0, false));
        numColumn = new JTextField();
        numColumn.setHorizontalAlignment(0);
        numColumn.setOpaque(false);
        columnSize.add(numColumn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-1973022));
        panel2.add(panel3, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        difficulty = new JLabel();
        difficulty.setText("Indique la dificultad del tablero:");
        panel3.add(difficulty, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), new Dimension(-1, 15), new Dimension(-1, 15), 0, false));
        buttonDiff = new JPanel();
        buttonDiff.setLayout(new GridBagLayout());
        buttonDiff.setBackground(new Color(-1973022));
        panel2.add(buttonDiff, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        easy = new JButton();
        easy.setBorderPainted(false);
        easy.setContentAreaFilled(false);
        easy.setFocusPainted(false);
        easy.setMaximumSize(new Dimension(120, 56));
        easy.setMinimumSize(new Dimension(120, 56));
        easy.setPreferredSize(new Dimension(120, 56));
        easy.setText("Fácil");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        buttonDiff.add(easy, gbc);
        medium = new JButton();
        medium.setBorderPainted(false);
        medium.setContentAreaFilled(false);
        medium.setFocusPainted(false);
        medium.setMaximumSize(new Dimension(120, 56));
        medium.setMinimumSize(new Dimension(120, 56));
        medium.setPreferredSize(new Dimension(120, 56));
        medium.setText("Medio");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        buttonDiff.add(medium, gbc);
        hard = new JButton();
        hard.setBorderPainted(false);
        hard.setContentAreaFilled(false);
        hard.setFocusPainted(false);
        hard.setMaximumSize(new Dimension(120, 56));
        hard.setMinimumSize(new Dimension(120, 56));
        hard.setPreferredSize(new Dimension(120, 3056));
        hard.setText("Dificil");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        buttonDiff.add(hard, gbc);
        textSize = new JLabel();
        textSize.setText("Indique el tamaño del tablero a generar:");
        panel2.add(textSize, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 25), new Dimension(-1, 25), new Dimension(-1, 25), 0, false));
        exit = new JButton();
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.setText("Volver");
        panel2.add(exit, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), new Dimension(-1, 30), 0, false));
        final Spacer spacer6 = new Spacer();
        panel2.add(spacer6, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        generateButton = new JButton();
        generateButton.setBorderPainted(false);
        generateButton.setContentAreaFilled(false);
        generateButton.setEnabled(true);
        generateButton.setFocusPainted(false);
        generateButton.setText("Generar");
        generateButton.setVerticalAlignment(0);
        panel2.add(generateButton, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel1.add(spacer7, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(10, -1), null, 0, false));
        final Spacer spacer8 = new Spacer();
        panel1.add(spacer8, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        config = new JButton();
        config.setBorderPainted(false);
        config.setContentAreaFilled(false);
        config.setEnabled(true);
        config.setFocusPainted(false);
        Font configFont = this.$$$getFont$$$(null, -1, -1, config.getFont());
        if (configFont != null) config.setFont(configFont);
        config.setMargin(new Insets(0, 0, 0, 50));
        config.setText("\uF013");
        panel1.add(config, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        final Spacer spacer9 = new Spacer();
        panel1.add(spacer9, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        label.setRequestFocusEnabled(true);
        label.setText("");
        panel1.add(label, new GridConstraints(0, 0, 4, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(800, 800), new Dimension(800, 800), new Dimension(800, 800), 0, false));
        logo = new JLabel();
        logo.setText("");
        panel1.add(logo, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}