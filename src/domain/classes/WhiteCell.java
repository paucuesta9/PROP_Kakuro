package domain.classes;

import java.util.ArrayList;
import java.util.List;

/** @file WhiteCell.java
 @brief Clase  <em>WhiteCell</em>.
 */

/** @brief Clase WhiteCell que identifica y contiene los atributos necesarios de una celda blanca
 * @author Pau Cuesta Arcos
 */
public class WhiteCell extends Cell {
    /**
     * value es el valor que tiene la celda [1-9]
     */
    private int value;
    /**
     * correctValue es el valor correcto de la celda
     */
    private int correctValue;

    /** @brief Creadora de una celda blanca sin valor
     *
     * Se ejecuta automáticamente al declarar una celda blanca sin valor.
     */
    public WhiteCell() {
        super();
        this.value = 0;
        this.correctValue = 0;
    }

    /** @brief Creadora de una celda blanca con valor
     *
     * Se ejecuta automáticamente al declarar una celda blanca con valor.
     * @param value representa el valor que tiene la casilla
     */
    public WhiteCell(int value) {
        super();
        this.value = value;
        this.correctValue = 0;
    }

    /** @brief Getter del atributo value
     *
     * @return el valor del atributo value
     */
    public int getValue() {
        return value;
    }

    /** @brief Setter del atributo value
     *
     * @param value representa el valor que tiene la celda
     * @return siempre cierto indicando que es una celda blanca
     */
    @Override
    public boolean setValue(int value) {
        this.value = value;
        return true;
    }

    /** @brief Getter del atributo CorrectValue
     *
     * @return el valor del atributo CorrectValue
     */
    public int getCorrectValue() {
        return correctValue;
    }

    /** @brief Setter del atributo CorrectValue
     *
     * @param correctValue representa el valor correcto de la celda
     */
    public void setCorrectValue(int correctValue) {
        this.correctValue = correctValue;
    }

    /** @brief Función que indica que la celda es blanca
     *
     * @return siempre cierto indicando que es una celda blanca
     */
    @Override
    public boolean isWhite() {
        return true;
    }
}
