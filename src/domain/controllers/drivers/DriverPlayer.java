package domain.controllers.drivers;

import domain.classes.Game;
import domain.classes.Player;
import domain.classes.Stats;
import presentation.Play;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** @file DriverPlayer.java
 * @class DriverPlayer
 *
 */

/** @brief Clase DriverPlayer que comprueba la correctividad de las funciones de Player
 * @author Alvaro Armada Ruiz
 *
 */
public class DriverPlayer {
    private static Scanner reader = new Scanner(System.in);


    /** @brief Test de la creadora
     *
     */
    private static void testCreadoras() {
        Player player = new Player();
        System.out.println("Se ha creado");

        Player player1 = new Player("UwU", "UwU");

        System.out.println("Se ha creado");
    }

    /** @brief Comprueba la función getGame
     *  Comprueba que se retornan todas las partidas de la lista de guardadas
     *
     */
    private static void testGetGame() {
        System.out.println("Se crean dos games");
        Player p = new Player();
        List<String> h = new ArrayList<>();
        Game game1 = new Game(0, 1, 2, 3, 4, 5, 6, h);
        Game game2 = new Game(6, 5, 4 ,3 ,2 ,1 , 0,h);
        List<Game> l = new ArrayList<>();
        l.add(game1); l.add(game2);
        p.setSavedGames(l);
        Game game = p.getGame(0);
        ArrayList<Integer> r = game.gameToArrayList();


        for(int i=0; i<r.size(); ++i) System.out.print(r.get(i)+" ");
        System.out.println();

        game = p.getGame(6);
        r = game.gameToArrayList();

        for(int i=0; i<r.size(); ++i) System.out.print(r.get(i)+" ");
        System.out.println();
    }

    /** @brief Comprueba que se devuelven en el formato correcto las estadísticas de jugadores
     *
     */
    private static void testGetStatsInt() {
        System.out.println("Primero crearemos las estadísticas: ");
        System.out.println("Introduzca las partidas ganadas:");
        int w = readNumber();
        System.out.println("Introduzca las partidas empezadas:");
        int t = readNumber();
        System.out.println("Introduzca las ayudas utilizadas:");
        int h = readNumber();
        System.out.println("Introduzca los puntos:");
        int p = readNumber();
        System.out.println("Introduzca los kakuros creados");
        int c = readNumber();
        Stats stats = new Stats(w, t, h, p, c, 0, 0, 0);

        System.out.println("A continuacion se anadiran a un ArrayList todos los atributos y se mostraran por pantalla en el mismo orden");
        Player player = new Player();
        player.setStats(stats);

        ArrayList<Integer> r = player.getStatsInt();

        for(int i=0; i<r.size(); ++i) System.out.print(r.get(i)+" ");
        System.out.println();

    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Estadisticas en formato entero \n 3. Partidas del usuario a arrayList\n 4. Salir");
        int value = readNumber();
        while (value != 4) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a la Creadora");
                    testCreadoras();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a getStatsInt");
                    testGetStatsInt();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Se llama a gameToArrayList");
                    testGetGame();
                    break;
                default:
                    System.out.println();
                    System.out.println("Valor incorrecto");
                    break;
            }
            System.out.println();
            System.out.println("Opciones: \n 1. Creadora \n 2. Estadisticas en formato entero \n 3. Partidas del usuario a arrayList\n 4. Salir");
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
