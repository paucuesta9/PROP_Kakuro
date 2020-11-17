package domain.controllers;

/** @file CtrlGenerate.java
 @brief Clase <em>CtrlGenerate</em>.
 */



import domain.classes.BlackCell;
import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.WhiteCell;
import domain.controllers.CtrlValidate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/** @brief Clase CtrlPlay que contiene los atributos y metodos para la funcionalidad de generar
 */
public class CtrlGenerate {
    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */

    private static Kakuro currentKakuro;

    /** @brief Creadora por defecto
     *
     */
    public CtrlGenerate() {}

    /** @brief Setter de Kakuro
     *
     * Settea un Kakuro al controlador para poder trabajar con él
     *
     * @param kakuro instància de Kakuro
     */
    public static void setKakuro(Kakuro kakuro) {
        currentKakuro = kakuro;
    }

    /**
     *
     * @param board
     * @param i
     * @param j
     * @return número de celdas blancas seguidas en vertical
     */
    public static int countWhiteCellsV(Cell[][] board, int i, int j) {
        if( !board[i][j].isWhite()) return 0;
        return 1+countWhiteCellsV(board,i-1,j);
    }

    /**
     *
     * @param board representa un tablero
     * @param i indica la fila actual
     * @param j indica la columna actual
     * @return número de celdas blancas seguidas en horizontal
     */
    public static int countWhiteCellsH(Cell[][] board,int i,int j) {
        if( !board[i][j].isWhite()) return 0;
        return 1+countWhiteCellsH(board,i,j-1);
    }

    /**
     *
     * @param board representa un tablero.
     * @param i indica la fila acutal.
     * @param j indica la columna actual.
     * @param posValues vector de 10 posiciones que nos indica que valores han sido usados.
     *
     * Esta funcion nos indica que valores se han usado en la run horizontal.
     * Si en la posicion i de posValues hay un 1, indica que ese valor ha sido usado en la "run" (posValues[i]=1).
     * Si es un 0, indica que ese valor esta disponible (posValues[i]=0).
     *
     */
    public static void valuesUsedRow(Cell[][] board,int i,int j, int [] posValues) {
        if(!board[i][j].isWhite()) {
            return;
        }
        else {
            valuesUsedRow(board,i,j-1,posValues);

            WhiteCell w = (WhiteCell) board[i][j];
            posValues[w.getCorrectValue()] = 1;
        }
    }

    /**
     *
     * @param board representa un tablero.
     * @param i indica la fila actual.
     * @param j indica la columna actual.
     * @param posValues vector de 10 posiciones que nos indica que valores han sido usados.
     *
     * Esta funcion nos indica que valores se han usado en la run vertical.
     * Si en la posicion i de posValues hay un 1, indica que ese valor ha sido usado en la "run" (posValues[i]=1).
     * Si es un 0, indica que ese valor esta disponible (posValues[i]=0).
     */
    public static void valuesUsedCol(Cell[][] board,int i,int j, int [] posValues) {
        if(!board[i][j].isWhite()) {
            return;
        }
        else {
            valuesUsedCol(board,i-1,j,posValues);

            WhiteCell w = (WhiteCell) board[i][j];
            posValues[w.getCorrectValue()] = 1;
        }
    }

    /**
     * @param board representa un tablero
     * Esta función calcula la suma de las runs horizontales y las setea en su correspondiente celda negra.
     */
    public static void rowSums(Cell[][] board) {
        int sum = 0;
        int lastCol = -1; //last col that has a blackCell
        int lastRow = -1;
        for(int i = 0; i < board.length; ++i ) {
            for(int j = 0; j < board[0].length;++j) {
                if(!board[i][j].isWhite()) {
                    if(sum != 0 ){
                        BlackCell b = (BlackCell) board[lastRow][lastCol];
                        b.setRow(sum);
                    }
                    sum = 0;
                    lastCol = j;
                    lastRow = i;
                }
                else {
                    WhiteCell w = (WhiteCell) board[i][j];
                    sum += w.getCorrectValue();
                }
            }
        }
        if(sum != 0 ){
            BlackCell b = (BlackCell) board[lastRow][lastCol];
            b.setRow(sum);
        }
    }

