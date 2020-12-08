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

    private JFrame frame;

    public AskNewContinue() {
        Utils.loadFonts();
        setListeners();

        photo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        question.setFont(Utils.roboto.deriveFont(18f));

        Utils.setButtons(síButton);
        Utils.setButtons(noButton);
        Utils.setButtons(volverButton);

        síButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/bottonOkError.png")));
        noButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-cyan-little.png")));
        volverButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
    }

    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//                Config c = new Config();
//                c.drawConfig();
            }
        });

        síButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadGame lg = new LoadGame();
                lg.drawLoadGame(frame);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGame ng = new NewGame();
                ng.drawNewGame(frame);
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main();
                main.drawMain(frame);
            }
        });
    }

    public void drawAskNewContinue(JFrame frame) {
        this.frame = frame;
        frame.setTitle("New or Continue");
        frame.setContentPane(panel1);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frameMain = new JFrame("New or Continue");
        frameMain.setContentPane(new AskNewContinue().panel1);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(1200,800);
        frameMain.setResizable(false);
        Utils.center(frameMain);
        frameMain.setVisible(true);
    }

    private void createUIComponents() {
        photo = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }
}
