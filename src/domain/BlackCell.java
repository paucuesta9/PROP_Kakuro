package domain;

/** @file BlackCell.java
 @brief Clase  <em>BlackCell</em>.
 */

public class BlackCell extends Cell{
    /**
     * column es el valor de la suma de la columna
     */
    private int column;

    /**
     * row es el valor de la suma de la fila
     */
    private int row;

    /** @brief Creadora de celda negra sin valores.
     *
     * Se ejecuta automáticamente al declarar una celda negra sin valores.
     * @param posX representa el número de fila del tablero
     * @param posY representa el número de columna del tablero
     */
    public BlackCell(int posX, int posY) {
        super(posX, posY);
        this.column = 0;
        this.row = 0;
    }

    /** @brief Creadora de celda negra con valores.
     *
     * Se ejecuta automáticamente al declarar una celda negra con valores.
     * @param posX representa el número de fila del tablero
     * @param posY representa el número de columna del tablero
     * @param column representa el valor de la suma de la columna
     * @param row representa el valor de la suma de la fila
     */
    public BlackCell(int posX, int posY, int column, int row) {
        super(posX, posY);
        this.column = column;
        this.row = row;
    }

    /** @brief Getter del atributo vertical
     *
     * @return devuelve el valor del atributo column
     */
    public int getColumn() {
        return column;
    }

    /** @brief Getter del atributo row
     *
     * @return devuelve el valor del atributo row
     */
    public int getRow() {
        return row;
    }

    /** @brief Setter del atributo column
     *
     * @param column representa el valor de la suma de la columna
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /** @brief Setter del atributo row
     *
     * @param row representa el valor de la suma de la fila
     */
    public void setRow(int row) {
        this.row = row;
    }

}
