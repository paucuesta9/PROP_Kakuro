package domain.controllers.drivers;

import domain.classes.BlackCell;

import java.util.Scanner;

public class DriverBlackCell {
    private static Scanner reader = new Scanner(System.in);

    private static void testCreadoraSinValor() {
        BlackCell bc = new BlackCell();
        System.out.println("Se ha creado la celda negra sin valores");
    }

    private static void testCreadoraConValor(int row, int column) {
        BlackCell bc = new BlackCell(column, row);
        System.out.println("Se ha creado la celda negra con los valores");
    }

    private static void testGetRow(int row) {
        BlackCell bc = new BlackCell(0, row);
        System.out.println("Row = " + bc.getRow());
    }

    private static void testGetColumn(int column) {
        BlackCell bc = new BlackCell(column, 0);
        System.out.println("Column = " + bc.getColumn());
    }

    private static void testSetRow(int row) {
        BlackCell bc = new BlackCell();
        bc.setRow(row);
        System.out.println("Row = " + bc.getRow());
    }

    private static void testSetColumn(int column) {
        BlackCell bc = new BlackCell();
        bc.setRow(column);
        System.out.println("Column = " + bc.getColumn());
    }

    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora sin valor \n 2. Creadora con valor \n 3. Getter de row \n 4. Getter de column \n 5. Setter de row \n 6. Setter de column \n 7. Salir");
        int value = readNumber();
        while (value != 7) {
            int x, y;
            switch (value) {
                case 1:
                    testCreadoraSinValor();
                    break;
                case 2:
                    System.out.println("Indique la suma de fila y la suma de columna o escriba -1 para usar fichero");
                    x = reader.nextInt();
                    if (x == -1) {
                        x = 0;
                        y = 0;
                        //TODO: Leer de fichero
                    } else {
                        y = reader.nextInt();
                    }
                    testCreadoraConValor(x, y);
                    break;
                case 3:
                    System.out.println("Indique la suma de fila o escriba -1 para usar fichero");
                    x = reader.nextInt();
                    if (x == -1) {
                        x = 0;
                        //TODO: Leer de fichero
                    }
                    testGetRow(x);
                    break;
                case 4:
                    System.out.println("Indique la suma de columna o escriba -1 para usar fichero");
                    x = reader.nextInt();
                    if (x == -1) {
                        x = 0;
                        //TODO: Leer de fichero
                    }
                    testGetColumn(x);
                    break;
                case 5:
                    System.out.println("Indique la suma de fila o escriba -1 para usar fichero");
                    x = reader.nextInt();
                    if (x == -1) {
                        x = 0;
                        //TODO: Leer de fichero
                    }
                    testSetRow(x);
                    break;
                case 6:
                    System.out.println("Indique la suma de columna o escriba -1 para usar fichero");
                    x = reader.nextInt();
                    if (x == -1) {
                        x = 0;
                        //TODO: Leer de fichero
                    }
                    testSetColumn(x);
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
