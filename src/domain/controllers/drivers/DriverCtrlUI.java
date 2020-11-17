package domain.controllers.drivers;

/** @file DriverCtrlUI.java
 @brief Clase  <em>DriverCtrlUI</em>.
 @author Judith Almoño Gómez
 */


import domain.classes.Kakuro;
import presentation.CtrlUI;

import java.util.Scanner;

/** @brief Clase DriverCtrlUI que comprueba la correctividad de las funciones del controlador UI
 */

public class DriverCtrlUI {
    private static Scanner reader = new Scanner(System.in);
    private static void testCreadora() {
        CtrlUI ctrlUI = new CtrlUI();
        System.out.println("Se ha creado");
    }

    private static void testRun() {

    }

    private static void testCheckCoord(String kakuro, int x, int y) {
        CtrlUI ctrlUI = new CtrlUI();
        Kakuro k = new Kakuro(kakuro);
        ctrlUI.checkCoord(x,y);

    }

    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Run \n 3. Menú \n 4. Play \n 5. Ayudas \n 6. Comprobadora de coordenadas \n 7. Lectura de un número por terminal \n 8. Lectura de una linea por terminal \n 9. Escritura de kakuro por terminal \n 10. Escritura del kakuro correcto por terminal \n 11. Salir");
        int value = readNumber();
        while (value != 11) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println("Se llama a la creadora");
                    testCreadora();
                    break;
                case 2:
                    testRun();
                    break;
                case 3:
                    testMenu();
                    break;
                case 4:
                    testPlay();
                    break;
                case 5:
                    testHelp();
                    break;
                case 6:
                    testCheckCoord();
                    break;
                case 7:
                    testReadNumber();
                    break;
                case 8:
                    testReadLine();
                    break;
                case 9:
                    testWriteKakuroInTerminal();
                    break;
                case 10:
                    testWriteCorrectKakuroInTerminal();
                    break;
                default:
                    System.out.println("El número introducido es incorrecto");
                    break;
            }
            System.out.println("Opciones: \n 1. Creadora \n 2. Run \n 3. Menú \n 4. Play \n 5. Ayudas \n 6. Comprobadora de coordenadas \n 7. Lectura de un número por terminal \n 8. Lectura de una linea por terminal \n 9. Escritura de kakuro por terminal \n 10. Escritura del kakuro correcto por terminal \n 11. Salir");
            value = readNumber();
        }
        System.exit(0);
    }

    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }

}
