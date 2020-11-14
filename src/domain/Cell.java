package domain;

/** @file BlackCell.java
 @brief Clase  <em>BlackCell</em>.
 */

public class Cell {
    /**
     * posX es el número de fila del tablero
     */
    private int posX;
    /**
     * posY es el número de columna del tablero
     */
    private int posY;

    /** @brief Creadora de una celda
     *
     * Se ejecuta automáticamente al declarar una celda sin valor.
     * @param posX
     * @param posY
     */
    public Cell(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /** @brief Comprobadora del tipo de celda
     *
     * @return devuelve siempre falso
     */
    public boolean isWhite() {return false;}

    public void setPosX(int posX) { this.posX = posX; }

    public int getPosX() { return posX; }

    public void setPosY(int posY) { this.posY = posY; }

    public int getPosY() { return posY; }

    /** @brief Setter de value
     *
     * @param value representa el valor de la celda
     * @return devuelve siempre falso
     */
    public boolean setValue(int value) { return false; }
}
