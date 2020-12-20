package domain.controllers.drivers;

import domain.classes.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverGame {

    private static Scanner reader = new Scanner(System.in);


    private static void testCreadora() {
        List<String> h = new ArrayList<>();
        Game game = new Game(0,0,0,0,10,10,1, h);
        System.out.println("Se ha creado");
    }

    private static void testAddHelp() {
        System.out.println("Cuantas ayudas quiere anadir?");
        int n = readNumber();
        List<String> h = new ArrayList<>();
        Game game = new Game(0,0,0,0,10,10,1, h);
        for (int i=0; i<n; ++i) {
            System.out.println("En que posicion quiere la ayuda? Escriba la posición horizontal y luego la vertical");
            int x = readNumber(); int y = readNumber();
            System.out.println("Si quiere que la ayuda sea correcta introduzca un 1, en caso contrario un 0");
            int c = readNumber();

            game.addHelp(x, y, c);

        }
        List<String> r = game.getHelps();
        System.out.println("Al comenzar no habia ayudas, ahora hay "+r.size()+" ayudas. \nA continuacion se listaran:\n");

        for(int i=0; i<r.size(); ++i) {
            System.out.println(r.get(i));
        }
    }

    private static void testGameToArrayList() {
        System.out.println("Primero crearemos la partida: ");
        System.out.println("Introduzca el id:");
        int id = readNumber();
        System.out.println("Introduzca el tiempo:");
        int time = readNumber();
        System.out.println("Introduzca la dificultad:");
        int diff = readNumber();
        System.out.println("Introduzca los puntos:");
        int points = readNumber();
        System.out.println("Introduzca el id del Kakuro");
        int kId = readNumber();
        System.out.println("Introduzca el numero de filas y columnas");
        int x = readNumber(); int y = readNumber();
        List<String> h = new ArrayList<>();
        Game game = new Game(id, time, points, kId, x, y, diff, h);

        System.out.println("A continuacion se anadiran a un ArrayList todos los atributos y se mostraran por pantalla en el mismo orden");

        ArrayList<Integer> r = game.gameToArrayList();

        for(int i=0; i<r.size(); ++i) System.out.print(r.get(i)+" ");
        System.out.println();

    }

    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Anadir ayuda \n 3. Informacion de game a un arraylist\n 4. Salir");
        int value = readNumber();
        while (value != 4) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a la Creadora");
                    testCreadora();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a addHelp");
                    testAddHelp();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Se llama a gameToArrayList");
                    testGameToArrayList();
                    break;
                default:
                    System.out.println();
                    System.out.println("Valor incorrecto");
                    break;
            }
            System.out.println();
            System.out.println("Opciones: \n 1. Creadora \n 2. Anadir ayuda \n 3. Informacion de game a un arraylist\n 4. Salir");
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
