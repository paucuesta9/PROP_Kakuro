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
    private JButton generateButton;
    private JButton rankingButton;
    private JButton logoutButton;
    private JButton config;
    private JLabel photo;
    private JPanel logotipo;

    private JFrame frame = new JFrame("Main");

    public Main() {
        Utils.loadFonts();
        setListeners();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        photo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        Utils.setButtons(playButton);
        playButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(createKakuroButton);
        createKakuroButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        Utils.setButtons(generateButton);
        generateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        Utils.setButtons(rankingButton);
        rankingButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        Utils.setButtons(logoutButton);
        logoutButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));
    }

    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config config = new Config();
                config.drawConfig();
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AskNewContinue a = new AskNewContinue();
                a.drawAskNewContinue();
            }
        });

        createKakuroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Create create = new Create();
                create.drawCreate();
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Generate g = new Generate();
                g.drawGenerate();
            }
        });

        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Rankings ranking = new Rankings();
                ranking.drawRanking();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login login = new Login();
                login.drawLogin();
            }
        });
    }

    public void drawMain() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        photo = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }
}
