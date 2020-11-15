package domain.controllers;

/** @file CtrlPlay.java
 @brief Clase  <em>CtrlPlay</em>.
 */

import domain.classes.Kakuro;
import domain.classes.WhiteCell;

/** @brief Clase CtrlPlay que contiene los atributos y metodos para la funcionalidad de jugar
 */
public class CtrlPlay {
    /**
     * kakuro instancia del kakuro con el que se trabaja en este momento
     */
    private static Kakuro kakuro;

    /** @brief Creadora por defecto
     *
     */
    public CtrlPlay() {

    }

    /** @brief Empieza una partida
     *
     * @param newKakuro representa el kakuro nuevo que se jugará
     */
    public static void startGame(Kakuro newKakuro) {
        kakuro = newKakuro;
        CtrlResolve.setKakuro(kakuro);
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
    public static int helpMyValue(int x, int y) {
        if (kakuro.getCell(x, y).isWhite()) {
            int value = ((WhiteCell) kakuro.getCell(x, y)).getValue();
            if (value == 0) return -2;
            else return (((WhiteCell) kakuro.getCell(x, y)).getCorrectValue() == value) ? 1 : 0;
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
    public static boolean helpCorrectNumber(int x, int y) {
        if (kakuro.getCell(x, y).isWhite()) {
            kakuro.setValue(x, y, ((WhiteCell) kakuro.getCell(x, y)).getCorrectValue());
            return true;
        }
        return false;
    }
}
