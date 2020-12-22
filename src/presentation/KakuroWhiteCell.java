package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** @file KakuroWhiteCell.java
 @brief Clase  <em>KakuroWhiteCell</em>.
 @class KakuroWhiteCell
 */

/** @brief Clase KakuroWhiteCell que carga una celda blanca con sus atributos.
 * @author Pau Cuesta Arcos
 */

public class KakuroWhiteCell extends KakuroCell {
    private int value = 0;
    private boolean[] pencil = new boolean[10];

    private JLabel jLabel;

    KakuroWhiteCell(int x, int y, int size) {
        super(x, y, size);
        setLayout(new GridBagLayout());
        jLabel = new JLabel();
        jLabel.setFont(Utils.roboto.deriveFont(20*size/50f));
        jLabel.setForeground(Utils.colorNumbersWhiteCell);
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
        setBackground(Utils.colorWhiteCell);
        setFocusable(true);

        GridBagConstraints c = new GridBagConstraints();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                JLabel labelPencil = new JLabel();
                labelPencil.setForeground(Utils.colorBorde);
                labelPencil.setFont(Utils.roboto.deriveFont(20*size/150f));
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.NONE;
                c.gridx = j;
                c.gridy = i;
                c.anchor = GridBagConstraints.CENTER;
                c.gridheight = 1;
                c.gridwidth = 1;
                labelPencil.setVisible(false);
                add(labelPencil, c);
            }
        }
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
        if (value != 0) jLabel.setText(String.valueOf(value));
        else jLabel.setText("");
        for (int i = 1; i < 10; ++i) {
            getComponent(i).setVisible(false);
        }
        jLabel.setVisible(true);
    }

    public void setPencil(int value) {
        for (int i = 1; i < 10; ++i) {
            getComponent(i).setVisible(true);
        }

        if (this.pencil[value]) {
            this.pencil[value] = false;
            ((JLabel) getComponent(value)).setText("");
        }
        else {
            this.pencil[value] = true;
            ((JLabel) getComponent(value)).setText(String.valueOf(value));
            if (this.value != 0) {
                ((JLabel) getComponent(this.value)).setText(String.valueOf(this.value));
                this.value = 0;
                jLabel.setText("");
                jLabel.setVisible(false);
            }
        }
    }

    public boolean[] getAllPencil() {
        return pencil;
    }

    public void setAllPencil(boolean[] pencil) {
        this.pencil = pencil;
        if (value != 0) {
            jLabel.setVisible(true);
        }
        else {
            jLabel.setText("");
            jLabel.setVisible(false);
            for (int i = 1; i < 10; ++i) {
                if (this.pencil[i]) {
                    ((JLabel) getComponent(i)).setText(String.valueOf(i));
                }
                getComponent(i).setVisible(true);
            }
        }

    }
}
