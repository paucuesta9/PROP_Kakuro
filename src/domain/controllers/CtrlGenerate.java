package domain.controllers;

/** @file CtrlGenerate.java
 @brief Clase <em>CtrlGenerate</em>.
 */

import domain.classes.BlackCell;
import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.WhiteCell;

import java.lang.reflect.Array;
import java.util.*;

/** @brief Clase CtrlGenerate que contiene los atributos y metodos para la funcionalidad de generar
 * @author Pol Vallespí Soro y Alvaro Armada Ruiz
 */
public class CtrlGenerate {
    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */

    private static Kakuro currentKakuro;

    private static int[][][] mat = new int[45][9][];
    private static int[][][] tempBoard;

    private static int[][] posComb = new int[][]{ //possible values for sum using n cels
            {}, //con zero
            {1, 2, 3, 4, 5, 6, 7, 8, 9}, //una casilla
            {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, //dos casillas
            {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24}, //tres casillas
            {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}, //cuatro casillas
            {15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 36}, //cinco casillas
            {21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39}, //seis casillas
            {28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42}, //siete casillas
            {36, 37, 38, 39, 40, 41, 42, 43, 44}, //ocho casillas
            {45} //nueve casillas
    };
    /**
     * @brief Creadora por defecto
     */
    public CtrlGenerate() {
    }

    /**
     * @param kakuro instància de Kakuro
     * @brief Setter de Kakuro
     * <p>
     * Settea un Kakuro al controlador para poder trabajar con él
     */
    public static void setKakuro(Kakuro kakuro) {
        currentKakuro = kakuro;
    }

    /**@brief función para saber el número de celdas blancas situadas en la misma run vertical y antes que una celda dada.
     *
     * @param board representa un tablero con celdas blancas y negras
     * @param i indica la fila de la casilla que estamos visitando
     * @param j indica la columna de la casilla que estamos visitando
     * @return número de casillas blancas de la run vertical situadas antes que una casilla dada.
     */
    public static int countWhiteCellsV(Cell[][] board, int i, int j) {
        if (!board[i][j].isWhite()) return 0;
        return 1 + countWhiteCellsV(board, i - 1, j);
    }

    /**@brief función para saber el número de celdas blancas situadas en la misma run horizontal y antes que una celda dada.
     *
     * @param board representa un tablero con celdas blancas y negras
     * @param i indica la fila de la casilla que estamos visitando
     * @param j indica la columna de la casilla que estamos visitando
     * @return número de casillas blancas de la run horizontal situadas antes que una casilla dada.
     */
    public static int countWhiteCellsH(Cell[][] board, int i, int j) {
        if (!board[i][j].isWhite()) return 0;
        return 1 + countWhiteCellsH(board, i, j - 1);
    }

    /**@brief función que cuenta el número de celdas blancas de cada run vertical
     *
     * @param board representa un tablero con celdas blancas y negras
     * @param tempBoard matriz de 3 dimensiones con el mismo número de filas y columnas que el tablero, pero en cada "celda" tiene un vector de 9 posiciones
     *
     * Esta función buscará las celdas negras de board y, dejará en su correspondiente celda de tempBoard el número de celdas blancas pertenecientes a la
     *                  run vertical.
     */
    public static void nineCellsRow(Cell[][] board, int[][][] tempBoard) { //horizontal
        int lastC = 0;
        int lastR = 0;
        int count = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (!board[i][j].isWhite()) {
                    if (count != 0) {
                        tempBoard[lastR][lastC][1] = count;
                    }
                    count = 0;
                    lastC = j;
                    lastR = i;
                } else ++count;
            }
        }
        if (count == 9) {
            BlackCell b = (BlackCell) board[lastR][lastC];
            b.setRow(45);
            tempBoard[lastR][lastC][1] = 9;
        } else {
            tempBoard[lastR][lastC][1] = count;
        }
    }

    /**@brief función que cuenta el número de celdas blancas de cada run horizontal
     *
     * @param board representa un tablero con celdas blancas y negras
     * @param tempBoard matriz de 3 dimensiones con el mismo número de filas y columnas que el tablero, pero en cada "celda" tiene un vector de 9 posiciones
     *
     * Esta función buscará las celdas negras de board y, dejará en su correspondiente celda de tempBoard el número de celdas blancas pertenecientes a la
     *                  run horizontal.
     */
    public static void nineCellsCol(Cell[][] board, int[][][] tempBoard) { //vertical
        int lastC = 0;
        int lastR = 0;
        int count = 0;
        for (int j = 0; j < board[0].length; ++j) {
            for (int i = 0; i < board.length; ++i) {
                if (!board[i][j].isWhite()) {
                    if (count != 0) {
                        tempBoard[lastR][lastC][0] = count;
                    }
                    count = 0;
                    lastC = j;
                    lastR = i;
                } else ++count;
            }
        }
        if (count != 0) {
            if (count == 9) {
                BlackCell b = (BlackCell) board[lastR][lastC];
                b.setColumn(45);
                tempBoard[lastR][lastC][0] = 9;
            } else {
                tempBoard[lastR][lastC][0] = count;
            }
        }
    }

    /**@brief función que nos permite encontrar todos los valores con los que se puede hacer una combinación de menos de 9 valores que sume num
     *
     * @param arr vector de enteros lim posiciones
     * @param index indica el número de valores
     * @param num indica la suma hasta el momento
     * @param reducedNum indica el numero que queremos sumar
     * @param lim indica el maximo numero de valores
     * @param posSums indica nos valores que son posibles
     * @param no indica un valor que no se puede usar
     */
    private static void computePosSumsRec(int arr[], int index, int num, int reducedNum, int lim, int posSums[], int no) {
        if (reducedNum < 0 || index > lim)
            return;
        if (reducedNum == 0 && index == lim) {
            for (int i = 0; i < index; i++)
                posSums[arr[i] - 1] = 1;
            return;
        }
        int prev = (index == 0) ? 0 : arr[index - 1];
        if (prev == 9 || index >= lim) return;
        for (int k = prev + 1; k <= 9; k++) {
            if (k != no) {
                arr[index] = k;
                computePosSumsRec(arr, index + 1, num, reducedNum - k, lim, posSums, no);
            }
        }
    }

    /**@brief función para encontrar los n valores con los que se puede hacer una combinación que sume x.
     *
     * @param x valor del que queremos encontrar una combinación
     * @param n número de valores necesarios
     * @param no elemento que no queremos usar
     * @return un vector de 9 posiciones donde, si vec[i]=1 quiere decir que con el valor i existe una combinación de n-1 valores más que suma x
     *
     * Esta función inicializa los vectores y llama a computePosSumsRec para hacer los cálculos
     */
    public static int[] computePosSums(int x, int n, int no) { //calcula combinaciones de numeros que suman x con n numeros. deja los resultados en posSums.
        int[] posSums = new int[9];
        for (int i = 0; i < 9; ++i) posSums[i] = 0;
        int[] arr = new int[n];
        computePosSumsRec(arr, 0, x - no, x - no, n, posSums, no);
        return posSums;
    }

    /**@brief función para saber si todos los valores posibles de una celda son 0.
     *
     * @param tempBoard matriz de 3 dimensiones  que guarda los valores posibles de cada celda blanca, si ya han sido asignados
     * @param i indica la fila de la celda que estamos tratando
     * @param j indica la columna de la celda que estamos trantando
     * @return true si no hay valores posibles, false si hay algún valor posible
     *
     * Esta función nos permite saber si una celda ya ha sido visitada o no
     */
    public static  boolean allZero(int[][][] tempBoard, int i, int j) {
        int temp = 0;
        for (int z = 0; z < 9; ++z) {
            if (tempBoard[i][j][z] == 1) ++temp;
        }
        return (temp == 0);
    }

    /**@brief esta función nos permite saber si hay valores en común entre dos vectores y, si se da el caso, saber si el segundo no contiene valores posibles
     *
     * @param a vector de enteros con 1s y/o 0s
     * @param b vector de enteros con 1s y/o 0s
     * @return true si el segundo vector no tiene valores posibles o si tienen algún valor en común, si no false
     *
     * Esta función nos permite saber si hay alguna combinación de números (representados por el vector a) que sume x(valor que no se tiene en cuenta en esta función) que tenga
     * algún valor en común con los posibles valores de una celda en concreto (representados por el vector b). Nos deja en b los valores en común.
     */
    public  static boolean intersection3(int a[], int b[]) { //modificamos el 2. //aqui NO estamos seguro que b[] aka temboard[][][] ya ha sido tratado.
        boolean allZero = true;
        boolean found = false;
        for (int i = 0; i < 9; ++i) {
            if (b[i] == 1) allZero = false;
        }

        if (allZero) {
            found = true;
            for (int i = 0; i < 9; ++i) b[i] = a[i];
        } else {
            for (int i = 0; i < 9; ++i) {
                if (a[i] == b[i] && a[i] == 1) found = true;
            }
            if (found) {
                for (int i = 0; i < 9; ++i) {
                    if (a[i] == b[i] && a[i] == 1) ;
                    else b[i] = 0;
                }
            }
        }
        return found;
    }

    /**@brief esta función nos permite saber si hay valores en común entre dos vectores
     *
     * @param a vector de enteros con 1s y/o 0s
     * @param b vector de enteros con 1s y/o 0s
     * @return true si hay algún valor en común, false si no
     * Esta función nos permite saber si hay alguna combinación de números (representados por el vector a) que sume x(valor que no se tiene en cuenta en esta función) que tenga
     * algún valor en común con los posibles valores de una celda en concreto (representados por el vector b). Nos deja en b los valores en común.
     */
    public  static boolean intersection2(int a[], int b[]) { //modificamos el 2. //aqui estamos seguro que b[] aka temboard[][][] ya ha sido tratado.
        boolean found = false;
        for (int i = 0; i < 9; ++i) {
            if (a[i] == b[i] && a[i] == 1) found = true;
        }
        if (found) {
            for (int i = 0; i < 9; ++i) {
                if (a[i] == b[i] && a[i] == 1) ;
                else b[i] = 0;
            }
        }
        return found;
    }

    /**@brief función que nos indica si dos vectores solo tienen un número en común
     *
     * @param valuesH vector de enteros con 1s y 0s
     * @param valuesV vector de enteros con 1s y 0s
     * @return si solo hay un valor en común nos retorna la posición de este valor, -1 si no hay valores en común o si hay más de uno
     */
    public static int intersection(int[] valuesH, int[] valuesV) {
        int c = 0;
        int x = -1;
        for (int i = 0; i < 9 && c != 2; ++i) {
            if (valuesH[i] == 1 && valuesV[i] == 1) {
                c++;
                x = i;
            }
        }
        if (c == 1) return x;
        return -1;
    }

    /**@brief función que nos indica si solo hay un valor posible
     *
     * @param a vector de enteros con 1s y/o 0s
     * @return true si solo hay un valor i tal que vec[i]=1, false si hay más de uno o no hay
     */
    public  static boolean isUnique(int[] a) {
        int b = 0;
        for (int i = 0; i < 9; ++i)
            if (a[i] == 1) ++b;

        return (b == 1);
    }

    private static boolean fillBoardAux2(Cell[][] board, int row, int col, int totalWhites, int whites) {
        if(totalWhites == whites) return true;
        boolean stop = false;
        int j = col;
        for(int i = row; i < board.length && !stop; ++i) {
            if(i != row) j = 0;
            while( j < board[0].length && !stop) {
                if(board[i][j].isWhite() && ((WhiteCell)board[i][j]).getCorrectValue() == 0 && !allZero(tempBoard,i,j)){
                    int nH = 0;
                    int nV = 0;
                    int lastCol = 0;
                    int lastRow = 0;
                    boolean found = false;
                    for (int k = j; k >= 0 && !found; --k) {
                        if (!board[i][k].isWhite()) {
                            found = true;
                            nH = tempBoard[i][k][1];
                            lastRow = k;
                        }
                    }
                    found = false;
                    for (int k = i; k >= 0 && !found; --k) {
                        if (!board[k][j].isWhite()) {
                            found = true;
                            nV = tempBoard[k][j][0];
                            lastCol = k;
                        }
                    }
                    if (tempBoard[i][lastRow][3] != 0 && tempBoard[lastCol][j][2] != 0) {
                        if (isUnique(tempBoard[i][j])) {
                            stop = true;
                            WhiteCell w = (WhiteCell) board[i][j];
                            //guardar el estado anterior
                            int [][] copyRow = new int[nH+1][9];
                            int [][] copyCol = new int[nV+1][9];

                            int r = lastRow+1;
                            int index = 0;
                            while(r < board.length && r < lastRow+nV && board[r][j].isWhite()) {
                                copyCol[index] = tempBoard[r][j];
                                ++index;
                                ++r;
                            }
                            r = lastCol+1;
                            index = 0;
                            while(r < board.length && r < lastCol+nH && board[i][r].isWhite()) {
                                copyRow[index] = tempBoard[i][r];
                                ++index;
                                ++r;
                            }

                            int v = 0;
                            for (int oo = 0; oo<9; ++oo) {
                                if (tempBoard[i][j][oo] == 1) {
                                    v = oo;
                                }
                            }
                            boolean allOk = true;
                            int f = 1;
                            int prueba2 [] = computePosSums(tempBoard[i][lastRow][3], nH-1, v+1);
                            while (j + f < board.length && board[i][j + f].isWhite() && allOk) {
                                allOk = intersection2(prueba2, tempBoard[i][j + f]);
                                ++f;
                            }
                            f = 1;
                            while (j - f >= 0 && board[i][j - f].isWhite() && allOk) {
                                allOk = intersection2(prueba2, tempBoard[i][j - f]);
                                ++f;
                            }
                            f = 1;
                            prueba2 = computePosSums(tempBoard[lastCol][j][2], nV-1, v+1);
                            while (i + f < board.length && board[i + f][j].isWhite() && allOk) {
                                allOk = intersection2(prueba2, tempBoard[i + f][j]);
                                ++f;
                            }
                            f = 1;
                            while (i - f >= 0 && board[i - f][j].isWhite() && allOk) {
                                allOk = intersection2(prueba2, tempBoard[i - f][j]);
                                ++f;
                            }
                            if (allOk) {
                                w.setCorrectValue(v+1);
                                if (fillBoardAux2(board, 1, 1, totalWhites, whites + 1)) return true;
                                w.setCorrectValue(0);
                            }

                            r = lastRow+1;
                            index = 0;
                            while(r < board.length && r < lastRow+nV && board[r][j].isWhite()) {
                                tempBoard[r][j] =copyCol[index];
                                ++index;
                                ++r;
                            }
                            r= lastCol+1;
                            index = 0;
                            while(r < board.length && r < lastCol+nH && board[i][r].isWhite()) {
                                tempBoard[i][r] = copyRow[index];
                                ++index;
                                ++r;
                            }
                        }
                    }
                    else if (tempBoard[i][lastRow][3] != 0) {
                        stop = true;
                        WhiteCell w = (WhiteCell) board[i][j];
                        //guardar el estado actual
                        int [][] copyRow = new int[nH+1][9];
                        int [][] copyCol = new int[nV+1][9];
                        int r = lastRow+1;
                        int index = 0;
                        while(r < board.length && r < lastRow+nV && board[r][j].isWhite()) {
                            copyCol[index] = tempBoard[r][j];
                            ++index;
                            ++r;
                        }
                        r = lastCol+1;
                        index = 0;
                        while(r < board.length && r < lastCol+nH && board[i][r].isWhite()) {
                            copyRow[index] = tempBoard[i][r];
                            ++index;
                            ++r;
                        }
                        for (int y = 0; y < posComb[nV].length; ++y) {
                            boolean allOk = true;
                            tempBoard[lastCol][j][2] = posComb[nV][y];
                            int[] prueba = mat[posComb[nV][y]-1][nV-1];
                            int value = intersection(prueba, tempBoard[i][j]);

                            int[] prueba2 = {0,0,0,0,0,0,0,0,0};

                            if (value != -1) {
                                tempBoard[i][j][value]=1;
                                for(int xx = 0; xx<9; ++xx) {
                                    if(xx!=value) tempBoard[i][j][xx] = 0;
                                }
                                prueba2 = computePosSums(tempBoard[i][lastRow][3], nH - 1, value + 1);
                                prueba = computePosSums(posComb[nV][y], nV - 1, value + 1);
                            }
                            else allOk = false;
                            if(allOk) {
                                int f = 0;
                                for (int pp = 0; pp < 9 && allOk; ++pp) {
                                    if (prueba[pp] == 1) ++f;
                                }
                                if (f == 0) allOk = false;

                                f = 0;
                                for (int pp = 0; pp < 9 && allOk; ++pp) {
                                    if (prueba2[pp] == 1) ++f;
                                }

                                if (f == 0) allOk = false;
                                if(allOk) {
                                    f = 1;
                                    while (j + f < board.length && board[i][j + f].isWhite() && allOk) {
                                        allOk = intersection2(prueba2, tempBoard[i][j + f]);
                                        ++f;
                                    }
                                    f = 1;
                                    while (j - f >= 0 && board[i][j - f].isWhite() && allOk) {
                                        allOk = intersection2(prueba2, tempBoard[i][j - f]);
                                        ++f;
                                    }
                                    f = 1;
                                    while (i + f < board.length && board[i + f][j].isWhite() && allOk) {
                                        allOk = intersection3(prueba, tempBoard[i + f][j]);
                                        ++f;
                                    }
                                    f = 1;
                                    while (i - f >= 0 && board[i - f][j].isWhite() && allOk) {
                                        allOk = intersection3(prueba, tempBoard[i - f][j]);
                                        ++f;
                                    }

                                    if (allOk) {
                                        w.setCorrectValue(value + 1);
                                        if (fillBoardAux2(board, i, j+1, totalWhites, whites + 1)) return true;
                                        w.setCorrectValue(0);
                                    }
                                    //reestablecer el estado anterior
                                    r = lastRow + 1;
                                    index = 0;
                                    while (r < board.length && r < lastRow + nV && board[r][j].isWhite()) {
                                        tempBoard[r][j] = copyCol[index];
                                        ++index;
                                        ++r;
                                    }
                                    r = lastCol + 1;
                                    index = 0;
                                    while (r < board.length && r < lastCol + nH && board[i][r].isWhite()) {
                                        tempBoard[i][r] = copyRow[index];
                                        ++index;
                                        ++r;
                                    }
                                }
                            }
                        }

                    }
                    else {
                        stop = true;
                        WhiteCell w = (WhiteCell) board[i][j];
                        //guardar el estado anterior
                        int [][] copyRow = new int[nH+1][9];
                        int [][] copyCol = new int[nV+1][9];

                        int r = lastRow+1;
                        int index = 0;
                        while(r < board.length && r < lastRow+nV && board[r][j].isWhite()) {
                            copyCol[index] = tempBoard[r][j];
                            ++index;
                            ++r;
                        }
                        r = lastCol+1;
                        index = 0;
                        while(r < board.length && r < lastCol+nH && board[i][r].isWhite()) {
                            copyRow[index] = tempBoard[i][r];
                            ++index;
                            ++r;
                        }
                        for (int y = 0; y < posComb[nH].length; ++y) {
                            boolean allOk = true;
                            tempBoard[i][lastRow][3] = posComb[nH][y];
                            int[] prueba = mat[posComb[nH][y]-1][nH-1];

                            int value = intersection(prueba, tempBoard[i][j]);

                            int[] prueba2 = {0,0,0,0,0,0,0,0,0};
                            if (value != -1) {
                                tempBoard[i][j][value]=1;
                                for(int xx = 0; xx<9; ++xx) {
                                    if(xx!=value) tempBoard[i][j][xx] = 0;
                                }
                                prueba2 = computePosSums(tempBoard[lastCol][j][2], nV - 1, value + 1);
                                prueba = computePosSums(posComb[nH][y], nH - 1, value + 1);
                            }
                            else allOk = false;
                            if(allOk) {
                                int f = 0;
                                for (int pp = 0; pp < 9 && allOk; ++pp) {
                                    if (prueba[pp] == 1) ++f;
                                }
                                if (f == 0) allOk = false;
                                f = 0;
                                for (int pp = 0; pp < 9 & allOk; ++pp) {
                                    if (prueba2[pp] == 1) ++f;
                                }
                                if (f == 0) allOk = false;
                                f = 1;
                                while (j + f < board.length && board[i][j + f].isWhite() && allOk) {
                                    allOk = intersection3(prueba, tempBoard[i][j + f]);
                                    ++f;
                                }
                                f = 1;
                                while (j - f >= 0 && board[i][j - f].isWhite() && allOk) {
                                    allOk = intersection3(prueba, tempBoard[i][j - f]);
                                    ++f;
                                }
                                f = 1;
                                while (i + f < board.length && board[i + f][j].isWhite() && allOk) {
                                    allOk = intersection2(prueba2, tempBoard[i + f][j]);
                                    ++f;
                                }
                                f = 1;
                                while (i - f >= 0 && board[i - f][j].isWhite() && allOk) {
                                    allOk = intersection2(prueba2, tempBoard[i - f][j]);
                                    ++f;
                                }
                                if (allOk) {
                                    w.setCorrectValue(value + 1);
                                    if (fillBoardAux2(board, i, j+1, totalWhites, whites + 1)) return true;
                                    w.setCorrectValue(0);
                                }
                                r = lastRow + 1;
                                index = 0;
                                while (r < board.length && r < lastRow + nV && board[r][j].isWhite()) {
                                    tempBoard[r][j] = copyCol[index];
                                    ++index;
                                    ++r;
                                }
                                r = lastCol + 1;
                                index = 0;
                                while (r < board.length && r < lastCol + nH && board[i][r].isWhite()) {
                                    tempBoard[i][r] = copyRow[index];
                                    ++index;
                                    ++r;
                                }
                            }
                        }

                    }
                }
                ++j;
            }
        }
        return false;
    }

    private static boolean fillBoardAux(Cell[][] board, int row, int col, int totalWhites, int whites) {
        int nH = 0;
        int nV = 0;
        int lastCol = 0;
        int lastRow = 0;
        boolean stop = false;
        for(int i = 1; i < board.length && !stop; ++i) {
            for(int j = 1; j < board[0].length && !stop; ++j) {
                if(board[i][j].isWhite()) {
                    boolean found = false;
                    for (int k = j; k >= 0 && !found; --k) {
                        if (!board[i][k].isWhite()) {
                            found = true;
                            nH = tempBoard[i][k][1];
                            lastRow = k;
                        }
                    }
                    found = false;
                    for (int k = i; k >= 0 && !found; --k) {
                        if (!board[k][j].isWhite()) {
                            found = true;
                            nV = tempBoard[k][j][0];
                            lastCol = k;
                        }
                    }
                    if (nV != 9 && nH != 9) {
                        stop = true; //si no es capaz de generar una solucion con todas las combinaciones de sumas posibles en una casilla, no va a poder nunca
                        int [][] copyRow = new int[nH+1][9];
                        int [][] copyCol = new int[nV+1][9];

                        int r = lastRow+1;
                        int index = 0;
                        while(r < board.length && r < lastRow+nV && board[r][j].isWhite()) {
                            copyCol[index] = tempBoard[r][j];
                            ++index;
                            ++r;
                        }
                        r= lastCol+1;
                        index = 0;
                        while(r < board.length && r < lastCol+nH && board[i][r].isWhite()) {
                            copyRow[index] = tempBoard[i][r];
                            ++index;
                            ++r;
                        }
                        for (int x = 0; x < posComb[nH].length; ++x) {
                            int[] valuesH = mat[posComb[nH][x]-1][nH-1];
                            for (int y = posComb[nV].length-1; y >= 0 ; --y) {
                                int[] valuesV = mat[posComb[nV][y]-1][nV-1];
                                int value = intersection(valuesH, valuesV);
                                if (value != -1) {
                                    tempBoard[i][j][value] = 1;
                                    tempBoard[lastCol][j][2] = posComb[nV][y];
                                    tempBoard[i][lastRow][3] = posComb[nH][x];
                                    int[] auxH = computePosSums(posComb[nH][x], nH - 1, value + 1);
                                    int[] auxV = computePosSums(posComb[nV][y], nV - 1, value + 1);

                                    int f = 1;
                                    while (j + f < board.length && board[i][j + f].isWhite()) {
                                        for (int tt = 0; tt < 9; ++tt)
                                            tempBoard[i][j + f][tt] = auxH[tt];
                                        ++f;
                                    }
                                    f = 1;
                                    while (j - f >= 0 && board[i][j - f].isWhite()) {
                                        for (int tt = 0; tt < 9; ++tt) tempBoard[i][j - f][tt] = auxH[tt];
                                        ++f;
                                    }
                                    f = 1;
                                    while (i + f < board.length && board[i + f][j].isWhite()) {
                                        for (int tt = 0; tt < 9; ++tt) tempBoard[i + f][j][tt] = auxV[tt];
                                        ++f;
                                    }
                                    f = 1;
                                    while (i - f > 0 && board[i - f][j].isWhite()) {
                                        for (int tt = 0; tt < 9; ++tt) tempBoard[i - f][j][tt] = auxV[tt];
                                        ++f;
                                    }
                                    WhiteCell w = (WhiteCell) board[i][j];
                                    w.setCorrectValue(value+1);
                                    if (fillBoardAux2(board, 1, 1, totalWhites, whites+1)) return true;
                                    r = lastRow+1;
                                    index = 0;
                                    while(r < board.length && r < lastRow+nV && board[r][j].isWhite()) {
                                        tempBoard[r][j] =copyCol[index];
                                        ++index;
                                        ++r;
                                    }
                                    r= lastCol+1;
                                    index = 0;
                                    while(r < board.length && r < lastCol+nH && board[i][r].isWhite()) {
                                        tempBoard[i][r] = copyRow[index];
                                        ++index;
                                        ++r;
                                    }
                                    w.setCorrectValue(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }



    /**@brief función que crea las estructuras necesarias para después poder crear un tablero con solución única
     *
     * @param board representa un tablero con casillas blancas y negras
     * @return true si tenemos un tablero con solución única, false si tiene más de una solución
     */
    public static boolean fillBoard(Cell[][] board, int dif) {
        /*if(dif == 3) {
            posComb = new int[][]{ //possible values for sum using n cels
                    {}, //con zero
                    {1, 2, 3, 4, 5, 6, 7, 8, 9}, //una casilla
                    {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, //dos casillas
                    {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24}, //tres casillas
                    {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}, //cuatro casillas
                    {15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 36}, //cinco casillas
                    {21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39}, //seis casillas
                    {28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42}, //siete casillas
                    {36, 37, 38, 39, 40, 41, 42, 43, 44}, //ocho casillas
                    {45} //nueve casillas
            };
            Random rand = new Random();
            //mezclamos los numeros para evitar que siempre encuentre los mismos valores
            for (int i = 0; i < posComb.length; i++) {
                for(int j = 0; j < posComb[i].length;++j) {
                    int randomIndexToSwap = rand.nextInt(posComb[i].length);
                    int temp = posComb[i][randomIndexToSwap];
                    posComb[i][randomIndexToSwap] = posComb[i][j];
                    posComb[i][j] = temp;
                }
            }
        }*/

        tempBoard = new int[board.length][board[0].length][9];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                for (int k = 0; k < 9; ++k) {
                    tempBoard[i][j][k] = 0;
                }
            }
        }
        nineCellsRow(board, tempBoard);
        nineCellsCol(board, tempBoard);
        int whites = howManyWhites(board);
        if( !fillBoardAux(board,1,1, whites, 0)) return false;
        //if(!buildUniqueSolution(board,whites)) return false;
        for (int x = 0; x < board.length; ++x) {
            for (int y = 0; y < board[0].length; ++y) {
                if (!board[x][y].isWhite()) {
                    BlackCell w = (BlackCell) board[x][y];
                    w.setColumn(tempBoard[x][y][2]);
                    w.setRow(tempBoard[x][y][3]);
                }
            }
        }
        currentKakuro = new Kakuro(0,0, board);
        CtrlValidate.setKakuro(currentKakuro);
        int[] vec = {0,0,0,0,0,0,0,0,0,0};
        int[] res = new int[1];
        CtrlValidate.validate(0,0,0,vec,res);
        if(res[0] == 1) {
            return true;
        }
        else return false;
    }



    /**@brief función que cuenta el número de celdas blancas del tablero
     *
     * @param board representa un tablero con casillas blancas y negras
     * @return número de casillas blancas del tablero
     */
    public static int howManyWhites(Cell[][] board) {
        int w = 0;
        for (int i=0; i< board.length; ++i) {
            for (int j=0; j< board[0].length; ++j) {
                if (board[i][j].isWhite()) ++w;
            }
        }
        return w;
    }

    /**@brief función que genera la primera y la última fila del talbero de manera que simétrico
     *
     * @param board representa un tablero con celdas sin especificar
     * @param dif indica el porcentage de casillas blancas
     */
    public static void firstColRow(Cell[][] board,int dif) {
        int size = board.length;
        int i = 1;
        int cont = 0;
        for(int j =1; j < size; ++j) {
            if( j == size -1 && !board[i][j-1].isWhite()) {
                board[i][j] = new BlackCell();
                board[size-i][size-j] = new BlackCell();
            }
            else if(cont == 1) {
                board[i][j] = new WhiteCell();
                board[size-i][size-j] = new WhiteCell();
                ++cont;
            }
            else if(cont == 9 ) {
                board[i][j] = new BlackCell();
                board[size-i][size-j] = new BlackCell();
                cont = 0;
            }
            else {
                int random = (int) (Math.random()*100);
                int x = 0;
                if( dif == 1) x = 55;
                else if( dif == 2) x = 60;
                else x = 65;
                if(cont > 3) x = 0;

                if( random <= x) {
                    board[i][j] = new WhiteCell();
                    board[size-i][size-j] = new WhiteCell();
                    ++cont;
                }
                else {
                    cont = 0;
                    board[i][j] = new BlackCell();
                    board[size-i][size-j] = new BlackCell();
                }
            }
        }
        ++i;
    }

    /**@brief función que rellena el tablero con celdas blancas y negras evitando runs superiores a 9 y de forma que sea simétrico
     *
     * @param board representa un tablero con celdas sin especificar
     * @param dif indica el porcentage de casillas blancas
     */
    public static void randomCells(Cell[][] board,int dif) {
        int i = 1;
        int size = board.length;
        while( i <= size/2 ) {
            for(int j = 1; j < size; ++j) {
                int n = countWhiteCellsV(board,i-1,j);
                int m = countWhiteCellsH(board,i,j-1);
                if(j == size-1 && m == 0) {
                    board[i][j] = new BlackCell();
                    board[size-i][size-j] = new BlackCell();
                }
                else if(i == size/2 && n >= 5) {
                    board[i][j] = new BlackCell();
                    board[size-i][size-j] = new BlackCell();
                }
                else if( n == 1 && m >= 6 ) {
                    board[i-1][j] = new BlackCell();
                    board[size-i-1][size-j] = new BlackCell();
                    board[i][j] = new BlackCell();
                    board[size-i][size-j] = new BlackCell();
                }
                else if( n >= 6 && m == 1 ) {
                    board[i][j-1] = new BlackCell();
                    board[size-i][size-j-1] = new BlackCell();
                    board[i][j] = new BlackCell();
                    board[size-i][size-j] = new BlackCell();
                }
                else if(n == 1 || m == 1) { //esta casilla esta forzada a ser blanca
                    board[i][j] = new WhiteCell();
                    board[size-i][size-j] = new WhiteCell();
                }
                else if( n == 9 || m == 9) { //esta casilla esta forzada a ser negra
                    board[i][j] = new BlackCell();
                    board[size-i][size-j] = new BlackCell();
                }
                else {
                    int x = 0;
                    if( dif == 1) x = 55;
                    else if( dif == 2) x = 65;
                    else x = 70;
                    if( n >= 3 || m >= 3 ) x = 0;
                    Random rand = new Random();
                    if (rand.nextInt(101) <= x ) {
                        board[i][j] = new WhiteCell();
                        board[size - i][size - j] = new WhiteCell();
                    } else {
                        board[i][j] = new BlackCell();
                        board[size - i][size - j] = new BlackCell();
                    }
                }
            }
            ++i;
        }
    }

    /**@brief función que comprueba que no hay celdas blancas solas
     *
     * @param board representa un tablero con celdas blancas y negras
     *
     * Mientras el tablero contenga alguna casilla blanca solitária hará los cambios que hagan falta.
     */
    public static void checkBoard(Cell[][] board) {
        int size = board.length;
        boolean canvi = true;
        while(canvi) { //mientas hagamos cambios tenemos que comprovar que el tablero esta bien
            canvi = false;
            for(int r = 1; r < size;++r){
                for(int c = 1; c < size; ++c) {
                    if(!board[r][c].isWhite()) {
                        if(r >= 2) {
                            if(board[r-1][c].isWhite() && !board[r-2][c].isWhite()){
                                board[r-1][c] = new BlackCell();
                                canvi = true;
                            }
                        }
                        if(c >= 2) {
                            if(board[r][c-1].isWhite() && !board[r][c-2].isWhite()){
                                board[r][c-1] = new BlackCell();
                                canvi = true;
                            }
                        }
                        if(r < size-2) {
                            if(board[r+1][c].isWhite() && !board[r+2][c].isWhite()){
                                board[r+1][c] = new BlackCell();
                                canvi = true;
                            }
                        }
                        if(c < size-2) {
                            if(board[r][c+1].isWhite() && !board[r][c+2].isWhite()){
                                board[r][c+1] = new BlackCell();
                                canvi = true;
                            }
                        }
                        if(c == size-2) {
                            if(board[r][c+1].isWhite()) {
                                board[r][c+1] = new BlackCell();
                                canvi = true;
                            }
                        }
                        if(r == size-2) {
                            if(board[r+1][c].isWhite()) {
                                board[r+1][c] = new BlackCell();
                                canvi = true;
                            }
                        }
                    }
                }
            }
        }
    }

    /**@brief función que nos indica cuantas celdas blancas estan conectadas a una celda blanca dada
     *
     * @param board representa un tablero con celdas blancas y negras
     * @param i indica la fila de la celda que estamos tratando
     * @param j indica la columna de la celda que estamos tratando
     * @param auxBoard matriz del tamaño del tablero que nos indica si una celda ha sido visitada o no para no contarla otra vez
     * @return número de casillas blancas conectadas a la celda dada
     */
    public static int DFS(Cell[][] board,int i,int j,int[][] auxBoard) {
        if( i >= board.length || i < 0 || j >= board[0].length || j<0) return 0;
        else if(!board[i][j].isWhite()) {
            auxBoard[i][j] = 1; //la marcamos como visitada
            return 0;
        }
        else if(auxBoard[i][j] == 1) return 0;
        else {
            auxBoard[i][j]=1;
            return 1 + DFS(board,i+1,j,auxBoard)+DFS(board,i,j+1,auxBoard)+DFS(board,i-1,j,auxBoard)+DFS(board,i,j-1,auxBoard);
        }
    }

    /**@brief función que nos comprueba si el tablero es conexo, es decir, si no hay dos kakuros más pequeños en un solo tablero
     *
     * @param board representa un tablero con celdas blancas y negras
     * @return true si es conexo, false si no lo es
     */
    public static boolean connexBoard(Cell[][] board) {
        int size = board.length;
        int nDFS = 0;
        int nWhite = 0;
        boolean found = false;
        int [][] auxBoard = new int[size][size];
        for(int i = 0; i < size; ++i ) {
            for(int j = 0; j < size;++j) {
                auxBoard[i][j] = 0;
            }
        }
        for(int i = 0; i < size; ++i ) {
            for(int j = 0; j < size;++j) {
                if(board[i][j].isWhite()){
                    nWhite+=1;
                    if(!found ) {
                        nDFS = DFS(board,i,j,auxBoard);
                        found = true;
                    }
                }

            }
        }
        if(nWhite < 0.4*size*size) return false;
        else if(nWhite == nDFS) return true;
        return false;
    }

    static boolean checkRunsH(Cell[][] board) {
        int n = 0;
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[0].length; ++j) {
                if(!board[i][j].isWhite()) n = 0;
                else {
                    ++n;
                    if (n > 9) return true;
                }
            }
        }
        return (n > 9);
    }

    static boolean checkRunsV(Cell[][] board) {
        int n = 0;
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[0].length; ++j) {
                if(!board[j][i].isWhite()) n = 0;
                else {
                    ++n;
                    if (n > 9) return true;
                }
            }
        }
        return (n > 9);
    }

    /**@brief función principal de CtrlGenerate
     *
     * @param size tamaño del tablero a generar
     * @param dif indica el porcenatge de celdas blancas
     * @return un kakuro con solución única
     *
     * Esta función generará tableros mientras no sean válidos o no puedan tener solución única
     */
    public static Kakuro generate(int size,int dif) {
        //Inicializamos la matriz
        mat = new int[45][9][];
        for(int i = 1; i <= 45; ++i) {
            for(int j = 1; j <= 9; ++j ){
                mat[i-1][j-1] = new int[] {0,0,0,0,0,0,0,0,0};
                mat[i-1][j-1] = computePosSums(i,j,0);
            }
        }
        boolean repeat = true;
        while(repeat) {
            repeat = false;
            Cell [][] board = new Cell [size][size];
            for(int i = 0; i < size; ++i){
                board[0][i] = new BlackCell();
                board[i][0] = new BlackCell();
            }
            firstColRow(board,dif);
            randomCells(board,dif);
            if(checkRunsV(board)) repeat = true;
            else if(checkRunsH(board)) repeat = true;
            else {
                checkBoard(board);
                if(!connexBoard(board)){
                    repeat = true;
                }
                else {
                    currentKakuro = new Kakuro(0, 0, board);
                    if (!repeat && fillBoard(board,dif)) {
                    } else repeat = true;
                }
            }
        }
        CtrlValidate.setDifficulty();
        System.out.println("Finalmente, la dificultad es " + currentKakuro.getDifficulty());
        return currentKakuro;
    }

    static void printBoard(Cell[][] board) {
        System.out.println("------------------------------------");
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board.length; ++j) {
                if(board[i][j].isWhite()) {
                    System.out.print(((WhiteCell)board[i][j]).getCorrectValue());
                    System.out.print(" ");
                }
                else System.out.print("X ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }
}
