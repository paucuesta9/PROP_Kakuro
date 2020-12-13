package domain.controllers;

/** @file CtrlResolve.java
 @brief Clase  <em>CtrlResolve</em>.
 */

import domain.classes.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/** @brief Clase CtrlResolve que contiene los atributos y metodos para la funcionalidad de resolver
 * @author Pol Vallespí Soro
 */
public class CtrlResolve {

    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */
    private static Kakuro kakuro;

    /** @brief Creadora por defecto
     *
     */
    public CtrlResolve(Kakuro kakuro) {
        this.kakuro = kakuro;
    }


    /**@Brief Función recursiva que calcula los numeros que aparecen en las combinaciones para sumar un determinado número con un determinado número de casillas
     *
     * @param arr array en el que guardamos los números de una combinación para intentar llegar hasta el número objetivo
     * @param index apunta al último número que hemos añadido a la suma para llegar al número objetivo
     * @param reducedNum inicializado al número objetivo, en cada iteración se le resta el número añadido a la suma para llegar al número objetivo
     * @param lim numero de casillas que tenemos disponibles para colocar números
     * @param posSums es el array en el que se guardan al final los números que aparecen en las combinacions para llegar al número objetivo
     * @param no indica si en las casillas blancas ya tenemos un número fijado (0 no lo tenemos, otro número es el número fijado), y por lo tanto queremos solo las combinaciones en las que aparezca ese número.
     *
     */

    public static void computePosSumsRec(int arr[], int index, int reducedNum, int lim, int posSums[], int no) {
        if (reducedNum < 0 || index > lim)
            return;

        if (reducedNum == 0 && index == lim) {
            for (int i = 0; i < index; i++)
                posSums[arr[i]-1] = 1;
            return;
        }

        int prev = (index == 0) ? 0 : arr[index - 1];

        if(prev == 9 || index >= lim) return;

        for (int k = prev+1; k <= 9 ; k++) {
            if (k!=no) {
                arr[index] = k;
                computePosSumsRec(arr, index + 1, reducedNum - k, lim, posSums, no);
            }
        }
    }

    /**@Brief Calcula los números que aparecen en las combinaciones para sumar un determinado número con un determinado número de casillas
     *
     * @param x es el número del cual queremos saber las combinaciones de números que suman hasta él con un número determinado de casillas
     * @param n es el número determinado de casillas
     * @param no indica si en las casillas blancas ya tenemos un número fijado (0 no lo tenemos, otro número es el número fijado), y por lo tanto queremos solo las combinaciones en las que aparezca ese número.
     *
     *
     * @return un array de 9 posiciones en el que dado un valor x, un número de casillas y un valor de no, deja un 1 en las posiciones de los números que aparezcan en las combinaciones y un 0 para aquellos que no
     */

    public static int [] computePosSums(int x, int n, int no) { //calcula combinaciones de numeros que suman x con n numeros. deja los resultados en posSums.
        int [] posSums = new int[9];
        for(int i=0; i<9; ++i) posSums[i] = 0;

        int [] arr = new int[n];

        computePosSumsRec(arr, 0, x-no, n, posSums, no);
        return posSums;
    }

    public static void iniColumn(int i, int j, int [] posSums, int numWhiteRun, int [][][] tempBoard) {
        for(int x = 0; x<numWhiteRun; ++x) {
            System.arraycopy(posSums, 0, tempBoard[i+1+x][j], 0, 9);
        }
    }

    public static int isUnique(int [] a) {
        int n = 0;
        int v = 0;
        for (int i=0; i<9; ++i) {
            if (a[i]==1) {
                ++n;
                v = i+1;
            }
        }
        if (n==1) return v;
        else return 0;
    }

    public static void intersection(int [] a, int [] b) { //se modifica a
        for (int i = 0; i<9; ++i) {
            if(!(a[i]==1 && b[i]==1)) a[i] = 0;
        }
    }

