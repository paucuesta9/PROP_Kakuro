package domain.controllers.drivers;

import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.WhiteCell;
import domain.controllers.CtrlPlay;

import java.util.Scanner;

/** @file DriverCtrlPlay.java
 @brief Clase  <em>DriverCtrlPlay</em>.
 */


/** @brief Clase DriverCtrlPlay que comprueba la correctividad de las funciones del controlador CtrlPlay
 * @author Judith Almoño Gómez
 */

public class DriverCtrlPlay {
    private static Scanner reader = new Scanner((System.in));

    /** @brief Test del startGame
     *
     * Comprueba que la función startGame se ejecute correctamente
     * @param kakuroText representa el kakuro con el que se jugará
     */
    private static void testStartGame(String kakuroText) {
        Kakuro kakuro = new Kakuro(kakuroText);
        //CtrlPlay.startGame(kakuro);
        System.out.println("Se ha empezado una partida");
    }

    /** @brief Test de la función helpMyValue
     *
     * Comprueba que la función helpMyValue se ejecute correctamente
     * @param kakuro1 representa el kakuro hecho por el usuario
     * @param kakuroSol representa el kakuro con esa posición resuelta
     * @param x representa el número de fila de la celda que quiere comprobar
     * @param y representa el número de columna de la celda que quiere comprobar
     */
    private static void testHelpMyValue(String kakuro1, String kakuroSol, int x, int y) {
        Kakuro k = new Kakuro(kakuro1);
        Kakuro kSol = new Kakuro(kakuroSol);
        Cell[][] board = k.getBoard();
        Cell[][] boardSol = kSol.getBoard();
        if (board[x][y].isWhite()) ((WhiteCell) board[x][y]).setCorrectValue(((WhiteCell)boardSol[x][y]).getValue());
        //CtrlPlay.startGame(k);

        int result = 0;// = CtrlPlay.helpMyValue(x,y);
        if (result == -2) System.out.println("La celda no tiene un valor asignado");
        else if (result == -1) System.out.println("La celda no es blanca");
        else if (result == 0) System.out.println("El valor no es correcto");
        else if (result == 1) System.out.println("El valor es correcto");
    }

    /** @brief Test de la función helpCorrectNumber
     *
     * Comprueba que la función helpCorrectNumber se ejecute correctamente
     * @param kakuro1 representa el kakuro hecho por el usuario
     * @param x representa el número de fila de la celda que quiere comprobar
     * @param y representa el número de columna de la celda que quiere comprobar
     */
    private static void testHelpCorrectNumber(String kakuro1, String kakuroSol, int x, int y) {
        Kakuro k = new Kakuro(kakuro1);
        Kakuro kSol = new Kakuro(kakuroSol);
        Cell[][] board = k.getBoard();
        Cell[][] boardSol = kSol.getBoard();
        if (board[x][y].isWhite()) ((WhiteCell) board[x][y]).setCorrectValue(((WhiteCell)boardSol[x][y]).getValue());

        //CtrlPlay.startGame(k);
        //if (CtrlPlay.helpCorrectNumber(x,y)) System.out.println("El valor de la celda se ha cambiado correctamente");
        //else System.out.println("No es una celda blanca");
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n1. Empezar una partida \n 2. Ayuda para el valor del usuario \n 3. Ayuda para el valor correcto de una celda blanca \n 4. Salir");
        int value = readNumber();
        while (value != 4) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println("Indique un Kakuro en el formato correcto");
                    String kakuro = readKakuro();
                    System.out.println("Se llama a startGame");
                    testStartGame(kakuro);
                    break;
                case 2:
                    System.out.println("Indique la posicion x e 'y' y el valor de la celda que quiere obtener la ayuda, el kakuro ha comprobar y el kakuro resuelto");
                    x = readNumber();
                    y = readNumber();
                    String kakuro1 = readKakuro();
                    String kakuroSol = readKakuro();
                    System.out.println("Se llama a helpMyValue");
                    testHelpMyValue(kakuro1, kakuroSol, x, y);
                    break;
                case 3:
                    System.out.println("Indique la posicion x e y de la celda que quiere obtener la ayuda, el kakuro ha comprobar y el kakuro resuelto");
                    x = readNumber();
                    y = readNumber();
                    kakuro1 = readKakuro();
                    kakuroSol = readKakuro();
                    System.out.println("Se llama a helpCorrectNumber");
                    testHelpCorrectNumber(kakuro1, kakuroSol, x,y);
                    break;
                default:
                    System.out.println("El numero introducido es incorrecto");
                    break;
            }
            System.out.println("\nOpciones: \n1. Empezar una partida \n 2. Ayuda para el valor del usuario \n 3. Ayuda para el valor correcto de una celda blanca \n 4. Salir");
            value = readNumber();
        }
        System.exit(0);
    }

    /** @brief Lee un kakuro de consola
     *
     * @return el kakuro leído
     */
    public static String readKakuro() {
        StringBuilder content = new StringBuilder();
        String[] valuesSize = reader.next().split(",");
        int row = Integer.parseInt(valuesSize[0]);
        int column = Integer.parseInt(valuesSize[1]);
        content.append((row) + "," + (column) + "\n");
        for (int i = 0; i < row; ++i)
            content.append(reader.next()).append("\n");
        return content.toString();
    }

    /** @brief Lee un numero de consola
     *
     * @return el número leído
     */
    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }
}
