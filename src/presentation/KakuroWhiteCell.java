package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KakuroWhiteCell extends KakuroCell {
    private int value = 0;
    private JLabel jLabel;

    KakuroWhiteCell(int x, int y) {
        super(x, y);
        setLayout(new GridBagLayout());
        jLabel = new JLabel();
        jLabel.setFont(new Font("Verdana",1,20));
        add(jLabel, new GridBagConstraints(
                0, // gridx
                0, // gridy
                1, // gridwidth
                1, // gridheight
                1, // weightx
                1, // weighty
                GridBagConstraints.CENTER, // anchor <------------
                GridBagConstraints.NONE, // fill
                new Insets(0, // inset top
                        0, // inset left
                        0, // inset bottom
                        0), // inset right
                0, // ipadx
                0));
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
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

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
