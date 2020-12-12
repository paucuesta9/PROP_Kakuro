package presentation;

import domain.classes.Exceptions.PlayerExists;
import domain.classes.Exceptions.WrongPasswordException;
import domain.classes.Player;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CtrlPlayerUI {
    private CtrlUI ctrlUI = CtrlUI.getInstance();

    private static CtrlPlayerUI ctrlPlayerUI;

    public static CtrlPlayerUI getInstance() {
        if (ctrlPlayerUI == null)
            ctrlPlayerUI = new CtrlPlayerUI();
        return ctrlPlayerUI;
    }

    public CtrlPlayerUI() {

    }

    public List<Player> getListOfPlayers(String s) {
        List<Player> p = ctrlUI.getListOfPlayers(s);
        return p;
    }

    public void login(String username, char[] password) throws FileNotFoundException, WrongPasswordException {
        ctrlUI.login(username, password);
        Utils.setConfig(ctrlUI.getConfig());
    }

    public void setConfigToPlayer(ArrayList<String> config) {
        ctrlUI.setConfigToPlayer(config);
    }

    public void signUp(String username, String password) throws PlayerExists {
        ctrlUI.signUp(username, password);
        Utils.setConfig(ctrlUI.getConfig());
    }

    public void resetConfigColors() {
        ctrlUI.resetConfigColors();
        Utils.setConfig(ctrlUI.getConfig());
    }
}
