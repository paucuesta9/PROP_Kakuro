package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KakuroWhiteCell extends KakuroCell {
    private int value = 0;
    private JLabel jLabel;
    private CtrlUI ctrlUI;

    KakuroWhiteCell(int x, int y, int size) {
        super(x, y, size);
        ctrlUI = CtrlUI.getInstance();
        setLayout(new GridBagLayout());
        jLabel = new JLabel();
        jLabel.setFont(new Font("Roboto",1,20*size/50));
        jLabel.setForeground(Color.BLACK);
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
                setBackground(Color.decode("#64b5f6"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!getBackground().equals(Color.decode("#e53935"))) setBackground(Color.WHITE);
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ctrlUI.setXY(x,y);
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
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1) value = 1;
                if (keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2) value = 2;
                if (keyCode == KeyEvent.VK_3 || keyCode == KeyEvent.VK_NUMPAD3) value = 3;
                if (keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) value = 4;
                if (keyCode == KeyEvent.VK_5 || keyCode == KeyEvent.VK_NUMPAD5) value = 5;
                if (keyCode == KeyEvent.VK_6 || keyCode == KeyEvent.VK_NUMPAD6) value = 6;
                if (keyCode == KeyEvent.VK_7 || keyCode == KeyEvent.VK_NUMPAD7) value = 7;
                if (keyCode == KeyEvent.VK_8 || keyCode == KeyEvent.VK_NUMPAD8) value = 8;
                if (keyCode == KeyEvent.VK_9 || keyCode == KeyEvent.VK_NUMPAD9) value = 9;
                if (value != 0) {
                    setValue(value);
                    ctrlUI.setValue(x,y,value);
                    if (!ctrlUI.checkValidity(x,y,value)) {
                        setBackground(Color.decode("#e53935"));
                    }
                }
            }
        });
    }

    public KakuroWhiteCell(int i, int j, int value, int size) {
        this(i, j, size);
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
