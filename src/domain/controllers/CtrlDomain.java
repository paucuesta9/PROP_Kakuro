package domain.controllers;

import data.CtrlData;
import domain.classes.*;

import java.io.IOException;
import java.util.ArrayList;

/** @file CtrlDomain.java
 @brief Clase  <em>CtrlDomain</em>.
 */


/** @brief Clase CtrlDomain que contiene los atributos y metodos para el intercambio de atributos entre controladores
 */
public class CtrlDomain {

    /**
     * Instáncia del cotrolador persistencia
     */
    private CtrlData data;
    //private Game currentGame;
    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */
    private Kakuro currentKakuro;
    //private Player currentPlayer;

    /** @brief Creadora por defecto
     *
     * Se obtiene la instancia singleton del controlador persistencia
     */
    public CtrlDomain() {
        data = CtrlData.getInstance();
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
        try {
            searchKakuro(difficulty, kakuroSizeRow, kakuroSizeColumn);
        } catch (IOException e) {
            currentKakuro = CtrlGenerate.generate(kakuroSizeRow);
        }
        CtrlPlay.startGame(currentKakuro);
        //currentGame = new Game(0,0, currentKakuro);
        //currentGame.startResumeTimer();
    }

    /** @brief
     *
     * @param game
     */
//    public void setGame(int game) {
//        //TODO: Leer game
//    }

    /** @brief Se mira si el usuario ha completado el tablero
     *
     * @return cierto si el usuario ha colocado todos los valores de las celdas y son correctos, en caso contrario, devuelve falso
     */
    public boolean isFinished() {
        return currentKakuro.isFinished();
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
        return CtrlPlay.helpMyValue(x, y);
    }

    /** @brief Se activa la ayuda que coloca el valor correcto de una celda
     *
     * @param x Posición de fila de la celda
     * @param y Posición de columna de la celda
     * @return Devuelve cierto si la posición [x][y] es una celda blanca, en caso contrario, devuelve falso
     */
    public boolean helpCorrectNumber(int x, int y) {
        return CtrlPlay.helpCorrectNumber(x, y);
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
        else return true;
    }

    // OPTION 3 - RESOLVE
    /** @brief Se resuelve un Kakuro
     *
     */
    public void resolve() {
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        CtrlResolve.setKakuro(currentKakuro);
        CtrlResolve.resolve(0,0, 0, vec);
    }

    // OPTION 4 - GENERATE
    /** @brief Se genera un Kakuro
     *
     */
    public void generate(int size) {
        currentKakuro = CtrlGenerate.generate(size);
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
        return currentKakuro.setValue(x, y, value);
    }

    /** @brief Getter del tamaño de fila
     *
     * @return Devuelve un entero con el tamaño de fila del tablero
     */
    public int getRowSize() {
        return currentKakuro.getRowSize();
    }

    /** @brief Getter del tamaño de columna
     *
     * @return Devuelve un entero con el tamaño de columna del tablero
     */
    public int getColumnSize() {
        return currentKakuro.getColumnSize();
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
    public void searchKakuro(int difficulty, int kakuroSizeRow, int kakuroSizeColumn ) throws IOException, IndexOutOfBoundsException, NumberFormatException {
        this.currentKakuro = new Kakuro(data.searchKakuro(difficulty, kakuroSizeRow, kakuroSizeColumn));
    }

    /** @brief Getter de Kakuro
     *
     * Se obtiene un Kakuro y se asigna al currentKakuro a partir de una ruta relativa
     *
     * @param filePath Ruta relativa al fichero con el Kakuro que se busca
     */
    public void getKakuro(String filePath) throws IOException, IndexOutOfBoundsException, NumberFormatException {
        this.currentKakuro = new Kakuro(data.getKakuro(/*"../" + */ filePath));
    }

    /** @brief Getter de partidas empezadas por el usuario actual
     *
     * @return Devuelve una lista de Strings con los identificadores de las partidas que tiene empezadas el usuario
     */
//    public ArrayList<Integer> getStartedGames() {
//        return currentPlayer.getStartedGames();
//    }

    /** @brief Guarda un Kakuro en un fichero
     *
     */
    public void saveKakuro() {
        data.saveKakuro(currentKakuro.toString(), currentKakuro.getDifficulty(), currentKakuro.getRowSize(), currentKakuro.getColumnSize());
    }

    /** @brief Comprobadora de coordenadas
     *
     * Comprueba si las coordenadas dadas se encuentran dentro del tablero
     * @param x representa el número de la fila del tablero
     * @param y representa el número de la columna del tablero
     * @return cierto si las coordenadas son correctas y falso si las coordenadas no lo son
     */
    public boolean checkCoord(int x, int y) {
        return (x < getRowSize() || x >= 0 || y < getColumnSize() || y >= 0);
    }
}