    public static void iniRow(int i, int j, int [] posSums, int numWhiteRun, int [][][] tempBoard, Set<Pair> uniques, Cell [][] board) {
        for(int x = 0; x<numWhiteRun; ++x) {
            intersection(tempBoard[i][j+1+x], posSums);
            int v = isUnique(tempBoard[i][j+1+x]);
            if (v != 0) {
                WhiteCell w = (WhiteCell) board[i][j+1+x];
                if (w.getCorrectValue()==0) {
                    Pair p = new Pair(i, j + 1 + x);
                    uniques.add(p);
                    w.setCorrectValue(v);
                }
            }
        }
    }

    public static void spreadUniqueCol(int i, int j, int [][][] tempBoard, Cell [][] board, Set<Pair> uniques) {
        int up = 0;
        int down = 0;
        while (i+1+down < board.length && board[i+1+down][j].isWhite()) ++down;
        while (board[i-1-up][j].isWhite()) ++up;
        int run = up + down + 1;

        BlackCell b = (BlackCell) board[i-1-up][j];
        WhiteCell w = (WhiteCell) board[i][j];
        int posSums [] = computePosSums(b.getColumn(), run-1, w.getCorrectValue());

        for (int x = 0; x<run; ++x) {
            if (i-up+x!=i) {
                WhiteCell wt = (WhiteCell) board[i-up+x][j];
                if (wt.getCorrectValue()==0) {
                    intersection(tempBoard[i - up + x][j], posSums);
                    int v = isUnique(tempBoard[i - up + x][j]);
                    if (v != 0) {
                        Pair p = new Pair(i - up + x, j);
                        uniques.add(p);
                        wt.setCorrectValue(v);
                    }
                }
            }
        }

    }

    public static void spreadUniqueRow(int i, int j, int [][][] tempBoard, Cell [][] board, Set<Pair> uniques) {
        int left = 0;
        int right = 0;
        while (board[i][j-1-left].isWhite()) ++left;
        while (j+1+right < board[0].length && board[i][j+1+right].isWhite()) ++right;
        int run = left + right + 1;

        BlackCell b = (BlackCell) board[i][j-1-left];
        WhiteCell w = (WhiteCell) board[i][j];
        int posSums [] = computePosSums(b.getRow(), run-1, w.getCorrectValue());

        for (int x = 0; x<run; ++x) {
            if (j-left-x!=j) {
                WhiteCell wt = (WhiteCell) board[i][j-left+x];
                if (wt.getCorrectValue()==0) {
                    intersection(tempBoard[i][j - left + x], posSums);
                    int v = isUnique(tempBoard[i][j - left + x]);
                    if (v != 0) {
                        Pair p = new Pair(i, j - left + x);
                        uniques.add(p);
                        wt.setCorrectValue(v);
                    }
                }
            }
        }
    }

    public static void spreadUnique(int i, int j, int [][][] tempBoard, Cell [][] board, Set<Pair> uniques) {
        spreadUniqueCol(i, j, tempBoard, board, uniques);
        spreadUniqueRow(i, j, tempBoard, board, uniques);
    }


