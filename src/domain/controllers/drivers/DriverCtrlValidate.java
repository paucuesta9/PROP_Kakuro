package domain.controllers.drivers;

/** @file DriverCtrlValidate.java
 @brief Clase  <em>DriverCtrlValidate</em>.
 */

import domain.classes.*;
import domain.controllers.CtrlResolve;
import domain.controllers.CtrlValidate;

import javax.swing.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/** @brief Clase DriverCtrlValidate que comprueba la correctividad de las funciones del controlador CtrlValidate
 * @author Alvaro Armada Ruiz
 */

public class DriverCtrlValidate {
    private static Scanner reader = new Scanner((System.in));

    /** @brief Test de la creadora
     *
     * Comprueba si se crea correctamente
     */
    private static void testCreadora() {
        CtrlValidate ctrlValidate = new CtrlValidate();
        System.out.println("Se ha creado");
    }

    /**@Brief Test de la función validate
     * Comprueva que la función validate se ejecuta correctamente y da el resultado esperado
     * @param kakuroText representa un kakuro hecho por el usuario
     *
     * Queremos ver que si un kakuro es válido, es decir, tiene solución única. Esta función comprueba si no tiene solución, tiene una, o tiene más de una.
     */


    private  static void testValidate(String kakuroText) {
        Kakuro kakuro = new Kakuro(kakuroText);
        CtrlValidate ctrlValidate = new CtrlValidate();
        ctrlValidate.setKakuro(kakuro);
        if (!ctrlValidate.validate())
            System.out.println("El kakuro no es valido");
        else
            System.out.println("El kakuro es valido");
    }


    /**@Brief Test de la función howManyNumbers
     * Comprueva que la función howManyNumbers se ejecuta correctamente y da el resultado esperado
     * @param a es un array de enteros de medida 9 donde cada número es un 0 o un 1
     *
     * Comprueba si cuenta de forma adecuada el número de 1 en el array a.
     */
    private static void testHowManyNumbers(int a []) {
        double c = CtrlValidate.howManyNumbers(a);
        System.out.println("El array tiene "+c+" unos.");
    }

    /**@Brief Test de la función isUnique
     * Comprueva que la función isUnique se ejecuta correctamente y da el resultado esperado
     * @param a es un array de enteros de medida 9 donde cada número es un 0 o un 1
     *
     * Comprueba si retorna cierto si en el array a hay solamente un 1, y falso de cualquier otra manera.
     */
    private static void testIsUnique(int a[]) { //a tiene mida 9
        if (CtrlValidate.isUnique(a)!=0) System.out.println("Hay exactamente un 1 entre los numeros introducidos");
        else System.out.println("No hay 1's entre los numeros introducidos o bien hay mas de un 1");
    }

    /**@Brief Test de la función setDifficulty
     * Comprueva que la función setDifficulty se ejecuta correctamente y da el resultado esperado
     * @param kakuroText representa un kakuro hecho por el usuario
     *
     * Comprueba si asigna la dificultad correspondiente al kakuro introducido por el usuario
     */
    private static void testSetDifficulty(String kakuroText) {
        Kakuro kakuro = new Kakuro(kakuroText);
        CtrlValidate.setKakuro(kakuro);
        CtrlValidate.setDifficulty();
        String s;
        if (kakuro.getDifficulty()==1) s = "facil";
        else if (kakuro.getDifficulty()==2) s = "media";
        else s = "dificil";
        System.out.println("El kakuro es de dificultad "+s);
    }

