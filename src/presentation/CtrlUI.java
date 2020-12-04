package presentation;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import data.CtrlData;
import domain.classes.Exceptions.WrongPasswordException;
import domain.classes.Player;
import domain.controllers.CtrlDomain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** @file CtrlUI.java
 @brief Clase  <em>CtrlUI</em>.
 @author Judith Almoño Gómez
 */


/** @brief Clase CtrlUI que contiene los atributos y metodos para el intercambio de atributos entre controladores
 */
public class CtrlUI {
    /**
     * cd es la instancia del CtrlDomain
     */
    private CtrlDomain cd;
    /**
     * reader es una atributo que sirve para leer de la terminal
     */
    private Scanner reader = new Scanner(System.in);
    private static CtrlUI ctrlUI;

    public static CtrlUI getInstance() {
        if (ctrlUI == null)
            ctrlUI = new CtrlUI();
        return ctrlUI;
    }

    /** @brief Creadora por defecto
     *
     * Se ejecuta automáticamente al declarar una instancia del CtrlDomain.
     *
     */
    public CtrlUI() {
        cd = new CtrlDomain();
    }

    // ****************** NUEVA VERSIÓN ****************** //

    public void run() {
        Utils.setMusic();
        Utils.loadFonts();
        Login login = new Login();
        login.drawLogin();
    }

    public int getRowSize() {
        return cd.getRowSize();
    }

    public int getColumnSize() {
        return cd.getColumnSize();
    }

    public String getKakuro() {
        return cd.getKakuroToString();
    }

    public String getCorrectKakuro() {
        return cd.getCorrectKakuroToString();
    }

    public void searchKakuro(int i, int i1, int i2) {
        try {
            cd.searchKakuro(i, i1, i2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkValidity (int x, int y, int value) {
        return cd.checkValidity(x,y,value);
    }

    public void setValue(int x, int y, int value) {
        cd.kakuroSetValue(x,y,value);
    }

    public int help1(int x, int y) {
        return cd.helpMyValue(x, y);
    }

    public int help2(int x, int y) {
        return cd.helpCorrectNumber(x,y);
    }

    public void startGame(int i, int i1, int i2) {
        cd.startNewGame(i,i1,i2);
    }

    public boolean isFinished() {
        return cd.isFinished();
    }

    public void finishGame() {
        cd.finishGame();
        //TODO: Hacer pantallita final partida
    }

    public ArrayList<ArrayList<Integer>> getStartedGames() {
        return cd.getStartedGames();
    }

    //Funcio de prova per agafar un ranking
    public List<Player> getListOfPlayers(String s) {
        List<Player> p = cd.getListOfPlayers(s);
        return p;
    }

    public void login(String username, char[] password) throws FileNotFoundException, WrongPasswordException {
        cd.login(username, String.valueOf(password));
        Utils.setConfig(cd.getConfig());
    }

    public void setConfigToPlayer(ArrayList<String> config) {
        cd.setConfigToPlayer(config);
    }

    public void signUp(String username, String password) {
        cd.signUp(username, String.valueOf(password));
        Utils.setConfig(cd.getConfig());
    }
}
