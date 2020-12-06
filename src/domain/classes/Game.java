package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("points")
    @Expose
    private int points;
    @SerializedName("kakuroId")
    @Expose
    private int kakuroId;
    private transient Kakuro kakuro;
    @SerializedName("rowSize")
    @Expose
    private int rowSize;
    @SerializedName("columnSize")
    @Expose
    private int columnSize;
    @SerializedName("diff")
    @Expose
    private int diff;

    /**
     * No args constructor for use in serialization
     *
     */
    public Game() {
    }

    /**
     *
     * @param id
     * @param time
     * @param points
     * @param kakuroId
     */
    public Game(int id, int time, int points, int kakuroId, int rowSize, int columnSize, int diff) {
        super();
        this.id = id;
        this.time = time;
        this.points = points;
        this.kakuroId = kakuroId;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.diff = diff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getKakuroId() {
        return kakuroId;
    }

    public void setKakuroId(int kakuroId) {
        this.kakuroId = kakuroId;
    }

    public Kakuro getKakuro() {
        return kakuro;
    }

    public void setKakuro(Kakuro kakuro) {
        this.kakuro = kakuro;
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

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public ArrayList<Integer> gameToArrayList () {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(id);
        list.add(time);
        list.add(diff);
        list.add(points);
        list.add(kakuroId);
        list.add(rowSize);
        list.add(columnSize);
        return list;
    }
}


