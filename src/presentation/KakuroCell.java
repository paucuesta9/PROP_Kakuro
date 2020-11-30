package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KakuroCell extends JPanel {
    private int x, y;

    KakuroCell(int x, int y) {
        super();
        this.x = x;
        this.y = y;

        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        setPreferredSize(new Dimension(50, 50));
    }
}
