package presentation;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class GameBrief extends JPanel {

    private JLabel idLabel;
    private JLabel sizeLabel;
    private JLabel diffLabel;
    private JLabel timeLabel;
    public JPanel panel1;
    private JPanel panel2;

    public GameBrief(int id, int rowSize, int columnSize, int diff, String time) {
        super();

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

    class ImageGame extends JPanel
    {
        public void paintComponent (Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/fondo-game-brief.png")).getImage(), 0, 0, null);
        }
    }
}
