package presentation;

import domain.controllers.CtrlDomain;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Play {
    private JPanel panel1;
    private JButton pause;
    private JButton exit;
    private JPanel board;
    private JPanel menu;
    private JPanel logotipo;
    private JButton help1;
    private JButton help2;
    private JLabel timeLogo;
    private JLabel time;
    private JButton config;

    private CtrlUI ctrlUI;
    private Font fontAwesome, roboto;

    private int rowSize, columnSize;

    public Play(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        ctrlUI = CtrlUI.getInstance();
        ctrlUI.startGame(1, 3, 3);
        loadFonts();

        String kakuro = ctrlUI.getKakuro();
        KakuroBoard sg = new KakuroBoard(kakuro);
        board.add(sg);

        config.setFont(fontAwesome);
        config.setForeground(Color.decode("#00204A"));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        pause.setFont(roboto);
        pause.setForeground(Color.WHITE);
        pause.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        pause.setBorderPainted(false);
        pause.setBackground(null);
        pause.setHorizontalTextPosition(JButton.CENTER);
        pause.setVerticalTextPosition(JButton.CENTER);

        exit.setFont(roboto);
        exit.setForeground(Color.WHITE);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));
        exit.setBorderPainted(false);
        exit.setBackground(null);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);

        help1.setFont(fontAwesome.deriveFont(Font.PLAIN, 40f));
        help1.setForeground(Color.WHITE);
        help1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/elipse-azul.png")));
        help1.setBorderPainted(false);
        help1.setBackground(null);
        help1.setHorizontalTextPosition(JButton.CENTER);
        help1.setVerticalTextPosition(JButton.CENTER);

        help2.setFont(fontAwesome.deriveFont(Font.PLAIN, 40f));
        help2.setForeground(Color.WHITE);
        help2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/elipse-azul.png")));
        help2.setBorderPainted(false);
        help2.setBackground(null);
        help2.setHorizontalTextPosition(JButton.CENTER);
        help2.setVerticalTextPosition(JButton.CENTER);

        timeLogo.setFont(fontAwesome.deriveFont(Font.PLAIN, 40f));
        timeLogo.setForeground(Color.decode("#1976D2"));

        time.setFont(roboto.deriveFont(Font.PLAIN, 30f));
        time.setForeground(Color.decode("#1976D2"));

        help1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = ctrlUI.getX();
                int y = ctrlUI.getY();
                int result = ctrlUI.help1(x,y);
                if (result != -1) {
                    KakuroWhiteCell w = (KakuroWhiteCell) sg.getComponent(x*columnSize+y);
                    if (result == 1) w.setBackground(Color.GREEN);
                    else if (result == 0) w.setBackground(Color.RED);
                    else if (result == -2) w.setBackground(Color.GRAY);
                }
            }
        });
        help2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = ctrlUI.getX();
                int y = ctrlUI.getY();
                int correctNumber = ctrlUI.help2(x,y);
                KakuroWhiteCell w = (KakuroWhiteCell) sg.getComponent(x*columnSize+y);
                w.setValue(correctNumber);
                w.setBackground(Color.GREEN);

            }
        });

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
        board = new JPanel();
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Play(12, 10).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
