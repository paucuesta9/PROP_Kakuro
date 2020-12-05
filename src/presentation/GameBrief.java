package presentation;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBrief extends JPanel {

    private JLabel idLabel;
    private JLabel sizeLabel;
    private JLabel diffLabel;
    private JLabel timeLabel;
    public JPanel panel1;
    public JPanel panel2;

    public String pathImage = "fondo-game-brief.png";
    private int difficulty;

    public GameBrief(int id, int rowSize, int columnSize, int diff, String time) {
        super();

        difficulty = diff;

        idLabel.setFont(Utils.roboto.deriveFont(16f));
        idLabel.setForeground(Color.decode(Utils.colorDarkBlue));

        sizeLabel.setFont(Utils.roboto.deriveFont(16f));
        sizeLabel.setForeground(Color.BLACK);

        diffLabel.setFont(Utils.roboto.deriveFont(16f));
        if (diff == 1) diffLabel.setForeground(Color.GREEN);
        else if (diff == 2) diffLabel.setForeground(Color.ORANGE);
        else if (diff == 3) diffLabel.setForeground(Color.red);

        timeLabel.setFont(Utils.digital.deriveFont(35f));
        timeLabel.setForeground(Color.decode("#1b5e20"));

        this.idLabel.setText("Partida " + id);
        this.sizeLabel.setText("Tamaño: " + rowSize + "x" + columnSize);
        this.diffLabel.setText("Dificultad: " + diff);
        this.timeLabel.setText(time);
    }

    private void createUIComponents() {
        panel2 = new ImageGame();
    }

    public void changeImage(int option) {
        if (option == 0) {
            pathImage = "fondo-game-brief.png";
            panel2.repaint();
            idLabel.setForeground(Color.decode(Utils.colorDarkBlue));
            sizeLabel.setForeground(Color.BLACK);
            if (difficulty == 1) diffLabel.setForeground(Color.GREEN);
            else if (difficulty == 2) diffLabel.setForeground(Color.ORANGE);
            else if (difficulty == 3) diffLabel.setForeground(Color.red);
            timeLabel.setForeground(Color.decode("#1b5e20"));
        }
        else {
            pathImage = "Partida_1.png";
            panel2.repaint();
            idLabel.setForeground(Color.WHITE);
            sizeLabel.setForeground(Color.WHITE);
            diffLabel.setForeground(Color.WHITE);
            timeLabel.setForeground(Color.WHITE);
        }

    }

    class ImageGame extends JPanel
    {
        public void paintComponent (Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/" + pathImage)).getImage(), 0, 0, null);
        }
    }
}
