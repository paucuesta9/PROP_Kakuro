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
    private JPanel panelText;
    private JPanel panel2;

    public GameBrief(int id, int rowSize, int columnSize, int diff, String time) {
        super();
        panel2.setBackground(Color.WHITE);
        panel2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        panelText.setBackground(Color.WHITE);

        idLabel.setFont(Utils.roboto.deriveFont(16f));
        idLabel.setForeground(Color.decode(Utils.colorDarkBlue));
        sizeLabel.setFont(Utils.roboto.deriveFont(16f));
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
}
