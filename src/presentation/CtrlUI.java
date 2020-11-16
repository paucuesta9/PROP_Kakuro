package presentation;

import domain.controllers.CtrlDomain;

import java.util.ArrayList;
import java.util.Scanner;

/** @file CtrlUI.java
 @brief Clase  <em>CtrlUI</em>.
 */


/** @brief Clase CtrlUI que contiene los atributos y metodos para el intercambio de atributos entre controladores
 */
public class CtrlUI {
    /**
     * cd es la instancia del CtrlDomain
     */
    private CtrlDomain cd;
    /**
     * reader es una atributo que sirve para leer de la terminal
     */
    private Scanner reader = new Scanner(System.in);

    /** @brief Creadora por defecto
     *
     * Se ejecuta automáticamente al declarar una instancia del CtrlDomain.
     *
     */
    public CtrlUI() {
        cd = new CtrlDomain();
    }

    /** @brief Inicio del juego
     *
     * Se da la bienvenida al usuario y lanza el menu
     */
    public void run () {
        System.out.println("########## BIENVENIDO A KAKURO ##########");
        System.out.println("");
        menu();
    }

    /** @brief Menú principal del juego
     *
     * Se da al usuario las diferentes opciones que tiene del juego y se lanza cada una de ellas
     */
    public void menu () {
        System.out.println("MENÚ:");
        System.out.println("1. Jugar");
        System.out.println("2. Crear/Validar");
        System.out.println("3. Resolver");
        System.out.println("4. Generar Automáticamente");
        System.out.println("5. Salir");
        System.out.println("");
        int choice = readNumber();
        reader.nextLine();
        String kakuro;
        switch(choice) {
            case 1:
                play();
                break;
            case 2:
                System.out.println("Indique el Kakuro dentro de la carpeta data que desea validar");
                kakuro = readLine();
                cd.getKakuro("data/" + kakuro + ".txt");
                //cd.difficulty();
                if (cd.validate()) {
                    System.out.println("El Kakuro es correcto");
                } else {
                    System.out.println("El Kakuro no es correcto");
                }
                menu();
                break;
            case 3:
                System.out.println("Indique el Kakuro dentro de la carpeta data que desea resolver");
                kakuro = readLine();
                cd.getKakuro("data/" + kakuro + ".txt");
                cd.resolve();
                writeCorrectKakuroInTerminal();
                menu();
                break;
            case 4:
                System.out.print("Indique la dimension n (NxN) del Kakuro que desea generar: ");
                int size = readNumber();
                System.out.println("");
                System.out.print("Indique la dificultad: Fácil (1), Medio (2), Dificil (3): ");
                int diff = readNumber();
                System.out.println("");
                cd.generate(size);
                writeKakuroInTerminal();
                menu();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("El número introducido es incorrecto");
                System.out.println("Debe introducir un número entre 1 y 4");
                menu();
                break;
        }
    }

    /** @brief Opcion de jugar del menu
     *
     * Se crea/carga una partida y se juega
     */
    public void play() {
        System.out.println("1. Empezar nueva partida");
        System.out.println("2. Cargar partida empezada");
        int choice = readNumber();
        if (choice == 1) {
            System.out.print("Escoge dificultad: Fácil (1), Medio (2), Dificil (3): ");
            int difficulty = readNumber();
            System.out.print("Escoge tamaño N (NxN): ");
            int kakuroSize = readNumber();
            cd.startNewGame(difficulty, kakuroSize);
        }
        else if (choice == 2) {
            ArrayList<Integer> startedGames= cd.getStartedGames();
            for (int i = 0; i < startedGames.size(); ++i) {
                System.out.println(i + ": " + startedGames.get(i));
            }
            System.out.println("Escoja la partida que desea retomar");
            int game = readNumber();
            cd.setGame(startedGames.get(game));
        }
        cd.resolve();
        System.out.println(" -- INSTRUCCIONES JUGAR --");
        System.out.println("Para colocar un número debe colocar la posición x e y seguido del valor de la casilla.");
        System.out.println("Ejemplo: Si desea colocar un 9 en la casilla (2,6) debe poner: 2 6 9");
        System.out.println("Si desea una ayuda escriba -2");
        System.out.println("Para salir escriba -1");
        while (!cd.isFinished()) {
            writeKakuroInTerminal();
            System.out.println("");
            int x = readNumber();
            if (x == -1) {
                System.out.println("¿Desea guardar la partida? Si (1), No (0)");
                int save = readNumber();
                if (save == 1) saveGame();
                menu();
            }
            if (x == -2) {
                help();
                System.out.println(" -- INSTRUCCIONES JUGAR --");
                System.out.println("Para colocar un número debe colocar la posición x e y seguido del valor de la casilla.");
                System.out.println("Ejemplo: Si desea colocar un 9 en la casilla (2,6) debe poner: 2 6 9");
                System.out.println("Si desea una ayuda escriba -2");
                System.out.println("Para salir escriba -1");
            } else {
                int y = readNumber();
                while (!checkCoord(x, y)) {
                    x = readNumber();
                    y = readNumber();
                }
                int value = readNumber();
                while (value <= 0 || value >= 10) {
                    System.out.println("Introduzca un valor entre 1 y 9");
                    value = readNumber();
                }
                if (!cd.kakuroSetValue(x, y, value)) {
                    System.out.println("Posición de casilla incorrecto. Por favor, introduzca una posición de una casilla blanca");
                }
                else if (!cd.checkValidity(x, y, value)) {
                    System.out.println("Valor de la casilla incorrecto. Por favor, introduzca un valor correcto");
                }
            }
            System.out.println("");
        }
        System.out.println("Felicidades! Has completado el Kakuro");
        menu();
    }

