package presentation;

import domain.classes.Exceptions.PlayerExists;
import domain.classes.Exceptions.WrongPasswordException;
import domain.classes.Player;
import domain.controllers.CtrlDomain;

import javax.swing.*;
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
        Utils.loadFonts();
        Login login = new Login();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        login.drawLogin(frame);
    }

    public String getKakuro() {
        return cd.getKakuroToString();
    }

    public String getCorrectKakuro() {
        return cd.getCorrectKakuroToString();
    }

    public void searchKakuro(int diff, int rowSize, int columnSize) {
        try {
            cd.searchKakuro(diff, rowSize, columnSize);
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

    public void startGame(int diff, int rowSize, int columnSize) {
        cd.startNewGame(diff, rowSize, columnSize);
    }

    public boolean isFinished() {
        return cd.isFinished();
    }

    public int finishGame(boolean selfFinished) {
        return cd.finishGame(selfFinished);
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
    }

    public void setConfigToPlayer(ArrayList<String> config) {
        cd.setConfigToPlayer(config);
    }

    public void signUp(String username, String password) throws PlayerExists {
        cd.signUp(username, String.valueOf(password));
    }

    public void resetConfigColors() {
        cd.resetConfigColors();
    }

    public void generate(int i, int j, int diff) {
        cd.generate(i, diff);
    }

    public void save() {
        cd.saveKakuro();
    }

    public void saveGame() {
        cd.saveGame();
    }

    public void setTimeToGame(int gameTime) {
        cd.setTimeToGame(gameTime);
    }

    public void resetParameters() {
        cd.resetParameters();
    }

    public void setGame(int id) {
        cd.setGame(id);
    }

    public void setKakuro(String kakuro) {
        cd.setKakuro(kakuro);
    }

    public boolean validate() {
        return cd.validate();
    }

    public ArrayList<String> getConfig() {
        return cd.getConfig();
    }
}
