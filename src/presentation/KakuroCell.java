package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KakuroCell extends JPanel {
    private int posX, posY;

    KakuroCell(int posX, int posY, int size) {
        super();
        this.posX = posX;
        this.posY = posY;

        setBorder(BorderFactory.createLineBorder(Utils.colorBorde));

        setPreferredSize(new Dimension(size, size));
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
