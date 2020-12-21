package domain.controllers;

/** @file CtrlValidate.java
 @brief Clase <em>CtrlValidate</em>.
 */

import domain.classes.*;

import java.lang.Math;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/** @brief Clase CtrlValidate que contiene los atributos y metodos para la funcionalidad de validar
 * @author Alvaro Armada Ruiz
 */
public class CtrlValidate {

    /**
     * kakuro instancia del kakuro con el que se trabaja en este momento
     */

    private static Kakuro kakuro;

    /** @brief Creadora por defecto
     *
     */

    public CtrlValidate() {}

    /** @brief Setter de Kakuro
     *
     * Settea un Kakuro al controlador para poder trabajar con él
     *
     * @param currentKakuro instáncia de Kakuro
     */

    public static void setKakuro(Kakuro currentKakuro) {
        kakuro = currentKakuro;
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

    /**@Brief Encuentra casillas con un valor único por columna o fila
     *
     * @param tempBoard es un tablero auxiliar donde cada posicion consta de 9 números (-1, 0 o 1). -1 indica negra o no tratado aún, 0 indica valor no posible y 1 indica valor posible. Ejemplo = 1 0 1 0 0 0 0 0 1, valores posibles: 1, 3, 9.
     * @param posSums es un array donde siguiendo el mismo razonamiento de tempBoard para 0 y 1, indica que valores aparecen en las combinaciones para sumar el valor de la casilla negra i, j con length casillas.
     * @param num indica como de larga es la fila o columna de blancas que sigue a la casilla negra i, j.
     * @param row 1 si trataremos la fila, 0 si trataremos la columna de la casilla negra i, j.
     * @param i posición i de la casilla negra
     * @param j posición j de la casilla negra
     *
     * Dado el tablero, los números que pueden sumar el valor, la casilla, si se trata por fila o columna y cuantas casillas blancas hay, se encuentran las casillas que tienen valor único y se actualiza el tablero
     * @return número de casillas únicas nuevas que se han encontrado
     */
    public static int validatePosSums(int [][][] tempBoard, int [] posSums, int num, int row, int i, int j) {
        int unique = 0;
        if(row == 1) {
            for(int x=1; x<=num; ++x) {
                int uniqueTemp = 0;
                if(tempBoard[i][j+x][0]==-1)
                    for(int y=0; y<9; ++y)
                        tempBoard[i][j+x][y] = posSums[y];

                else {
                    for(int y=0; y<9; ++y) {
                        if(tempBoard[i][j+x][y] == 0 || posSums[y] == 0) tempBoard[i][j+x][y] = 0;
                        else ++uniqueTemp;
                    }
                }

                if (uniqueTemp == 1) {
                    ++unique;
                }
            }
        }
        else {
            for(int x=1; x<=num; ++x) {
                int uniqueTemp = 0;
                if(tempBoard[i+x][j][0]==-1)
                    for(int y=0; y<9; ++y)
                        tempBoard[i+x][j][y] = posSums[y];

                else {
                    for(int y=0; y<9; ++y) {
                        if(tempBoard[i+x][j][y] == 0 || posSums[y] == 0) tempBoard[i+x][j][y] = 0;
                        else ++uniqueTemp;
                    }
                }

                if (uniqueTemp == 1) {
                  //  System.out.println("Unique i:"+(i+x)+" j:"+(j));
                    ++unique;
                }
            }
        }
        return unique;
    }


    /** @brief Encuentra casillas con valor trivial
     *
     * @param tempBoard es un tablero auxiliar donde cada posicion consta de 9 números (-1, 0 o 1). -1 indica negra o no tratado aún, 0 indica valor no posible y 1 indica valor posible. Ejemplo = 1 0 1 0 0 0 0 0 1, valores posibles: 1, 3, 9.
     *
     * Comprueba si dado el tablero, encuentra aquellas casillas con valor único trivial, es decir, aquellas que toda su fila o columna ya tienen un valor asignado excepto ella y modifica el tablero auxiliar acorde.
     *
     * @return número de casillas nuevas con valor único
     */


    public static int checkForNewUniques(int [][][] tempBoard) {
        Cell[][] board = kakuro.getBoard();
        int changed = 0;
        for(int i=0; i<kakuro.getRowSize(); ++i) {
            for(int j=0; j< kakuro.getColumnSize(); ++j) {
                if (!board[i][j].isWhite()) {
                    if (j< kakuro.getColumnSize()-1 && board[i][j+1].isWhite()) { //fila hacia derecha
                        // System.out.println("i: "+i+" j: "+(j+1));
                        int numWhiteRun = 1;
                        int numUnique = 0;
                        int numTemp = 0;
                        if (isUnique(tempBoard[i][j+1])!=0) {
                            //System.out.println("i: "+i+"j: "+(j+1));
                            ++numUnique;
                            for(int r = 0; r<9; ++r) {
                                if (tempBoard[i][j+1][r] == 1) numTemp = numTemp + r + 1;
                            }
                        }

                        while(j+1+numWhiteRun< kakuro.getColumnSize() && board[i][j+1+numWhiteRun].isWhite()) {
                            ++numWhiteRun;
                            if (isUnique(tempBoard[i][j+numWhiteRun])!=0) {
                                // System.out.println("i: "+i+"j: "+(j+1));
                                ++numUnique;
                                for(int r = 0; r<9; ++r) {
                                    if (tempBoard[i][j+numWhiteRun][r] == 1) numTemp = numTemp + r + 1;
                                }
                            }
                        }
                        if (numWhiteRun == numUnique + 1) {
                            // System.out.println("i: "+i+"j: "+j);
                            ++changed;
                            BlackCell b = (BlackCell) board[i][j];
                            numTemp = b.getRow()-numTemp;
                            for(int r = 1; r<=numWhiteRun; ++r) {
                                if (isUnique(tempBoard[i][j+r])==0) {
                                   // System.out.println("New unique: "+(i)+" "+(j+r));
                                    for (int rr = 0; rr<9; ++rr) {
                                        if(rr==numTemp-1) tempBoard[i][r+j][rr] = 1;
                                        else tempBoard[i][r+j][rr] = 0;
                                    }
                                }
                            }
                        }
                    }
                    if (i<kakuro.getRowSize()-1 && board[i+1][j].isWhite()) { //columna hacia abajo
                        int numWhiteRun = 1;
                        int numUnique = 0;
                        int numTemp = 0;
                        if (isUnique(tempBoard[i+1][j])!=0) {
                            ++numUnique;
                            //  System.out.println("i: "+(i+1)+"j: "+j);
                            for(int r = 0; r<9; ++r) {
                                if (tempBoard[i+1][j][r] == 1) numTemp = numTemp + r + 1;
                            }
                        }

                        while(i+1+numWhiteRun<kakuro.getRowSize() && board[i+1+numWhiteRun][j].isWhite()) {
                            ++numWhiteRun;
                            if (isUnique(tempBoard[i+numWhiteRun][j])!=0) {
                                ++numUnique;
                                //   System.out.println("i: "+(i+1)+"j: "+j);
                                for(int r = 0; r<9; ++r) {
                                    if (tempBoard[i+numWhiteRun][j][r] == 1) numTemp = numTemp + r + 1;
                                }
                            }
                        }
                        if (numWhiteRun == numUnique + 1) {
                            ++changed;
                            BlackCell b = (BlackCell) board[i][j];
                            numTemp = b.getColumn()-numTemp;
                            for(int r = 1; r<=numWhiteRun; ++r) {
                                if (isUnique(tempBoard[i+r][j])==0) {
                                   // System.out.println("New unique: "+(i+r)+" "+j);
                                    for (int rr = 0; rr<9; ++rr) {
                                        if(rr==numTemp-1) tempBoard[i+r][j][rr] = 1;
                                        else tempBoard[i+r][j][rr] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return changed;
    }

    /** @brief Asigna dificultad a un kakuro
     * Asigna dificultad 1 si es un kakuro fácil, 2 si es mediano o 3 si es difícil
     */


    public static void setDifficulty() {
        //System.out.println("uwu1");
        int maxRunLength = 0;
        int avgRunLength = 0;
        int numWhiteCell = 0;
        int numBlackCell = 0;
        int numRun = 0;
        int unique = 0;

        int temp = 0;   //guardamos la longitud de la run actual.

        Cell [][] board = kakuro.getBoard();

        for (int i=0; i< kakuro.getRowSize(); ++i) {
            for (int j=0; j< kakuro.getColumnSize(); ++j) {
                if (board[i][j].isWhite()) {
                    ++numWhiteCell;
                    ++temp;
                }
                else {
                    ++numBlackCell;
                    if (temp > maxRunLength) maxRunLength = temp;
                    if (temp!=0) {
                        ++numRun;
                        avgRunLength += temp;
                    }
                    temp = 0;
                }
            }
        }

        if (temp!=0) {
            ++numRun;
            avgRunLength += temp;
        }

        avgRunLength /= numRun;

        int [][][] tempBoard = new int[ kakuro.getRowSize()][ kakuro.getColumnSize()][9];
        for(int i=0; i< kakuro.getRowSize(); ++i) for(int j=0; j< kakuro.getColumnSize(); ++j) for (int z=0; z<9; ++z) tempBoard [i][j][z] = -1;

        for (int i=0; i< kakuro.getRowSize(); ++i) {
            for (int j=0; j< kakuro.getColumnSize(); ++j) {
                if (!board[i][j].isWhite()) {
                    if (j< kakuro.getColumnSize()-1 && board[i][j+1].isWhite()) { //fila hacia derecha
                        int numWhiteRun = 1;
                        while(j+1+numWhiteRun< kakuro.getColumnSize() && board[i][j+1+numWhiteRun].isWhite()) ++numWhiteRun;
                        BlackCell b = (BlackCell) board[i][j];
                        int [] posSums = computePosSums(b.getRow(), numWhiteRun,0);
                        unique+= validatePosSums(tempBoard, posSums, numWhiteRun, 1, i, j);
                    }
                    if (i< kakuro.getRowSize()-1 && board[i+1][j].isWhite()) { //columna hacia abajo
                        int numWhiteRun = 1;
                        while(i+1+numWhiteRun< kakuro.getRowSize() && board[i+1+numWhiteRun][j].isWhite()) ++numWhiteRun;
                        BlackCell b = (BlackCell) board[i][j];
                        int [] posSums = computePosSums(b.getColumn(), numWhiteRun,0);
                        unique+= validatePosSums(tempBoard, posSums, numWhiteRun, 0, i, j);
                    }
                }
            }
        }

        boolean changed = true;
        int newUniques = 0;
        while (changed) {
            changed = false;
            int c = checkForNewUniques(tempBoard);
            if(c!=0) changed = true;
            newUniques += c;
        }

        int implied = 0;

        changed = true;
        while (changed) {   //we check for implied
            changed = false;
            for (int i=0; i< kakuro.getRowSize(); ++i) {
                for (int j=0; j< kakuro.getColumnSize(); ++j) {

                    if(board[i][j].isWhite()) {
                        //subimos para arriba pa saber de cuanto es la run y de paso cogemos el valor de vertical
                        //idem para horizontal
                        int numWhiteRun = 1;
                        while ((i-numWhiteRun)>=0 && board[i-numWhiteRun][j].isWhite()) ++numWhiteRun;
                        BlackCell b = (BlackCell) board[i-numWhiteRun][j];
                        int vertical = b.getColumn();
                        temp = 1;   //contar fichas blancas hacia abajo

                        while ((i+temp)< kakuro.getRowSize() && board[i+temp][j].isWhite()) {
                            ++numWhiteRun;
                            ++temp;
                        }

                        int arr [] = new int [9];
                        for (int a = 0; a<9; ++a) arr[a] = 0;

                        for (int a = 0; a<9; ++a) {
                            if (tempBoard[i][j][a] == 1) {
                                int p [] = computePosSums(vertical, numWhiteRun - 1, a + 1);
                                for(int y=0; y<9; ++y) if(p[y] == 1) arr[y] = 1;
                            }
                        }

                        numWhiteRun = 1;
                        while ((i-numWhiteRun)>=0 && board[i-numWhiteRun][j].isWhite()) {
                            for (int y = 0; y<9; ++y) {
                                if (tempBoard[i-numWhiteRun][j][y]==1 && arr[y]==0) {
                                    changed = true;
                                    tempBoard [i-numWhiteRun][j][y] = 0;
                                }
                            }
                            ++numWhiteRun;
                        }
                        numWhiteRun = 1;
                        while ((i+numWhiteRun)< kakuro.getRowSize() && board[i+numWhiteRun][j].isWhite()) {
                            for (int y = 0; y<9; ++y) {
                                if (tempBoard[i+numWhiteRun][j][y]==1 && arr[y]==0) {
                                    changed = true;
                                    tempBoard [i+numWhiteRun][j][y] = 0;
                                }
                            }
                            ++numWhiteRun;
                        }

                        numWhiteRun = 1;
                        while ((j-numWhiteRun)>=0 && board[i][j-numWhiteRun].isWhite()) ++numWhiteRun;
                        b = (BlackCell) board[i][j-numWhiteRun];
                        int horizontal = b.getRow();
                        temp = 1;

                        while ((j+temp)< kakuro.getColumnSize() && board[i][j+temp].isWhite()) {
                            ++numWhiteRun;
                            ++temp;
                        }

                        for (int a = 0; a<9; ++a) arr[a] = 0;

                        for (int a = 0; a<9; ++a) {
                            if (tempBoard[i][j][a] == 1) {
                                int p [] = computePosSums(horizontal, numWhiteRun - 1, a + 1);
                                for(int y=0; y<9; ++y) if(p[y] == 1) arr[y] = 1;
                            }
                        }
                        numWhiteRun = 1;
                        while ((j-numWhiteRun)>=0 && board[i][j-numWhiteRun].isWhite()) {
                            for (int y = 0; y<9; ++y) {
                                if (tempBoard[i][j-numWhiteRun][y]==1 && arr[y]==0) {
                                    changed = true;
                                    tempBoard [i][j-numWhiteRun][y] = 0;
                                }
                            }
                            ++numWhiteRun;
                        }
                        numWhiteRun = 1;
                        while ((j+numWhiteRun)< kakuro.getColumnSize() && board[i][j+numWhiteRun].isWhite()) {
                            for (int y = 0; y<9; ++y) {
                                if (tempBoard[i][j+numWhiteRun][y]==1 && arr[y]==0) {
                                    changed = true;
                                    tempBoard [i][j+numWhiteRun][y] = 0;
                                }
                            }
                            ++numWhiteRun;
                        }
                    }
                }
            }
        }
        //System.out.println("uwu2");
        int prueba = 0;
        double number = 0;


        for (int i=0; i<kakuro.getRowSize(); ++i) {
            for (int j=0; j<kakuro.getColumnSize(); ++j) {
                if(board[i][j].isWhite()) {

                    number += howManyNumbers(tempBoard[i][j]);

                    if (isUnique(tempBoard[i][j])!=0)
                        ++prueba;
                }

            }
        }
        number = number/numWhiteCell;


        int difficulty = 0;

        if (numBlackCell+numWhiteCell<100); //facil -10x10
        else if (numBlackCell+numWhiteCell>223) difficulty+=2; //dificil +12x12
        else ++difficulty; //media entre los dos

        if (100*numWhiteCell/(numBlackCell+numWhiteCell)<=58); //facil
        else if (100*numWhiteCell/(numBlackCell+numWhiteCell)>=63) difficulty+=2;//dificil
        else ++difficulty; //media entre los dos

        if (numWhiteCell/(unique+newUniques)<3); //facil
        else if (numWhiteCell/(unique+newUniques)>=5) difficulty+=2;
        else ++difficulty;

        if (number<1.2); //facil
        else if (number>2) difficulty+=2;
        else ++difficulty;

        if (avgRunLength > 4) ++difficulty;
        else if (maxRunLength == 9) ++difficulty;

        double diff = (double) difficulty / 4.0;

        diff = Math.round(diff);

        difficulty = (int) diff;
        ++difficulty;
        kakuro.setDifficulty(difficulty);

    }

    /** @brief Cuenta cuantos 1 hay en un array de 9 posiciones
     *
     * @param a array de 9 posiciones que pueden ser 1 o 0
     *
     * @return retorna cuantos 1 hay en a
     */

    public static double howManyNumbers(int [] a) {
        double b = 0;
        for (int i=0; i<9; ++i) {
            if(a[i]==1) ++b;
        }
        return b;
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

    private static void spreadUniqueCol(int i, int j, int [][][] tempBoard, Cell [][] board, Set<Pair> uniques) {
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

    private static void spreadUniqueRow(int i, int j, int [][][] tempBoard, Cell [][] board, Set<Pair> uniques) {
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


    public static boolean validate() {
        //inicialización

        int [][][] tempBoard = new int [kakuro.getRowSize()][kakuro.getColumnSize()][9];
        for(int i=0; i< kakuro.getRowSize(); ++i) for(int j=0; j< kakuro.getColumnSize(); ++j) for (int z=0; z<9; ++z) tempBoard [i][j][z] = -1;

        Cell [][] board = kakuro.getBoard();
        Set<Pair> uniques = new HashSet<>();
        int numWhite = 0;
        int numBlack = 0;

        for (int i=0; i < kakuro.getRowSize(); ++i) {
            for (int j=0; j < kakuro.getColumnSize(); ++j) {
                if (!board[i][j].isWhite()) {
                    ++numBlack;
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
        if (numBlack == kakuro.getColumnSize()* kakuro.getRowSize()) return false;
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
        int a[] = new int[1]; a[0] = 0;
        validateRecursive(0,0,0, tmp, tempBoard, a);

        if (a[0]==1) return true;
        return false;
    }

    private static void validateRecursive(int r, int c, int sum, int [] vec, int [][][] tempBoard, int a[]) {
        if (a[0]==2) return;
        if( r == kakuro.getRowSize() ) { ++a[0]; } //hemos llegado al final, la solucion es correcta
        else {
            Cell[][] board = kakuro.getBoard();
            if( !board[r][c].isWhite() ) { // estamos en una casilla negra; queremos cambiar de columna o de casilla
                if (sum != 0) return;
                BlackCell b = (BlackCell) board[r][c];

                if (c != kakuro.getColumnSize() - 1) { // si no estem a la utlima columna
                    if (board[r][c + 1].isWhite()) {
                        sum = b.getRow();
                    }
                }
                int [] aux = {0,0,0,0,0,0,0,0,0,0};
                if (c == kakuro.getColumnSize() - 1) validateRecursive(r + 1, 0, 0, aux, tempBoard, a); //cambiamos de fila, estamos en la ultima columna }
                else validateRecursive(r,c+1, sum, aux, tempBoard, a); //cambiamos de columna
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
                                validateRecursive( r + 1, 0, 0, vec, tempBoard, a);
                            }
                            else validateRecursive(r, c + 1, sum-i, vec, tempBoard, a);
                        }
                        vec[i] = 0;
                    }
                }
            }
        }
    }
}
