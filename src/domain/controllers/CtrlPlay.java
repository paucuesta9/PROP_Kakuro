package domain.controllers;

/** @file CtrlPlay.java
 @brief Clase  <em>CtrlPlay</em>.
 */

/** @brief Clase CtrlPlay que contiene los atributos y metodos para el intercambio de atributos entre controladores
 * @author Judith Almoño Gómez
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import domain.classes.Exceptions.CantGenerateException;
import domain.classes.Game;
import domain.classes.Kakuro;
import domain.classes.Player;
import domain.classes.WhiteCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** @brief Clase CtrlPlay que contiene los atributos y metodos para la funcionalidad de jugar
 * @author Judith Almoño Gómez
 */
public class CtrlPlay {
    /**
     * currentKakuro representa la instancia del kakuro con la que se trabaja en este momento
     */
    private Kakuro currentKakuro;
    /**
     * currentGame representa la instancia de la partida con la que se trabaja en este momento
     */
    private Game currentGame;
    /**
     * currentPlayer representa la instancia del jugador con la que se trabaja en este momento
     */
    private Player currentPlayer;

    /**
     * cd representa el controlador de dominio
     */
    private CtrlDomain cd;

    /**
     * training representa si está en modo entrenamiento
     */
    private boolean training;

    /** @brief Creadora a partir de unas características
     *
     * @param difficulty representa la dificultad del kakuro de la partida
     * @param rowSize representa el número de filas del kakuro de la partida
     * @param columnSize representa el número de columnas del kakuro de la partida
     * @param cd representa la instancia del ctrlDomain
     * @param training represneta si se empieza partida en modo entrenamiento
     */
    public CtrlPlay(int difficulty, int rowSize, int columnSize, CtrlDomain cd, boolean training) throws CantGenerateException {
        this.cd = cd;
        this.training = training;
        currentPlayer = cd.getCurrentPlayer();
        try {
            String kakuro = cd.searchKakuro(difficulty, rowSize, columnSize);
            int id = Character.getNumericValue(kakuro.charAt(0));
            this.currentKakuro = new Kakuro(kakuro.substring(2));
            currentKakuro.setId(id);
            currentKakuro.setDifficulty(difficulty);
            cd.setKakuro(currentKakuro);
        } catch (IOException e) {
            if (rowSize > 20 || columnSize > 20) throw new CantGenerateException();
            System.out.println("No se ha encontrado ningun kakuro con estas características, se está generando uno... (Puede que finalmente no sea la misma dificultad)");
            currentKakuro = CtrlGenerate.generate(rowSize,columnSize, difficulty);
            System.out.println("Finalmente la dificultad es de " + currentKakuro.getDifficulty());
            CtrlResolve ctrlResolve =  new CtrlResolve(currentKakuro);
            ctrlResolve.resolve();
            cd.setKakuro(currentKakuro);
            currentKakuro.setId(cd.saveKakuro());
        }
        int id = cd.getGameId();
        currentGame = new Game(id, 0, 0, currentKakuro.getId(), rowSize, columnSize, currentKakuro.getDifficulty(), new ArrayList<>());
        currentPlayer.setCurrentGame(currentGame);
        if (!training) currentPlayer.getStats().setTotal(1);
        setCorrectValues();
    }

