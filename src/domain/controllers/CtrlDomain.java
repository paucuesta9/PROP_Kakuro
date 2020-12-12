package domain.controllers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import data.*;
import domain.classes.*;
import domain.classes.Exceptions.PlayerExists;
import domain.classes.Exceptions.WrongPasswordException;
import domain.controllers.drivers.CtrlPlayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** @file CtrlDomain.java
 @brief Clase  <em>CtrlDomain</em>.
 */


/** @brief Clase CtrlDomain que contiene los atributos y metodos para el intercambio de atributos entre controladores
 * @author Pau Cuesta Arcos
 */
public class CtrlDomain {

    /**
     * Instáncia del cotrolador persistencia
     */
    private CtrlData data;
    private CtrlPlay ctrlPlay;
    private CtrlPlayer ctrlPlayer;
    private Game currentGame;
    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */
    private Kakuro currentKakuro;
    private Player currentPlayer;
    private Gson gson;

    /** @brief Creadora por defecto
     *
     * Se obtiene la instancia singleton del controlador persistencia
     */
    public CtrlDomain() {
        data = CtrlData.getInstance();
        gson = new Gson();
    }

    public void login(String username, String password) throws FileNotFoundException, WrongPasswordException {
        ctrlPlayer = new CtrlPlayer(this);
        ctrlPlayer.login(username, password);
        currentPlayer = ctrlPlayer.getPlayer();
    }

    public void logout() {
        currentPlayer = null;
        currentGame = null;
        currentKakuro = null;
        ctrlPlayer = null;
    }

    public void signUp(String username, String password) throws PlayerExists {
        ctrlPlayer = new CtrlPlayer(this);
        ctrlPlayer.signUp(username, password);
        currentPlayer = ctrlPlayer.getPlayer();
    }

    public void resetConfigColors() {
        currentPlayer.resetCongifColors();
    }

    // OPTION 1 - PLAY

    /** @brief Inicia una nueva partida
     *
     * Se busca un tablero que cumpla las condiciones y llama al CtrlPlay para iniciar la partida
     *
     * @param difficulty dificultad del tablero, entre 1 y 3
     * @param kakuroSizeRow número de filas del tablero
     * @param kakuroSizeColumn número de columnas del tablero
     *
     */
    public void startNewGame(int difficulty, int kakuroSizeRow, int kakuroSizeColumn) {
        ctrlPlay = new CtrlPlay(difficulty, kakuroSizeRow, kakuroSizeColumn, this);
        currentGame = ctrlPlay.getGame();
        currentKakuro = ctrlPlay.getKakuro();
    }

    public int getGameId() {
        return data.getNewGameId(currentPlayer.getUsername());
    }

    public void setGame(int game) {
        ctrlPlay = new CtrlPlay(game, this);
        currentGame = ctrlPlay.getGame();
        currentKakuro = ctrlPlay.getKakuro();
    }

    /** @brief Se mira si el usuario ha completado el tablero
     *
     * @return cierto si el usuario ha colocado todos los valores de las celdas y son correctos, en caso contrario, devuelve falso
     */
    public boolean isFinished() {
        return ctrlPlay.isFinished();
    }

    /** @brief Comprueba la validez de un número añadido a una celda blanca
     *
     * Comprueba si el valor añadido a la celda blanca cumple las condiciones de la fila y de la columna
     * @param x representa el número de la fila del tablero
     * @param y representa el número de la columna del tablero
     * @param value representa el valor añadido a la celda blanca
     * @return devuelve cierto si se cumplen las condiciones tanto en la fila como en la columna y falso si no se cumplen en la fila, la columna o ambas
     */
    public boolean checkValidity(int x, int y, int value) {
        return ctrlPlay.checkValidity(x, y, value);
    }

    /** @brief Se activa la ayuda que mira si el valor que el usuario ha puesto es correcto
     *
     * @param x Posición de fila de la celda
     * @param y Posición de columna de la celda
     * @return 1 si el valor que el usuario ha colocado en la posición del tablero [x][y] es correcto,
     * @return 0 si es incorrecto,
     * @return -1 si la casilla [x][y] es negra,
     * @return -2 si no se ha colocado un valor
     */
    public int helpMyValue(int x, int y) {
        return ctrlPlay.helpMyValue(x, y);
    }

    /** @brief Se activa la ayuda que coloca el valor correcto de una celda
     *
     * @param x Posición de fila de la celda
     * @param y Posición de columna de la celda
     * @return Devuelve cierto si la posición [x][y] es una celda blanca, en caso contrario, devuelve falso
     */
    public int helpCorrectNumber(int x, int y) {
        return ctrlPlay.helpCorrectNumber(x, y);
    }

    public int finishGame(boolean selfFinished) {
        return ctrlPlay.finishGame(selfFinished);
    }

    public void updateStatsPlayer() {
        ctrlPlay.updateStatsPlayer();
    }

    // OPTION 2 - CREATE VALIDATE
    /** @brief Se valida un Kakuro que sea correcto
     *
     * @return Devuelve cierto si el Kakuro es correcto, en caso contrario, devuelve falso
     */
    public boolean validate() {
        int [] res = new int[1];
        res[0] = 0;
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        CtrlValidate.setKakuro(currentKakuro);
        CtrlValidate.validate(0,0, 0, vec, res);
        if (res[0]!=1) return false;
        else {
            CtrlValidate.setDifficulty();
            return true;
        }
    }

