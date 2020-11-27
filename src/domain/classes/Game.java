
package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

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
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("points")
    @Expose
    private int points;

    /**
     * No args constructor for use in serialization
     *
     */
    public Game() {
    }

    /**
     *
     * @param columnSize
     * @param difficult
     * @param id
     * @param time
     * @param rowSize
     * @param points
     */
    public Game(int id, int rowSize, int columnSize, int difficult, int time, int points) {
        super();
        this.id = id;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.difficult = difficult;
        this.time = time;
        this.points = points;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}

