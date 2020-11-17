package domain.controllers.drivers;

import domain.classes.Kakuro;
import domain.controllers.CtrlPlay;

import java.util.Scanner;

/** @file DriverCtrlPlay.java
 @brief Clase  <em>DriverCtrlPlay</em>.
 @author Judith Almoño Gómez
 */


/** @brief Clase DriverCtrlPlay que comprueba la correctividad de las funciones del controlador Play
 */

public class DriverCtrlPlay {
    private static Scanner reader = new Scanner((System.in));

    /** @brief Test de la creadora
     *
     * Comprueba si se crea correctamente
     */
    private static void testCreadora() {
        CtrlPlay ctrlPlay = new CtrlPlay();
        System.out.println("Se ha creado");
    }

    /** @brief Test del startGame
     *
     * Comprueba que la función startGame se ejecute correctamente
     * @param kakuroText representa el kakuro con el que se jugará
     */
    private static void testStartGame(String kakuroText) {
        Kakuro kakuro = new Kakuro(kakuroText);
        CtrlPlay.startGame(kakuro);
        System.out.println("Se ha empezado una partida");
    }

    /** @brief Test de la función helpMyValue
     *
     * Comprueba que la función helpMyValue se ejecute correctamente
     * @param kakuro1 representa el kakuro hecho por el usuario
     * @param x representa el número de fila de la celda que quiere comprobar
     * @param y representa el número de columna de la celda que quiere comprobar
     */
    private static void testHelpMyValue(String kakuro1, int x, int y) {
        Kakuro k = new Kakuro(kakuro1);
        CtrlPlay.startGame(k);
        int result = CtrlPlay.helpMyValue(x,y);
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
    private static void testHelpCorrectNumber(String kakuro1, int x, int y) {
        Kakuro k = new Kakuro(kakuro1);
        CtrlPlay.startGame(k);
        if (CtrlPlay.helpCorrectNumber(x,y)) System.out.println("El valor de la celda es correcto");
        else System.out.println("El valor de la celda no és correcto");
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Empezar una partida \n 3. Ayuda para el valor del usuario \n 4. Ayuda para el valor correcto de una celda blanca \n 5. Salir");
        int value = readNumber();
        while (value != 5) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println("Se llama a la creadora");
                    testCreadora();
                    break;
                case 2:
                    System.out.println("Indique un Kakuro en el formato correcto");
                    String kakuro = readKakuro();
                    System.out.println("Se llama a startGame");
                    testStartGame(kakuro);
                    break;
                case 3:
                    System.out.println("Indique la posición x e 'y' y el valor de la celda que quiere obtener la ayuda y el kakuro");
                    x = readNumber();
                    y = readNumber();
                    String kakuro1 = readKakuro();
                    System.out.println("Se llama a helpMyValue");
                    testHelpMyValue(kakuro1, x, y);
                    break;
                case 4:
                    System.out.println("Indique la posición x e y de la celda que quiere obtener la ayuda y el kakuro");
                    x = readNumber();
                    y = readNumber();
                    kakuro1 = readKakuro();
                    System.out.println("Se llama a helpCorrectNumber");
                    testHelpCorrectNumber(kakuro1, x,y);
                    break;
                default:
                    System.out.println("El número introducido es incorrecto");
                    break;
            }
            System.out.println("Opciones: \n 1. Creadora \n 2. Empezar una partida \n 3. Ayuda para el valor del usuario \n 4. Ayuda para el valor correcto de una celda blanca \n 5. Salir");
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
        while (reader.hasNext())
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
