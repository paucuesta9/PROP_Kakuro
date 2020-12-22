package presentation;

import domain.classes.Exceptions.CantGenerateException;
import domain.controllers.CtrlPlay;

import java.util.ArrayList;

/** @file CtrlPlayUI.java
 * @brief Clase  <em>CtrlPlayUI</em>.
 * @class CtrlPlayUI
 */

/** @brief Clase CtrlPlayUI de presentación para jugar
 * @author Judith Almoño Gómez
 */

public class CtrlPlayUI {
    private CtrlUI ctrlUI = CtrlUI.getInstance();

    private static CtrlPlayUI ctrlPlayUI;

    public static CtrlPlayUI getInstance() {
        if (ctrlPlayUI == null)
            ctrlPlayUI = new CtrlPlayUI();
        return ctrlPlayUI;
    }

    public CtrlPlayUI() {

    }

    public int help1(int posX, int posY) {
        return ctrlUI.help1(posX, posY);
    }

    public int help2(int posX, int posY) {
        return ctrlUI.help2(posX, posY);
    }

    public String getKakuro() {
        return ctrlUI.getKakuro();
    }

    public String getCorrectKakuro() {
        return ctrlUI.getCorrectKakuro();
    }

    public void setTimeToGame(int gameTime) {
        ctrlUI.setTimeToGame(gameTime);
    }

    public boolean isFinished() {
        return ctrlUI.isFinished();
    }

    public boolean checkValidity (int x, int y, int value) {
        return ctrlUI.checkValidity(x,y,value);
    }

    public void setValue(int x, int y, int value) {
        ctrlUI.setValue(x, y, value);
    }

    public int finishGame(boolean selfFinished) {
       return ctrlUI.finishGame(selfFinished);
    }

    public void startGame(int diff, int rowSize, int columnSize, boolean training) throws CantGenerateException {
        ctrlUI.startGame(diff, rowSize, columnSize, training);
    }

    public void startGame(String absolutePath, boolean training) {
        ctrlUI.startGame(absolutePath, training);
    }

    public ArrayList<ArrayList<Integer>> getStartedGames() {
        return ctrlUI.getStartedGames();
    }

    public void setGame(Integer id) {
        ctrlUI.setGame(id);
    }

    public int getTime() {
        return ctrlUI.getTime();
    }

    public ArrayList<String> getHelps() {
        return ctrlUI.getHelps();
    }
}
