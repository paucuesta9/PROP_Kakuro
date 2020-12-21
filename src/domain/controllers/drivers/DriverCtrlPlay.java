package domain.controllers.drivers;

import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.Player;
import domain.classes.WhiteCell;
import domain.controllers.CtrlPlay;
import domain.controllers.stubs.CtrlDomain;

import java.util.Scanner;

/** @file DriverCtrlPlay.java
 @brief Clase  <em>DriverCtrlPlay</em>.
 */


/** @brief Clase DriverCtrlPlay que comprueba la correctividad de las funciones del controlador CtrlPlay
 * @author Judith Almoño Gómez
 */

public class DriverCtrlPlay {
    private static Scanner reader = new Scanner((System.in));

    /** @brief Test de la creadora
     */
    private static void testCtrlPlay1() {
        CtrlDomain cdStub = new CtrlDomain();
        CtrlPlay play = new CtrlPlay(0,9,9,cdStub);
        System.out.println("Se ha creado");
    }

    /** @brief Test de la creadora
     */
    private static void testCtrlPlay2() {
        CtrlDomain cdStub = new CtrlDomain();
        CtrlPlay play = new CtrlPlay("alvaroFeardo\\diff1\\3_3\\0.txt",cdStub);
        System.out.println("Se ha creado");
    }

    private static void testCtrlPlay3() {

    }


    /** @brief Test de la función helpMyValue
     * Comprueba si la función helpMyValue detecta adecuadamente un valor correcto
     */
    private static void testHelpMyValue() {
        CtrlDomain cdStub = new CtrlDomain();
        CtrlPlay p = new CtrlPlay("estoNoImporta\\diff1\\3_3\\0.txt",cdStub);
        System.out.println("Introduce la fila de la celda:");
        int x = readNumber();
        System.out.println("Introduce la columna de la celda:");
        int y = readNumber();
        System.out.println();

        int result = p.helpMyValue(x,y);
        if (result == -2) System.out.println("La celda no tiene un valor asignado");
        else if (result == -1) System.out.println("La celda no es blanca");
        else if (result == 0) System.out.println("El valor no es correcto");
        else if (result == 1) System.out.println("El valor es correcto");
    }

    /** @brief Test de la función helpCorrectNumber
     *  Comprueba que se retorna adecuadamente el valor correcto
     */
    private static void testHelpCorrectNumber() {
        CtrlDomain cdStub = new CtrlDomain();
        CtrlPlay p = new CtrlPlay("estoNoImporta\\diff1\\3_3\\0.txt",cdStub);
        System.out.println("Introduce la fila de la celda:");
        int x = readNumber();
        System.out.println("Introduce la columna de la celda:");
        int y = readNumber();
        System.out.println();

        int result = p.helpCorrectNumber(x,y);
        if (result == 0) System.out.println("El valor no es correcto");
        else System.out.println("El valor es correcto");
    }

    /**@brief Test de la función finishGame
     * Comprueba que se hace correctamente su función dependiendo de si lo he resuelto el usuario o la máquina
     */
    private static void testFinishGame() {
        CtrlDomain cdStub = new CtrlDomain();
        CtrlPlay p = new CtrlPlay("estoNoImporta\\diff1\\3_3\\0.txt",cdStub);
        System.out.println("¿Se ha pulsado el boton de resolver? (0->false, 1->true)");
        int x = readNumber();
        boolean b = (x==1);
        System.out.println("Se han sumado "+p.finishGame(b)+" puntos");
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. 1ra constructora \n 2. 2da constructora \n 3. 3ra constructora \n 4. Comprobar si un valor és correcto \n 5. Retornar el valor correcto \n 6. Acabar una partida \n 7. Salir");
        int value = readNumber();
        while (value != 7) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println("Constructora 1");
                    System.out.println("Se llama a Constructora 1");
                    testCtrlPlay1();
                    break;
                case 2:
                    System.out.println("Constructora 2");
                    System.out.println("Se llama a Constructora 2");
                    testCtrlPlay2();
                    break;
                case 3:
                    System.out.println("Constructora 3");
                    System.out.println("Se llama a Constructora 3");
                    testCtrlPlay3();
                    break;
                case 4:
                    System.out.println("Se llama a helpMyValue");
                    testHelpMyValue();
                    break;
                case 5:
                    System.out.println("Se llama a helpCorrectNumber");
                    testHelpCorrectNumber();
                    break;
                case 6:
                    System.out.println("Se llama a FinishGame");
                    testFinishGame();
                    break;
                default:
                    System.out.println("El numero introducido es incorrecto");
                    break;
            }
            System.out.println("Opciones: \n 1. 1ra constructora \n 2. 2da constructora \n 3. 3ra constructora \n 4. Comprobar si un valor és correcto \n 5. Retornar el valor correcto \n 6. Acabar una partida \n 7. Salir");
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
