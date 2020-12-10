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

    private CtrlUI ctrlUI = CtrlUI.getInstance();

    private boolean muted;
    private int vol;

// logotipo.add(new JColorChooser());

    public Config() {
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
                ctrlUI.resetConfigColors();
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
                if(muted) {
                    mute.setText("\uF6A9");
                    vol = volume.getValue();
                    volume.setValue(-70);
                }
                else {
                    mute.setText("\uF028");
                    volume.setValue(vol);
                }
            }
        });

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ctrlUI.setConfigToPlayer(Utils.getConfig());
            }
        });

        blackCell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser c = new ColorChooser(Utils.colorBlackCell);
                Color color = c.drawColorChooser();
                if(color != null) {
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
                if(color != null) {
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
                if(color != null) {
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
                if(color != null) {
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
                if(color != null) {
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
                if(color != null) {
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
                if(color != null) {
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
                if(color != null) {
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

    public static void main(String [] args) {
        JFrame frame = new JFrame("Configuración");
        frame.setContentPane(new Config().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,650);
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
}
