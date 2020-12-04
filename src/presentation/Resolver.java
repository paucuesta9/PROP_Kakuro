package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Resolver {
    private JPanel board;
    private JPanel menu;
    private JButton config;
    private JButton volver;
    private JPanel logotipo;
    private JPanel panel;
    private static CtrlUI ctrlUI;
    private Font fontAwesome, roboto;

    private int rowSize, columnSize;
    private int posX, posY;
    private KakuroBoard sg;
    private Component[] components;

    public Resolver(String kakuro) {
        String[] values = kakuro.split("\n");
        String[] valuesSize = values[0].split(",");
        this.rowSize = Integer.parseInt(valuesSize[0]);
        this.columnSize = Integer.parseInt(valuesSize[1]);
        Utils.loadFonts();

        sg = new KakuroBoard(kakuro);
        board.add(sg);
        components = sg.getComponents();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10, 0, 0, 10));

        volver.setFont(Utils.roboto);
        volver.setForeground(Color.WHITE);
        volver.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        volver.setBorderPainted(false);
        volver.setBackground(null);
        volver.setHorizontalTextPosition(JButton.CENTER);
        volver.setVerticalTextPosition(JButton.CENTER);
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Resolver");
        ctrlUI = CtrlUI.getInstance();
        ctrlUI.startGame(1, 6, 6);
        String kakuro = ctrlUI.getCorrectKakuro();
        frame.setContentPane(new Resolver(kakuro).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        board = new JPanel();
    }
}
