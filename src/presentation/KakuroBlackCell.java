package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class KakuroBlackCell extends KakuroCell {
    private int row = 0;
    private int column = 0;
    private JLabel jLabelRow;
    private JLabel jLabelColumn;

    KakuroBlackCell(int x, int y) {
        super(x, y);
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        jLabelRow = new JLabel();
        jLabelRow.setFont(new Font("Verdana", Font.PLAIN,15));
        jLabelRow.setForeground(Color.WHITE);

        add(jLabelRow, new GridBagConstraints(
                0, // gridx
                0, // gridy
                1, // gridwidth
                1, // gridheight
                1, // weightx
                1, // weighty
                GridBagConstraints.NORTHEAST, // anchor <------------
                GridBagConstraints.NONE, // fill
                new Insets(1, // inset top
                        0, // inset left
                        0, // inset bottom
                        5), // inset right
                0, // ipadx
                0));

        jLabelColumn = new JLabel();
        jLabelColumn.setFont(new Font("Verdana", Font.PLAIN,15));
        jLabelColumn.setForeground(Color.WHITE);
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
                        5, // inset left
                        1, // inset bottom
                        0), // inset right
                0, // ipadx
                0));
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
        jLabelRow.setText(String.valueOf(column));
    }
}
