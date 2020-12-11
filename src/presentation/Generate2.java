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
    private JLabel logo;

    private KakuroBoard sg;
    private CtrlUI ctrlUI = CtrlUI.getInstance();

    private JFrame frame;

    private JFrame conf;
    private String kAux;
    private int option;

    public Generate2(String uwu, String k, int option) {
        Utils.loadFonts();

        setListeners();

        this.option = option;
        kAux = k;
        sg = new KakuroBoard(k);
        board.add(sg);

        board.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        question.setFont(Utils.roboto.deriveFont(18f));
        question.setText(uwu);

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

    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config config = new Config();
                config.drawConfig();
                config.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        sg = new KakuroBoard(kAux);
                        board.removeAll();
                        board.add(sg);
                        board.validate();
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });

        síButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option == 1) ctrlUI.save();
                else {
                    ctrlUI.saveGame();
                }
                Main main = new Main();
                main.drawMain(frame);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main();
                main.drawMain(frame);
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main m = new Main();
                m.drawMain(frame);
            }
        });
    }

    public void drawGenerate2(JFrame frame) {
        this.frame = frame;
        frame.setTitle("Save");
        frame.setContentPane(panel1);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frameMain = new JFrame("Save");
        //frameMain.setContentPane(new Generate2(6, 6, 1).panel1);
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