    // OPTION 4 - GENERATE
    /** @brief Se genera un Kakuro
     *
     */
    public void generate(int size, int dif) {
        CtrlGenerate ctrlGenerate = new CtrlGenerate();
        currentKakuro = ctrlGenerate.generate(size,dif);
        System.out.println("Finalmente la dificultad es de " + currentKakuro.getDifficulty());
    }

    /* GETTERS AND SETTERS (CLASSES) */
    /** @brief Setter de valor en una celda
     *
     * Se setea el valor en la celda de la posición [x][y] con el valor "value" si la celda es blanca
     *
     * @param x Posición de fila de una celda
     * @param y Posición de columna de una celda
     * @param value Valor que el usuario quiere colocar
     * @return Devuelve cierto si la celda es blanco, en caso contrario, devuelve falso
     */
    public boolean kakuroSetValue(int x, int y, int value) {
        return ctrlPlay.setValue(x, y, value);
    }

    /** @brief Getter del kakuro en formato String
     *
     * @return Devuelve un String con el kakuro en formato para fichero o para printear en consola
     */
    public String getKakuroToString() {
        return currentKakuro.toString();
    }

    /** @brief Getter del kakuro correcto en formato String
     *
     * @return Devuelve un String con el kakuro correcto (resuelto) en formato para fichero o para printear en consola
     */
    public String getCorrectKakuroToString() {
        return currentKakuro.correctToString();
    }

    /* READ AND WRITE (FILE) */
    /** @bief Busca un Kakuro
     *
     * Busca un kakuro que coincida con las condiciones de dificultad y tamaño
     *
     * @param difficulty Dificultad del Kakuro
     * @param kakuroSizeRow tamaño de filas del tablero
     * @param kakuroSizeColumn tamaño de columnas del tablero
     */
    public String searchKakuro(int difficulty, int kakuroSizeRow, int kakuroSizeColumn) throws IOException, IndexOutOfBoundsException, NumberFormatException {
        return data.searchKakuro(difficulty, kakuroSizeRow, kakuroSizeColumn);
    }

    /** @brief Getter de Kakuro
     *
     * Se obtiene un Kakuro y lo asigna a correctKakuro
     *
     * @param filePath Ruta relativa al fichero con el Kakuro que se busca
     */
    public void getKakuroAndAssign(String filePath) throws IOException, IndexOutOfBoundsException, NumberFormatException {
        this.currentKakuro = new Kakuro(data.getKakuro(filePath));
    }

    /** @brief Getter de Kakuro
     *
     * Se obtiene un Kakuro
     *
     * @param filePath Ruta relativa al fichero con el Kakuro que se busca
     */
    public String getKakuro(String filePath) throws IOException, IndexOutOfBoundsException, NumberFormatException {
        return data.getKakuro(filePath);
    }

    /** @brief Guarda un Kakuro en un fichero
     *
     */
    public int saveKakuro() {
        return data.saveKakuro(currentKakuro.toString(), currentKakuro.correctToString(), currentKakuro.getDifficulty(), currentKakuro.getRowSize(), currentKakuro.getColumnSize());
    }

    /** @brief Getter de partidas empezadas por el usuario actual
     *
     * @return Devuelve una lista de listas de enteros con los atributos de todas las partidas que tiene empezadas el usuario
     */
    public ArrayList<ArrayList<Integer>> getStartedGames() {
        ArrayList<Game> listGames = (ArrayList<Game>) currentPlayer.getSavedGames();
        ArrayList<ArrayList<Integer>> startedGames = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < listGames.size(); ++i) {
            startedGames.add(listGames.get(i).gameToArrayList());
        }
        return startedGames;
    }

    public void saveGame() {
        data.saveKakuroGame(currentKakuro.toString(), currentPlayer.getUsername(), currentGame.getId());
        currentPlayer.addSavedGame();
        savePlayer();
    }

    public List<Player> getListOfPlayers(String s) {
        Ranking r;
        if(s == "puntos")  r = new PointsRanking();
        else if( s == "wins") r = new WinsRanking();
        else  r = new CreatedRanking();
        List<Player> p = r.getList(s);
        return p;
    }

    public ArrayList<String> getConfig() {
        return currentPlayer.getConfigToArray();
    }

    public void setConfigToPlayer(ArrayList<String> config) {
        Config c = new Config(config.get(0), config.get(1), config.get(2), config.get(3), config.get(4), config.get(5), config.get(6), config.get(7), Integer.parseInt(config.get(8)));
        currentPlayer.setConfig(c);
        String playerJSON = gson.toJson(currentPlayer);
        data.savePlayer(currentPlayer.getUsername(), playerJSON);
    }

    public void setTimeToGame(int gameTime) {
        ctrlPlay.setTimeToGame(gameTime);
    }

    public void resetParameters() {
        currentKakuro = null;
        currentGame = null;
        currentPlayer.setCurrentGame(null);
    }

    public void setKakuro(String kakuro) {
        currentKakuro = new Kakuro(kakuro);
    }

    public void savePlayer() {
        String playerJSON = gson.toJson(currentPlayer);
        data.savePlayer(currentPlayer.getUsername(), playerJSON);
    }

    public JsonReader getUser(String username) throws FileNotFoundException {
        return data.getUser(username);
    }

    public void saveNewPlayer(String username, String playerJSON) throws PlayerExists {
        if (data.existsPlayer(username)) {
            throw new PlayerExists();
        }
        data.savePlayer(username, playerJSON);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
