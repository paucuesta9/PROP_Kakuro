package domain;

/** @file BlackCell.java
 @brief Clase  <em>BlackCell</em>.
 */

public class Cell {

    /** @brief Creadora de una celda
     *
     * Se ejecuta automáticamente al declarar una celda.
     */
    public Cell() {

    }

    /** @brief Comprobadora del tipo de celda
     *
     * @return devuelve siempre falso
     */
    public boolean isWhite() {
        return false;
    }

    /** @brief Setter de value
     *
     * @param value representa el valor de la celda
     * @return devuelve siempre falso
     */
    public boolean setValue(int value) {
        return false;
    }
}
