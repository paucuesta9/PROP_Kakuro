package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadGame {
    private JPanel panel1;
    private JLabel logo;
    private JButton config;
    private JLabel picLabel;
    private JButton play;
    private JButton exit;
    private JScrollPane games;
    private JLabel text;

    private JFrame frame = new JFrame("Load Game");
    private JPanel logotipo;
    private JPanel scroll;

    public LoadGame() {

        scroll = new JPanel();

        Utils.loadFonts();
        setListeners();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,20));

        picLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setFont(Utils.roboto.deriveFont(40f));
        logo.setForeground(Color.decode(Utils.colorDarkBlue));

        text.setForeground(Color.BLACK);
        text.setFont(Utils.roboto.deriveFont(18f));

        //CtrlUI ctrlUI = CtrlUI.getInstance();
        //ctrlUI.getStartedGames();
        scroll.setLayout(new BoxLayout(scroll, BoxLayout.Y_AXIS));

        for (int i = 0; i < 10; ++i) {
            scroll.add(new JLabel("Partida " + i ));
        }
        games.setViewportView(scroll);
        games.setBorder(null);

        Utils.setButtons(play);
        play.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(exit);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

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

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main main = new Main();
                main.drawMain();
            }
        });
    }

    private void createUIComponents() {
        picLabel = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Load Game");
        frame.setContentPane(new LoadGame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public void drawLoadGame() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}
