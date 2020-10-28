package domain;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private int id;
    private int timeSec;
    private Timer timer;
    private Kakuro kakuro;

    public Game() {
        this.id = 0; //TODO: Coger el número de kakuros del usuario y ponerle uno mas.
        this.timeSec = 0;
        this.timer = new Timer();
    }

    public Game(int id, int time, Kakuro kakuro) {
        this.id = id;
        this.timeSec = time;
        this.kakuro = kakuro;
    }

    public int getId() {
        return id;
    }

    public int getTimeSec() {
        return timeSec;
    }

    public void setTimeSec(int timeSec) {
        this.timeSec = timeSec;
    }

    public Kakuro getKakuro() {
        return kakuro;
    }

    public void setKakuro(Kakuro kakuro) {
        this.kakuro = kakuro;
    }

    public void startResumeTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ++timeSec;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void pauseTimer() {
        timer.cancel();
    }
}
