package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/** @file Config.java
 @brief Clase  <em>Config</em>.
 @class Config
 */

/**
 * @author Pol Vallespí Soro
 * @brief Extend de JDialog, Config, que nos permite hacer un dialog para la pantalla de configuración. Contiene las funciones y atributos necesarios para poder hacerlo.
 */

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

    public Config() {
        $$$setupUI$$$();
        setContentPane(panel);
        setModal(true);
        getRootPane().setDefaultButton(volver);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                ctrlPlayerUI.setConfigToPlayer(Utils.getConfig());
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


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
                    int r = Utils.colorIncorrectCell.getRed();
                    int g = Utils.colorIncorrectCell.getGreen();
                    int b = Utils.colorIncorrectCell.getBlue();
                    int a = Utils.colorIncorrectCell.getAlpha();
                    Utils.colorIncorrectHelp1Cell = new Color(r, g, b, a);
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
                    muted = false;
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
        panel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-1973532));
        configuracion = new JPanel();
        configuracion.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        configuracion.setBackground(new Color(-1973532));
        panel.add(configuracion, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(400, -1), new Dimension(400, -1), new Dimension(400, -1), 0, false));
        musicText = new JLabel();
        musicText.setText("Volumen de la música");
        configuracion.add(musicText, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        colors = new JLabel();
        colors.setText("Colores");
        configuracion.add(colors, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        colorPane = new JPanel();
        colorPane.setLayout(new GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.setBackground(new Color(-1973532));
        configuracion.add(colorPane, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        colorPane.add(spacer1, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        whiteCell = new JButton();
        whiteCell.setBorderPainted(false);
        whiteCell.setContentAreaFilled(false);
        whiteCell.setFocusPainted(false);
        whiteCell.setFocusable(false);
        whiteCell.setText("Celda blanca");
        colorPane.add(whiteCell, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        selectedCell = new JButton();
        selectedCell.setBorderPainted(false);
        selectedCell.setContentAreaFilled(false);
        selectedCell.setFocusPainted(false);
        selectedCell.setFocusable(false);
        selectedCell.setText("Celda seleccionada");
        colorPane.add(selectedCell, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        blackPanel = new JPanel();
        blackPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(blackPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        corrCell = new JButton();
        corrCell.setBorderPainted(false);
        corrCell.setContentAreaFilled(false);
        corrCell.setFocusPainted(false);
        corrCell.setFocusable(false);
        corrCell.setText("Celda correcta");
        colorPane.add(corrCell, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        incCell = new JButton();
        incCell.setBorderPainted(false);
        incCell.setContentAreaFilled(false);
        incCell.setFocusPainted(false);
        incCell.setFocusable(false);
        incCell.setText("Celda incorrecta");
        colorPane.add(incCell, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        blackNum = new JButton();
        blackNum.setBorderPainted(false);
        blackNum.setContentAreaFilled(false);
        blackNum.setFocusPainted(false);
        blackNum.setFocusable(false);
        blackNum.setText("Núm. celdas negras");
        colorPane.add(blackNum, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        whiteNum = new JButton();
        whiteNum.setBorderPainted(false);
        whiteNum.setContentAreaFilled(false);
        whiteNum.setFocusPainted(false);
        whiteNum.setFocusable(false);
        whiteNum.setText("Núm. celdas blancas");
        colorPane.add(whiteNum, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        whitePanel = new JPanel();
        whitePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(whitePanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        selectedPanel = new JPanel();
        selectedPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(selectedPanel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        corrPanel = new JPanel();
        corrPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(corrPanel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        incPanel = new JPanel();
        incPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(incPanel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        borderPanel = new JPanel();
        borderPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(borderPanel, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        nBlPanel = new JPanel();
        nBlPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(nBlPanel, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        nWhPanel = new JPanel();
        nWhPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPane.add(nWhPanel, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), new Dimension(40, 40), new Dimension(40, 40), 0, false));
        blackCell = new JButton();
        blackCell.setBorderPainted(false);
        blackCell.setContentAreaFilled(false);
        blackCell.setFocusPainted(false);
        blackCell.setFocusable(false);
        blackCell.setText("Celda negra");
        colorPane.add(blackCell, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        border = new JButton();
        border.setBorderPainted(false);
        border.setContentAreaFilled(false);
        border.setFocusPainted(false);
        border.setFocusable(false);
        border.setText("Bordes y Lápiz");
        colorPane.add(border, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 40), new Dimension(252, 40), new Dimension(252, 40), 0, false));
        reset = new JButton();
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        reset.setFocusPainted(false);
        reset.setFocusable(true);
        reset.setText("Resetear colores");
        colorPane.add(reset, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        configuracion.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final Spacer spacer3 = new Spacer();
        configuracion.add(spacer3, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final Spacer spacer4 = new Spacer();
        configuracion.add(spacer4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final Spacer spacer5 = new Spacer();
        configuracion.add(spacer5, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        volver = new JButton();
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setText("Volver");
        configuracion.add(volver, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973532));
        configuracion.add(panel1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        volume.setBackground(new Color(-1973532));
        volume.setMaximum(6);
        volume.setMinimum(-70);
        volume.setValue(-10);
        volume.setValueIsAdjusting(false);
        panel1.add(volume, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mute = new JButton();
        mute.setAlignmentY(0.0f);
        mute.setBorderPainted(false);
        mute.setContentAreaFilled(false);
        mute.setFocusPainted(false);
        mute.setFocusable(false);
        mute.setForeground(new Color(-12145477));
        mute.setHorizontalTextPosition(0);
        mute.setText("");
        panel1.add(mute, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        configuracion.add(spacer6, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final Spacer spacer7 = new Spacer();
        panel.add(spacer7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
