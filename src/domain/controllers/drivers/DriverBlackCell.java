package domain.controllers.drivers;

import domain.classes.BlackCell;

import java.util.Scanner;

/** @file DriverBlackCell.java
 @brief Clase  <em>DriverBlackCell</em>.
 @author Pau Cuesta Arcos
 */


/** @brief Clase DriverBlackCell que comprueba la correctividad de las funciones de la clase BlackCell
 */

public class DriverBlackCell {
    private static Scanner reader = new Scanner(System.in);

    /** @brief Test de la creadora sin valor
     *
     * Comprueba si se crea correctamente una celda negra sin valor
     */
    private static void testCreadoraSinValor() {
        BlackCell bc = new BlackCell();
        System.out.println("Se ha creado la celda negra sin valores");
    }

    /** @brief Test de la creadora con valor
     *
     * Comprueba si se crea correctamente una celda negra con valor
     */
    private static void testCreadoraConValor(int row, int column) {
        BlackCell bc = new BlackCell(column, row);
        System.out.println("Se ha creado la celda negra con los valores");
    }

    /** @brief Test del Getter de row
     *
     * Comprueba si se hace correctamente el getter de row
     */
    private static void testGetRow(int row) {
        BlackCell bc = new BlackCell(0, row);
        System.out.println("Row = " + bc.getRow());
    }

    /** @brief Test del Getter de column
     *
     * Comprueba si se hace correctamente el getter de column
     */
    private static void testGetColumn(int column) {
        BlackCell bc = new BlackCell(column, 0);
        System.out.println("Column = " + bc.getColumn());
    }

    /** @brief Test del Setter de row
     *
     * Comprueba si se hace correctamente el setter de row
     */
    private static void testSetRow(int row) {
        BlackCell bc = new BlackCell();
        bc.setRow(row);
        System.out.println("Row = " + bc.getRow());
    }

    /** @brief Test del Setter de column
     *
     * Comprueba si se hace correctamente el setter de column
     */
    private static void testSetColumn(int column) {
        BlackCell bc = new BlackCell();
        bc.setRow(column);
        System.out.println("Column = " + bc.getColumn());
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora sin valor \n 2. Creadora con valor \n 3. Getter de row \n 4. Getter de column \n 5. Setter de row \n 6. Setter de column \n 7. Salir");
        int value = readNumber();
        while (value != 7) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println("Se llama a la creadora sin valores");
                    testCreadoraSinValor();
                    break;
                case 2:
                    System.out.println("Indique la suma de fila y la suma de columna");
                    x = readNumber();
                    y = readNumber();
                    System.out.println("Se llama a la creadora con valores");
                    testCreadoraConValor(x, y);
                    break;
                case 3:
                    System.out.println("Indique la suma de fila");
                    x = readNumber();
                    System.out.println("Se llama a getRow");
                    testGetRow(x);
                    break;
                case 4:
                    System.out.println("Indique la suma de columna");
                    x = readNumber();
                    System.out.println("Se llama a getColumn");
                    testGetColumn(x);
                    break;
                case 5:
                    System.out.println("Indique la suma de fila");
                    x = readNumber();
                    System.out.println("Se llama a setRow");
                    testSetRow(x);
                    break;
                case 6:
                    System.out.println("Indique la suma de columna");
                    x = readNumber();
                    System.out.println("Se llama a setColumn");
                    testSetColumn(x);
                    break;
                default:
                    System.out.println("El número no es correcto");
                    break;
            }
            System.out.println("\nOpciones: \n 1. Creadora sin valor \n 2. Creadora con valores \n 3. Obtener tamaño fila \n 4. Obtener tamaño columna \n 5. Introducir tamaño fila \n 6. Introducir tamaño columna \n 7. Salir");
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
