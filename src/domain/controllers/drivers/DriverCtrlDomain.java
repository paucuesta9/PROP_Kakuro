package domain.controllers.drivers;

import domain.classes.Kakuro;
import domain.controllers.CtrlPlay;

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
        System.out.println("Indique el tamaño del kakuro:");
        int kakuroSize = readNumber();
    }

    /**
     * @brief Test de la función isFinished
     *
     * Necesita un kakuro y un entero f que nos indicará si el kakuro que hemos entrado esta acabado o no
     */
    private static void testIsFinished() {
        System.out.println("Escribe un kakuro válido:");
        String kakuroText = readKakuro();
        System.out.println("Indica si el kakuro esta terminado (1 si esta tarminado, 0 si no lo esta):");
        int f = readNumber();
        Kakuro kakuro = new Kakuro(kakuroText);
        if((kakuro.isFinished() && f == 1) || (!kakuro.isFinished() && f == 0 )) System.out.println("Resultado correcto");
        else System.out.println("No funciona correctamente");
    }

    /**
     * @brief Test de la función checkValidity
     *
     */
    private static void testCheckValidity() {
        //necesitamos un
    }

    private static void testHelpMyValue() {

    }

    private static void testHelpMyCorrectNumber() {
        
    }

    private static void testValidate() {

    }

    private static void testResolve() {
        
    }

    private static void testGenerate() {

    }

    private static void testKakuroSetValue() {

    }

    private static void testGetRowSize() {
        
    }

    private static void testGetColumnSize() {

    }

    private static void testGetKakuroToString() {
        
    }

    private static void testGetCorrectKakuroToString() {
    }

    private static void testSearchKakuro() {

    }

    private static void testGetKakuro() {
        ///per fer el test obtenir un kakuro i printejar-lo
    }

    private static void testSaveKakuro() {
    }

    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Empezar partida \n 3. Mirar si ha acabado \n 4. Mirar la validez de columna y fila a partir de una celda i un valor \n 5. Solicitar ayuda, mirar si mi valor es correcto \n 6. Solicitar ayuda, colocar valor correcto" +
                "\n 7. Validar Kakuro \n 8. Resolver Kakuro \n 9. Generar Kakuro \n 10. Asignar valor a celda blanca \n 11. Obtener tamaño de fila \n 12. Obtener tamaño de columna \n 13. Obtener Kakuro en formato String \n 14. Obtener el Kakuro resuleto en formato String " +
                "\n 15. Buscar un Kakuro de dificultad y tamaño especificados \n 16. Obtener Kakuro a partir de ruta \n 17. Guardar Kakuro");
        int value = readNumber();
        while (value != 19) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println("Se llama a la Creadora");
                    testCreadora();
                    break;
                case 2:
                    testStartNewGame();
                    break;
                case 3:
                    testIsFinished();
                    break;
                case 4:
                    testCheckValidity();
                    break;
                case 5:
                    testHelpMyValue();
                    break;
                case 6:
                    testHelpMyCorrectNumber();
                    break;
                case 7:
                    testValidate();
                    break;
                case 8:
                    testResolve();
                    break;
                case 9:
                    testGenerate();
                    break;
                case 10:
                    testKakuroSetValue();
                    break;
                case 11:
                    testGetRowSize();
                    break;
                case 12:
                    testGetColumnSize();
                    break;
                case 13:
                    testGetKakuroToString();
                    break;
                case 14:
                    testGetCorrectKakuroToString();
                    break;
                case 15:
                    testSearchKakuro();
                    break;
                case 16:
                    testGetKakuro();
                    break;
                case 17:
                    testSaveKakuro();
                    break;
            }
            System.out.println("Opciones: \n 1. Creadora \n 2. Empezar partida \n 3. Mirar si ha acabado \n 4. Mirar la validez de columna y fila a partir de una celda i un valor \n 5. Solicitar ayuda, mirar si mi valor es correcto \n 6. Solicitar ayuda, colocar valor correcto" +
                    "\n 7. Validar Kakuro \n 8. Resolver Kakuro \n 9. Generar Kakuro \n 10. Asignar valor a celda blanca \n 11. Obtener tamaño de fila \n 12. Obtener tamaño de columna \n 13. Obtener Kakuro en formato String \n 14. Obtener el Kakuro resuleto en formato String " +
                    "\n 15. Buscar un Kakuro de dificultad y tamaño especificados \n 16. Obtener Kakuro a partir de ruta \n 17. Guardar Kakuro");
            value = readNumber();
        }
        System.exit(0);
    }

    public static String readKakuro() {
        StringBuilder content = new StringBuilder();
        while (reader.hasNext())
            content.append(reader.next()).append("\n");
        return content.toString();
    }

    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }
}
