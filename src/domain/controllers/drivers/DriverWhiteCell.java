package domain.controllers.drivers;

import domain.classes.WhiteCell;

import java.util.Scanner;

/** @file DriverWhiteCell.java
 @brief Clase  <em>DriverWhiteCell</em>.
 */


/** @brief Clase DriverWhiteCell que comprueba la correctividad de las funciones de la clase WhiteCell
 * @author Judith Almoño Gómez
 */

public class DriverWhiteCell {
    private static Scanner reader = new Scanner(System.in);

    /** @brief Test de la creadora sin valor
     *
     * Comprueba si se crea correctamente una celda blanca sin valor
     */
    private static void testCreadoraSinValor() {
        WhiteCell wc = new WhiteCell();
        System.out.println("Se ha creado una celda blanca sin valor");
    }

    /** @brief Test de la creadora con valor
     *
     * Comprueba si se crea correctamente una celda blanca sin valor
     */
    private static void testCreadoraConValor(int value) {
        WhiteCell wc = new WhiteCell(value);
        System.out.println("Se ha creado una celda blanca con valor");
    }

    /** @brief Test del Getter de value
     *
     * Comprueba si se hace correctamente el getter de value
     */
    private static void testGetValue(int value) {
        WhiteCell wc = new WhiteCell(value);
        System.out.println("Value = " + wc.getValue());
    }

    /** @brief Test del Setter de value
     *
     * Comprueba si se hace correctamente el Setter de value
     */
    private static void testSetValue(int value) {
        WhiteCell wc = new WhiteCell();
        wc.setValue(value);
        System.out.println("Value = " + wc.getValue());
    }

    /** @brief Test del Getter de correctValue
     *
     * Comprueba si se hace correctamente el getter de correctValue
     */
    private static void testGetCorrectValue() {
        WhiteCell wc = new WhiteCell();
        System.out.println("Correct value = " + wc.getCorrectValue());
    }

    /** @brief Test del Setter de correctValue
     *
     * Comprueba si se hace correctamente el setter de correctValue
     */
    private static void testSetCorrectValue(int correctValue) {
        WhiteCell wc = new WhiteCell();
        wc.setCorrectValue(correctValue);
        System.out.println("Correct value = " + wc.getCorrectValue());
    }

    /** @brief Test que comprueba si la celda es blanca
     *
     */
    private static void testIsWhite() {
        WhiteCell wc = new WhiteCell();
        if (wc.isWhite()) System.out.println("La celda es blanca");
        else System.out.println("La celda no es blanca");
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora sin valor \n 2. Creadora con valor \n 3. Getter de value \n 4. Setter de value \n 5. Getter de CorrectValue \n 6. Setter de CorrectValue \n 7. Comprobar si es una celda blanca \n 8. Salir");
        int value = readNumber();
        while (value != 8) {
            int x;
            switch (value) {
                case 1:
                    System.out.println("Se llama a la creadora sin valor");
                    testCreadoraSinValor();
                    break;
                case 2:
                    System.out.println("Indique el valor de la celda");
                    x = readNumber();
                    System.out.println("Se llama a la creadora con valor");
                    testCreadoraConValor(x);
                    break;
                case 3:
                    System.out.println("Indique el valor de la celda");
                    x = readNumber();
                    System.out.println("Se llama a getValue");
                    testGetValue(x);
                    break;
                case 4:
                    System.out.println("Indique el valor de la celda");
                    x = readNumber();
                    System.out.println("Se llama a setValue");
                    testSetValue(x);
                    break;
                case 5:
                    System.out.println("Se llama a getCorrectValue");
                    testGetCorrectValue();
                    break;
                case 6:
                    System.out.println("Indique el valor de la celda");
                    x = readNumber();
                    System.out.println("Se llama a setCorrectValue");
                    testSetCorrectValue(x);
                    break;
                case 7:
                    System.out.println("Se llama a isWhite");
                    testIsWhite();
                    break;
                default:
                    System.out.println("El número introducido es incorrecto");
                    break;
            }
            System.out.println("\nOpciones: \n 1. Creadora sin valor \n 2. Creadora con valor \n 3. Getter de value \n 4. Setter de value \n 5. Getter de CorrectValue \n 6. Setter de CorrectValue \n 7. Comprobar si es una celda blanca \n 8. Salir");
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
