package domain;

/** @file WhiteCell.java
 @brief Clase  <em>WhiteCell</em>.
 */

import java.util.ArrayList;
import java.util.List;

public class WhiteCell extends Cell {
    /**
     * value es el valor que tiene la celda [1-9]
     */
    private int value;
    /**
     * correctValue es el valor correcto de la celda
     */
    private int correctValue;
    private List<Boolean> tempValues;

    /** @brief Creadora de una celda blanca sin valor
     *
     * Se ejecuta automáticamente al declarar una celda blanca sin valor.
     * @param posX representa el número de fila del tablero
     * @param posY representa el número de columna del tablero
     */
    public WhiteCell(int posX, int posY) {
        super(posX, posY);
        this.value = 0;
        this.correctValue = 0;
        tempValues = new ArrayList<>(9);
    }

    /** @brief Creadora de una celda blanca con valor
     *
     * Se ejecuta automáticamente al declarar una celda blanca con valor.
     * @param posX representa el número de fila del tablero
     * @param posY representa el número de columna del tablero
     * @param value representa el valor que tiene la casilla
     */
    public WhiteCell(int posX, int posY, int value) {
        super(posX, posY);
        this.value = value;
        this.correctValue = 0;
        tempValues = new ArrayList<>(9);
    }

    /** @brief Getter del atributo value
     *
     * @return devuelve el valor del atributo value
     */
    public int getValue() {
        return value;
    }

    /** @brief Setter del atributo value
     *
     * @param value representa el valor que tiene la celda
     * @return devuelve siempre cierto indicando que es una celda blanca
     */
    @Override
    public boolean setValue(int value) {
        this.value = value;
        return true;
    }

    /** @brief Getter del atributo CorrectValue
     *
     * @return devuelve el valor del atributo CorrectValue
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

    public List<Boolean> getTempValues() {
        return this.tempValues;
    }

    public Boolean getTempValue(int index) {
        return this.tempValues.get(index);
    }

    public void setTempValues(List<Boolean> tempValues) {
        this.tempValues = tempValues;
    }

    public void setTempValue(int index, Boolean value) {
        this.tempValues.set(index, value);
    }

    /** @brief Función que indica que la celda es blanca
     *
     * @return devuelve siempre cierto indicando que es una celda blanca
     */
    @Override
    public boolean isWhite() {
        return true;
    }
}
