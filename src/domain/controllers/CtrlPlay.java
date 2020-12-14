package domain.controllers;

/** @file CtrlPlay.java
 @brief Clase  <em>CtrlPlay</em>.
 */

import domain.classes.Game;
import domain.classes.Kakuro;
import domain.classes.Player;
import domain.classes.WhiteCell;
import presentation.CtrlPlayUI;
import presentation.CtrlUI;

import java.io.IOException;
import java.util.ArrayList;

/** @brief Clase CtrlPlay que contiene los atributos y metodos para la funcionalidad de jugar
 * @author Judith Almoño Gómez
 */
public class CtrlPlay {
    /**
     * kakuro instancia del kakuro con el que se trabaja en este momento
     */
    private Kakuro currentKakuro;
    private Game currentGame;
    private Player currentPlayer;

    private CtrlDomain cd;

    public CtrlPlay(int difficulty, int rowSize, int columnSize, CtrlDomain cd) {
        this.cd = cd;
        currentPlayer = cd.getCurrentPlayer();
        try {
            String kakuro = cd.searchKakuro(difficulty, rowSize, columnSize);
            int id = Character.getNumericValue(kakuro.charAt(0));
            this.currentKakuro = new Kakuro(kakuro.substring(2));
            currentKakuro.setId(id);
            currentKakuro.setDifficulty(difficulty);
            cd.setKakuro(currentKakuro);
        } catch (IOException e) {
            System.out.println("No se ha encontrado ningun kakuro con estas características, se está generando uno... (Puede que finalmente no sea la misma dificultad)");
            currentKakuro = CtrlGenerate.generate(rowSize, difficulty);
            System.out.println("Finalmente la dificultad es de " + currentKakuro.getDifficulty());
            CtrlResolve ctrlResolve =  new CtrlResolve(currentKakuro);
            ctrlResolve.resolve();
            cd.setKakuro(currentKakuro);
            currentKakuro.setId(cd.saveKakuro());
        }
        int id = cd.getGameId();
        currentGame = new Game(id, 0, 0, currentKakuro.getId(), rowSize, columnSize, difficulty);
        currentPlayer.setCurrentGame(currentGame);
        currentPlayer.getStats().setTotal(1);
        setCorrectValues();
    }

    public CtrlPlay(int game, CtrlDomain cd) {
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
        updatePoints(-1);
        currentPlayer.getStats().setHelps(1);
        if (currentKakuro.getCell(x, y).isWhite()) {
            int value = ((WhiteCell) currentKakuro.getCell(x, y)).getValue();
            if (value == 0) return -2;
            else return (((WhiteCell) currentKakuro.getCell(x, y)).getCorrectValue() == value) ? 1 : 0;
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
        updatePoints(-2);
        currentPlayer.getStats().setHelps(1);
        if (currentKakuro.getCell(x, y).isWhite()) {
            int correctNumber = ((WhiteCell) currentKakuro.getCell(x, y)).getCorrectValue();
            currentKakuro.setValue(x, y, correctNumber);
            return correctNumber;
        }
        return 0;
    }

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

    public void setTimeToGame(int gameTime) {
        currentGame.setTime(gameTime);
    }

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
        }
        else {
            currentGame.setPoints(0);
        }
        points = currentGame.getPoints();
        currentGame = null;
        currentKakuro = null;
        return points;
    }

    public boolean setValue(int x, int y, int value) {
        return currentKakuro.setValue(x, y, value);
    }

    public void updateStatsPlayer() {
        currentPlayer.getStats().setPoints(currentGame.getPoints());
        currentPlayer.getStats().setFinished(1);
        cd.savePlayer();
    }

    public Game getGame() {
        return currentGame;
    }

    public Kakuro getKakuro() {
        return currentKakuro;
    }
}
