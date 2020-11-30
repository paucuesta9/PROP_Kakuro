package presentation;

import domain.controllers.CtrlDomain;

import javax.sound.sampled.*;
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
        ctrlUI.startGame(1, rowSize, columnSize);
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

        InputStream audioSrc = getClass().getClassLoader().getResourceAsStream("Prueba1.wav");
        AudioInputStream as1 = null;
        try {
            as1 = AudioSystem.getAudioInputStream(audioSrc);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioFormat af = as1.getFormat();
        Clip clip1 = null;
        try {
            clip1 = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        DataLine.Info info = new DataLine.Info(Clip.class, af);

        Line line1 = null;
        try {
            line1 = AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        if ( ! line1.isOpen() )
        {
            try {
                clip1.open(as1);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clip1.loop(Clip.LOOP_CONTINUOUSLY);
            clip1.start();
        }

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
        frame.setContentPane(new Play(3, 3).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
