package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("finished")
    @Expose
    private int finished;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("helps")
    @Expose
    private int helps;
    @SerializedName("points")
    @Expose
    private int points;
    @SerializedName("created")
    @Expose
    private int created;
    @SerializedName("minTimeEasy")
    @Expose
    private int minTimeEasy;
    @SerializedName("minTimeMedium")
    @Expose
    private int minTimeMedium;
    @SerializedName("minTimeDifficult")
    @Expose
    private int minTimeDifficult;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Stats() {
    }

    /**
     * 
     * @param total
     * @param created
     * @param minTimeEasy
     * @param finished
     * @param helps
     * @param minTimeMedium
     * @param minTimeDifficult
     * @param points
     */
    public Stats(int finished, int total, int helps, int points, int created, int minTimeEasy, int minTimeMedium, int minTimeDifficult) {
        super();
        this.finished = finished;
        this.total = total;
        this.helps = helps;
        this.points = points;
        this.created = created;
        this.minTimeEasy = minTimeEasy;
        this.minTimeMedium = minTimeMedium;
        this.minTimeDifficult = minTimeDifficult;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHelps() {
        return helps;
    }

    public void setHelps(int helps) {
        this.helps = helps;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getMinTimeEasy() {
        return minTimeEasy;
    }

    public void setMinTimeEasy(int minTimeEasy) {
        this.minTimeEasy = minTimeEasy;
    }

    public int getMinTimeMedium() {
        return minTimeMedium;
    }

    public void setMinTimeMedium(int minTimeMedium) {
        this.minTimeMedium = minTimeMedium;
    }

    public int getMinTimeDifficult() {
        return minTimeDifficult;
    }

    public void setMinTimeDifficult(int minTimeDifficult) {
        this.minTimeDifficult = minTimeDifficult;
    }

}
