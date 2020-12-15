package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Config extends JDialog {
    private JButton volver;
    private JPanel panel;
    private JPanel configuracion;
    private JLabel musicText;
    private JSlider volume;
    private JLabel colors;
    private JPanel colorPane;
    private JButton blackCell;
    private JButton whiteCell;
    private JButton selectedCell;
    private JPanel blackPanel;
    private JButton corrCell;
    private JButton incCell;
    private JButton border;
    private JButton blackNum;
    private JButton whiteNum;
    private JPanel whitePanel;
    private JPanel selectedPanel;
    private JPanel corrPanel;
    private JPanel incPanel;
    private JPanel borderPanel;
    private JPanel nBlPanel;
    private JPanel nWhPanel;
    private JButton mute;
    private JButton reset;

    private CtrlPlayerUI ctrlPlayerUI = CtrlPlayerUI.getInstance();

    private boolean muted;
    private int vol;

// logotipo.add(new JColorChooser());

    public Config() {
        $$$setupUI$$$();
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(volver);

        Utils.loadFonts();
        musicText.setFont(Utils.roboto);
        musicText.setForeground(Color.BLACK);
        musicText.setBackground(null);

        colors.setFont(Utils.roboto);
        colors.setForeground(Color.BLACK);
        colors.setBackground(null);

        Utils.setButtons(volver);
        volver.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        volume.setValue(Utils.volume);


        Utils.setButtons(blackCell);
        blackCell.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        blackPanel.setBackground(Utils.colorBlackCell);

        Utils.setButtons(whiteCell);
        whiteCell.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        whitePanel.setBackground(Utils.colorWhiteCell);

        Utils.setButtons(selectedCell);
        selectedCell.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        selectedPanel.setBackground(Utils.colorSelCell);

        Utils.setButtons(corrCell);
        corrCell.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        corrPanel.setBackground(Utils.colorCorrectCell);

        Utils.setButtons(incCell);
        incCell.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        incPanel.setBackground(Utils.colorIncorrectCell);

        Utils.setButtons(border);
        border.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        borderPanel.setBackground(Utils.colorBorde);

        Utils.setButtons(blackNum);
        blackNum.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        nBlPanel.setBackground(Utils.colorNumbersBlackCell);

        Utils.setButtons(whiteNum);
        whiteNum.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        nWhPanel.setBackground(Utils.colorNumbersWhiteCell);

        mute.setFont(Utils.fontAwesome);
        mute.setForeground(Color.decode("#00204A"));
        mute.setBackground(null);
        if (Utils.volume == -70) {
            muted = true;
            mute.setText("\uF6A9");
        } else {
            muted = false;
            mute.setText("\uF028");
        }

        Utils.setButtons(reset);
        reset.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));


        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPlayerUI.resetConfigColors();
                blackPanel.setBackground(Utils.colorBlackCell);
                whitePanel.setBackground(Utils.colorWhiteCell);
                selectedPanel.setBackground(Utils.colorSelCell);
                corrPanel.setBackground(Utils.colorCorrectCell);
                incPanel.setBackground(Utils.colorIncorrectCell);
                borderPanel.setBackground(Utils.colorBorde);
                nBlPanel.setBackground(Utils.colorNumbersBlackCell);
                nWhPanel.setBackground(Utils.colorNumbersWhiteCell);
            }
        });

        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                muted = !muted;
                if (muted) {
                    mute.setText("\uF6A9");
                    vol = volume.getValue();
                    volume.setValue(-70);
                } else {
                    mute.setText("\uF028");
                    volume.setValue(vol);
                }
            }
        });

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ctrlPlayerUI.setConfigToPlayer(Utils.getConfig());
            }
        });

        blackCell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorBlackCell);
                Color color = c.drawColorChooser();
                if (color != null) {
                    Utils.colorBlackCell = color;
                    blackPanel.setBackground(Utils.colorBlackCell);
                }
            }
        });

        whiteCell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorWhiteCell);
                Color color = c.drawColorChooser();
                if (color != null) {
                    Utils.colorWhiteCell = color;
                    whitePanel.setBackground(Utils.colorWhiteCell);
                }
            }
        });

        selectedCell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorSelCell);
                Color color = c.drawColorChooser();
                if (color != null) {
                    Utils.colorSelCell = color;
                    selectedPanel.setBackground(Utils.colorSelCell);
                }
            }
        });

        corrCell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorCorrectCell);
                Color color = c.drawColorChooser();
                if (color != null) {
                    Utils.colorCorrectCell = color;
                    corrPanel.setBackground(Utils.colorCorrectCell);
                }
            }
        });

        incCell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorIncorrectCell);
                Color color = c.drawColorChooser();
                if (color != null) {
                    Utils.colorIncorrectCell = color;
                    incPanel.setBackground(Utils.colorIncorrectCell);
                }
            }
        });

        border.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorBorde);
                Color color = c.drawColorChooser();
                if (color != null) {
                    Utils.colorBorde = color;
                    borderPanel.setBackground(Utils.colorBorde);
                }
            }
        });

        blackNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorNumbersBlackCell);
                Color color = c.drawColorChooser();
                if (color != null) {
                    Utils.colorNumbersBlackCell = color;
                    nBlPanel.setBackground(Utils.colorNumbersBlackCell);
                }
            }
        });

        whiteNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorNumbersWhiteCell);
                Color color = c.drawColorChooser();
                if (color != null) {
                    Utils.colorNumbersWhiteCell = color;
                    nWhPanel.setBackground(Utils.colorNumbersWhiteCell);
                }
            }
        });

        volume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int vol = volume.getValue();
                Utils.updateVolume(vol);
                if (vol == -70) {
                    muted = true;
                    mute.setText("\uF6A9");
                } else {
                    mute.setText("\uF028");
                }
            }
        });
    }

    public void drawConfig() {
        pack();
        setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width) / 2 - getSize().width / 2;
        int y = (screenSize.height) / 2 - getSize().height / 2;
        setLocation(x, y);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Configuración");
        frame.setContentPane(new Config().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 650);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        volume = new JSlider() {
            @Override
            public void updateUI() {
                setUI(new Slider(this));
            }
        };
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
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-1973532));
        configuracion = new JPanel();
        configuracion.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        configuracion.setBackground(new Color(-1973532));
        panel.add(configuracion, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(400, -1), new Dimension(400, -1), new Dimension(400, -1), 0, false));
        musicText = new JLabel();
        musicText.setText("Volumen de la música");
        configuracion.add(musicText, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        colors = new JLabel();
        colors.setText("Colores");
        configuracion.add(colors, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        colorPane = new JPanel();
        colorPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.setBackground(new Color(-1973532));
        configuracion.add(colorPane, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        colorPane.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        whiteCell = new JButton();
        whiteCell.setBorderPainted(false);
        whiteCell.setContentAreaFilled(false);
        whiteCell.setFocusPainted(false);
        whiteCell.setFocusable(false);
        whiteCell.setText("Celda blanca");
        colorPane.add(whiteCell, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        selectedCell = new JButton();
        selectedCell.setBorderPainted(false);
        selectedCell.setContentAreaFilled(false);
        selectedCell.setFocusPainted(false);
        selectedCell.setFocusable(false);
        selectedCell.setText("Celda seleccionada");
        colorPane.add(selectedCell, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        blackPanel = new JPanel();
        blackPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(blackPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        corrCell = new JButton();
        corrCell.setBorderPainted(false);
        corrCell.setContentAreaFilled(false);
        corrCell.setFocusPainted(false);
        corrCell.setFocusable(false);
        corrCell.setText("Celda correcta");
        colorPane.add(corrCell, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        incCell = new JButton();
        incCell.setBorderPainted(false);
        incCell.setContentAreaFilled(false);
        incCell.setFocusPainted(false);
        incCell.setFocusable(false);
        incCell.setText("Celda incorrecta");
        colorPane.add(incCell, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        blackNum = new JButton();
        blackNum.setBorderPainted(false);
        blackNum.setContentAreaFilled(false);
        blackNum.setFocusPainted(false);
        blackNum.setFocusable(false);
        blackNum.setText("Núm. celdas negras");
        colorPane.add(blackNum, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        whiteNum = new JButton();
        whiteNum.setBorderPainted(false);
        whiteNum.setContentAreaFilled(false);
        whiteNum.setFocusPainted(false);
        whiteNum.setFocusable(false);
        whiteNum.setText("Núm. celdas blancas");
        colorPane.add(whiteNum, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        whitePanel = new JPanel();
        whitePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(whitePanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        selectedPanel = new JPanel();
        selectedPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(selectedPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        corrPanel = new JPanel();
        corrPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(corrPanel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        incPanel = new JPanel();
        incPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(incPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        borderPanel = new JPanel();
        borderPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(borderPanel, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        nBlPanel = new JPanel();
        nBlPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(nBlPanel, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        nWhPanel = new JPanel();
        nWhPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(nWhPanel, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        blackCell = new JButton();
        blackCell.setBorderPainted(false);
        blackCell.setContentAreaFilled(false);
        blackCell.setFocusPainted(false);
        blackCell.setFocusable(false);
        blackCell.setText("Celda negra");
        colorPane.add(blackCell, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        border = new JButton();
        border.setBorderPainted(false);
        border.setContentAreaFilled(false);
        border.setFocusPainted(false);
        border.setFocusable(false);
        border.setText("Bordes y Lápiz");
        colorPane.add(border, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        reset = new JButton();
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        reset.setFocusPainted(false);
        reset.setFocusable(true);
        reset.setText("Resetear colores");
        colorPane.add(reset, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        configuracion.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        configuracion.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        configuracion.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        configuracion.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        volver = new JButton();
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setText("Volver");
        configuracion.add(volver, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973532));
        configuracion.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        volume.setBackground(new Color(-1973532));
        volume.setMaximum(6);
        volume.setMinimum(-70);
        volume.setValue(-10);
        volume.setValueIsAdjusting(false);
        panel1.add(volume, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mute = new JButton();
        mute.setAlignmentY(0.0f);
        mute.setBorderPainted(false);
        mute.setContentAreaFilled(false);
        mute.setFocusPainted(false);
        mute.setFocusable(false);
        mute.setForeground(new Color(-12145477));
        mute.setHorizontalTextPosition(0);
        mute.setText("");
        panel1.add(mute, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        configuracion.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
