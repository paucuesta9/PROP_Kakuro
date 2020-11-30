package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KakuroWhiteCell extends KakuroCell {
    private int value = 0;
    private JLabel jLabel;

    KakuroWhiteCell(int x, int y) {
        super(x, y);
        jLabel = new JLabel();
        jLabel.setFont(new Font("Verdana",1,20));
        add(jLabel);
        jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel.setVerticalTextPosition(SwingConstants.CENTER);
        setBackground(Color.WHITE);
        setFocusable(true);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(Color.CYAN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(Color.WHITE);
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public KakuroWhiteCell(int i, int j, int value) {
        this(i, j);
        this.value = value;
        if (value != 0) jLabel.setText(String.valueOf(value));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        jLabel.setText(String.valueOf(value));
    }
}
