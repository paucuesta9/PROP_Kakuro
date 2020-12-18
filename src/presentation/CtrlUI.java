package presentation;

import domain.classes.Exceptions.PlayerExists;
import domain.classes.Exceptions.WrongPasswordException;
import domain.controllers.CtrlDomain;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    private JFrame mainFrame = new JFrame();
    private static CtrlUI ctrlUI;


    public static CtrlUI getInstance() {
        if (ctrlUI == null)
            ctrlUI = new CtrlUI();
        return ctrlUI;
    }

    private Login login;
    private Register register;
    private Main main;
    private AskNewContinue askNewContinue;
    private LoadGame loadGame;
    private NewGame newGame;
    private Play play;
    private Create create;
    private Generate generate;
    private AskSave askSave;
    private Rankings rankings;
    private ShowKakuro showKakuro;
    private Records records;

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
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200,800);
        mainFrame.setResizable(false);
        mainFrame.setIconImage(Utils.getLogo().getImage());
        Utils.center(mainFrame);
        toLogin();
    }

    public void toLogin() {
        login = new Login();
        mainFrame.setTitle("Login");
        mainFrame.setContentPane(login.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toRegister() {
        register = new Register();
        mainFrame.setTitle("Register");
        mainFrame.setContentPane(register.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toMain() {
        main = new Main();
        mainFrame.setTitle("Main");
        mainFrame.setContentPane(main.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toAskNewContinue() {
        askNewContinue = new AskNewContinue();
        mainFrame.setTitle("New game or continue");
        mainFrame.setContentPane(askNewContinue.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toNewGame(boolean training) {
        newGame = new NewGame(training);
        mainFrame.setTitle("New Game");
        mainFrame.setContentPane(newGame.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toLoadGame() {
        loadGame = new LoadGame();
        mainFrame.setTitle("LoadGame");
        mainFrame.setContentPane(loadGame.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toPlay(String kakuro, boolean training) {
        play = new Play(kakuro, training);
        mainFrame.setTitle("Play");
        mainFrame.setContentPane(play.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toCreate() {
        create = new Create();
        mainFrame.setTitle("Create");
        mainFrame.setContentPane(create.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toGenerate() {
        generate = new Generate();
        mainFrame.setTitle("Generate");
        mainFrame.setContentPane(generate.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toRankings() {
        rankings = new Rankings();
        mainFrame.setTitle("Ranking");
        mainFrame.setContentPane(rankings.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toShowKakuro(String s) {
        showKakuro = new ShowKakuro(s);
        mainFrame.setTitle("ShowKakuro");
        mainFrame.setContentPane(showKakuro.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toAskSave(String question, String boardKakuro, int option) {
        askSave = new AskSave(question, boardKakuro, option);
        mainFrame.setTitle("Save");
        mainFrame.setContentPane(askSave.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toRecords() {
        records = new Records();
        mainFrame.setTitle("Records");
        mainFrame.setContentPane(records.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public String getKakuro() {
        return cd.getKakuroToString();
    }

    public String getCorrectKakuro() {
        return cd.getCorrectKakuroToString();
    }

    public void findKakuro(String path) {
        try {
            String kakuro = cd.getKakuro(path);
            cd.setKakuro(kakuro);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void startGame(String absolutePath) {
        cd.startNewGame(absolutePath);
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

    public String[][] getListOfPlayers(String s) {
        return cd.getListOfPlayers(s);
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

    public ArrayList<Integer> getStatsList() {
        return cd.getStatsList();
    }

    public int getTime() {
        return cd.getTime();
    }

    public ArrayList<String> getHelps() {
        return cd.getHelps();
    }

    public void resolve() {
        cd.resolve();
    }

    public String getThisKakuro(String s){
        return cd.getThisKakuro(s);
    }

    public String[][] getListOfKakuros() {
        return cd.getListOfKakuros();
    }
}
