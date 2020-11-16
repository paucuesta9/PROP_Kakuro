package domain.controllers.drivers;

import domain.classes.Cell;

import java.util.Scanner;

/** @file DriverCell.java
 @brief Clase  <em>DriverCell</em>.
 @author Pau Cuesta Arcos
 */


/** @brief Clase DriverCell que comprueba la correctividad de las funciones de la clase Cell
 */

public class DriverCell {
    private static Scanner reader = new Scanner(System.in);

    /** @brief Test de la creadora
     *
     * Comprueba si se crea correctamente una celda
     */
    private static void testCreadora() {
        Cell cell = new Cell();
        System.out.println("Se ha creado la celda");
    }

    /** @brief Test que comprueba si la celda es blanca
     *
     */
    private static void testIsWhite() {
        Cell cell = new Cell();
        Boolean result = cell.isWhite();
        if (result) System.out.println("La celda es blanca");
        else System.out.println("La celda no es blanca");
    }

    /** @brief Test del setter de value
     *
     * Comprueba si se hace correctamente el setter de value
     */
    private static void testSetValue() {
        Cell cell = new Cell();
        Boolean result = cell.setValue(0);
        if (result) System.out.println("La celda es blanca y se ha modificado el valor");
        else System.out.println("La celda no es blanca");
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. isWhite \n 3. setValue \n 4. Salir");
        int value = readNumber();
        while (value != 4) {
            switch (value) {
                case 1:
                    System.out.println("Se llama a la Creadora");
                    testCreadora();
                    break;
                case 2:
                    System.out.println("Se llama a isWhite");
                    testIsWhite();
                    break;
                case 3:
                    System.out.println("Se llama a setValue");
                    testSetValue();
                    break;
                default:
                    System.out.println("El número no es correcto");
                    break;
            }
            System.out.println("Opciones: \n 1. Creadora \n 2. isWhite \n 3. setValue \n 4. Salir");
            value = readNumber();
        }
        System.exit(0);
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
