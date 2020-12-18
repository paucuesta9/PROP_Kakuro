package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.NumberFormatter;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

/** @file NewGame.java
 @brief Clase  <em>NewGame</em>.
 */

/**
 * @author Judith Almoño Gómez
 * @brief Clase NewGame que carga la pantalla de NewGame y contiene las funciones y atributos necesarios para empezar una partida.
 */

public class NewGame {
    /**
     * panel1 es la ventana entera
     */
    private JPanel panel1;
    /**
     * picLabel es la imagen de un kakuro de la izquierda
     */
    private JLabel picLabel;
    /**
     * config es el boton de configuración situado en la esquina superior derecha
     */
    private JButton config;
    /**
     * easy es el boton Fácil que indica la dificultad del kakuro
     */
    private JButton easy;
    /**
     * easy es el boton Medio que indica la dificultad del kakuro
     */
    private JButton medium;
    /**
     * easy es el boton Difícil que indica la dificultad del kakuro
     */
    private JButton hard;
    /**
     * play es el boton de jugar que inicia la partida
     */
    private JButton play;
    /**
     * exit es el boton de volver situado en la zona inferior de la derecha
     */
    private JButton exit;

    /**
     * numColumn es el campo donde se introduce el número de columnas
     */
    private JTextField numColumn;
    /**
     * textSize es el "Indique el tamaño del tablero a jugar:"
     */
    private JLabel textSize;
    /**
     * rowSizeText es el "Número de filas:"
     */
    private JLabel rowSizeText;
    /**
     * columnSizeText es el "Número de columnas:"
     */
    private JLabel columnSizeText;
    /**
     * numColumn es el campo donde se introduce el número de filas
     */
    private JTextField numRow;
    /**
     * difficulty es el "Indique la dificultad del tablero:"
     */
    private JLabel difficulty;
    /**
     * logo es el ""
     */
    private JLabel logo;
    private JLabel labelO;
    private JButton ImportButton;
    private JLabel textPath;

    /**
     * diff indica la dificultad del kakuro
     */
    private int diff = 1;
    private boolean training;

    private CtrlUI ctrlUI = CtrlUI.getInstance();
    private CtrlPlayUI ctrlPlayUI = CtrlPlayUI.getInstance();
    private JFileChooser chooser = null;

