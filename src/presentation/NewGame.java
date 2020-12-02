package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    private JPanel textSize;
    private JPanel rowSize;
    private JPanel columnSize;
    private JTextField numRow;
    private JTextField numColumn;
    private JPanel difficulty;

    private static JFrame frame;
    private Font fontAwesome, roboto;

    public NewGame() {

        loadFonts();

        picLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ImageIcon icon = new ImageIcon("resources/images/Captura.PNG"); //se ajusta cuadrado
                Image img = icon.getImage();
                int w = picLabel.getWidth(); int h = picLabel.getHeight();
                if (w>h) w = h;
                else h = w;
                Image imgScale = img.getScaledInstance(w, h, Image.SCALE_FAST);
                ImageIcon scaledIcon = new ImageIcon(imgScale);
                label.setIcon(scaledIcon);
            }
        });

        config.setFont(fontAwesome);
        config.setForeground(Color.decode("#00204A"));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        easy.setFont(roboto);
        easy.setForeground(Color.BLACK);
        easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
        easy.setBorderPainted(false);
        easy.setBackground(null);
        easy.setHorizontalTextPosition(JButton.CENTER);
        easy.setVerticalTextPosition(JButton.CENTER);

        medium.setFont(roboto);
        medium.setForeground(Color.BLACK);
        medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
        medium.setBorderPainted(false);
        medium.setBackground(null);
        medium.setHorizontalTextPosition(JButton.CENTER);
        medium.setVerticalTextPosition(JButton.CENTER);

        hard.setFont(roboto);
        hard.setForeground(Color.BLACK);
        hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
        hard.setBorderPainted(false);
        hard.setBackground(null);
        hard.setHorizontalTextPosition(JButton.CENTER);
        hard.setVerticalTextPosition(JButton.CENTER);

        play.setFont(roboto);
        play.setForeground(Color.WHITE);
        play.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        play.setBorderPainted(false);
        play.setBackground(null);
        play.setHorizontalTextPosition(JButton.CENTER);
        play.setVerticalTextPosition(JButton.CENTER);

        exit.setFont(roboto);
        exit.setForeground(Color.WHITE);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        exit.setBorderPainted(false);
        exit.setBackground(null);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);


    }
    private void loadFonts() {
        BufferedInputStream myStream = null;
        try {
            myStream = new BufferedInputStream(new FileInputStream("resources/fonts/fa-solid.ttf"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        Font ttfBase = null;
        try {
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (FontFormatException fontFormatException) {
            fontFormatException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        fontAwesome = ttfBase.deriveFont(Font.PLAIN, 30f);

        try {
            myStream = new BufferedInputStream(new FileInputStream("resources/fonts/Roboto-Bold.ttf"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        try {
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (FontFormatException fontFormatException) {
            fontFormatException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        roboto = ttfBase.deriveFont(Font.PLAIN, 20f);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage().getScaledInstance(600,600, Image.SCALE_DEFAULT)));
        label.requestFocusInWindow();
    }

    public static void main(String [] args) {
        frame = new JFrame("Play");
        frame.setContentPane(new NewGame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}

