package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AskNewContinue {
    private JPanel panel1;
    private JLabel photo;
    private JButton config;
    private JButton síButton;
    private JButton noButton;
    private JButton volverButton;
    private JLabel question;

    private JFrame frame = new JFrame("New or Continue");

    public AskNewContinue() {
        Utils.loadFonts();
        setListeners();

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
                frame.dispose();
//                LeadGame lg = new LoadGame();
//                ld.drawLoadGame();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                NewGame ng = new NewGame();
//                ng.drawNewGame();
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main main = new Main();
                main.drawMain();
            }
        });
    }

    public void drawAskNewContinue() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setVisible(true);
        Utils.center(frame);
    }

    public static void main(String [] args) {
        JFrame frameMain = new JFrame("New or Continue");
        frameMain.setContentPane(new AskNewContinue().panel1);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(1200,800);
        frameMain.setResizable(false);
        frameMain.setVisible(true);
        Utils.center(frameMain);
    }

    private void createUIComponents() {
        photo = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }
}
