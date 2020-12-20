
package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.awt.*;
import java.util.ArrayList;

/** @file Config.java
 @brief Clase  <em>Config</em>.
 */


/** @brief Clase Config que guarda las configuraciones del usuario
 * @author -----------
 */

public class Config {

    /**
     * colorBlackCell representa el color de las celdas negras
     */
    @SerializedName("colorBlackCell")
    @Expose
    private String colorBlackCell;
    /**
     * colorWhiteCell representa el color de las celdas blancas
     */
    @SerializedName("colorWhiteCell")
    @Expose
    private String colorWhiteCell;
    /**
     * colorNumbersBlackCell representa el color de los números de las celdas negras
     */
    @SerializedName("colorNumbersBlackCell")
    @Expose
    private String colorNumbersBlackCell;
    /**
     * colorNumbersWhiteCell representa el color de los números de las celdas blancas
     */
    @SerializedName("colorNumbersWhiteCell")
    @Expose
    private String colorNumbersWhiteCell;
    /**
     * colorCorrectCell representa el color de las celdas con valor correcto
     */
    @SerializedName("colorCorrectCell")
    @Expose
    private String colorCorrectCell;
    /**
     * colorIncorrectCell representa el color de las celdas con valor no válido
     */
    @SerializedName("colorIncorrectCell")
    @Expose
    private String colorIncorrectCell;
    /** colorSelCell representa el color de la celda seleccionada
     *
     */
    @SerializedName("colorSelCell")
    @Expose
    private String colorSelCell;
    /**
     * colorBorde representa el color del borde entre casillas y de los número a lápiz
     */
    @SerializedName("colorBorde")
    @Expose
    private String colorBorde;
    /**
     * volume representa el valor del volumen de la música
     */
    @SerializedName("volume")
    @Expose
    private int volume;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Config() {
        colorBlackCell = "#000000";
        colorWhiteCell = "#FFFFFF";
        colorNumbersBlackCell = "#FFFFFF";
        colorNumbersWhiteCell = "#000000";
        colorCorrectCell = "#00FF00";
        colorIncorrectCell = "#E53935";
        colorSelCell = "#64b5f6";
        colorBorde = "#808080";
        volume = -10;
    }

    /** @brief Creadora de Config
     * 
     * @param colorIncorrectCell color de las celdas incorrectas
     * @param volume valor del volumen de la música
     * @param colorNumbersBlackCell color de los números de las celdas negras
     * @param colorSelCell color de la celda seleccionada
     * @param colorNumbersWhiteCell color de los números de las celdas blancas
     * @param colorBlackCell color de las celdas negras
     * @param colorBorde color del borde entre casillas y de los número que se escriben la función lapiz
     * @param colorWhiteCell color de las celdas blancas
     * @param colorCorrectCell color de las celdas con valor correcto
     */
    public Config(String colorBlackCell, String colorWhiteCell, String colorNumbersBlackCell, String colorNumbersWhiteCell, String colorCorrectCell, String colorIncorrectCell, String colorSelCell, String colorBorde, int volume) {
        super();
        this.colorBlackCell = colorBlackCell;
        this.colorWhiteCell = colorWhiteCell;
        this.colorNumbersBlackCell = colorNumbersBlackCell;
        this.colorNumbersWhiteCell = colorNumbersWhiteCell;
        this.colorCorrectCell = colorCorrectCell;
        this.colorIncorrectCell = colorIncorrectCell;
        this.colorSelCell = colorSelCell;
        this.colorBorde = colorBorde;
        this.volume = volume;
    }

    /** @brief Getter del color de la celda negra
     *
     * @return string que representa el color de las celdas negras
     */
    public String getColorBlackCell() {
        return colorBlackCell;
    }

    /** @brief Setter del color de la celda negra
     *
     * @param colorBlackCell string que representa el color de las celdas negras
     */
    public void setColorBlackCell(String colorBlackCell) {
        this.colorBlackCell = colorBlackCell;
    }

    /** @brief Getter del color de las celdas blancas
     *
     * @return string que representa el color de las celdas blancas
     */
    public String getColorWhiteCell() {
        return colorWhiteCell;
    }

    /** @brief Setter del color de las celdas blancas
     *
     * @param colorWhiteCell string que representa el color de las celdas blancas
     */
    public void setColorWhiteCell(String colorWhiteCell) {
        this.colorWhiteCell = colorWhiteCell;
    }

