package presentation;

import jdk.jshell.execution.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Generate {
    private JPanel panel1;
    private JPanel picLabel;
    private JLabel label;
    private JButton config;
    private JButton easy;
    private JButton medium;
    private JButton hard;
    private JButton generateButton;
    private JButton exit;
    private JPanel logotipo;
    private JPanel rowSize;
    private JPanel columnSize;
    private JTextField numColumn;
    private JLabel textSize;
    private JLabel rowSizeText;
    private JLabel columnSizeText;
    private JTextField numRow;
    private JLabel difficulty;
    private JLabel errorValue;

    private JFrame frame = new JFrame("Generate");

    int diff = 1;

    public Generate() {

        Utils.loadFonts();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,20));

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

        generateButton.setFont(Utils.roboto);
        generateButton.setForeground(Color.WHITE);
        generateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        generateButton.setBorderPainted(false);
        generateButton.setBackground(null);
        generateButton.setHorizontalTextPosition(JButton.CENTER);
        generateButton.setVerticalTextPosition(JButton.CENTER);

        exit.setFont(Utils.roboto);
        exit.setForeground(Color.WHITE);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        exit.setBorderPainted(false);
        exit.setBackground(null);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);

        numRow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                String value = numRow.getText();
                int l = value.length();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    numRow.setEditable(true);
                } else {
                    numRow.setEditable(false);
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
                String value = numRow.getText();
                int l = value.length();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    numRow.setEditable(true);
                } else {
                    numRow.setEditable(false);
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
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                easy.setForeground(Color.WHITE);
                medium.setForeground(Color.BLACK);
                hard.setForeground(Color.BLACK);
                diff = 1;
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -4.png")));
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                easy.setForeground(Color.BLACK);
                medium.setForeground(Color.WHITE);
                hard.setForeground(Color.BLACK);
                diff = 2;
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 8.png")));
                easy.setForeground(Color.BLACK);
                medium.setForeground(Color.BLACK);
                hard.setForeground(Color.WHITE);
                diff = 3;
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!numRow.getText().equals("") && !numColumn.getText().equals("") && Integer.parseInt(numRow.getText())>=4 && Integer.parseInt(numColumn.getText())>=4) {
                    generateButton.setText("Cargando...");
                    frame.dispose();
                    Generate2 g = new Generate2(Integer.parseInt(numRow.getText()), Integer.parseInt(numColumn.getText()), diff);
                    g.drawGenerate2();
                }
                else Utils.showError("Tamaño inválido");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main m = new Main();
                m.drawMain();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
        label.requestFocusInWindow();
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Generate");
        frame.setContentPane(new Generate().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public void drawGenerate() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}