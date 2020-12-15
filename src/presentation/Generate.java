package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

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

        Utils.loadFonts();
        ctrlUI = CtrlUI.getInstance();

        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setIcon(Utils.getLogo());

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,54));

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
}