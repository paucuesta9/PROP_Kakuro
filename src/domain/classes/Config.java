
package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.awt.*;
import java.util.ArrayList;

public class Config {

    @SerializedName("colorBlackCell")
    @Expose
    private String colorBlackCell;
    @SerializedName("colorWhiteCell")
    @Expose
    private String colorWhiteCell;
    @SerializedName("colorNumbersBlackCell")
    @Expose
    private String colorNumbersBlackCell;
    @SerializedName("colorNumbersWhiteCell")
    @Expose
    private String colorNumbersWhiteCell;
    @SerializedName("colorCorrectCell")
    @Expose
    private String colorCorrectCell;
    @SerializedName("colorIncorrectCell")
    @Expose
    private String colorIncorrectCell;
    @SerializedName("colorSelCell")
    @Expose
    private String colorSelCell;
    @SerializedName("colorBorde")
    @Expose
    private String colorBorde;
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

    /**
     * 
     * @param colorIncorrectCell
     * @param volume
     * @param colorNumbersBlackCell
     * @param colorSelCell
     * @param colorNumbersWhiteCell
     * @param colorBlackCell
     * @param colorBorde
     * @param colorWhiteCell
     * @param colorCorrectCell
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

    public String getColorBlackCell() {
        return colorBlackCell;
    }

    public void setColorBlackCell(String colorBlackCell) {
        this.colorBlackCell = colorBlackCell;
    }

    public String getColorWhiteCell() {
        return colorWhiteCell;
    }

    public void setColorWhiteCell(String colorWhiteCell) {
        this.colorWhiteCell = colorWhiteCell;
    }

    public String getColorNumbersBlackCell() {
        return colorNumbersBlackCell;
    }

    public void setColorNumbersBlackCell(String colorNumbersBlackCell) {
        this.colorNumbersBlackCell = colorNumbersBlackCell;
    }

    public String getColorNumbersWhiteCell() {
        return colorNumbersWhiteCell;
    }

    public void setColorNumbersWhiteCell(String colorNumbersWhiteCell) {
        this.colorNumbersWhiteCell = colorNumbersWhiteCell;
    }

    public String getColorCorrectCell() {
        return colorCorrectCell;
    }

    public void setColorCorrectCell(String colorCorrectCell) {
        this.colorCorrectCell = colorCorrectCell;
    }

    public String getColorIncorrectCell() {
        return colorIncorrectCell;
    }

    public void setColorIncorrectCell(String colorIncorrectCell) {
        this.colorIncorrectCell = colorIncorrectCell;
    }

    public String getColorSelCell() {
        return colorSelCell;
    }

    public void setColorSelCell(String colorSelCell) {
        this.colorSelCell = colorSelCell;
    }

    public String getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(String colorBorde) {
        this.colorBorde = colorBorde;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

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