    /**
     * @param board
     * Esta función calcula la suma de las runs vertical y las setea en su correspondiente celda negra.
     */
    public static void colSums(Cell[][] board) {
        int sum = 0;
        int lastCol = -1; //last col that has a blackCell
        int lastRow = -1;
        for(int j = 0; j < board[0].length; ++j ) {
            for(int i = 0; i < board.length;++i) {
                if(!board[i][j].isWhite()) {
                    if(sum != 0 ){
                        BlackCell b = (BlackCell) board[lastRow][lastCol];
                        b.setColumn(sum);
                    }
                    sum = 0;
                    lastCol = j;
                    lastRow = i;
                }
                else {
                    WhiteCell w = (WhiteCell) board[i][j];
                    sum += w.getCorrectValue();
                }
            }
        }
        if(sum != 0 ){
            BlackCell b = (BlackCell) board[lastRow][lastCol];
            b.setColumn(sum);
        }
    }



    public static boolean ValueInRow(Cell[][] board,int r,int c,int x) {
        if(!board[r][c].isWhite()) {
            return false;
        }
        WhiteCell w = (WhiteCell) board[r][c];
        if(w.getCorrectValue() == x) return true;
        else return ValueInRow(board,r,c-1,x);
    }
    public static boolean ValueInCol(Cell[][] board,int r,int c,int x) {
        if(!board[r][c].isWhite()) {
            return false;
        }
        WhiteCell w = (WhiteCell) board[r][c];
        if(w.getCorrectValue() == x) return true;
        else return ValueInRow(board,r-1,c,x);
    }


    /**
     * @param board representa un talbero
     *
     * Esta función setea el correctValue de las celdas del tablero a 0.
     */
    public static void clearBoard(Cell[][] board) {
        for(int i = 0; i < board.length;++i) {
            for(int j = 0; j < board[0].length;++j) {
                if(board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    w.setCorrectValue(0);
                }
            }
        }
    }



    /**
     *
     * @param board representa un tablero
     * @return una copia del tablero board
     *
     */
    public static Cell[][] copyBoard(Cell[][] board) {
        Cell[][] r = new Cell[board.length][board[0].length];
        for(int i = 0; i < board.length;++i) {
            for(int j = 0;j < board[0].length;++j) {
                if(!board[i][j].isWhite()) {
                    r[i][j] = new BlackCell();
                    BlackCell b = (BlackCell) board[i][j];
                    BlackCell b1 = (BlackCell) r[i][j];
                    b1.setRow(b.getRow());
                    b1.setColumn(b.getColumn());
                }
                else {
                    WhiteCell w = (WhiteCell) board[i][j];
                    r[i][j] = new WhiteCell();
                    WhiteCell w1 = (WhiteCell) r[i][j];
                    w1.setCorrectValue(w.getCorrectValue());
                }
            }
        }
        return r;
    }

    /**
     *
     * @param board representa un tablero
     * @return numero de casillas blancas del tablero
     */
    public static int countCells(Cell[][] board) {
        int r = 0;
        for(int i = 0; i < board.length;++i) {
            for(int j = 0; j < board[0].length;++j) {
                if(board[i][j].isWhite()) ++r;
            }
        }
        return r;
    }

