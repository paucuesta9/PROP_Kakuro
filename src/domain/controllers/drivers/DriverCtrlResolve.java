package domain.controllers.drivers;

/** @file DriverCtrlResolve.java
 * @brief Clase <em>DriverCtrlResolve</em>.
 */


import domain.classes.*;
import domain.controllers.CtrlResolve;
import domain.controllers.CtrlValidate;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/** @brief Clase DriverCtrlResolve que comprueba la correctividad de las funciones del controlador CtrlResolve
 * @author Pol Vallespí Soro
 */
public class DriverCtrlResolve {

    private static Scanner reader = new Scanner((System.in));

    public static final String KAKURO_TEXT_ENUNCIADO = "9,9\n" +
            "*,*,C19,C12,*,*,*,C7,C10\n" +
            "*,F14,?,?,C4,C11,C17F4,?,?\n" +
            "*,C7F36,?,?,?,?,?,?,?\n" +
            "F12,?,?,F10,?,?,?,C25,C14\n" +
            "F3,?,?,C20,C11F20,?,?,?,?\n" +
            "F17,?,?,?,?,C8,F6,?,?\n" +
            "*,C11,C7F13,?,?,?,C4F10,?,?\n" +
            "F28,?,?,?,?,?,?,?,*\n" +
            "F6,?,?,*,*,F8,?,?,*";

