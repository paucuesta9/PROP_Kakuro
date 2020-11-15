package domain.controllers.drivers;

import domain.classes.BlackCell;

import java.util.Scanner;

public class DriverBlackCell {
    private static Scanner reader = new Scanner(System.in);

    private static void testCreadoraSinValor() {
        BlackCell bc = new BlackCell();
        System.out.println("Row = " + bc.getRow() + "\n Column = " + bc.getColumn());
    }

    private static void testCreadoraConValor(int row, int column) {
        BlackCell bc = new BlackCell(row, column);
        System.out.println("Row = " + bc.getRow() + "\n Column = " + bc.getColumn());
    }

    private static void testGetRow() {

    }

    private static void testGetColumn() {

    }

    private static void testSetRow() {

    }

    private static void testSetColumn() {

    }

    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora sin valor \n 2. Creadora con valor \n 3. Getter de row \n 4. Getter de column \n 5. Setter de row \n 6. Setter de column \n 7. Salir");
        int value = readNumber();
        while (value != 7) {
            switch (value) {
                case 1:
                    testCreadoraSinValor();
                    break;
                case 2:
                    System.out.println("Indique la suma de fila y la suma de columna o escriba -1 para usar fichero");
                    int x = reader.nextInt();
                    int y;
                    if (x == -1) {

                    } else {
                        y = reader.nextInt();
                    }
                    //testCreadoraConValor(x, y);
                    break;
                case 3:
                    testGetRow();
                    break;
                case 4:
                    testGetColumn();
                    break;
                case 5:
                    testSetRow();
                    break;
                case 6:
                    testSetColumn();
                    break;
            }
            System.out.println("\nOpciones: \n 1. Creadora sin valor \n 2. Creadora con valores \n 3. Obtener tamaño fila \n 4. Obtener tamaño columna \n 5. Introducir tamaño fila \n 6. Introducir tamaño columna \n 7. Salir");
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