    public static void printBoard(Cell[][] board) {
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board.length; ++j) {
                if(board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    System.out.print(w.getCorrectValue());
                }
                else System.out.print('.');
                System.out.print(' ');
            }
            System.out.println();
        }
    }


   public static boolean fillBoardAux(Cell[][] board,int r,int c, int[][] posComb) {
        //( 1 ) Rellenamos el tablero
        //( 2 ) Calculamos las sumas
        if( r == board.length) {
            colSums(board);
            rowSums(board);
            printBoard(board);
            Cell[][] sol = copyBoard(board);
            clearBoard(sol);
            currentKakuro = new Kakuro("0",0, sol);
            CtrlValidate.setKakuro(currentKakuro);
            int[] vec = {0,0,0,0,0,0,0,0,0,0};
            int[] res = new int[1];
            CtrlValidate.validate(0,0,0,vec,res);
            if(res[0] == 1) {
                System.out.println("kakuro único generado");
            }
            else System.out.println("kakuro no es unico");
            return true;
        }
        else if( c == board[0].length) return fillBoardAux(board,r+1,0,posComb);
        else if(!board[r][c].isWhite()) return fillBoardAux(board,r,c+1,posComb);
        else {
            WhiteCell w = (WhiteCell) board[r][c];
            int random = (int) Math.abs(ThreadLocalRandom.current().nextInt()) % 9 + 1;
            int[] posValues = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            valuesUsedCol(board, r - 1, c, posValues);
            valuesUsedRow(board, r, c - 1, posValues);
            if (random == 0) random = 1;
            for (int k = 1; k < 10; ++k) {
                if (posValues[random] == 0) {
                    w.setCorrectValue(random);
                    if (fillBoardAux(board, r, c + 1, posComb)) return true;
                    w.setCorrectValue(0);
                }
                ++random;
                if (random == 10) random = 1;
            }
        }
        return false;
   }


    /**
     *
     * @param board representa un tablero
     * @return retorna cierto si se ha podido crear un tablero único, sino, false.
     */
    public static boolean fillBoard(Cell[][] board){
        int [][] posComb =new int[][] { //possible values for sum using n cels
                {}, //con zero
                {1,2,3,4,5,6,7,8,9}, //una casilla
                {3,4,5,6,7,8,9,10,11,12,13,14,15,16,17}, //dos casillas
                {6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24}, //tres casillas
                {10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}, //cuatro casillas
                {15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36}, //cinco casillas
                {21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,37,38,39}, //seis casillas
                {28,29,30,31,32,33,34,36,37,38,39,40,41,42}, //siete casillas
                {36,37,38,39,40,41,42,43,44}, //ocho casillas
                {45} //nueve casillas
        };
        return fillBoardAux(board,0,0,posComb);
    }

    /**
     *
     * @param board representa un tablero
     *
     * Nos crea la primera fila y columna, y la última fila y columna
     */
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

    /**
     *
     * @param board representa un tablero
     * Situa en el tablero celdas negras y blancas.
     */
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


    public static void checkMaxRows(Cell[][] board) {
        int size = board.length;

        for(int i = 1; i < size; ++i ) {
            for(int j = 1; j < size;++j) {
                int n = countWhiteCellsV(board,i-1,j);
                int m = countWhiteCellsH(board,i,j-1);
                int n1 = countWhiteCellsV(board,i-1,j-1);
                int m1 = countWhiteCellsH(board,i-1,j-1);
                if( n == 9 && n>= n1-2 ) {
                    board[i][j] = new BlackCell();
                }
                else if( m == 9 && m>= m1-2) {
                    board[i][j] = new BlackCell();
                }
            }
        }
    }

    /**
     *
     * @param board representa un tablero
     * Esta función se encarga de comprobar que no hay runs de una sola casilla blanca.
     * Si esto sucede, hace los cambios necesarios para arreglarlo
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
                            }
                        }
                        if( r == size-2) {
                            if(board[r+1][c].isWhite()) {
                                board[r+1][c] = new BlackCell();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param board representa un tablero
     * @param i indica la fila actual
     * @param j indica la columna actual
     * @param auxBoard tablero que nos indica si una casilla ha sido visitada
     * @return número de casillas blancas a las que podemos llegar
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

    /**
     *
     * @param board representa un tablero
     * @return true si el tablero es connexo, false si no lo es.
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

    /**
     *
     * @param size
     * @return un kakuro con solución única
     *
     * Esta función es la encargada de generar una kakuro des de zero, siguiendo las normas básicas
     */
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
            checkMaxRows(board);
            checkBoard(board);
            //falta mirar que sigui connex
            if(!connexBoard(board)){
                repeat = true;
            }
            System.out.println("Tablero creado");

            currentKakuro = new Kakuro("0",0,board);
            if(!repeat ) fillBoard(board);
            //if(!repeat && fillBoard(board)) System.out.println("Kakuro with unique solution found");
            //else repeat = true;
        }
        return currentKakuro;
    }
}


