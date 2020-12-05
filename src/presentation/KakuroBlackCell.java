package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class KakuroBlackCell extends KakuroCell {
    private int row = 0;
    private int column = 0;
    private JLabel jLabelRow;
    private JLabel jLabelColumn;

    KakuroBlackCell(int x, int y, int size) {
        super(x, y, size);
        setBackground(Utils.colorBlackCell);
        setLayout(new GridBagLayout());

        jLabelRow = new JLabel();
        jLabelRow.setFont(new Font("Roboto", Font.PLAIN,15*size/50));
        jLabelRow.setForeground(Utils.colorNumbersBlackCell);

        add(jLabelRow, new GridBagConstraints(
                0, // gridx
                0, // gridy
                1, // gridwidth
                1, // gridheight
                1, // weightx
                1, // weighty
                GridBagConstraints.NORTHEAST, // anchor <------------
                GridBagConstraints.NONE, // fill
                new Insets(size/50, // inset top
                        0, // inset left
                        0, // inset bottom
                        5*size/50), // inset right
                0, // ipadx
                0));

        jLabelColumn = new JLabel();
        jLabelColumn.setFont(new Font("Roboto", Font.PLAIN,15*size/50));
        jLabelColumn.setForeground(Utils.colorNumbersBlackCell);
        add(jLabelColumn, new GridBagConstraints(
                0, // gridx
                0, // gridy
                1, // gridwidth
                1, // gridheight
                1, // weightx
                1, // weighty
                GridBagConstraints.SOUTHWEST, // anchor <------------
                GridBagConstraints.NONE, // fill
                new Insets(0, // inset top
                        5*size/50, // inset left
                        size/50, // inset bottom
                        0), // inset right
                0, // ipadx
                0));
    }

    public KakuroBlackCell(int i, int j, int horizontal, int vertical, int size) {
        this(i, j, size);
        this.row = horizontal;
        this.column = vertical;
        if (row != 0) jLabelRow.setText(String.valueOf(row));
        if (column != 0) jLabelColumn.setText(String.valueOf(column));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (row != 0 || column != 0) {
            g.setColor(Color.WHITE);
            g.drawLine(0, 0, getWidth(), getHeight());
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
        jLabelRow.setText(String.valueOf(row));
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
        jLabelColumn.setText(String.valueOf(column));
    }
}