    public static boolean resolve() {
      //inicialización

        int [][][] tempBoard = new int [kakuro.getRowSize()][kakuro.getColumnSize()][9];
        for(int i=0; i< kakuro.getRowSize(); ++i) for(int j=0; j< kakuro.getColumnSize(); ++j) for (int z=0; z<9; ++z) tempBoard [i][j][z] = -1;

        Cell [][] board = kakuro.getBoard();
        Set<Pair> uniques = new HashSet<>();
        int numWhite = 0;

        for (int i=0; i < kakuro.getRowSize(); ++i) {
            for (int j=0; j < kakuro.getColumnSize(); ++j) {
                if (!board[i][j].isWhite()) {
                    if (i < kakuro.getRowSize()-1 && board[i+1][j].isWhite()) {//columna hacia abajo
                        int numWhiteRun = 1;
                        while(i+1+numWhiteRun< kakuro.getRowSize() && board[i+1+numWhiteRun][j].isWhite()) ++numWhiteRun;
                        BlackCell b = (BlackCell) board[i][j];
                        int [] posSums = computePosSums(b.getColumn(), numWhiteRun, 0);
                        iniColumn(i, j, posSums, numWhiteRun, tempBoard);
                    }
                    if (j < kakuro.getColumnSize()-1 && board[i][j+1].isWhite()) {//fila hacia derecha
                        int numWhiteRun = 1;
                        while(j+1+numWhiteRun< kakuro.getColumnSize() && board[i][j+1+numWhiteRun].isWhite()) ++numWhiteRun;
                        BlackCell b = (BlackCell) board[i][j];
                        int [] posSums = computePosSums(b.getRow(), numWhiteRun, 0);
                        iniRow(i, j, posSums, numWhiteRun, tempBoard, uniques, board);
                    }
                } else ++numWhite;
            }
        }
        int u = uniques.size();
        while (uniques.size()!=0 && u!=numWhite) {
            Iterator it = uniques.iterator();
            Pair p = (Pair) it.next();
            it.remove();
            int tmpu = uniques.size();
            spreadUnique(p.getFirst(), p.getSecond(), tempBoard, board, uniques);
            int ttmpu = uniques.size();
            u = u + (ttmpu - tmpu);
        }

        if (u==numWhite) return true;
        int [] tmp = {0,0,0,0,0,0,0,0,0};
        //return resolveRecursive(0,0,0, tmp, board, tempBoard);
        return resolveRecursive(0,0,0, tmp, tempBoard);
    }

    public static boolean resolveRecursive(int r, int c, int sum, int [] vec, int [][][] tempBoard) {
        if( r == kakuro.getRowSize() ) { return true; } //hemos llegado al final, la solucion es correcta
        else {
            Cell[][] board = kakuro.getBoard();
            if( !board[r][c].isWhite() ) { // estamos en una casilla negra; queremos cambiar de columna o de casilla
                if (sum != 0) return false;
                BlackCell b = (BlackCell) board[r][c];

                if (c != kakuro.getColumnSize() - 1) { // si no estem a la utlima columna
                    if (board[r][c + 1].isWhite()) {
                        sum = b.getRow();
                    }
                }
                int [] aux = {0,0,0,0,0,0,0,0,0,0};
                if (c == kakuro.getColumnSize() - 1) return resolveRecursive(r + 1, 0, 0, aux, tempBoard); //cambiamos de fila, estamos en la ultima columna }
                else return resolveRecursive(r,c+1, sum, aux, tempBoard); //cambiamos de columna
            }
            else { // si estamos en una casilla blanca
                vec[0] = 1; //indica que s'ha modificat
                WhiteCell w = (WhiteCell) board[r][c];
                for(int i = 1; i < 10 && sum-i >= 0; ++i ) {
                    if ( vec[i] == 0 && tempBoard[r][c][i-1]==1) {
                        vec[i] = 1;
                        w.setCorrectValue(i);
                        boolean f = false;
                        if(r == kakuro.getRowSize() - 1 || !board[r+1][c].isWhite()) f =true;
                        if(kakuro.checkColumn(r-1, c, i, f, i)) {
                            if (c == kakuro.getColumnSize() - 1 && sum-i != 0) { }
                            else if (c == kakuro.getColumnSize() - 1 && sum-i == 0) { // estamos en la ultima casilla de la fila pero la suma es correcta
                                if (resolveRecursive( r + 1, 0, 0, vec, tempBoard)) {
                                    return true; // quiere decir que hemos llegado al final y esta bien
                                }
                            }
                            else if (resolveRecursive(r, c + 1, sum-i, vec, tempBoard)) { return true; }
                        }
                        vec[i] = 0;
                    }
                }
            }
        }
        return false;
    }

}