    /**@Brief Test de la función validatePosSums
     * Comprueva que la función validatePosSums se ejecuta correctamente y da el resultado esperado
     *
     * @param tempBoard es un tablero auxiliar donde cada posicion consta de 9 números (-1, 0 o 1). -1 indica negra o no tratado aún, 0 indica valor no posible y 1 indica valor posible. Ejemplo = 1 0 1 0 0 0 0 0 1, valores posibles: 1, 3, 9.
     * @param posCombs es un array donde siguiendo el mismo razonamiento de tempBoard para 0 y 1 indica que valores aparecen en las combinaciones para sumar el valor de la casilla negra i, j con length casillas.
     * @param length indica como de larga es la fila o columna de blancas que sigue a la casilla negra i, j.
     * @param row 1 si trataremos la fila, 0 si trataremos la columna de la casilla negra i, j.
     * @param i posición i de la casilla negra
     * @param j posición j de la casilla negra
     *
     * Comprueba si, dado el tablero, los números que pueden sumar el valor, la casilla, si se trata por fila o columna y cuantas casillas blancas hay, se encuentran las casillas que tienen valor único y como se actualiza el tablero
     */
    private static void testValidatePosSums(int [][][] tempBoard, int [] posCombs, int length, int row, int i, int j) {
        int unique = CtrlValidate.validatePosSums(tempBoard, posCombs, length, row, i, j);
        System.out.println("Ha encontrado "+unique+" casillas con valor unico y de esta manera se ha actualizado el tablero:");
        for (i=0; i< tempBoard.length; ++i) {
            for(j=0; j< tempBoard[0].length; ++j) {
                for(int z=0; z<9; ++z) {
                    System.out.print(tempBoard[i][j][z]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**@Brief Test de la función checkForNewUniques
     * Comprueva que la función checkForNewUniques se ejecuta correctamente y da el resultado esperado
     *
     * @param tempBoard es un tablero auxiliar donde cada posicion consta de 9 números (-1, 0 o 1). -1 indica negra o no tratado aún, 0 indica valor no posible y 1 indica valor posible. Ejemplo = 1 0 1 0 0 0 0 0 1, valores posibles: 1, 3, 9.
     * @param kakuroText representa un kakuro hecho por el usuario
     *
     * Comprueba si dado el tablero y asignado el kakuro, encuentra aquellas casillas con valor único trivial, es decir, aquellas que toda su fila o columna ya tienen un valor asignado excepto ella y modifica el tablero auxiliar acorde.
     */

    private static void testCheckForNewUniques(int [][][] tempBoard, String kakuroText) {
        Kakuro kakuro = new Kakuro(kakuroText);
        CtrlValidate.setKakuro(kakuro);
        int unique = CtrlValidate.checkForNewUniques(tempBoard);
        System.out.println("Ha encontrado "+unique+" casillas con valor unico y de esta manera se ha actualizado el tablero:");
        for (int i=0; i<tempBoard.length; ++i) {
            for(int j=0; j< tempBoard[0].length; ++j) {
                for(int z=0; z<9; ++z) {
                    System.out.print(tempBoard[i][j][z]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
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
        for (int i=0; i<9; ++i) System.out.println(a[i]+" ");
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

        BlackCell b = (BlackCell) board[x][y];

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
     * Comprueba que la función computePosSums se ejecuta correctamente y da el resultado esperado
     *
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

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */

    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Validar \n 3. Dificultad \n 4. isUnique \n 5. howManyNumbers \n 6. validatePosSums \n 7. computePosSums \n 8. checkForNewUniques \n 10. intersection \n 11. iniRow \n 12. spreadUnique \n 13. Salir");
        int value = readNumber();
        String kakuro;
        while(value != 12) {
            int x,y;
            switch(value) {
                case 1:
                    System.out.println("Se llama a la creadora");
                    testCreadora();
                    break;
                case 2:
                    System.out.println("Indique el kakuro a validar");
                    kakuro = readKakuro();
                    System.out.println("Se llama a validar");
                    testValidate(kakuro);
                    break;
                case 3:
                    System.out.println("Indique el kakuro para mirar su dificultad");
                    kakuro = readKakuro();
                    System.out.println("Se llama a dificultad");
                    testSetDifficulty(kakuro);
                    break;
                case 4:
                    System.out.println("Introduce 9 enteros entre 0 y 1");
                    int a [] = new int [9];
                    for (int i=0; i<9; ++i) a[i] = readNumber();
                    System.out.println("Se llama a isUnique");
                    testIsUnique(a);
                    break;
                case 5:
                    System.out.println("Introduce 9 enteros entre 0 y 1");
                    int aa [] = new int [9];
                    for (int i=0; i<9; ++i) aa[i] = readNumber();
                    System.out.println("Se llama a howManyNumbers");
                    testHowManyNumbers(aa);
                    break;
                case 6:
                    System.out.println("Introduce tamano de tablero N x M");
                    int n = readNumber(); int m = readNumber();
                    int [][][] tempBoard= new int [n][m][9];
                    System.out.println("Introduce un tablero de la siguiente manera: \nNueve -1 si es una casilla negra y en caso de ser blanca 9 valores entre 0 y 1, si todo son zeros aun no se ha tratado, si hay 0 y unos indica que los 1 son valores posibles");
                    for (int i=0; i<n; ++i) {
                        for(int j=0; j<m; ++j) {
                            for(int z=0; z<9; ++z) {
                                tempBoard[i][j][z] = readNumber();
                            }
                        }
                    }
                    System.out.println("Introduce para que casilla negra(i, j) quieres encontrar valores unicos");
                    int i = readNumber(); int j =readNumber();
                    System.out.println("Introduce un 1 para comprobar la fila o un 0 para comprobar la columna, y tambien que valor de suma tiene la casilla negra, y como de larga es la fila o la columna");
                    int row = readNumber(); int valuee = readNumber(); int length = readNumber();
                    int [] posCombs = CtrlValidate.computePosSums(valuee, length,0);
                    System.out.println("Se llama a validatePosSums");
                    testValidatePosSums(tempBoard, posCombs, length, row, i, j);
                    break;
                case 7:
                    System.out.println("Se llama a computePosSums");
                    testComputePosSums();
                    break;
                case 8:
                    System.out.println("Indique el kakuro para mirar cuantas casillas con valor unico trivial se pueden encontrar");
                    kakuro = readKakuro();
                    System.out.println("Introduce tamano de tablero N x M");
                    int nn = readNumber(); int mm = readNumber();
                    int [][][] tempBoardd= new int [nn][mm][9];
                    System.out.println("Introduce un tablero de la siguiente manera: \nNueve -1 si es una casilla negra y en caso de ser blanca 9 valores entre 0 y 1, si todo son zeros aun no se ha tratado, si hay 0 y unos indica que los 1 son valores posibles");
                    for (int ii=0; ii<nn; ++ii) {
                        for(int jj=0; jj<mm; ++jj) {
                            for(int zz=0; zz<9; ++zz) {
                                tempBoardd[ii][jj][zz] = readNumber();
                            }
                        }
                    }
                    System.out.println("Se llama a checkForNewUniques");
                    testCheckForNewUniques(tempBoardd, kakuro);
                    break;
                case 9:
                    System.out.println();
                    System.out.println("Se llama a intersection");
                    testIntersection();
                    break;
                case 10:
                    System.out.println();
                    System.out.println("Se llama a iniRow");
                    testIniRow();
                    break;
                case 11:
                    System.out.println();
                    System.out.println("Se llama a spreadUnique");
                    testSpreadUnique();
                    break;
                default:
                    System.out.println("El número introduciodo es incorrecto");
                    break;

            }
            System.out.print("\n");
            System.out.println("Opciones: \n 1. Creadora \n 2. Validar \n 3. Dificultad \n 4. isUnique \n 5. howManyNumbers \n 6. validatePosSums \n 7. computePosSums \n 8. checkForNewUniques \n 10. intersection \n 11. iniRow \n 12. spreadUnique \n 13. Salir");
            value = readNumber();
        }
    }

    /** @brief Lee un kakuro de consola
     *
     * @return el kakuro leído
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

