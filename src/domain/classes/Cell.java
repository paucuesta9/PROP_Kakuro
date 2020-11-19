package domain.classes;

/** @file Cell.java
 * @brief Clase  <em>Cell</em>.
 */


/** @brief Clase Cell que contiene los métodos necesarios para cualquier tipo de Celda
 * @author Pol Vallespí Soro
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
     * @return siempre falso
     */
    public boolean isWhite() {
        return false;
    }

    /** @brief Setter de value
     *
     * @param value representa el valor de la celda
     * @return siempre falso
     */
    public boolean setValue(int value) {
        return false;
    }
}
