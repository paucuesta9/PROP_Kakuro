package presentation;

import domain.classes.Kakuro;
import domain.controllers.CtrlDomain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Generate2 {
    private JPanel panel1;
    private JLabel photo;
    private JButton config;
    private JButton síButton;
    private JButton noButton;
    private JButton volverButton;
    private JLabel question;
    private JPanel board;

    private KakuroBoard sg;
    private Component[] components;

    private static CtrlDomain ctrlDomain;

    private JFrame frame = new JFrame("Generate");

    private int rowSize, columnSize;
    private int posX, posY;

    public Generate2(int i, int j, int diff) {
        Utils.loadFonts();

        setListeners();
        rowSize = i; columnSize = j;

        ctrlDomain = new CtrlDomain();
        ctrlDomain.generate(i, diff);
        String k = ctrlDomain.getKakuroToString();

        sg = new KakuroBoard(k);
        board.add(sg);
        components = sg.getComponents();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        question.setFont(Utils.roboto.deriveFont(18f));

        síButton.setFont(Utils.roboto);
        síButton.setForeground(Color.WHITE);
        síButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/bottonOkError.png")));
        síButton.setBorderPainted(false);
        síButton.setBackground(Color.magenta);
        síButton.setHorizontalTextPosition(JButton.CENTER);
        síButton.setVerticalTextPosition(JButton.CENTER);

        noButton.setFont(Utils.roboto);
        noButton.setForeground(Color.WHITE);
        noButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-cyan-little.png")));
        noButton.setBorderPainted(false);
        noButton.setBackground(Color.magenta);
        noButton.setHorizontalTextPosition(JButton.CENTER);
        noButton.setVerticalTextPosition(JButton.CENTER);

        volverButton.setFont(Utils.roboto);
        volverButton.setForeground(Color.WHITE);
        volverButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        volverButton.setBorderPainted(false);
        volverButton.setBackground(null);
        volverButton.setHorizontalTextPosition(JButton.CENTER);
        volverButton.setVerticalTextPosition(JButton.CENTER);
    }

   /* private void listeners() {
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof KakuroWhiteCell) {
                KakuroWhiteCell cell = (KakuroWhiteCell) components[i];
                cell.addFocusListener(new FocusListener() {
                    Color color;
                    int value;
                    @Override
                    public void focusGained(FocusEvent e) {
                        color = cell.getBackground();
                        value = cell.getValue();
                        cell.setBackground(Color.decode(Utils.colorBlueSelectedCell));
                        posX = cell.getPosX();
                        posY = cell.getPosY();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (!cell.getBackground().equals(Color.decode(Utils.colorRedCell))) cell.setBackground(color);
                        if (value != cell.getValue() && color.equals(Color.decode(Utils.colorRedCell))) cell.setBackground(Color.WHITE);
                       // checkValidityCell(cell, posX, posY);
                        int posXAux = posX;
                        int posYAux = posY;
                        while (true) {
                            --posYAux;
                            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
                            else {
                                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                               // checkValidityCell(whiteCell, posXAux, posYAux);
                            }
                        }
                        posYAux = posY;
                        while (true) {
                            ++posYAux;
                            if (posYAux == columnSize) break;
                            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
                            else {
                                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                               // checkValidityCell(whiteCell, posXAux, posYAux);
                            }
                        }
                        posYAux = posY;
                        while (true) {
                            --posXAux;
                            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
                            else {
                                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                          //      checkValidityCell(whiteCell, posXAux, posYAux);
                            }
                        }
                        posXAux = posX;
                        while (true) {
                            ++posXAux;
                            if (posXAux == rowSize) break;
                            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
                            else {
                                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                             //   checkValidityCell(whiteCell, posXAux, posYAux);
                            }
                        }
                    }
                });
            }
        }

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }*/

    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                Config c = new Config();
//                c.drawConfig();
            }
        });

        síButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDomain.resolve();
                ctrlDomain.saveKakuro();
                frame.dispose();
                Main main = new Main();
                main.drawMain();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main main = new Main();
                main.drawMain();
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Generate g = new Generate();
                g.drawGenerate();
            }
        });
    }

    public void drawGenerate2() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frameMain = new JFrame("Generate");
        frameMain.setContentPane(new Generate2(6, 6, 1).panel1);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(1200,800);
        frameMain.setResizable(false);
        Utils.center(frameMain);
        frameMain.setVisible(true);
    }

    private void createUIComponents() {
        board = new JPanel();
    }
}
