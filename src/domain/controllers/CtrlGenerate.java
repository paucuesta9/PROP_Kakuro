package domain.controllers;

/** @file CtrlGenerate.java
 @brief Clase <em>CtrlGenerate</em>.
 */

import domain.classes.BlackCell;
import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.WhiteCell;

/** @brief Clase CtrlPlay que contiene los atributos y metodos para la funcionalidad de generar
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

    public static int countWhiteCellsV(Cell[][] board, int i, int j) {
        if (!board[i][j].isWhite()) return 0;
        return 1 + countWhiteCellsV(board, i - 1, j);
    }

    public static int countWhiteCellsH(Cell[][] board, int i, int j) {
        if (!board[i][j].isWhite()) return 0;
        return 1 + countWhiteCellsH(board, i, j - 1);
    }


    public static void valuesUsedRow(Cell[][] board, int i, int j, int[] posValues) {
        if (!board[i][j].isWhite()) {
            return;
        } else {
            valuesUsedRow(board, i, j - 1, posValues);

            WhiteCell w = (WhiteCell) board[i][j];
            posValues[w.getCorrectValue()] = 1;
        }
    }

    public static void valuesUsedCol(Cell[][] board, int i, int j, int[] posValues) {
        if (!board[i][j].isWhite()) {
            return;
        } else {
            valuesUsedCol(board, i - 1, j, posValues);

            WhiteCell w = (WhiteCell) board[i][j];
            posValues[w.getCorrectValue()] = 1;
        }
    }

    public static void rowSums(Cell[][] board) {
        int sum = 0;
        int lastCol = -1; //last col that has a blackCell
        int lastRow = -1;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (!board[i][j].isWhite()) {
                    if (sum != 0) {
                        BlackCell b = (BlackCell) board[lastRow][lastCol];
                        b.setRow(sum);
                    }
                    sum = 0;
                    lastCol = j;
                    lastRow = i;
                } else {
                    WhiteCell w = (WhiteCell) board[i][j];
                    sum += w.getCorrectValue();
                }
            }
        }
        if (sum != 0) {
            BlackCell b = (BlackCell) board[lastRow][lastCol];
            b.setRow(sum);
        }
    }

    public static void colSums(Cell[][] board) {
        int sum = 0;
        int lastCol = -1; //last col that has a blackCell
        int lastRow = -1;
        for (int j = 0; j < board[0].length; ++j) {
            for (int i = 0; i < board.length; ++i) {
                if (!board[i][j].isWhite()) {
                    if (sum != 0) {
                        BlackCell b = (BlackCell) board[lastRow][lastCol];
                        b.setColumn(sum);
                    }
                    sum = 0;
                    lastCol = j;
                    lastRow = i;
                } else {
                    WhiteCell w = (WhiteCell) board[i][j];
                    sum += w.getCorrectValue();
                }
            }
        }
        if (sum != 0) {
            BlackCell b = (BlackCell) board[lastRow][lastCol];
            b.setColumn(sum);
        }
    }

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

    public static void computePosSumsRec(int arr[], int index, int num, int reducedNum, int lim, int posSums[], int no) {
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

    public static int[] computePosSums(int x, int n, int no) { //calcula combinaciones de numeros que suman x con n numeros. deja los resultados en posSums.
        int[] posSums = new int[9];
        for (int i = 0; i < 9; ++i) posSums[i] = 0;

        int[] arr = new int[n];

        computePosSumsRec(arr, 0, x - no, x - no, n, posSums, no);
        return posSums;
    }

    static public void computePosSumsRec2(int arr[], int index, int num, int reducedNum, int lim, int posSums[], int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int no) {
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
            if (k != a1 && k != a2 && k != a3 && k != a4 && k != a5 && k != a6 && k != a7 && k != a8 && k != a9 && k != no) {
                arr[index] = k;
                computePosSumsRec2(arr, index + 1, num, reducedNum - k, lim, posSums, a1, a2, a3, a4, a5, a6, a7, a8, a9, no);
            }
        }
    }

    static public int[] computePosSums2(int x, int n, int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int no) { //calcula combinaciones de numeros que suman x con n numeros. deja los resultados en posSums.
        int[] posSums = new int[9];
        for (int i = 0; i < 9; ++i) posSums[i] = 0;

        int[] arr = new int[n];

        computePosSumsRec2(arr, 0, x - no, x - no, n, posSums, a1, a2, a3, a4, a5, a6, a7, a8, a9, no);
        return posSums;
    }

    static int intersection(int[] valuesH, int[] valuesV) {
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

    static int atLeastOneValue(int[] valuesH, int[] valuesV) {
        for (int i = 0; i < 9; ++i) {
            if (valuesH[i] == 1 && valuesV[i] == 1) {
                return 1;
            }
        }
        return 0;
    }

    static  int[] possibleUsedValuesRow(Cell[][] board, int i, int j, int[][][] tempBoard) {
        int[] res = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        int c = j;
        while (c < board[0].length && board[i][c].isWhite()) {
            for (int k = 0; k < 9; ++k) {
                if (tempBoard[i][c][k] == 1) res[k] = 1;
            }
            ++c;
        }
        return res;
    }

    static boolean ValueInRow(Cell[][] board, int r, int c, int x) {
        if (!board[r][c].isWhite()) {
            return false;
        }
        WhiteCell w = (WhiteCell) board[r][c];
        if (w.getCorrectValue() == x) return true;
        else return ValueInRow(board, r, c - 1, x);
    }

    static  boolean ValueInCol(Cell[][] board, int r, int c, int x) {
        if (!board[r][c].isWhite()) {
            return false;
        }
        WhiteCell w = (WhiteCell) board[r][c];
        if (w.getCorrectValue() == x) return true;
        else return ValueInRow(board, r - 1, c, x);
    }

    static boolean applyBacktrack(Cell[][] board, int r, int c, int[][][] tempBoard) {
        if (r == board.length) return true;
        else if (c == board[0].length) return applyBacktrack(board, r + 1, 0, tempBoard);
        else if (!board[r][c].isWhite()) return applyBacktrack(board, r, c + 1, tempBoard);
        else {
            WhiteCell w = (WhiteCell) board[r][c];
            if (w.getCorrectValue() == 0) {
                //( 1 ) mirar que este valor no se ha usado en la fila ni en la columna
                if (ValueInRow(board, r, c - 1, w.getCorrectValue())) return false;
                if (ValueInCol(board, r - 1, c, w.getCorrectValue())) return false;
                return applyBacktrack(board, r, c + 1, tempBoard);
            } else {
                for (int k = 0; k < 9; ++k) {
                    if (tempBoard[r][c][k] == 1) {
                        if (!ValueInRow(board, r, c - 1, k + 1) && !ValueInCol(board, r - 1, c, k + 1)) {
                            w.setCorrectValue(k + 1);
                            if (applyBacktrack(board, r, c + 1, tempBoard)) return true;
                            w.setCorrectValue(0);
                        }
                    }
                }
            }
        }
        return false;
    }

    static void printBoard(Cell[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                if (board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    System.out.print(w.getCorrectValue());
                } else System.out.print('.');
                System.out.print(' ');
            }
            System.out.println();
        }
    }

   /* boolean fillBoardAux(Cell[][] board, int r,int c, int [][] posComb, int[][][] tempBoard) {
        if (r == board.length) {
            //( 1 ) no se han podido fixear mas valores
            //( 2 ) mirar que todos los valores posibles son correctos
            //( 3 ) backtrack para ver si encontramos solucion unica
            if( !applyBacktrack(board,0,0,tempBoard) ) {
                System.out.println("No ha sido posible rellenar el kakuro");
                return false;
            }
            //( 4 ) Check if unique
            printBoard(board);
            return true;
        }
        else if (c == board[0].length) return fillBoardAux(board, r + 1, 0, posComb, tempBoard);
        else if (!board[r][c].isWhite()) return fillBoardAux(board, r, c + 1, posComb, tempBoard);
        else {
            //( 1 ) Encontrar nH y nV
            //( 2 ) Si alguno de ellos es 9, pasamos de la casilla ya que cualquier valor es posible
            //( 3 ) Buscar una combinacion posible de valores con nH y nV numeros resp. que solo coincidan en un valor
            //( 4 ) Fijamos ese valor, y actualizamos la lista de posibles valores
            //( 5 ) Pasamos a la siguiente casilla
            //( ! ) Diferenciamos 4 tipos de casillas blancas: con negra <- y ^, negra ^, negra <- y sin negras
            if (!board[r - 1][c].isWhite() && !board[r][c - 1].isWhite()) {
                //( 1 ) En este caso hay que buscar que una combinacion posible por nH y nV, ya que esta casilla es principio
                //( 2 ) Hay que mirar si en esa columna hay valores que ya son posibles y buscar una combinacion que use por lo menos uno de ellos
                int[] posValues = possibleUsedValuesRow(board, r, c, tempBoard);                                        //posibles valores a utilizar en la columna
                int aLo = 0;                                                                                            // nos indica si en la fila ya hay algun posible valor 0-> No, 1-> Si
                for (int k = 0; k < 9 && aLo == 0; ++k) {
                    if (posValues[k] == 1) aLo = 1;
                }

                int nH = tempBoard[r][c - 1][1];                                                                        //numero de casillas blancas horizontal
                int nV = tempBoard[r - 1][c][0];                                                                        //numero de casillas blancas vertical
                if (nH == 9 || nV == 9) return fillBoardAux(board, r, c + 1, posComb, tempBoard);                    //no podemos decidir nada, todas las combinaciones son posibles
                else {
                    int value = 1;
                    for (int x = 0; x < posComb[nH].length; ++x) {
                        int[] valuesH = computePosSums(posComb[nH][x], nH, 0);
                        //( * ) Buscamos una combinacion que por lo menos tenga un valor de los que ya hay
                        if (aLo == 1) {
                            value = atLeastOneValue(posValues, valuesH);
                        }
                        if (value == 1) {
                            for (int y = 0; y < posComb[nV].length; ++y) {
                                int[] valuesV = computePosSums(posComb[nV][y], nV, 0);
                                //( * ) Tenemos que mirar que solo coincida un valor
                                value = intersection(valuesH, valuesV);
                                if (value != -1) {
                                    tempBoard[r][c][value] = 1;
                                    tempBoard[r - 1][c][2] = posComb[nV][y];                                            //ponemos en la segunda casilla que el valor en vertical es X
                                    tempBoard[r][c - 1][3] = posComb[nH][x];                                            //ponemos en la tercera casilla que el valor en horizontal es X
                                    int[] auxH = computePosSums(posComb[nH][x], nH - 1, value + 1);
                                    int[] auxV = computePosSums(posComb[nV][y], nV - 1, value + 1);
                                    int f = 1;
                                    while (c + f < board.length && board[r][c + f].isWhite()) {                         //le decimos a las casillas blancas de la misma run sus pos valores
                                        tempBoard[r][c + f] = auxH;
                                        ++f;
                                    }
                                    f = 1;
                                    while (r + f < board.length && board[r + f][c].isWhite()) {
                                        tempBoard[r + f][c] = auxV;
                                        ++f;
                                    }
                                    WhiteCell w = (WhiteCell) board[r][c];
                                    w.setCorrectValue(value);                                                           //marcamos como fijo el valor y podemos cambiar d casilla
                                    if(fillBoardAux(board,r,c+1,posComb,tempBoard)) return true;
                                    w.setCorrectValue(0);                                                               //el valor que hemos fijado no es correcto, backtrack
                                }                                                                                       //fin if value
                            }                                                                                           //fin for y
                        }                                                                                               //fin if value
                    }                                                                                                   //fin for x
                }                                                                                                       //fin else
            }
            else if (!board[r - 1][c].isWhite()) {
                //( 1 ) En este caso lo que hay que hacer es mirar si hay una combinacion en vertical que coincida con un valor de los posibles
                //( 2 ) Si lo hay, fixeamos el valor y actualizamos los posibles
                //( 3 ) Si no lo hay, retornamos falso
                int nV = tempBoard[r - 1][c][0];
                int[] posValues = tempBoard[r][c];                                                                      //nos guardamos los valores que puede tener esta casilla
                int aLo = 0;
                for(int k = 0; k < 9 && aLo ==0; ++k) {
                    if(posValues[k] == 1) aLo = 1;
                }
                //( * ) Si aLo es 0, quiere decir que esa run es de 9 casillas, por lo tanto no podemops decidir nada
                if(aLo == 0 ) return fillBoardAux(board,r,c+1,posComb,tempBoard);
                else if(nV != 9) {                                                                                      //si es 9, no podemos hacer nada
                    for(int x = 0; x < posComb[nV].length;++x ) {
                        int[] valuesV = computePosSums(posComb[nV][x], nV, 0);
                        int value = intersection(posValues,valuesV);
                        if( value != -1) {
                            //( 1 ) Actualizar los posibles valores que les demas casillas blancas de la fila
                            //( 2 ) Posibles valores de la columna
                            //( 3 ) Fixear valor
                            posValues[value] = 0;                                                                       //este valor ya no se puede usar
                            tempBoard[r - 1][c][2] = posComb[nV][x];                                                    //actualizamos el valor de la suma vertical de la casilla negra
                            int[] auxV = computePosSums(posComb[nV][x], nV - 1, value + 1);                      //posibles numeros sin value
                            int f = 1;
                            while (c + f < board.length && board[r][c + f].isWhite()) {                                 //le decimos a las casillas blancas de la misma run sus pos valores
                                tempBoard[r][c + f] = posValues;                                                        //mismos que antes sin value
                                ++f;
                            }
                            f = 1;
                            while (r + f < board.length && board[r + f][c].isWhite()) {                                 //valores possible para la columna
                                tempBoard[r + f][c] = auxV;
                                ++f;
                            }
                            WhiteCell w = (WhiteCell) board[r][c];
                            w.setCorrectValue(value);                                                                   //marcamos como fijo el valor y podemos cambiar d casilla
                            if(fillBoardAux(board,r,c+1,posComb,tempBoard)) return true;
                            tempBoard[r - 1][c][2] = 0;
                            w.setCorrectValue(0);                                                                       //el valor que hemos fijado no es correcto, backtrack
                        }
                    }
                }
            }
            else if (!board[r][c - 1].isWhite()) {
                // Tendremos que solucionarlo por backtrack
                return fillBoardAux(board,r,c+1,posComb,tempBoard);
            }
            else { //sin
                return fillBoardAux(board,r,c+1,posComb,tempBoard);
            }
        }
        return false;
    }*/

    static  boolean allZero(int[][][] tempBoard, int i, int j) {
        int temp = 0;
        for (int z = 0; z < 9; ++z) {
            if (tempBoard[i][j][z] == 1) ++temp;
        }
        return (temp == 0);
    }

    static int howManyOnes(int [][][] tempBoard, int x, int y, Cell[][] board) {
        int ans = 0;
        if (board[x][y].isWhite()) {
            WhiteCell w = (WhiteCell) board[x][y];
            if(w.getCorrectValue()==0) {
                for(int i=0; i<9; ++i) {
                    if(tempBoard[x][y][i]==1) ++ans;
                }
            }
        }
        if (ans == 0) ans = 10;
        return ans;
    }

    static boolean fillBoardAux2(Cell[][] board, int i, int j, int[][] posComb, int[][][] tempBoard, int totalWhites, int whites) {
  //
        if (totalWhites == whites) {
            return true;
        }
      /*  if (i == 0 && j == 0) {
            int min = 9;
            int xB = 0;
            int yB = 0;
            for(int x = 0; x<tempBoard.length && min!=1; ++x) {
                for (int y =0; y<tempBoard[0].length && min!=1; ++y) {
                    int temp = howManyOnes(tempBoard, x, y, board);
                    if (temp<min) {
                        xB = x;
                        yB = y;
                        min = temp;
                    }
                }
            }
            if (min == 9) return false;
            else return fillBoardAux2(board, xB, yB, posComb, tempBoard, totalWhites, whites);
        }*/
        if (i == board.length) {
            return false;
        } else if (j == board[0].length) return fillBoardAux2(board, i + 1, 0, posComb, tempBoard, totalWhites, whites);
        else if (!board[i][j].isWhite()) return fillBoardAux2(board, i, j + 1, posComb, tempBoard, totalWhites, whites);
        WhiteCell w = (WhiteCell) board[i][j];
        if (w.getCorrectValue() != 0) {
            System.out.println(w.getCorrectValue());
            return fillBoardAux2(board, i, j + 1, posComb, tempBoard, totalWhites, whites);
        }//aqui soy blanca y ya me han fijado un valor
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

            int a1 = 0;
            int a2 = 0;
            int a3 = 0;
            int a4 = 0;
            int a5 = 0;
            int a6 = 0;
            int a7 = 0;
            int a8 = 0;
            int a9 = 0;

            found = false;
            int a;

            if (row && column) {
                System.out.println("ASDKJLSANDLKSAJDLASJNLDFKSAJLDSAJDLSADJSALDSJALDJSALDJASLDJSLAKDJASLDJKLASDJKLAS");
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
                        // System.out.println("2");
                        allOk = intersection2(prueba2, tempBoard[i][j - f]);
                        ++f;
                    }
                    f = 1;
                    int prueba [] = computePosSums(tempBoard[lastCol][j][2], nV-1, v+1);
                    while (i + f < board.length && board[i + f][j].isWhite() && allOk) {
                        // System.out.println("3");
                        allOk = intersection2(prueba, tempBoard[i + f][j]);
                        ++f;
                    }
                    f = 1;
                    while (i - f >= 0 && board[i - f][j].isWhite() && allOk) {
                        // System.out.println("4");
                        allOk = intersection2(prueba, tempBoard[i - f][j]);
                        ++f;
                    }

                    if (allOk) {
                        System.out.println("Trato ambos i:"+i+" j:"+j);

                        System.out.println();System.out.println();
                        for (int xx = 0; xx < board.length; ++xx) {
                            for (int yy = 0; yy < board.length; ++yy) {
                                if (!board[xx][yy].isWhite()) {
                                    System.out.print('B');
                                } else System.out.print('W');
                                for (int z = 0; z < 9; ++z) {
                                    System.out.print(tempBoard[xx][yy][z]);
                                }
                                System.out.print(' ');
                            }
                            System.out.println();
                        }

                        w.setCorrectValue(v+1);
                        if (fillBoardAux2(board, 0, 0, posComb, tempBoard, totalWhites, whites + 1)) return true;
                        w.setCorrectValue(0);
                    }

                    for (int r = 0; r < board.length; ++r)
                        for (int c = 0; c < board[0].length; ++c)
                            for (int z = 0; z < 9; ++z) tempBoard[r][c][z] = copy[r][c][z];
                }
                else return (fillBoardAux2(board, i, j+1, posComb, tempBoard, totalWhites, whites + 1));
            }

            // int f = 1;
           /* if (!row) {
                while (j + f < board.length && board[i][j + f].isWhite()) {
                    if (isUnique(tempBoard[i][j + f])) {
                        for (int a = 0; a < 9; ++a) {
                            if (tempBoard[i][j + f][a] == 1) {
                                if (a == 0) a1 = 1;
                                else if (a == 1) a2 = 2;
                                else if (a == 2) a3 = 3;
                                else if (a == 3) a4 = 4;
                                else if (a == 4) a5 = 5;
                                else if (a == 5) a6 = 6;
                                else if (a == 6) a7 = 7;
                                else if (a == 7) a8 = 8;
                                else if (a == 8) a9 = 9;
                            }
                        }
                    }
                    ++f;
                }
                f = 1;
                while (j - f >= 0 && board[i][j - f].isWhite()) {
                    if (isUnique(tempBoard[i][j - f])) {
                        for (int a = 0; a < 9; ++a) {
                            if (tempBoard[i][j - f][a] == 1) {
                                if (a == 0) a1 = 1;
                                else if (a == 1) a2 = 2;
                                else if (a == 2) a3 = 3;
                                else if (a == 3) a4 = 4;
                                else if (a == 4) a5 = 5;
                                else if (a == 5) a6 = 6;
                                else if (a == 6) a7 = 7;
                                else if (a == 7) a8 = 8;
                                else if (a == 8) a9 = 9;
                            }
                        }
                    }
                    --f;
                }
            }
            else {
                while (j + f < board[0].length && board[i + f][j].isWhite()) {
                    if (isUnique(tempBoard[i + f][j])) {
                        for (int a = 0; a < 9; ++a) {
                            if (tempBoard[i + f][j][a] == 1) {
                                if (a == 0) a1 = 1;
                                else if (a == 1) a2 = 2;
                                else if (a == 2) a3 = 3;
                                else if (a == 3) a4 = 4;
                                else if (a == 4) a5 = 5;
                                else if (a == 5) a6 = 6;
                                else if (a == 6) a7 = 7;
                                else if (a == 7) a8 = 8;
                                else if (a == 8) a9 = 9;
                            }
                        }
                    }
                    ++f;
                }
                f = 1;
                while (i - f >= 0 && board[i - f][j].isWhite()) {
                    if (isUnique(tempBoard[i - f][j])) {
                        for (int a = 0; a < 9; ++a) {
                            if (tempBoard[i - f][j][a] == 1) {
                                if (a == 0) a1 = 1;
                                else if (a == 1) a2 = 2;
                                else if (a == 2) a3 = 3;
                                else if (a == 3) a4 = 4;
                                else if (a == 4) a5 = 5;
                                else if (a == 5) a6 = 6;
                                else if (a == 6) a7 = 7;
                                else if (a == 7) a8 = 8;
                                else if (a == 8) a9 = 9;
                            }
                        }
                    }
                    --f;
                }
            }*/
            //if row, computePosSums2 con nH (numero de casillas disponibles) [3].
            else if (row) {
                //for (int x = 0; x < 9; ++x) {
                   /* if (tempBoard[i][j][x] == 1) {
                        a = x + 1;
                        for (int y = 0; y < 9; ++y) {
                            if (x != y && tempBoard[i][j][y] == 1) {
                                if (y == 0) a1 = 1;
                                else if (y == 1) a2 = 2;
                                else if (y == 2) a3 = 3;
                                else if (y == 3) a4 = 4;
                                else if (y == 4) a5 = 5;
                                else if (y == 5) a6 = 6;
                                else if (y == 6) a7 = 7;
                                else if (y == 7) a8 = 8;
                                else if (y == 8) a9 = 9;
                            }
                        }*/
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
                               // System.out.println("1");
                                allOk = intersection2(prueba2, tempBoard[i][j + f]);
                                ++f;
                            }
                            f = 1;
                            while (j - f >= 0 && board[i][j - f].isWhite() && allOk) {
                               // System.out.println("2");
                                allOk = intersection2(prueba2, tempBoard[i][j - f]);
                                ++f;
                            }
                            f = 1;
                            while (i + f < board.length && board[i + f][j].isWhite() && allOk) {
                               // System.out.println("3");
                                allOk = intersection3(prueba, tempBoard[i + f][j]);
                                ++f;
                            }
                            f = 1;
                            while (i - f >= 0 && board[i - f][j].isWhite() && allOk) {
                               // System.out.println("4");
                                allOk = intersection3(prueba, tempBoard[i - f][j]);
                                ++f;
                            }

                            if (allOk) {
                                System.out.println("Trato i:"+i+" j:"+j);

                                System.out.println();System.out.println();
                                for (int xx = 0; xx < board.length; ++xx) {
                                    for (int yy = 0; yy < board.length; ++yy) {
                                        if (!board[xx][yy].isWhite()) {
                                            System.out.print('B');
                                        } else System.out.print('W');
                                        for (int z = 0; z < 9; ++z) {
                                            System.out.print(tempBoard[xx][yy][z]);
                                        }
                                        System.out.print(' ');
                                    }
                                    System.out.println();
                                }

                                int temp = w.getCorrectValue();
                                w.setCorrectValue(value + 1);
                                if (fillBoardAux2(board, 0, 0, posComb, tempBoard, totalWhites, whites+1)) return true;
                                w.setCorrectValue(temp);
                            }
                            for (int r = 0; r < board.length; ++r)
                                for (int c = 0; c < board[0].length; ++c)
                                    for (int z = 0; z < 9; ++z) tempBoard[r][c][z] = copy[r][c][z];
                        }
                  //  } //falta por hacer, en row, calculamos la suma de columna y vamos hacia abajo comprobando que no se quede ninguna casilla sin numeros, pero hay que tner en cuenta que puede ser la primera vez que la visitamos.
                    //despues sabemos la suma de la row, por lo tanto calculamos sumas posibles con el numero fijado y comprabamos que no haya ninguna casilla que se quede sin numeros posibles.
                //}

            }
            else {
                //for (int x = 0; x < 9; ++x) {
                   /* if (tempBoard[i][j][x] == 1) {
                        a = x + 1;
                        for (int y = 0; y < 9; ++y) {
                            if (x != y && tempBoard[i][j][y] == 1) {
                                if (y == 0) a1 = 1;
                                else if (y == 1) a2 = 2;
                                else if (y == 2) a3 = 3;
                                else if (y == 3) a4 = 4;
                                else if (y == 4) a5 = 5;
                                else if (y == 5) a6 = 6;
                                else if (y == 6) a7 = 7;
                                else if (y == 7) a8 = 8;
                                else if (y == 8) a9 = 9;
                            }
                        }*/

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
                                System.out.println("Trato i:"+i+" j:"+j);

                                System.out.println();System.out.println();
                                for (int xx = 0; xx < board.length; ++xx) {
                                    for (int yy = 0; yy < board.length; ++yy) {
                                        if (!board[xx][yy].isWhite()) {
                                            System.out.print('B');
                                        } else System.out.print('W');
                                        for (int z = 0; z < 9; ++z) {
                                            System.out.print(tempBoard[xx][yy][z]);
                                        }
                                        System.out.print(' ');
                                    }
                                    System.out.println();
                                }

                                int temp = w.getCorrectValue();
                                w.setCorrectValue(value + 1);
                                if (fillBoardAux2(board, 0, 0, posComb, tempBoard, totalWhites, whites+1)) return true;
                                w.setCorrectValue(temp);
                            }
                            for (int r = 0; r < board.length; ++r)
                                for (int c = 0; c < board[0].length; ++c)
                                    for (int z = 0; z < 9; ++z) tempBoard[r][c][z] = copy[r][c][z];
                        }
                  //  } //falta por hacer, en row, calculamos la suma de columna y vamos hacia abajo comprobando que no se quede ninguna casilla sin numeros, pero hay que tner en cuenta que puede ser la primera vez que la visitamos.
                    //despues sabemos la suma de la row, por lo tanto calculamos sumas posibles con el numero fijado y comprabamos que no haya ninguna casilla que se quede sin numeros posibles.
              //  }

            }
        }
        return false;
    }

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

    public  static boolean isUnique(int[] a) { //a tiene mida 9
        int b = 0;
        for (int i = 0; i < 9; ++i)
            if (a[i] == 1) ++b;

        return (b == 1);
    }

    static boolean fillBoardAux(Cell[][] board, int i, int j, int[][] posComb, int[][][] tempBoard, int totalWhites, int whites) {
        if (i == board.length) {
            System.out.println("AAAAAAAA");
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
                found = false;
                for (int x = 0; x < posComb[nH].length && !found; ++x) {
                    int[] valuesH = computePosSums(posComb[nH][x], nH, 0);
                    for (int y = 0; y < posComb[nV].length && !found; ++y) {
                        int[] valuesV = computePosSums(posComb[nV][y], nV, 0);
                        int value = intersection(valuesH, valuesV);
                        if (value != -1) {

                            found = true;
                            tempBoard[i][j][value] = 1;
                            tempBoard[lastCol][j][2] = posComb[nV][y];
                            tempBoard[i][lastRow][3] = posComb[nH][x];
                            int[] auxH = computePosSums(posComb[nH][x], nH - 1, value + 1);
                            int[] auxV = computePosSums(posComb[nV][y], nV - 1, value + 1);
                            int f = 1;
                          /*  System.out.println();System.out.println();
                            for (int xx = 0; xx < board.length; ++xx) {
                                for (int yy = 0; yy < board.length; ++yy) {
                                    if (!board[xx][yy].isWhite()) {
                                        System.out.print('B');
                                    } else System.out.print('W');
                                    for (int z = 0; z < 9; ++z) {
                                        System.out.print(tempBoard[xx][yy][z]);
                                    }
                                    System.out.print(' ');
                                }
                                System.out.println();
                            }
                            System.out.println("1j: " + j + "i: " + i);*/
                            while (j + f < board.length && board[i][j + f].isWhite()) {
                                for (int tt = 0; tt < 9; ++tt)
                                    tempBoard[i][j + f][tt] = auxH[tt];
                                ++f;
                            }
                            f = 1;
                           /* System.out.println();System.out.println();
                            for (int xx = 0; xx < board.length; ++xx) {
                                for (int yy = 0; yy < board.length; ++yy) {
                                    if (!board[xx][yy].isWhite()) {
                                        System.out.print('B');
                                    } else System.out.print('W');
                                    for (int z = 0; z < 9; ++z) {
                                        System.out.print(tempBoard[xx][yy][z]);
                                    }
                                    System.out.print(' ');
                                }
                                System.out.println();
                            }
                            System.out.println("2j: " + j + "i: " + i);*/
                            while (j - f >= 0 && board[i][j - f].isWhite()) {
                                for (int tt = 0; tt < 9; ++tt) tempBoard[i][j - f][tt] = auxH[tt];
                                ++f;
                            }
                            f = 1;
                           /* System.out.println();System.out.println();
                            for (int xx = 0; xx < board.length; ++xx) {
                                for (int yy = 0; yy < board.length; ++yy) {
                                    if (!board[xx][yy].isWhite()) {
                                        System.out.print('B');
                                    } else System.out.print('W');
                                    for (int z = 0; z < 9; ++z) {
                                        System.out.print(tempBoard[xx][yy][z]);
                                    }
                                    System.out.print(' ');
                                }
                                System.out.println();
                            }*/
                            while (i + f < board.length && board[i + f][j].isWhite()) {
                                for (int tt = 0; tt < 9; ++tt) tempBoard[i + f][j][tt] = auxV[tt];
                                ++f;
                            }
                            f = 1;
                           /* System.out.println();System.out.println();
                            for (int xx = 0; xx < board.length; ++xx) {
                                for (int yy = 0; yy < board.length; ++yy) {
                                    if (!board[xx][yy].isWhite()) {
                                        System.out.print('B');
                                    } else System.out.print('W');
                                    for (int z = 0; z < 9; ++z) {
                                        System.out.print(tempBoard[xx][yy][z]);
                                    }
                                    System.out.print(' ');
                                }
                                System.out.println();
                            }*/
                            while (i - f > 0 && board[i - f][j].isWhite()) {
                                for (int tt = 0; tt < 9; ++tt) tempBoard[i - f][j][tt] = auxV[tt];
                                ++f;
                            }

                            WhiteCell w = (WhiteCell) board[i][j];
                            int temp = w.getCorrectValue();
                            w.setCorrectValue(value+1);
                           /* System.out.println();System.out.println();
                            for (int xx = 0; xx < board.length; ++xx) {
                                for (int yy = 0; yy < board.length; ++yy) {
                                    if (!board[xx][yy].isWhite()) {
                                        System.out.print('B');
                                    } else System.out.print('W');
                                    for (int z = 0; z < 9; ++z) {
                                        System.out.print(tempBoard[xx][yy][z]);
                                    }
                                    System.out.print(' ');
                                }
                                System.out.println();
                            }*/

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

    static boolean fillBoard(Cell[][] board) {
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
        System.out.println(whites);
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
        currentKakuro = new Kakuro("0",0, board);
        CtrlValidate.setKakuro(currentKakuro);
        int[] vec = {0,0,0,0,0,0,0,0,0,0};
        int[] res = new int[1];
        CtrlValidate.validate(0,0,0,vec,res);
        if(res[0] == 1 && ans) {
            System.out.println("kakuro único generado");
            return true;
        }
        else {
            System.out.println("Sad :c");
            return false;
        }
    }

    static int howManyWhites(Cell[][] board) {
        int w = 0;
        for (int i=0; i< board.length; ++i) {
            for (int j=0; j< board[0].length; ++j) {
                if (board[i][j].isWhite()) ++w;
            }
        }
        return w;
    }

    public static void randomCells(Cell[][] board) {
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
                else { //random
                    int random = (int) (Math.random()*10); //generamos un numero aleatorio
                    if( random <= 5) {
                        board[i][j] = new WhiteCell();
                        board[size-i][size-j] = new WhiteCell();
                    }
                    else {
                        board[i][j] = new BlackCell();
                        board[size-i][size-j] = new BlackCell();
                    }
                }
            }
            ++i;
        }
    }

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
                            }
                        }
                    }
                }
            }
        }
    }

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

    //FUNCION PARA GENERAR TABLEROS
    public static Kakuro generate(int size) {
        boolean repeat = true;
        while(repeat) {
            repeat = false;
            Cell [][] board = new Cell [size][size];
            for(int i = 0; i < size; ++i){ //la primera fila y la primera columna tienen que ser negras
                board[0][i] = new BlackCell();
                board[i][0] = new BlackCell();
            }
            System.out.println("Empezamos nuevo tablero");
            //Construimos el tablero
            firstColRow(board);
            randomCells(board);
            checkBoard(board);
            //falta mirar que sigui connex
            if(!connexBoard(board)){
                repeat = true;
            }
            System.out.println("Tablero creado");

            printBoard(board);
            currentKakuro = new Kakuro("0",0,board);
            if(!repeat && fillBoard(board)) {
                System.out.println("Kakuro with unique solution found");
            }
            else repeat = true;
        }
        return currentKakuro;
    }

    public static void firstColRow(Cell[][] board) {
        int size = board.length;
        int i = 1;
        int cont = 0; //cuenta cuantas casillas blancas hay seguidas
        for(int j =1; j < size; ++j) {
            if( j == size -1 && !board[i][j-1].isWhite()) {
                board[i][j] = new BlackCell();
                board[size-i][size-j] = new BlackCell();
            }
            else if(cont == 1) { //tiene que ser si o si blanca, sino tendriamos una casilla sola --> imposible
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
                int random = (int) (Math.random()*10); //generamos un numero aleatorio
                if( random <= 5) {
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


}

