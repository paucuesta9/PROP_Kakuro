package domain;

import java.util.ArrayList;

public class Player {
    private String username;
    private String password;
    private Game currentGame;
    private ArrayList<Integer> startedGames;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
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

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public ArrayList<Integer> getStartedGames() {
        return startedGames;
    }

    public void setStartedGames(ArrayList<Integer> startedGames) {
        this.startedGames = startedGames;
    }

    public void addStartedGame(int id) {
        this.startedGames.add(id);
    }

    public void deletedStartedGame(int id) {
        this.startedGames.remove(id);
    }
}

