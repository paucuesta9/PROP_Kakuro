package presentation;

import domain.classes.WhiteCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.IOException;
import java.io.InputStream;

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

    private int rowSize, columnSize;

    public Play(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        ctrlUI = new CtrlUI();
        InputStream is = Play.class.getResourceAsStream("fa-regular.ttf");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.PLAIN, 50f);
            config.setText("\uF013");
            config.setFont(font);
        } catch (FontFormatException e) {
            System.out.println("Salta de aqui");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Salta de allá");
            e.printStackTrace();
        }


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
        frame.setSize(800,700);
        frame.setVisible(true);
    }
}
