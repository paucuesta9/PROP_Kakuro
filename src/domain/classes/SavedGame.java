
package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SavedGame {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("rowSize")
    @Expose
    private int rowSize;
    @SerializedName("columnSize")
    @Expose
    private int columnSize;
    @SerializedName("difficult")
    @Expose
    private int difficult;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SavedGame() {
    }

    /**
     * 
     * @param columnSize
     * @param difficult
     * @param id
     * @param rowSize
     */
    public SavedGame(int id, int rowSize, int columnSize, int difficult) {
        super();
        this.id = id;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.difficult = difficult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

}
