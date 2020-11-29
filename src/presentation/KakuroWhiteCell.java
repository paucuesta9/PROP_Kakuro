package presentation;

import javax.swing.*;
import java.awt.*;

public class KakuroWhiteCell extends KakuroCell {
    private int value = 0;
    private JLabel jLabel;

    KakuroWhiteCell(int x, int y) {
        super(x, y);
        jLabel = new JLabel();
        jLabel.setFont(new Font("Verdana",1,20));
        add(jLabel);
        setBackground(Color.WHITE);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        jLabel.setText(String.valueOf(value));
    }
}
