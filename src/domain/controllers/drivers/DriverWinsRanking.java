package domain.controllers.drivers;

import domain.classes.Game;
import domain.classes.WinsRanking;
import domain.controllers.stubs.CtrlDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverWinsRanking {

    private static Scanner reader = new Scanner(System.in);


    private static void testCreadora() {
        CtrlDomain cdStub = new CtrlDomain();
        WinsRanking winsRanking = new WinsRanking(cdStub);
        System.out.println("Se ha creado");
    }

    private static void testGetList() {
        System.out.println("Se ordenan los jugadores segun victorias");

        String s = "wins";

        CtrlDomain cdStub = new CtrlDomain();
        WinsRanking winsRanking = new WinsRanking(cdStub);

        String [][] list = winsRanking.getList(s);

        for (int i=0; i<list.length; ++i) {
            for (int j=0; j<list[i].length; ++j) System.out.print(list[i][j]+" ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Lista ordenada de jugadores por victorias \n 3. Salir");
        int value = readNumber();
        while (value != 3) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a la Creadora");
                    testCreadora();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a getList");
                    testGetList();
                    break;
                default:
                    System.out.println();
                    System.out.println("Valor incorrecto");
                    break;
            }
            System.out.println();
            System.out.println("Opciones: \n 1. Creadora \n 2. Lista ordenada de jugadores por victorias \n 3. Salir");
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
