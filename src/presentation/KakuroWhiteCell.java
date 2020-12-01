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
