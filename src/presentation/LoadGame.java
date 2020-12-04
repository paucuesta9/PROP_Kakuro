package presentation;

import com.google.gson.stream.JsonReader;
import domain.classes.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.synth.SynthScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private CtrlUI ctrlUI = CtrlUI.getInstance();
    private JPanel logotipo;
    private JPanel scroll;

    public LoadGame() {

        scroll = new JPanel();

        Utils.loadFonts();
        setListeners();
        loadGames();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,20));

        picLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setFont(Utils.roboto.deriveFont(40f));
        logo.setForeground(Color.decode(Utils.colorDarkBlue));

        text.setForeground(Color.BLACK);
        text.setFont(Utils.roboto.deriveFont(18f));

        Utils.setButtons(play);
        play.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(exit);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        games.setBorder(null);

        games.getVerticalScrollBar().setBackground(Color.white);



        games.getVerticalScrollBar().setUI(new Scroll());
        games.getVerticalScrollBar().setUnitIncrement(16);


    }

    private void loadGames() {
        ArrayList<ArrayList<Integer>> listGames = ctrlUI.getStartedGames();
        scroll.setLayout(new BoxLayout(scroll, BoxLayout.Y_AXIS));

        if (listGames.size() == 0) {
            JLabel noGames = new JLabel("No hay partidas");
            noGames.setFont(Utils.roboto.deriveFont(20f));
            noGames.setForeground(Color.RED);
            play.setEnabled(false);
            scroll.add(noGames);
        }
        for (int i = 0; i < listGames.size(); ++i) {
            int id = listGames.get(i).get(0);
            int time = listGames.get(i).get(1);
            int diff = listGames.get(i).get(2);
            int rowSize = listGames.get(i).get(5);
            int columnSize = listGames.get(i).get(6);

            String timeString = Utils.setTimeToLabel(time);
            GameBrief gb = new GameBrief (id, rowSize, columnSize, diff, timeString);
            scroll.add(gb.panel1);
            scroll.validate();
            scroll.add(new JSeparator(SwingConstants.VERTICAL));
        }

        games.setViewportView(scroll);
        games.validate();

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
