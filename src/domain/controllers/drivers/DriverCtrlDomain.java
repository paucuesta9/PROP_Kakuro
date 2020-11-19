package domain.controllers.drivers;

import domain.classes.Kakuro;
import domain.controllers.CtrlDomain;
import domain.controllers.CtrlPlay;

import java.io.IOException;
import java.util.Scanner;


/** @file DriverCtrlDomain.java
 @brief Clase  <em>DriverCtrlDomain</em>.
 @author Pol Vallespí Soro
 */

/** @brief Clase DriverCtrlDomain que comprueba la correctividad de las funciones del controlador CtrlDomain
 */

public class DriverCtrlDomain {
    private static Scanner reader = new Scanner(System.in);
    public static final String KAKURO_TEXT_ENUNCIADO = "9,9\n" +
            "*,*,C19,C12,*,*,*,C7,C10\n" +
            "*,F14,?,?,C4,C11,C17F4,?,?\n" +
            "*,C7F36,?,?,?,?,?,?,?\n" +
            "F12,?,?,F10,?,?,?,C25,C14\n" +
            "F3,?,?,C20,C11F20,?,?,?,?\n" +
            "F17,?,?,?,?,C8,F6,?,?\n" +
            "*,C11,C7F13,?,?,?,C4F10,?,?\n" +
            "F28,?,?,?,?,?,?,?,*\n" +
            "F6,?,?,*,*,F8,?,?,*";


    /** @brief Test de la creadora
     *
     * Comprueba si se crea correctamente
     */
    private static void testCreadora() {
    }
    /** @brief Test de la función startNewGame
     *
     * Comprueba si la función hace correctamente la función de buscar un kakuro de la dificultad y tamaño indicado
     * y si inicia correctamente la partida
     */
    private static void testStartNewGame() {
        System.out.println("Indique la dificultad del kakuro: ");
        int difficulty = readNumber();
        System.out.println("Indique el numero de filas del kakuro:");
        int kakuroRows = readNumber();
        System.out.println("Indique el numero de columnas del kakuro:");
        int kakuroCols = readNumber();
        CtrlDomain cd = new CtrlDomain();
        cd.startNewGame(difficulty,kakuroRows,kakuroCols);
        System.out.println("Se ha creado la partida correctamente");
    }
    /** @brief Test de la función checkCoord
     *
     * Comprueba si la función checkBoard detecta correctamente cuando unas coordenadas pertenecen al tablero y cuando no
     */
    private static void testCheckCoord() {
        System.out.println("Introduce la ruta de un kakuro para hacer el test: ");
        String s = reader.next();
        System.out.println("Introduce una fila: ");
        int fila = readNumber();
        System.out.println("Introduce una columna: ");
        int columna = readNumber();

        CtrlDomain cd = new CtrlDomain();
        try {
            cd.getKakuro("data/"+s+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean b = cd.checkCoord(fila,columna);
        if(b) System.out.println("Coordenadas correctas");
        else System.out.println("Coordenadas incorrectas");
    }

    /**@brief función main del driver CtrlDomain
     *
     * Nos permite escoger el test que queremos realizar
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Empezar una partida \n 3. Comprobar que las coordenadas son correctas\n 4. Salir");
        int value = readNumber();
        while (value != 4) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a la Creadora");
                    testCreadora();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a start new game");
                    testStartNewGame();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Se llama a checkCoord");
                    testCheckCoord();
                    break;
                default:
                    System.out.println();
                    System.out.println("Valor incorrecto");
                    break;
            }
            System.out.println();
            System.out.println("Opciones: \n 1. Creadora \n 2. Empezar una partida \n 3. Comprobar que las coordenadas son correctas\n 4. Salir");
            value = readNumber();
        }
        System.exit(0);
    }

    /**@brief función que nos permite leer un kakuro en formato string
     *
     * @return kakuro en formato string
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
    /**@brief función que nos permite leer un entero del terminal o un fichero
     *
     * @return el entero leido
     */
    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }
}
