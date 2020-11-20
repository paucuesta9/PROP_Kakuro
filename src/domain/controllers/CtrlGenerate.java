package domain.controllers;

/** @file CtrlGenerate.java
 @brief Clase <em>CtrlGenerate</em>.
 */

import domain.classes.BlackCell;
import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.WhiteCell;

import java.util.Random;

/** @brief Clase CtrlGenerate que contiene los atributos y metodos para la funcionalidad de generar
 * @author Pol Vallespí Soro y Alvaro Armada Ruiz
 */
public class CtrlGenerate {
    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */

    private static Kakuro currentKakuro;

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
                        if (count == 9) {
                            BlackCell b = (BlackCell) board[lastR][lastC];
                            // b.setRow(45);
                            tempBoard[lastR][lastC][1] = 9;
                        } else {
                            tempBoard[lastR][lastC][1] = count;
                        }
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
                        if (count == 9) {
                            BlackCell b = (BlackCell) board[lastR][lastC];
                            // b.setColumn(45);
                            tempBoard[lastR][lastC][0] = 9;
                        } else {
                            tempBoard[lastR][lastC][0] = count;
                        }
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
     * @param num
     * @param reducedNum
     * @param lim
     * @param posSums
     * @param no
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

    /**@brief función que va rellenando todas las celdas posibles
     *
     * @param board representa un tablero con celdas blancas y negras
     * @param i indica la fila de la celda que estamos tratando
     * @param j indica la columna de la celda que estamos tratando
     * @param posComb matriz de 2 dimensiones en la que guardamos las posibles sumas que se pueden hacer con n celdas blancas
     * @param tempBoard matriz de 3 dimensiones que nos permite guardar información adicional
     * @param totalWhites número total de celdas blancas a rellenar
     * @param whites número de celdas blancas con valor hasta el momento
     * @return true si es posible rellenar el tablero entero, false si no es posible
     */
    private static boolean fillBoardAux2(Cell[][] board, int i, int j, int[][] posComb, int[][][] tempBoard, int totalWhites, int whites) {
        if (totalWhites == whites) {
            return true;
        }
        if (i == board.length) {
            return false;
        } else if (j == board[0].length) return fillBoardAux2(board, i + 1, 0, posComb, tempBoard, totalWhites, whites);
        else if (!board[i][j].isWhite()) return fillBoardAux2(board, i, j + 1, posComb, tempBoard, totalWhites, whites);
        WhiteCell w = (WhiteCell) board[i][j];
        if (w.getCorrectValue() != 0) {
            return fillBoardAux2(board, i, j + 1, posComb, tempBoard, totalWhites, whites);
        }
        else if (allZero(tempBoard, i, j)) {
            return fillBoardAux2(board, i, j + 1, posComb, tempBoard, totalWhites, whites);
        }
        else {

            int[][][] copy = new int[board.length][board[0].length][9];
            for (int r = 0; r < board.length; ++r)
                for (int c = 0; c < board[0].length; ++c)
                    for (int z = 0; z < 9; ++z) copy[r][c][z] = tempBoard[r][c][z];

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

            boolean row = (tempBoard[i][lastRow][3] != 0);
            boolean column = (tempBoard[lastCol][j][2] != 0);

            if (row && column) {
                if (isUnique(tempBoard[i][j])) {
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
                    int prueba [] = computePosSums(tempBoard[lastCol][j][2], nV-1, v+1);
                    while (i + f < board.length && board[i + f][j].isWhite() && allOk) {
                        allOk = intersection2(prueba, tempBoard[i + f][j]);
                        ++f;
                    }
                    f = 1;
                    while (i - f >= 0 && board[i - f][j].isWhite() && allOk) {
                        allOk = intersection2(prueba, tempBoard[i - f][j]);
                        ++f;
                    }

                    if (allOk) {
                        w.setCorrectValue(v+1);
                        if (fillBoardAux2(board, 0, 0, posComb, tempBoard, totalWhites, whites + 1)) return true;
                        w.setCorrectValue(0);
                    }

                    for (int r = 0; r < board.length; ++r)
                        for (int c = 0; c < board[0].length; ++c)
                            for (int z = 0; z < 9; ++z) tempBoard[r][c][z] = copy[r][c][z];
                }
                else return (fillBoardAux2(board, i, j+1, posComb, tempBoard, totalWhites, whites));
            }

            else if (row) {
                for (int y = 0; y < posComb[nV].length; ++y) {
                    boolean allOk = true;
                    tempBoard[lastCol][j][2] = posComb[nV][y];
                    int[] prueba = computePosSums(posComb[nV][y], nV, 0);

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

                    int temp2 = 0;
                    for (int pp = 0; pp<9 && allOk; ++pp) {
                        if (prueba[pp]==1) ++temp2;
                    }
                    if (temp2==0) allOk = false;

                    temp2 = 0;
                    for (int pp = 0; pp<9 && allOk; ++pp) {
                        if (prueba2[pp]==1) ++temp2;
                    }

                    if (temp2==0) allOk = false;

                    int f = 1;
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
                        int temp = w.getCorrectValue();
                        w.setCorrectValue(value + 1);
                        if (fillBoardAux2(board, 0, 0, posComb, tempBoard, totalWhites, whites+1)) return true;
                        w.setCorrectValue(temp);
                    }
                    for (int r = 0; r < board.length; ++r)
                        for (int c = 0; c < board[0].length; ++c)
                            for (int z = 0; z < 9; ++z) tempBoard[r][c][z] = copy[r][c][z];
                }

            }
            else {
                for (int y = 0; y < posComb[nH].length; ++y) {
                    boolean allOk = true;
                    tempBoard[i][lastRow][3] = posComb[nH][y];
                    int[] prueba = computePosSums(posComb[nH][y], nH, 0);

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

                    int temp2 = 0;
                    for (int pp = 0; pp<9&&allOk; ++pp) {
                        if (prueba[pp]==1) ++temp2;
                    }
                    if (temp2==0) allOk = false;

                    temp2 = 0;
                    for (int pp = 0; pp<9&allOk; ++pp) {
                        if (prueba2[pp]==1) ++temp2;
                    }

                    if (temp2==0) allOk = false;

                    int f = 1;
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

                        int temp = w.getCorrectValue();
                        w.setCorrectValue(value + 1);
                        if (fillBoardAux2(board, 0, 0, posComb, tempBoard, totalWhites, whites+1)) return true;
                        w.setCorrectValue(temp);
                    }
                    for (int r = 0; r < board.length; ++r)
                        for (int c = 0; c < board[0].length; ++c)
                            for (int z = 0; z < 9; ++z) tempBoard[r][c][z] = copy[r][c][z];
                }

            }
        }
        return false;
    }

    /**@brief funcion para buscar una combinación inicial con un valor único
     *
     * @param board representa un tablero con casillas blancas y negras
     * @param i indica la fila de la celda que estamos tratando
     * @param j indica la columna de la celda que estamos tratando
     * @param posComb matriz de 2 dimensiones en la que guardamos las posibles sumas que se pueden hacer con n celdas blancas
     * @param tempBoard matriz de 3  dimensiones que nos permite guardar información adicional
     * @param totalWhites número total de casillas blancas
     * @param whites número de casillas blancas con valor hasta el momento
     * @return false si no podemos encontrar ninguna combinación inicial o si el tablero no puede ser único, true si puede ser único
     *
     * Esta función nos permite rellenar un tablero para que sea único junto a la función fillBoardAux2
     */
    private static boolean fillBoardAux(Cell[][] board, int i, int j, int[][] posComb, int[][][] tempBoard, int totalWhites, int whites) {
        if (i == board.length) {
            return false;
        } else if (j == board[0].length) return fillBoardAux(board, i + 1, 0, posComb, tempBoard, totalWhites, whites);
        else if (!board[i][j].isWhite()) return fillBoardAux(board, i, j + 1, posComb, tempBoard, totalWhites, whites);
        else {
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
            if (nV == 9 || nH == 9) return fillBoardAux(board, i, j + 1, posComb, tempBoard, totalWhites, whites);
            else {
                int[][][] copy = new int[board.length][board[0].length][9];
                for (int r = 0; r < board.length; ++r)
                    for (int c = 0; c < board[0].length; ++c)
                        for (int z = 0; z < 9; ++z) copy[r][c][z] = tempBoard[r][c][z];
                for (int x = 0; x < posComb[nH].length; ++x) {
                    int[] valuesH = computePosSums(posComb[nH][x], nH, 0);
                    for (int y = 0; y < posComb[nV].length; ++y) {
                        int[] valuesV = computePosSums(posComb[nV][y], nV, 0);
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
                            int temp = w.getCorrectValue();
                            w.setCorrectValue(value+1);
                            if (fillBoardAux2(board, 0, 0, posComb, tempBoard, totalWhites, whites+1)) return true;
                            for (int r = 0; r < board.length; ++r)
                                for (int c = 0; c < board[0].length; ++c)
                                    for (int z = 0; z < 9; ++z) tempBoard[r][c][z] = copy[r][c][z];
                            w.setCorrectValue(temp);

                        }


                    }
                }

                return fillBoardAux(board, i, j + 1, posComb, tempBoard, totalWhites, whites);
            }
        }
    }

    /**@brief función que crea las estructuras necesarias para después poder crear un tablero con solución única
     *
     * @param board representa un tablero con casillas blancas y negras
     * @return true si tenemos un tablero con solución única, false si tiene más de una solución
     */
    public static boolean fillBoard(Cell[][] board) {
        int[][] posComb = new int[][]{ //possible values for sum using n cels
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

        for (int i = 0; i < posComb.length; i++) {
            for(int j = 0; j < posComb[i].length;++j) {
                int randomIndexToSwap = rand.nextInt(posComb[i].length);
                int temp = posComb[i][randomIndexToSwap];
                posComb[i][randomIndexToSwap] = posComb[i][j];
                posComb[i][j] = temp;
            }
        }

        int tempBoard[][][] = new int[board.length][board[0].length][9];  //Matriz para calcular posibles valores
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                for (int k = 0; k < 9; ++k) {
                    tempBoard[i][j][k] = 0;
                }
            }
        }
        nineCellsRow(board, tempBoard);
        nineCellsCol(board, tempBoard);
        int whites = howManyWhites(board);
        boolean ans = fillBoardAux(board, 0, 0, posComb, tempBoard, whites, 0);
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
        if(res[0] == 1 && ans) {
            return true;
        }
        else {
            return false;
        }
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

    /**@brief función principal de CtrlGenerate
     *
     * @param size tamaño del tablero a generar
     * @param dif indica el porcenatge de celdas blancas
     * @return un kakuro con solución única
     *
     * Esta función generará tableros mientras no sean válidos o no puedan tener solución única
     */
    public static Kakuro generate(int size,int dif) {
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
            checkBoard(board);
            if(!connexBoard(board)){
                repeat = true;
            }
            currentKakuro = new Kakuro(0,0,board);
            if(!repeat && fillBoard(board)) { }
            else repeat = true;
        }
        CtrlValidate.setDifficulty();
        return currentKakuro;
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
                else if( n == 1 && m == 9 ) {
                    board[i-1][j] = new BlackCell();
                    board[size-i-1][size-j] = new BlackCell();
                    board[i][j] = new BlackCell();
                    board[size-i][size-j] = new BlackCell();
                }
                else if( n == 9 && m == 1 ) {
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
                    if( dif == 1) x = 30;
                    else if( dif == 2) x = 50;
                    else x = 60;
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
        if(nWhite == nDFS) return true;
        return false;
    }
}