    /** @brief Guardar una partida
     *
     */
    private void saveGame() {
        //TODO: Hacer la funcion entera de guardar partida
    }

    /** @brief Ayudas
     *
     * Ofrece dos tipos de ayudas al usuario:
     *  - Le comprueba si el valor de la celda blanca que ha introducido es correcto o no
     *  - Le introduce el valor correcto de la celda blanca que el usuario pide
     */
    private void help() {
        System.out.println(" -- INSTRUCCIONES AYUDA --");
        System.out.println("1. Comprobar valor: Comprobar si un valor que ha introducido es correcto");
        System.out.println("2. Obtener valor: Obtener el valor correcto de una casilla");
        int help = readNumber();
        System.out.println("Introduzca la posición x e y de la casilla para la que quiere la ayuda");
        int x = readNumber();
        int y = readNumber();
        while (!checkCoord(x, y)) {
            System.out.println("Introduzca valores correctos para x e y");
            System.out.println("La variable x tiene que ir entre 0 y " + cd.getRowSize());
            System.out.println("La variable y tiene que ir entre 0 y " + cd.getColumnSize());
            x = readNumber();
            y = readNumber();
        }
        if (help == 1) {
            int helpReply = cd.helpMyValue(x, y);
            switch (helpReply) {
                case -2:
                    System.out.println("Aún no ha introducido valor en la casilla");
                    break;
                case -1:
                    System.out.println("Las coordenadas que ha introducido son de una casilla negra");
                    break;
                case 0:
                    System.out.println("El valor que ha introducido es incorrecto");
                    break;
                case 1:
                    System.out.println("El valor que ha introducido es correcto");
                    break;
            }
        }
        else if (help == 2) {
            if (!cd.helpCorrectNumber(x, y)) {
                System.out.println("Las coordenadas que ha introducido son de una casilla negra");
            }
        }
        else {
            System.out.println("El número que ha introducido no es correcto");
            help();
        }
    }

    /** @brief Comprobadora de coordenadas
     *
     * Comprueba si las coordenadas dadas se encuentran dentro del tablero
     * @param x representa el número de la fila del tablero
     * @param y representa el número de la columna del tablero
     * @return cierto si las coordenadas son correctas y falso si las coordenadas no lo son
     */
    private boolean checkCoord(int x, int y) {
        return (x < cd.getRowSize() || x >= 0 || y < cd.getColumnSize() || y >= 0);
    }

    /** @brief Lectura de un número por terminal
     *
     * @return el número introducido por la terminal
     */
    public int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }

    /** @brief Lectura de una linea por terminal
     *
     * @return la linea introducida por la terminal
     */
    public String readLine() {
        return reader.nextLine();
    }

    /** @brief Escritura del Kakuro por terminal
     *
     * Escribe el Kakuro con los valores que tiene en ese momento por la terminal
     */
    public void writeKakuroInTerminal() {
        System.out.println(cd.getKakuroToString());
    }

    /** @brief Escritura Kakuro correcto por terminal
     *
     * Escribe la solución del Kakuro por la terminal
     */
    public void writeCorrectKakuroInTerminal() {
        System.out.println(cd.getCorrectKakuroToString());
    }

}
