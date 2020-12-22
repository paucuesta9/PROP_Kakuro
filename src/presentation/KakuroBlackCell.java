package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/** @file KakuroBlackCell.java
 @brief Clase  <em>KakuroBlackCell</em>.
 @class KakuroBlackCell
 */

/** @brief Clase KakuroBlackCell que carga una celda negra con sus atributos.
 * @author Judith Almoño Gómez
 */

public class KakuroBlackCell extends KakuroCell {
    /**
     * row representa la suma total del bloque de celdas blancas de la fila
     */
    private int row = 0;
    /**
     * column representa la suma total del bloque de celdas blancas de la columna
     */
    private int column = 0;

    private JLabel jLabelRow;
    private JLabel jLabelColumn;

    /** @brief Constructora
     *
     * @param x representa la posición de la fila
     * @param y representa la posición de la columna
     * @param size representa el tamaño de la celda
     */
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

    /** @brief Función para introducir los valores de la celda negra
     *
     * @param i representa la posición de la fila
     * @param j representa la posición de la columna
     * @param horizontal representa la suma total del bloque de celdas blancas de la fila
     * @param vertical representa la suma total del bloque de celdas blancas de la columna
     * @param size representa el tamaño de la celda
     */
    public KakuroBlackCell(int i, int j, int horizontal, int vertical, int size) {
        this(i, j, size);
        this.row = horizontal;
        this.column = vertical;
        if (row != 0) jLabelRow.setText(String.valueOf(row));
        if (column != 0) jLabelColumn.setText(String.valueOf(column));
    }

    /** @brief Función que pinta la linea diagonal
     *
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (row != 0 || column != 0) {
            g.setColor(Color.WHITE);
            g.drawLine(0, 0, getWidth(), getHeight());
        }
    }

    /** @brief Getter de row
     *
     * @return la suma total del bloque de celdas blancas de la fila
     */
    public int getRow() {
        return row;
    }

    /** @brief Setter de row
     *
     * @param row representa la suma total del bloque de celdas blancas de la fila
     */
    public void setRow(int row) {
        this.row = row;
        if (row != 0) jLabelRow.setText(String.valueOf(row));
    }

    /** @brief Getter de column
     *
     * @return la suma total del bloque de celdas blancas de la columna
     */
    public int getColumn() {
        return column;
    }

    /** @brief Setter de column
     *
     * @param column representa la suma total del bloque de celdas blancas de la columna
     */
    public void setColumn(int column) {
        this.column = column;
        if (column != 0) jLabelColumn.setText(String.valueOf(column));
    }
}
