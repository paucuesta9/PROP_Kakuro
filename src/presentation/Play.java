package presentation;

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
    private JPanel time;
    private JLabel config;
    private JButton help1;
    private JButton help2;

    private CtrlUI ctrlUI;
    private Font fontAwesome, roboto;

    private int rowSize, columnSize;

    public Play(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        ctrlUI = new CtrlUI();
        loadFonts();

        pause.setFont(roboto);
        exit.setFont(roboto);
        config.setFont(fontAwesome);
        config.setForeground(Color.BLACK);
        config.setText("\uF013");
        config.setForeground(Color.decode("#00204A"));
        config.setBorder(new EmptyBorder(10,0,0,10));
        config.setBackground(Color.CYAN);

        pause.setBackground(Color.decode("#1976D2"));
        pause.setBorderPainted(false);
        pause.setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.drawRoundRect(x, y, width-1, height-1, 50, 50);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(11, 11, 12, 10);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        });

        help1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.help();
            }
        });

        help2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.help();
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
        KakuroBoard sg = new KakuroBoard(rowSize, columnSize);
        board = new JPanel();
        board.add(sg);
        KakuroWhiteCell w = (KakuroWhiteCell) sg.getComponent(0);
        w.setValue(2);
        KakuroBlackCell b = (KakuroBlackCell) sg.getComponent(10);
        b.setRow(1);
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