    /**
     * @brief Constructora
     */
    public NewGame(boolean training) {

        $$$setupUI$$$();
        Utils.loadFonts();
        setListeners();

        this.training = training;

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10, 0, 0, 54));

        picLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setFont(Utils.roboto.deriveFont(40f));
        logo.setForeground(Color.decode(Utils.colorDarkBlue));

        logo.setIcon(Utils.getLogo());

        textSize.setFont(Utils.roboto);
        textSize.setForeground(Color.BLACK);

        Utils.setButtons(ImportButton);
        ImportButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        labelO.setFont(Utils.roboto);
        labelO.setForeground(Color.BLACK);

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
        medium.setForeground(Color.decode(Utils.colorDarkBlue));
        medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));

        Utils.setButtons(hard);
        hard.setForeground(Color.decode(Utils.colorDarkBlue));
        hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));

        Utils.setButtons(play);
        play.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(exit);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

    }

    /**
     * @brief Listeners
     * <p>
     * Funcionalidades de los botones config, easy, medium, hard, play exit y de los campos numRow y numColumn
     */
    private void setListeners() {

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config config = new Config();
                config.drawConfig();
            }
        });

        ImportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "TXT Files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(panel1);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                }
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
                easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));
                easy.setForeground(Color.WHITE);
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
                medium.setForeground(Color.decode(Utils.colorDarkBlue));
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                hard.setForeground(Color.decode(Utils.colorDarkBlue));
                diff = 1;
            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                easy.setForeground(Color.decode(Utils.colorDarkBlue));
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -4.png")));
                medium.setForeground(Color.WHITE);
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                hard.setForeground(Color.decode(Utils.colorDarkBlue));
                diff = 2;
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                easy.setForeground(Color.decode(Utils.colorDarkBlue));
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
                medium.setForeground(Color.decode(Utils.colorDarkBlue));
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 8.png")));
                hard.setForeground(Color.WHITE);
                diff = 3;
            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numRow.getText().isEmpty() || numColumn.getText().isEmpty())
                    Utils.showError("No se ha indicado alguno de los tamaños solicitados");
                else {
                    int rowSize = Integer.parseInt(numRow.getText());
                    int columnSize = Integer.parseInt(numColumn.getText());
                    if (rowSize >= 3 && columnSize >= 3) {
                        ctrlPlayUI.startGame(diff, rowSize, columnSize);
                        String kakuro = ctrlPlayUI.getKakuro();
                        ctrlUI.toPlay(kakuro, training);
                    } else Utils.showError("El tamaño debe ser minimo 3x3");
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toMain();
            }
        });
    }

    public JPanel getDefaultPanel() {
        return panel1;
    }

    /**
     * @brief Inserción Imagen Kakuro
     */
    private void createUIComponents() {
        picLabel = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
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
        panel1.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973022));
        panel1.setDoubleBuffered(true);
        panel1.setMaximumSize(new Dimension(1200, 800));
        panel1.setMinimumSize(new Dimension(1200, 800));
        panel1.setPreferredSize(new Dimension(1200, 800));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(14, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setAutoscrolls(false);
        panel2.setBackground(new Color(-12828863));
        panel2.setDoubleBuffered(false);
        panel2.setForeground(new Color(-4473925));
        panel2.setOpaque(false);
        panel1.add(panel2, new GridConstraints(4, 2, 1, 2, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-1973022));
        panel2.add(panel3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rowSizeText = new JLabel();
        rowSizeText.setText("Número de filas:");
        panel3.add(rowSizeText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numRow = new JTextField();
        numRow.setBackground(new Color(-1973022));
        numRow.setHorizontalAlignment(0);
        numRow.setOpaque(false);
        panel3.add(numRow, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-1973022));
        panel2.add(panel4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        columnSizeText = new JLabel();
        columnSizeText.setText("Número de columnas:");
        panel4.add(columnSizeText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(187, 16), null, 0, false));
        numColumn = new JTextField();
        numColumn.setBackground(new Color(-1973022));
        numColumn.setHorizontalAlignment(0);
        numColumn.setOpaque(false);
        panel4.add(numColumn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), new Dimension(-1, 15), new Dimension(-1, 15), 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        panel5.setBackground(new Color(-1973022));
        panel2.add(panel5, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
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
        panel5.add(easy, gbc);
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
        panel5.add(medium, gbc);
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
        panel5.add(hard, gbc);
        play = new JButton();
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.setText("Jugar");
        play.setVerticalAlignment(0);
        panel2.add(play, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exit = new JButton();
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.setText("Volver");
        panel2.add(exit, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        textSize = new JLabel();
        textSize.setText("Indique el tamaño del tablero a jugar:");
        panel2.add(textSize, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 25), new Dimension(-1, 25), new Dimension(-1, 25), 0, false));
        difficulty = new JLabel();
        difficulty.setText("Indique la dificultad del tablero:");
        panel2.add(difficulty, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        labelO = new JLabel();
        labelO.setText("o");
        panel2.add(labelO, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ImportButton = new JButton();
        ImportButton.setActionCommand("Importar Kakuro");
        ImportButton.setBorderPainted(false);
        ImportButton.setContentAreaFilled(false);
        ImportButton.setFocusable(false);
        ImportButton.setText("Añadir Kakuro");
        panel2.add(ImportButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel1.add(spacer6, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        picLabel.setEnabled(true);
        picLabel.setRequestFocusEnabled(true);
        picLabel.setText("");
        panel1.add(picLabel, new GridConstraints(0, 0, 5, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(800, 800), new Dimension(800, 800), new Dimension(800, 800), 0, false));
        final Spacer spacer7 = new Spacer();
        panel1.add(spacer7, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
        final Spacer spacer8 = new Spacer();
        panel1.add(spacer8, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        config = new JButton();
        config.setBorderPainted(false);
        config.setContentAreaFilled(false);
        config.setEnabled(true);
        config.setFocusPainted(false);
        Font configFont = this.$$$getFont$$$(null, -1, -1, config.getFont());
        if (configFont != null) config.setFont(configFont);
        config.setHorizontalAlignment(2);
        config.setHorizontalTextPosition(2);
        config.setMargin(new Insets(0, 0, 0, 0));
        config.setText("\uF013");
        panel1.add(config, new GridConstraints(0, 2, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        logo = new JLabel();
        logo.setText("");
        panel1.add(logo, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
        final Spacer spacer9 = new Spacer();
        panel1.add(spacer9, new GridConstraints(1, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 35), new Dimension(-1, 35), new Dimension(-1, 35), 0, false));
        final Spacer spacer10 = new Spacer();
        panel1.add(spacer10, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
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