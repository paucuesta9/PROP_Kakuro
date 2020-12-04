package presentation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Config {
    private JButton volver;
    private JPanel panel;
    private JPanel configuracion;
    private JPanel menu;
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
    private JFrame frame = new JFrame("Configuración");

    private CtrlUI ctrlUI = CtrlUI.getInstance();


// logotipo.add(new JColorChooser());

    public Config() {
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

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
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
            }
        });
    }

    public void drawConfig() {
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Configuración");
        frame.setContentPane(new Config().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}
