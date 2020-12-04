package domain.classes;

import java.util.ArrayList;
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
    private List<Game> savedGames = null;
    @SerializedName("config")
    @Expose
    private Config config;

    /**
     * No args constructor for use in serialization
     *
     */
    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.stats = new Stats();
        this.savedGames = new ArrayList<Game>();
    }

    /**
     *
     * @param password
     * @param stats
     * @param savedGames
     * @param config
     * @param username
     */
    public Player(String username, String password, Stats stats, int currentGame, List<Game> savedGames, Config config) {
        super();
        this.username = username;
        this.password = password;
        this.stats = stats;
        this.currentGame = currentGame;
        this.savedGames = savedGames;
        this.config = config;
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

    public List<Game> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(List<Game> savedGames) {
        this.savedGames = savedGames;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public ArrayList<String> getConfigToArray() {
        return config.getConfig();
    }
}
