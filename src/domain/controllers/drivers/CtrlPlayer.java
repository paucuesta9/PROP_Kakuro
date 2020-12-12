package domain.controllers.drivers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domain.classes.Exceptions.PlayerExists;
import domain.classes.Exceptions.WrongPasswordException;
import domain.classes.Player;
import domain.controllers.CtrlDomain;
import presentation.Play;

import java.io.FileNotFoundException;

public class CtrlPlayer {

    private CtrlDomain cd;
    private Player currentPlayer;
    private Gson gson;

    public CtrlPlayer(CtrlDomain cd) {
        this.cd = cd;
        this.gson = new Gson();
    }

    public void login(String username, String password) throws FileNotFoundException, WrongPasswordException {
        JsonReader reader = cd.getUser(username);
        currentPlayer = gson.fromJson(reader, Player.class);
        if (!password.equals(currentPlayer.getPassword())) {
            currentPlayer = null;
            throw new WrongPasswordException();
        }
    }

    public void signUp(String username, String password) throws PlayerExists {
        currentPlayer = new Player(username, password);
        String playerJSON = gson.toJson(currentPlayer);
        cd.saveNewPlayer(username, playerJSON);
    }

    public Player getPlayer() {
        return currentPlayer;
    }
}
