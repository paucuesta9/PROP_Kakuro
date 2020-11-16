package domain.controllers.drivers;

import domain.classes.Cell;

import java.util.Scanner;

public class DriverCell {
    private static Scanner reader = new Scanner(System.in);

    private static void testCreadora() {
        Cell cell = new Cell();
        System.out.println("Se ha creado la celda");
    }

    private static void testIsWhite() {
        Cell cell = new Cell();
        Boolean result = cell.isWhite();
        if (result) System.out.println("La celda es blanca");
        else System.out.println("La celda no es blanca");
    }

    private static void testSetValue() {
        Cell cell = new Cell();
        Boolean result = cell.setValue(0);
        if (result) System.out.println("La celda es blanca y se ha modificado el valor");
        else System.out.println("La celda no es blanca");
    }

    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. isWhite \n 3. setValue \n 4. Salir");
        int value = readNumber();
        while (value != 4) {
            switch (value) {
                case 1:
                    testCreadora();
                    break;
                case 2:
                    testIsWhite();
                    break;
                case 3:
                    testSetValue();
                    break;
            }
            System.out.println("Opciones: \n 1. Creadora \n 2. isWhite \n 3. setValue \n 4. Salir");
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