    /** @brief Creadora a partir de un kakuro existente
     *
     * @param absolutePath representa el path donde se encuentra el kakuro
     * @param cd representa la instancia del CtrlDomain
     * @param training representa si esta en modo entrenamiento
     */
    public CtrlPlay(String absolutePath, CtrlDomain cd, boolean training) {
        this.cd = cd;
        this.training = training;
        currentPlayer = cd.getCurrentPlayer();
        String kakuro = null;
        try {
            kakuro = cd.getKakuro(absolutePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(absolutePath.substring(absolutePath.lastIndexOf("\\") + 1, absolutePath.lastIndexOf(".")));
        this.currentKakuro = new Kakuro(kakuro);
        currentKakuro.setId(id);
        currentKakuro.setDifficulty(Character.getNumericValue(absolutePath.charAt(absolutePath.indexOf("diff") + 4)));
        cd.setKakuro(currentKakuro);
        id = cd.getGameId();
        currentGame = new Game(id, 0, 0, currentKakuro.getId(), currentKakuro.getRowSize(), currentKakuro.getColumnSize(), currentKakuro.getDifficulty(), new ArrayList<>());
        currentPlayer.setCurrentGame(currentGame);
        currentPlayer.getStats().setTotal(1);
        setCorrectValues();
    }

    /** @brief Creadora a partir de una partida empezada
     *
     * @param game representa la partida empezada
     * @param cd representa la instancia del CtrlDomain
     */
    public CtrlPlay(int game, CtrlDomain cd) {
        this.cd = cd;
        this.training = false;
        currentPlayer = cd.getCurrentPlayer();
        currentGame = currentPlayer.getGame(game);
        currentPlayer.setCurrentGame(currentGame);
        try {
            currentGame.setKakuro(new Kakuro(cd.getKakuro("data/players/" + currentPlayer.getUsername() + "/" + game + ".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentKakuro = currentGame.getKakuro();
        currentKakuro.setDifficulty(currentGame.getDiff());
        currentKakuro.setId(currentGame.getKakuroId());
        try {
            Kakuro sol = new Kakuro(cd.getKakuro("data/solutions/diff" + currentGame.getDiff() + "/" + currentKakuro.getRowSize() + "_" + currentKakuro.getColumnSize() + "/" + currentKakuro.getId() + ".txt"));
            currentKakuro.setCorrectValues(sol);
            cd.setKakuro(currentKakuro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** @brief Busca la solución del kakuro actual y escribe el valor correcto de cada celda blanca en el atributo correctValue de la correspondiente celda blanca
     *
     */
    public void setCorrectValues() {
        try {
            Kakuro sol = new Kakuro(cd.getKakuro("data/solutions/diff" + currentKakuro.getDifficulty() + "/" + currentKakuro.getRowSize() + "_" + currentKakuro.getColumnSize() + "/" + currentKakuro.getId() + ".txt"));
            currentKakuro.setCorrectValues(sol);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return currentKakuro.checkValidity(x, y, value);
    }

    /** @brief Ayuda para el valor del usuario
     *
     * Se activa la ayuda que comprueba si el valor que el usuario ha puesto es correcto
     * @param x representa el número de fila del tablero
     * @param y representa el número de columna del tablero
     * @return 1 si el valor que el usuario ha colocado en la posición del tablero [x][y] es correcto,
     * @return 0 si es incorrecto,
     * @return -1 si la casilla [x][y] es negra,
     * @return -2 si no se ha colocado un valor
     */
    public int helpMyValue(int x, int y) {
        if (!training) updatePoints(-1);
        currentPlayer.getStats().setHelps(1);
        if (currentKakuro.getCell(x, y).isWhite()) {
            int value = ((WhiteCell) currentKakuro.getCell(x, y)).getValue();
            if (value == 0) return -2;
            else {
                int help = (((WhiteCell) currentKakuro.getCell(x, y)).getCorrectValue() == value) ? 1 : 0;
                currentGame.addHelp(x, y, help);
                return help;
            }
        }
        return -1;
    }

    /** @brief Ayuda para el valor correcto de una celda blanca
     *
     * Se la activa la ayuda que coloca el valor correcto de una celda blanca
     * @param x representa el número de fila del tablero
     * @param y representa el número de columna del tablero
     * @return cierto si es una celda blanca y falso si es una celda negra
     */
    public int helpCorrectNumber(int x, int y) {
        if (!training) updatePoints(-2);
        currentPlayer.getStats().setHelps(1);
        if (currentKakuro.getCell(x, y).isWhite()) {
            int correctNumber = ((WhiteCell) currentKakuro.getCell(x, y)).getCorrectValue();
            currentKakuro.setValue(x, y, correctNumber);
            currentGame.addHelp(x, y, 1);
            return correctNumber;
        }
        return 0;
    }

    /** @brief Actualización de los puntos de la partida
     *
     * @param points representan los puntos a añadir a los puntos de la partida
     */
    public void updatePoints(int points) {
        currentGame.setPoints(currentGame.getPoints() + points);
    }

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
        return currentKakuro.setValue(x, y, value);
    }

    /** @brief Se mira si el usuario ha completado el tablero
     *
     * @return cierto si el usuario ha colocado todos los valores de las celdas y son correctos, en caso contrario, devuelve falso
     */
    public boolean isFinished() {
        return currentKakuro.isFinished();
    }

    /** @brief Guardar el tiempo en la partida
     *
     * @param gameTime representa el tiempo a guardar de la partida
     */
    public void setTimeToGame(int gameTime) {
        currentGame.setTime(gameTime);
    }

    /** @brief Acabar partida
     *
     * @param selfFinished representa si el kakuro se ha resuelto con el boton resolver o lo ha resuelto el usuario
     * @return los puntos de la partida
     */
    public int finishGame(boolean selfFinished) {
        int points = 0;
        if (selfFinished) {
            int diff = currentKakuro.getDifficulty();
            if (diff == 1) points = 10;
            else if (diff == 2) points = 20;
            else if (diff == 3) points = 30;
            points += Integer.max(currentKakuro.getRowSize(), currentKakuro.getColumnSize()) / 2;
            updatePoints(points);
            cd.updateStatsPlayer();
            JsonArray records = cd.getRecords();
            for (int i = 0; i < records.size(); ++i) {
                JsonObject record = records.get(i).getAsJsonObject();
                diff = Integer.parseInt(record.get("diff").getAsString());
                String size = record.get("size").getAsString();
                String id = record.get("id").getAsString();
                id = id.substring(0, id.indexOf("."));
                int idInt = Integer.parseInt(id);
                String[] sizes = size.split("_");
                int rowSize = Integer.parseInt(sizes[0]);
                int columnSize = Integer.parseInt(sizes[1]);
                if (diff == currentKakuro.getDifficulty() && rowSize == currentKakuro.getRowSize() && columnSize == currentKakuro.getColumnSize() && idInt == currentKakuro.getId()) {
                    if (record.get("minTime").getAsInt() == 0 || record.get("minTime").getAsInt() > currentGame.getTime()) {
                        record.addProperty("minTime", currentGame.getTime());
                        record.addProperty("player", currentPlayer.getUsername());
                        record.addProperty("maxPoints", currentGame.getPoints());
                    }
                }
            }
            cd.saveRecords(records);
        }
        else {
            currentGame.setPoints(0);
        }
        points = currentGame.getPoints();
        cd.savePlayer();
        currentGame = null;
        currentKakuro = null;
        return points;
    }

    /** @brief Setter valor celda
     *
     * @param x representa la posición de la celda respecto las filas
     * @param y representa la posición de la celda respecto las columnas
     * @param value representa el valor de la celda a introducir
     * @return cierto si se ha insertado el valor
     */
    public boolean setValue(int x, int y, int value) {
        currentGame.removeHelp(x, y);
        return currentKakuro.setValue(x, y, value);
    }

    /** @brief Actualizar estadísticas del jugador
     *
     */
    public void updateStatsPlayer() {
        currentPlayer.getStats().setPoints(currentGame.getPoints());
        currentPlayer.getStats().setFinished(1);
    }

    /** @brief Getter de game
     *
     * @return la partida actual
     */
    public Game getGame() {
        return currentGame;
    }

    /** @brief Getter de kakuro
     *
     * @return el kakuro de la partida
     */
    public Kakuro getKakuro() {
        return currentKakuro;
    }

    /** @brief Getter de time
     *
     * @return el tiempo de la partida
     */
    public int getTime() {
        return currentGame.getTime();
    }

    /** Getter de las ayudas
     *
     * @return una lista con todas las ayudas utilizadas
     */
    public ArrayList<String> getHelps() {
        return (ArrayList<String>) currentGame.getHelps();
    }
}
