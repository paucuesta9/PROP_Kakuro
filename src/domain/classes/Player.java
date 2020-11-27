
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
    @SerializedName("currentGame")
    @Expose
    private String currentGame;
    @SerializedName("savedGames")
    @Expose
    private List<Game> savedGames = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Player() {

    }

    /**
     * 
     * @param password
     * @param stats
     * @param currentGame
     * @param savedGames
     * @param username
     */
    public Player(String username, String password, Stats stats, String currentGame, List<Game> savedGames) {
        super();
        this.username = username;
        this.password = password;
        this.stats = stats;
        this.currentGame = currentGame;
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

    public String getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(String currentGame) {
        this.currentGame = currentGame;
    }

    public List<Game> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(List<Game> savedGames) {
        this.savedGames = savedGames;
    }

}