    private static void testCreadora() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        CtrlResolve ctrlResolve = new CtrlResolve(k);
        System.out.println("Se ha creado");
    }

    /**@Brief Test de la función resolve
     * Comprueva que la función resolve se ejecuta correctamente y da el resultado esperado
     *
     *
     * Queremos ver que si un kakuro tiene solución esta función la encuentra. En caso de no tener solución, nos informa de ello.
     */
    private  static void testResolve() {
        System.out.println("Indique un kakuro para realizar el test: ");
        String kakuroText = readKakuro();
        Kakuro kakuro = new Kakuro(kakuroText);
        CtrlResolve c = new CtrlResolve(kakuro);
        boolean b = c.resolve();

        if(b) {
            System.out.println("Se ha encontrado la siguiente solucion: ");
            String s = kakuro.correctToString();
            System.out.print(s);
        }
        else System.out.println("No se ha encontrado solucion");
    }

    /**@Brief Test de la función isUnique
     *
     * Comprueba si retorna cierto si en el array a hay solamente un 1, y falso de cualquier otra manera.
     */

    private static void testIsUnique() {
        System.out.println("Introduzca nueve numeros (0 o 1)");
        int a [] = new int [9];
        for (int i=0; i<9; ++i) a[i] = readNumber();

        int value = CtrlResolve.isUnique(a);
        if (value == 0) System.out.println("No habia unos o habia mas de un uno");
        else System.out.println("Habia un uno en la posicion "+(value-1));
    }

    /** @brief Comprueba que la funcion hace correctamente la insterseccion entre dos arrays
     *
     */
    private static void testIntersection() {
        System.out.println("Introduzca nueve numeros (0 o 1)");
        int a [] = new int [9];
        for (int i=0; i<9; ++i) a[i] = readNumber();

        System.out.println("Introduzca nueve numeros (0 o 1)");
        int b [] = new int [9];
        for (int i=0; i<9; ++i) b[i] = readNumber();

        CtrlResolve.intersection(a, b);

        System.out.println("Esta es la interseccion de los dos arrays anteriores");
        for (int i=0; i<9; ++i) System.out.print(a[i]+" ");
        System.out.println();
    }

    /** @brief Comprueba que la función recorre la run de la fila negra y hace la interseccion entre el valor de las casillas negras y los valores posibles, actualizando las casillas con valor único
     *
     */
    private static void testIniRow() {
        System.out.println("Introduzca el numero de filas y el numero de columnas");
        int r = readNumber(); int c = readNumber();
        System.out.println("Introduzca un kakuro");
        String k = readKakuro();
        Kakuro kakuro = new Kakuro(k);
        Cell[][] board = kakuro.getBoard();
        System.out.println("Introduce el tablero del kakuro de la siguiente forma, por cada casilla negra 9 ceros. \nPor cada casilla blanca un 1 si el valor de la posicion mas uno en la que se encuentra el 1 es un valor posible para esa casilla, 0 por el contrario");
        int tempBoard [][][] = new int [r][c][9];
        for (int i=0; i<r; ++i) {
            for (int j=0; j<c; ++j) {
                for (int z=0; z<9; ++z) tempBoard[i][j][z] = readNumber();
            }
        }
        System.out.println("Introduzca las coordenadas de la casilla negra a probar");
        int x = readNumber(); int y = readNumber();
        System.out.println("Introduzca como de larga es la run asociada a la casilla negra");
        int numWhiteRun = readNumber();

        BlackCell b = (BlackCell) board[x][y];

        Set<Pair> uniques = new HashSet<>();

        CtrlResolve.iniRow(x, y, CtrlResolve.computePosSums(b.getRow(), numWhiteRun, 0), numWhiteRun, tempBoard, uniques, board);
        System.out.println("Ha encontrado "+uniques.size()+" casillas unicas");
        System.out.println("Asi ha quedado tempboard");

        for (int i=0; i<r; ++i) {
            for (int j=0; j<c; ++j) {
                for (int z=0; z<9; ++z) System.out.print(tempBoard[i][j][z]+" ");
                System.out.print("    ");
            }
            System.out.println();
        }

    }

    /** @brief Comprueba que la función, dada una casilla con valor único y un tempBoard, se propagan restricciones de forma adecuada
     *
     */
    private static void testSpreadUnique() {
        System.out.println("Introduzca el numero de filas y el numero de columnas");
        int r = readNumber(); int c = readNumber();
        System.out.println("Introduzca un kakuro");
        String k = readKakuro();
        Kakuro kakuro = new Kakuro(k);
        Cell[][] board = kakuro.getBoard();
        System.out.println("Introduce el tablero del kakuro de la siguiente forma, por cada casilla negra 9 ceros. \nPor cada casilla blanca un 1 si el valor de la posicion mas uno en la que se encuentra el 1 es un valor posible para esa casilla, 0 por el contrario");
        int tempBoard [][][] = new int [r][c][9];
        for (int i=0; i<r; ++i) {
            for (int j=0; j<c; ++j) {
                for (int z=0; z<9; ++z) tempBoard[i][j][z] = readNumber();
            }
        }

        System.out.println("Introduzca cuantas casillas con valor fijado tiene el kakuro");
        int n = readNumber();

        for (int i=0; i<n; ++i) {
            System.out.println("Introduzca las coordenadas y el valor de la casilla blanca con valor unico");
            int x = readNumber(); int y = readNumber(); int v = readNumber();
            WhiteCell w = (WhiteCell) board[x][y];
            w.setCorrectValue(v);
        }

        System.out.println("Introduzca las coordenadas de la casilla blanca unica a probar");
        int x = readNumber(); int y = readNumber();

        Set<Pair> uniques = new HashSet<>();

        CtrlResolve.spreadUnique(x, y, tempBoard, board, uniques);
        System.out.println("Ha encontrado "+uniques.size()+" casillas unicas");
        System.out.println("Asi ha quedado tempboard");

        for (int i=0; i<r; ++i) {
            for (int j=0; j<c; ++j) {
                for (int z=0; z<9; ++z) System.out.print(tempBoard[i][j][z]+" ");
                System.out.print("    ");
            }
            System.out.println();
        }
    }

    /**@Brief Test de la función computePosSums
     *
     * Comprueba si dado un valor x, un número de casillas y un valor de no, deja en un array de 9 posiciones un 1 para los números que aparezcan en las combinacione y un 0 para aquellos que no
     */
    private static void testComputePosSums() {
        System.out.println("Introduce un entero y un numero de casillas, y un valor distinto de 0 si quieres que en esas casillas ya haya un numero fijo, 0 si no.\n ");
        int x = readNumber(); int n = readNumber(); int no = readNumber();
        int [] a;
        if (no == 0) a = CtrlValidate.computePosSums(x,n,no);
        else a = CtrlValidate.computePosSums(x,n-1,no);
        if (no==0) System.out.println("Estos son los valores que que tienen una combinacion para sumar "+x+" en "+n+" casillas");
        else System.out.println("Estos son los valores que que tienen una combinacion para sumar "+x+" en "+n+" casillas sabiendo que tenemos fijo el numero "+no);
        System.out.println("Un 1 en la posicion 0 indica que 0+1, es decir 1, aparece en la combinacion, 0 que no aparece");
        for (int i=0; i<9; ++i) System.out.print(a[i]+" ");
        System.out.print("\n");

    }

    /**@brief función main del driver CtrlResolve
     *
     * Nos permite escoger el test que queremos realizar
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Resolver \n 3. IsUnique \n 4. Intersection \n 5. IniRow \n 6. ComputePosSums \n 7. SpreadUnique \n 8. Salir");
        int value = readNumber();
        while(value != 8) {
            switch(value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a la creadora");
                    testCreadora();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a resolver");
                    testResolve();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Se llama a isUnique");
                    testIsUnique();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Se llama a intersection");
                    testIntersection();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Se llama a iniRow");
                    testIniRow();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Se llama a computePosSums");
                    testComputePosSums();
                    break;
                case 7:
                    System.out.println();
                    System.out.println("Se llama a spreadUnique");
                    testSpreadUnique();
                    break;
                default:
                    System.out.println();
                    System.out.println("El numero introduciodo es incorrecto");
                    break;

            }
            System.out.println();
            System.out.println("Opciones: \n 1. Creadora \n 2. Resolver \n 3. IsUnique \n 4. Intersection \n 5. IniRow \n 6. ComputePosSums \n 7. SpreadUnique \n 8. Salir");
            value = readNumber();
        }
    }

    /**@brief función que nos permite leer un kakuro en formato string
     *
     * @return kakuro en formato string
     */
    public static String readKakuro() {
        StringBuilder content = new StringBuilder();
        String[] valuesSize = reader.next().split(",");
        int row = Integer.parseInt(valuesSize[0]);
        int column = Integer.parseInt(valuesSize[1]);
        content.append((row) + "," + (column) + "\n");
        for (int i = 0; i < row; ++i)
            content.append(reader.next()).append("\n");
        return content.toString();
    }

    /**@brief función que nos permite leer un entero del terminal o un fichero
     *
     * @return el entero leido
     */
    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }
}
