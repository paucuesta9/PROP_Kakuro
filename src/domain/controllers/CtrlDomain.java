package domain.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import data.*;
import domain.classes.*;
import domain.classes.Exceptions.CantGenerateException;
import domain.classes.Exceptions.PlayerExists;
import domain.classes.Exceptions.WrongPasswordException;

import java.io.File;
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
    /**
     * Instáncia del controlador de jugar
     */
    private CtrlPlay ctrlPlay;
    /**
     * Instancia del controlador de jugador
     */
    private CtrlPlayer ctrlPlayer;
    /**
     * Instáncia de la partida actual
     */
    private Game currentGame;
    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */
    private Kakuro currentKakuro;
    /**
     * Instáncia del jugador actual
     */
    private Player currentPlayer;
    /**
     * gson con información de los jugadores
     */
    private Gson gson;

    /** @brief Creadora por defecto
     *
     * Se obtiene la instancia singleton del controlador persistencia
     */
    public CtrlDomain() {
        data = CtrlData.getInstance();
        gson = new Gson();
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /** @brief función que permite al usuario iniciar sesión
     *
     * @param username representa el nombre de usuario
     * @param password representa la contraseña del usuario
     * @throws FileNotFoundException en caso de que el usuario no exista
     * @throws WrongPasswordException en caso de que la contraseña no sea correcta
     *
     * Actualiza el jugador asociado al CtrlDomain con el usuario que corresponda al usuario y contraseña, en caso que exista y el usuario y contraseña sean correctos
     */

    public void login(String username, String password) throws FileNotFoundException, WrongPasswordException {
        ctrlPlayer = new CtrlPlayer(this);
        ctrlPlayer.login(username, password);
        currentPlayer = ctrlPlayer.getPlayer();
    }

    /** @brief función que permite al usuario cerrar sesión
     *
     * Actualiza los parámetros asociados al jugador a nulo
     */

    public void logout() {
        currentPlayer = null;
        currentGame = null;
        currentKakuro = null;
        ctrlPlayer = null;
    }

    /** @brief función que permite al usuario registrarse
     *
     * @param username representa el nombre de usuario
     * @param password representa la contraseña del usuario
     * @throws PlayerExists en caso de que ya exista un usuario con el mismo username
     *
     * En caso de que el usuario se pueda registrar, crea un usuario con el nombre y contraseña e inicia sesión
     */

    public void signUp(String username, String password) throws PlayerExists {
        ctrlPlayer = new CtrlPlayer(this);
        ctrlPlayer.signUp(username, password);
        currentPlayer = ctrlPlayer.getPlayer();
    }

    /**
     * @brief Reinicia la configuración de colores del usuario, poniendolos por defecto
     */

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
     * @param training indica si la partida es en modo entrenamiento
     */
    public void startNewGame(int difficulty, int kakuroSizeRow, int kakuroSizeColumn, boolean training) throws CantGenerateException {
        ctrlPlay = new CtrlPlay(difficulty, kakuroSizeRow, kakuroSizeColumn, this, training);
        currentGame = ctrlPlay.getGame();
        currentKakuro = ctrlPlay.getKakuro();
    }

    /** @brief Inicia una nueva partida
     *
     *  Se inicia una partida con el kakuro correspondiente al path escrito
     *
     * @param absolutePath Ruta absoluta en la que se encuentra el kakuro a jugar
     * @param training resepresenta si es en modo entrenamiento
     */
    public void startNewGame(String absolutePath, boolean training) {
        ctrlPlay = new CtrlPlay(absolutePath, this, training);
        currentGame = ctrlPlay.getGame();
        currentKakuro = ctrlPlay.getKakuro();
    }

    /** @brief Devuelve un id para una partida
     *
     * @return un id de partida diferente a los que ya tenga el usuario
     */

    public int getGameId() {
        return data.getNewGameId(currentPlayer.getUsername());
    }

    /** @brief Continua una partida existente
     *
     * @param game id de la partida que se quiere continuar
     */

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

    /** @brief Acaba la partida
     *
     * @param selfFinished representa si el kakuro se ha resuelto con el boton resolver o lo ha resuelto el usuario
     * @return los puntos de la partida
     */

    public int finishGame(boolean selfFinished) {
        return ctrlPlay.finishGame(selfFinished);
    }

    /**
     * @brief Actualiza las estadísticas del jugador
     */
    public void updateStatsPlayer() {
        ctrlPlay.updateStatsPlayer();
    }

    // OPTION 2 - CREATE VALIDATE
    /** @brief Se valida un Kakuro que sea correcto
     *
     * @return Devuelve cierto si el Kakuro es correcto, en caso contrario, devuelve falso
     */
    public boolean validate() {
        CtrlValidate.setKakuro(currentKakuro);
        if (!CtrlValidate.validate()) return false;
        else {
            CtrlValidate.setDifficulty();
            return true;
        }
    }

    // OPTION 4 - GENERATE
    /** @brief Se genera un Kakuro
     *
     */
    public void generate(int i,int j, int dif) {
        CtrlGenerate ctrlGenerate = new CtrlGenerate();
        currentKakuro = ctrlGenerate.generate(i,j,dif);
        System.out.println("Finalmente la dificultad es " + currentKakuro.getDifficulty());
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
        int id = data.saveKakuro(currentKakuro.toString(), currentKakuro.correctToString(), currentKakuro.getDifficulty(), currentKakuro.getRowSize(), currentKakuro.getColumnSize());
        JsonArray array = data.getkakuroRecord();
        JsonObject object = new JsonObject();
        object.addProperty("diff", String.valueOf(currentKakuro.getDifficulty()));
        object.addProperty("size", currentKakuro.getRowSize() + "_" + currentKakuro.getColumnSize());
        object.addProperty("id", id+".txt");
        object.addProperty("minTime", 0);
        object.addProperty("player", "");
        object.addProperty("maxPoints", 0);
        array.add(object);
        data.saveKakuroRecord(array);
        return id;
    }

    /** @brief Guarda un kakuro y obtiene la información de este
     *
     * @return Devuelve la ruta del kakuro guardado
     */

    public String saveKakuroAndGetInfo() {
        int id = saveKakuro();
        return "diff" + currentKakuro.getDifficulty() + "/" + currentKakuro.getRowSize() + "_" + currentKakuro.getColumnSize() + "/" + id + ".txt";
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

    /**
     * @brief Guarda la partida
     */
    public void saveGame() {
        data.saveKakuroGame(currentKakuro.toString(), currentPlayer.getUsername(), currentGame.getId());
        currentPlayer.addSavedGame();
        savePlayer();
    }

    /** @brief esta función sirve para organizar el ranking
     *
     * @param s define si los jugadores han de estar ordenados por puntos, victorias o número de creados.
     * @return una matriz con los jugadores ordenados según s
     */

    public String[][] getListOfPlayers(String s) {
        Ranking r;
        if(s == "puntos")  r = new PointsRanking(this);
        else if( s == "wins") r = new WinsRanking(this);
        else  r = new CreatedRanking(this);
        return r.getList(s);
    }

    /**
     * @brief Retorna la configuración
     *
     * @return la configuración del usuario asociado al Domain
     */
    public ArrayList<String> getConfig() {
        return currentPlayer.getConfigToArray();
    }

    /**
     * @brief Se le asocia al jugador actual la configuración config
     * @param config la configuración que se le asociará al usuario
     */

    public void setConfigToPlayer(ArrayList<String> config) {
        Config c = new Config(config.get(0), config.get(1), config.get(2), config.get(3), config.get(4), config.get(5), config.get(6), config.get(7), Integer.parseInt(config.get(8)));
        currentPlayer.setConfig(c);
        String playerJSON = gson.toJson(currentPlayer);
        data.savePlayer(currentPlayer.getUsername(), playerJSON);
    }

    /** @brief Guardar el tiempo en la partida
     *
     * @param gameTime representa el tiempo a guardar de la partida
     */
    public void setTimeToGame(int gameTime) {
        ctrlPlay.setTimeToGame(gameTime);
    }

    /**
     * @brief Reinicia los parametros del controlador de dominio
     */

    public void resetParameters() {
        currentKakuro = null;
        currentGame = null;
        currentPlayer.setCurrentGame(null);
    }

    /**
     * @brief Asocia el kakuro pasado por parametro al controlador
     * @param kakuro string que representa el kakuro que se asociará
     */
    public void setKakuro(String kakuro) throws NumberFormatException {
        currentKakuro = new Kakuro(kakuro);
    }

    /**
     * @brief Asocia el kakuro pasado por parametro al controlador
     * @param kakuro kakuro que representa el kakuro que se asociará
     */
    public void setKakuro(Kakuro kakuro) {
        currentKakuro = kakuro;
    }

    /**
     * @brief Guarda el jugador
     */
    public void savePlayer() {
        String playerJSON = gson.toJson(currentPlayer);
        data.savePlayer(currentPlayer.getUsername(), playerJSON);
    }

    /** Retorna al jugador actual
     *
     * @param username representa un nombre de usuario
     * @return el fichero json del usuario con nombre de usuario username
     * @throws FileNotFoundException en caso de que no exista ningun usuario con ese username
     */
    public JsonReader getUser(String username) throws FileNotFoundException {
        return data.getUser(username);
    }

    /** Retorna todos los usuarios
     *
     * @return un vector con todos los usuarios en formato json
     * @throws NullPointerException
     */
    public JsonReader[] getListOfPlayers() throws NullPointerException {
        return data.getListOfPlayers();
    }

    /** @brief Crea y guarda un nuevo jugador
     *
     * @param username nombre del usuario
     * @param playerJSON contenido del fichero asociado al jugador
     * @throws PlayerExists en caso de que ya exista un usuario con ese username
     */
    public void saveNewPlayer(String username, String playerJSON) throws PlayerExists {
        if (data.existsPlayer(username)) {
            throw new PlayerExists();
        }
        data.savePlayer(username, playerJSON);
    }

    /** @brief Retorna un jugador
     *
     * @return el jugador actual
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /** @brief Retorna de forma ordenada las estadísticas
     *
     * @return un arraylist con las estadísticas del jugador actual
     */

    public ArrayList<Integer> getStatsList() {
        return currentPlayer.getStatsInt();
    }


    /** @brief Getter de time
     *
     * @return el tiempo de la partida
     */
    public int getTime() {
        return ctrlPlay.getTime();
    }

    /** @brief Getter de las ayudas
     *
     * @return una lista con todas las ayudas utilizadas
     */
    public ArrayList<String> getHelps() {
        return ctrlPlay.getHelps();
    }

    /** @brief Resuelvo el kakuro actual
     *
     */
    public void resolve() {
        new CtrlResolve(currentKakuro);
        CtrlResolve.resolve();
    }

    /** Retorna una lista de kakuros
     *
     * @return una matriz con todos los kakuros, con su dificultad, carpeta y id.
     */
    public void getListOfKakuros() {
        data.getListOfKakuros();
    }

    /** @brief Busca un kakuro en fichero a partir de una ruta relativa
     *
     * @param s representa una ruta relativa
     * @return el kakuro que se encuentra en la ruta relativa en formato String
     */
    public String getThisKakuro(String s) {
        return data.getThisKakuro(s);
    }

    /** @brief Lee el fichero de records
     *
     * @return
     */
    public JsonArray getRecords() {
        return data.getkakuroRecord();
    }

    /** Guarda los records
     *
     * @param records records que se han de guardar
     */
    public void saveRecords(JsonArray records) {
        data.saveKakuroRecord(records);
    }

    /** @brief Comprueba que la posición x, y sea váldia
     *
     * @param x coordenada horizontal de la posición
     * @param y coordenada vertical de la posición
     * @return cierto si la posición x, y es válida, falso en caso contrario
     */
    public boolean checkCoord(int x, int y) {
        return x >= 0 && x < currentKakuro.getRowSize() && y >= 0 && y < currentKakuro.getColumnSize();
    }

    /** @brief Retorna el número de filas
     *
     * @return entero que representa el número de filas del kakuro actual
     */
    public int getRowSize() {
        return currentKakuro.getRowSize();
    }

    /** @brief Retorna el número de columnas
     *
     * @return entero que representa el número de columnas del kakuro actual
     */
    public int getColumnSize() {
        return currentKakuro.getColumnSize();
    }

    /**@brief Augmenta en uno el número de kakuros creados del jugador
     *
     */
    public void addCreatedToPlayer() {
        currentPlayer.getStats().setCreated(1);
        savePlayer();
    }
}
