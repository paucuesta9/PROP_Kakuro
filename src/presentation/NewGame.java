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
    private JPanel picLabel;
    private JLabel label;
    private JButton config;
    private JButton easy;
    private JButton medium;
    private JButton hard;
    private JButton play;
    private JButton exit;
    private JPanel logotipo;
    private JPanel rowSize;
    private JPanel columnSize;
    private JTextField numColumn;
    private JLabel textSize;
    private JLabel rowSizeText;
    private JLabel columnSizeText;
    private JTextField numSize;
    private JLabel difficulty;

    private JFrame frame = new JFrame("New Game");

    public NewGame() {

        Utils.loadFonts();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,20));

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

        easy.setFont(Utils.roboto);
        easy.setForeground(Color.WHITE);
        easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));
        easy.setBorderPainted(false);
        easy.setBackground(null);
        easy.setHorizontalTextPosition(JButton.CENTER);
        easy.setVerticalTextPosition(JButton.CENTER);

        medium.setFont(Utils.roboto);
        medium.setForeground(Color.BLACK);
        medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
        medium.setBorderPainted(false);
        medium.setBackground(null);
        medium.setHorizontalTextPosition(JButton.CENTER);
        medium.setVerticalTextPosition(JButton.CENTER);

        hard.setFont(Utils.roboto);
        hard.setForeground(Color.BLACK);
        hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
        hard.setBorderPainted(false);
        hard.setBackground(null);
        hard.setHorizontalTextPosition(JButton.CENTER);
        hard.setVerticalTextPosition(JButton.CENTER);

        play.setFont(Utils.roboto);
        play.setForeground(Color.WHITE);
        play.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        play.setBorderPainted(false);
        play.setBackground(null);
        play.setHorizontalTextPosition(JButton.CENTER);
        play.setVerticalTextPosition(JButton.CENTER);

        exit.setFont(Utils.roboto);
        exit.setForeground(Color.WHITE);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        exit.setBorderPainted(false);
        exit.setBackground(null);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);

        numSize.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                String value = numSize.getText();
                int l = value.length();
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
                String value = numSize.getText();
                int l = value.length();
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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
        label.requestFocusInWindow();
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