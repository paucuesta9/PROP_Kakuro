package domain;/* Pau Cuesta Arcos */

import java.util.ArrayList;
import java.util.List;

public class WhiteCell extends Cell {
    private int value;
    private int correctValue;
    private List<Boolean> tempValues;

    public WhiteCell(int posX, int posY) {
        super(posX, posY);
        this.value = 0;
        this.correctValue = 0;
        tempValues = new ArrayList<>(9);
    }

    public WhiteCell(int posX, int posY, int value) {
        super(posX, posY);
        this.value = value;
        this.correctValue = 0;
        tempValues = new ArrayList<>(9);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean setValue(int value) {
        this.value = value;
        return true;
    }

    public int getCorrectValue() {
        return correctValue;
    }

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

    @Override
    public boolean isWhite() {
        return true;
    }
}
