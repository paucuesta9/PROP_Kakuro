package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    @SerializedName("kakuro")
    @Expose
    private int kakuroId;
    private Kakuro kakuro;
    private Timer timer;

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
    public Game(int id, int time, int points, int kakuroId) {
        super();
        this.id = id;
        this.time = time;
        this.points = points;
        this.kakuroId = kakuroId;
        this.timer = new Timer();
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
}


