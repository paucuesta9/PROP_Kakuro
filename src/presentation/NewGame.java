package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;

public class NewGame {
    private JPanel panel1;
    private JLabel picLabel;
    private JButton config;
    private JButton easy;
    private JButton medium;
    private JButton hard;
    private JButton play;
    private JButton exit;
    private JPanel logotipo;
    private JTextField numColumn;
    private JLabel textSize;
    private JLabel rowSizeText;
    private JLabel columnSizeText;
    private JTextField numSize;
    private JLabel difficulty;
    private JLabel logo;

    private JFrame frame = new JFrame("New Game");

    private int diff = 1;

    public NewGame() {

        Utils.loadFonts();
        setListeners();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,30));

        picLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setFont(Utils.digital.deriveFont(40f));
        logo.setForeground(Color.decode(Utils.colorDarkBlue));

        textSize.setFont(Utils.roboto);
        textSize.setForeground(Color.BLACK);

        rowSizeText.setForeground(Color.BLACK);
        rowSizeText.setFont(Utils.roboto.deriveFont(18f));

        numSize.setForeground(Color.BLACK);
        numSize.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        numSize.setFont(Utils.roboto.deriveFont(18f));

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

    private void setListeners() {

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                Config c = new Config();
//                c.drawConfig();
            }
        });

        numSize.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    numSize.setEditable(true);
                } else {
                    numSize.setEditable(false);
                }
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
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    numSize.setEditable(true);
                } else {
                    numSize.setEditable(false);
                }
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
                frame.dispose();
                if (numSize.getText().isEmpty() || numColumn.getText().isEmpty()) Utils.showError("No se ha indicado alguno de los tamaños solicitados");
                else {
                    int rowSize = Integer.parseInt(numSize.getText());
                    int columnSize = Integer.parseInt(numColumn.getText());
                    if (rowSize >= 3 && columnSize >= 3) {
                        CtrlUI ctrlUI = CtrlUI.getInstance();
                        ctrlUI.startGame(diff, rowSize, columnSize);
                        String kakuro = ctrlUI.getKakuro();
                        Play play = new Play(kakuro);
                        play.drawPlay();
                    }
                    else Utils.showError("El tamaño debe ser minimo 3x3");
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main main = new Main();
                main.drawMain();
            }
        });
    }

    private void createUIComponents() {
        picLabel = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("New Game");
        frame.setContentPane(new NewGame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public void drawNewGame() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}