package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private JPanel panel1;
    private JButton playButton;
    private JButton createKakuroButton;
    private JButton solveButton;
    private JButton generateButton;
    private JButton rankingButton;
    private JButton logoutButton;
    private JButton config;
    private JLabel photo;

    JFrame frame = new JFrame("Main");

    public Main() {
        Utils.loadFonts();
        setListeners();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        playButton.setFont(Utils.roboto);
        playButton.setForeground(Color.WHITE);
        playButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        playButton.setBorderPainted(false);
        playButton.setBackground(null);
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);

        createKakuroButton.setFont(Utils.roboto);
        createKakuroButton.setForeground(Color.WHITE);
        createKakuroButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        createKakuroButton.setBorderPainted(false);
        createKakuroButton.setBackground(null);
        createKakuroButton.setHorizontalTextPosition(JButton.CENTER);
        createKakuroButton.setVerticalTextPosition(JButton.CENTER);

        solveButton.setFont(Utils.roboto);
        solveButton.setForeground(Color.WHITE);
        solveButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        solveButton.setBorderPainted(false);
        solveButton.setBackground(null);
        solveButton.setHorizontalTextPosition(JButton.CENTER);
        solveButton.setVerticalTextPosition(JButton.CENTER);

        generateButton.setFont(Utils.roboto);
        generateButton.setForeground(Color.WHITE);
        generateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        generateButton.setBorderPainted(false);
        generateButton.setBackground(null);
        generateButton.setHorizontalTextPosition(JButton.CENTER);
        generateButton.setVerticalTextPosition(JButton.CENTER);

        rankingButton.setFont(Utils.roboto);
        rankingButton.setForeground(Color.WHITE);
        rankingButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        rankingButton.setBorderPainted(false);
        rankingButton.setBackground(null);
        rankingButton.setHorizontalTextPosition(JButton.CENTER);
        rankingButton.setVerticalTextPosition(JButton.CENTER);

        logoutButton.setFont(Utils.roboto);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));
        logoutButton.setBorderPainted(false);
        logoutButton.setBackground(null);
        logoutButton.setHorizontalTextPosition(JButton.CENTER);
        logoutButton.setVerticalTextPosition(JButton.CENTER);
    }

    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                Config config = new Config();
//                config.drawConfig();
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                Play p = new Play("");
//                p.drawPlay();
            }
        });

        createKakuroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                Create create = new Create();
//                create.drawCreate();
            }
        });

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                Solve solve = new Solve();
//                solve.drawSolve();
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                Generate g = new Generate();
//                g.drawGenerate();
            }
        });

        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                Ranking ranking = new Ranking();
//                ranking.drawRanking();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login login = new Login();
//                login.drwarLogin();
            }
        });
    }

    public void drawMain() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.setResizable(false);
        frame.setVisible(true);
        Utils.center(frame);
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.setResizable(false);
        frame.setVisible(true);
        Utils.center(frame);
    }

    private void createUIComponents() {
        photo = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }
}