    /** @brief Getter del color de los números de las casillas negras
     *
     * @return string que representa el color de los números de las casillas negras
     */
    public String getColorNumbersBlackCell() {
        return colorNumbersBlackCell;
    }

    /** @brief Setter del color de los números de las casillas negras
     *
     * @param colorNumbersBlackCell string que representa el color de los números de las casillas negras
     */
    public void setColorNumbersBlackCell(String colorNumbersBlackCell) {
        this.colorNumbersBlackCell = colorNumbersBlackCell;
    }

    /** @brief Getter del color de los números de las casillas blancas
     *
     * @return string que representa el color de los números de las casillas blancas
     */
    public String getColorNumbersWhiteCell() {
        return colorNumbersWhiteCell;
    }

    /** @brief Setter del color de los números de las casillas negras
     *
     * @param colorNumbersWhiteCell string que representa el color de los números de las casillas blancas
     */
    public void setColorNumbersWhiteCell(String colorNumbersWhiteCell) {
        this.colorNumbersWhiteCell = colorNumbersWhiteCell;
    }

    /** @brief Getter del color de las celdas con valor correcto
     *
     * @return string que representa el color de las celdas con valor correcto
     */
    public String getColorCorrectCell() {
        return colorCorrectCell;
    }

    /** @brief Setter del color de las celdas con valor correcto
     *
     * @param colorCorrectCell string que representa el color de las celdas con valor correcto
     */
    public void setColorCorrectCell(String colorCorrectCell) {
        this.colorCorrectCell = colorCorrectCell;
    }

    /** @brief Getter del color de las celdas con valor incorrecto
     *
     * @return string que representa el color de las celdas con valor incorrecto
     */
    public String getColorIncorrectCell() {
        return colorIncorrectCell;
    }

    /** @brief Setter del color de las celdas con valor incorrecto
     *
     * @param colorIncorrectCell string que representa el color de las celdas con valor incorrecto
     */
    public void setColorIncorrectCell(String colorIncorrectCell) {
        this.colorIncorrectCell = colorIncorrectCell;
    }

    /** @brief Getter del color de la celda seleccionada
     *
     * @return string que representa el color de la celda seleccionada
     */
    public String getColorSelCell() {
        return colorSelCell;
    }

    /** @brief Setter del color de la celda seleccionada
     *
     * @param colorSelCell string que representa el color de la celda seleccionada
     */
    public void setColorSelCell(String colorSelCell) {
        this.colorSelCell = colorSelCell;
    }

    /** @brief Getter del color del borde entre casillas y de los números a lapiz
     *
     * @return string que representa el color del borde entre casillas y de los número a lápiz
     */
    public String getColorBorde() {
        return colorBorde;
    }

    /** @brief Setter del color del borde entre casillas y de los números a lapiz
     *
     * @param colorBorde string que representa el color del borde entre casillas y de los número a lápiz
     */
    public void setColorBorde(String colorBorde) {
        this.colorBorde = colorBorde;
    }

    /** @brief Getter del valor del volumen de la música
     *
     * @return string que representa el valor del volumen de la música
     */
    public int getVolume() {
        return volume;
    }

    /** @brief Setter del valor del volumen de la música
     *
     * @param volume string que representa el valor del volumen de la música
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /** @brief Getter de todos los atributos de la clase
     *
     * @return ArrayList de todos los atributos de la clase
     */
    public ArrayList<String> getConfig() {
        ArrayList config = new ArrayList();
        config.add(colorBlackCell);
        config.add(colorWhiteCell);
        config.add(colorNumbersBlackCell);
        config.add(colorNumbersWhiteCell);
        config.add(colorCorrectCell);
        config.add(colorIncorrectCell);
        config.add(colorSelCell);
        config.add(colorBorde);
        config.add(String.valueOf(volume));
        return config;
    }

    /** @brief Pone todos los atributos de la clase con su valor por defecto
     *
     */
    public void resetColors() {
        colorBlackCell = "#000000";
        colorWhiteCell = "#FFFFFF";
        colorNumbersBlackCell = "#FFFFFF";
        colorNumbersWhiteCell = "#000000";
        colorCorrectCell = "#00FF00";
        colorIncorrectCell = "#E53935";
        colorSelCell = "#64b5f6";
        colorBorde = "#808080";
    }
}
