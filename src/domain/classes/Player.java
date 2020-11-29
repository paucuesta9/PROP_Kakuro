package domain.classes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    private int currentGame;
    @SerializedName("savedGames")
    @Expose
    private List<Integer> savedGames = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @param password
     * @param stats
     * @param savedGames
     * @param username
     */
    public Player(String username, String password, Stats stats, List<Integer> savedGames) {
        super();
        this.username = username;
        this.password = password;
        this.stats = stats;
        this.savedGames = savedGames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(int currentGame) {
        this.currentGame = currentGame;
    }

    public List<Integer> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(List<Integer> savedGames) {
        this.savedGames = savedGames;
    }

}
