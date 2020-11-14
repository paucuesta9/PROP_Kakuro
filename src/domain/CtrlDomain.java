package domain;

import data.CtrlData;
import java.util.ArrayList;

/** @file CtrlDomain.java
 @brief Clase  <em>CtrlDomain</em>.
 */


/** @brief Clase CtrlDomain que contiene los atributos y metodos para el intercambio de atributos entre controladores
 */
public class CtrlDomain {

    /**
     * Instáncia del cotrolador persistencia
     */
    private CtrlData data;
    /**
     *
     */
    private Game currentGame;
    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */
    private Kakuro currentKakuro;
    private Player currentPlayer;

    /** @brief Creadora por defecto
     *
     * Se obtiene la instancia singleton del controlador persistencia
     */
    public CtrlDomain() {
        data = CtrlData.getInstance();
    }

    // OPTION 1 - PLAY

    /** @brief Inicia una nueva partida
     *
     * Se busca un tablero que cumpla las condiciones y llama al CtrlPlay para iniciar la partida
     *
     * @param difficulty dificultad del tablero, entre 1 y 3
     * @param kakuroSize tamaño del tablero
     */
    public void startNewGame(int difficulty, int kakuroSize) {
        searchKakuro(difficulty, kakuroSize);
        CtrlPlay.startGame(currentKakuro);
        //currentGame = new Game(0,0, currentKakuro);
        //currentGame.startResumeTimer();
    }

    /** @brief
     *
     * @param game
     */
    public void setGame(int game) {
        //TODO: Leer game
    }

    /** @brief Se mira si el usuario ha completado el tablero
     *
     * @return cierto si el usuario ha colocado todos los valores de las celdas y son correctos, en caso contrario, devuelve falso
     */
    public boolean isFinished() {
        return currentKakuro.isFinished();
    }

    /** @brief Comprueba la validez de un número añadido a una celda blanca
     *
     * Comprueba si el valor añadido a la celda blanca cumple las condiciones de la fila y de la columna
     * @param x representa el número de la fila del tablero
     * @param y representa el número de la columna del tablero
     * @param value representa el valor añadido a la celda blanca
     * @return devuelve cierto si se cumplen las condiciones tanto en la fila como en la columna y falso si no se cumplen en la fila, la columna o ambas
     */
    public boolean checkValidity(int x, int y, int value) {
        return currentKakuro.checkValidity(x, y, value);
    }

    /** @brief Se activa la ayuda que mira si el valor que el usuario ha puesto es correcto
     *
     * @param x Posición de fila de la celda
     * @param y Posición de columna de la celda
     * @return 1 si el valor que el usuario ha colocado en la posición del tablero [x][y] es correcto,
     * @return 0 si es incorrecto,
     * @return -1 si la casilla [x][y] es negra,
     * @return -2 si no se ha colocado un valor
     */
    public int helpMyValue(int x, int y) {
        return CtrlPlay.helpMyValue(x, y);
    }

    /** @brief Se activa la ayuda que coloca el valor correcto de una celda
     *
     * @param x Posición de fila de la celda
     * @param y Posición de columna de la celda
     * @return Devuelve cierto si la posición [x][y] es una celda blanca, en caso contrario, devuelve falso
     */
    public boolean helpCorrectNumber(int x, int y) {
        return CtrlPlay.helpCorrectNumber(x, y);
    }

    // OPTION 2 - CREATE VALIDATE

    /** @brief Se valida un Kakuro que sea correcto
     *
     * @return Devuelve cierto si el Kakuro es correcto, en caso contrario, devuelve falso
     */
    public boolean validate() {
        int [] res = new int[1];
        res[0] = 0;
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        CtrlValidate.validate(0,0, 0, vec, res);
        if (res[0]!=1) return false;
        else return true;
    }


    // OPTION 3 - RESOLVE

    /** @brief Se resuelve un Kakuro
     *
     */
    public void resolve() {
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        CtrlResolve.setKakuro(currentKakuro);
        CtrlResolve.resolve(0,0, 0, vec);
    }


    int countWhiteCellsV(Cell[][] board,int i,int j) {
        if( !board[i][j].isWhite()) return 0;
        return 1+countWhiteCellsV(board,i-1,j);
    }

    int countWhiteCellsH(Cell[][] board,int i,int j) {
        if( !board[i][j].isWhite()) return 0;
        return 1+countWhiteCellsH(board,i,j-1);
    }


    void valuesUsedRow(Cell[][] board,int i,int j, int [] posValues) {
        if(!board[i][j].isWhite()) {
            return;
        }
        else {
            valuesUsedRow(board,i,j-1,posValues);

            WhiteCell w = (WhiteCell) board[i][j];
            posValues[w.getCorrectValue()] = 1;
        }
    }

    void valuesUsedCol(Cell[][] board,int i,int j, int [] posValues) {
        if(!board[i][j].isWhite()) {
            return;
        }
        else {
            valuesUsedCol(board,i-1,j,posValues);

            WhiteCell w = (WhiteCell) board[i][j];
            posValues[w.getCorrectValue()] = 1;
        }
    }

    void rowSums(Cell[][] board) {
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
    void colSums(Cell[][] board) {
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

    void nineCellsRow(Cell[][] board, int[][][] tempBoard) { //horizontal
        int lastC = 0;
        int lastR = 0;
        int count = 0;
        for(int i = 0; i < board.length; ++i ) {
            for(int j = 0; j < board[0].length; ++j) {
                if(!board[i][j].isWhite()) {
                    if(count != 0) {
                        if(count == 9) {
                            BlackCell b = (BlackCell) board[lastR][lastC];
                            b.setRow(45);
                            tempBoard[lastR][lastC][1] = 9;
                        }
                        else {
                            tempBoard[lastR][lastC][1] = count;
                        }
                    }
                    count = 0;
                    lastC= j;
                    lastR = i;
                }
                else ++count;
            }
        }
        if(count == 9) {
            BlackCell b = (BlackCell) board[lastR][lastC];
            b.setRow(45);
            tempBoard[lastR][lastC][1] = 9;
        }
        else {
            tempBoard[lastR][lastC][1] = count;
        }
    }
    void nineCellsCol(Cell[][] board, int [][][] tempBoard) { //vertical
        int lastC = 0;
        int lastR = 0;
        int count = 0;
        for(int j = 0; j < board[0].length; ++j ) {
            for(int i = 0; i < board.length; ++i) {
                if(!board[i][j].isWhite()) {
                    if(count != 0) {
                        if(count == 9) {
                            BlackCell b = (BlackCell) board[lastR][lastC];
                            b.setColumn(45);
                            tempBoard[lastR][lastC][0] = 9;
                        }
                        else {
                            tempBoard[lastR][lastC][0] = count;
                        }
                    }
                    count = 0;
                    lastC= j;
                    lastR = i;
                }
                else ++count;
            }
        }
        if(count != 0) {
            if(count == 9) {
                BlackCell b = (BlackCell) board[lastR][lastC];
                b.setColumn(45);
                tempBoard[lastR][lastC][0] = 9;
            }
            else {
                tempBoard[lastR][lastC][0] = count;
            }
        }
    }

    public void computePosSumsRec(int arr[], int index, int num, int reducedNum, int lim, int posSums[], int no) {
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
                computePosSumsRec(arr, index + 1, num, reducedNum - k, lim, posSums, no);
            }
        }
    }

    public int [] computePosSums(int x, int n, int no) { //calcula combinaciones de numeros que suman x con n numeros. deja los resultados en posSums.
        int [] posSums = new int[9];
        for(int i=0; i<9; ++i) posSums[i] = 0;

        int [] arr = new int[n];

        computePosSumsRec(arr, 0, x-no, x-no, n, posSums, no);
        return posSums;
    }

    int intersection(int [] valuesH,int [] valuesV) {
        int c = 0;
        int x = -1;
        for(int i =0; i < 9 && c != 2; ++i) {
            if(valuesH[i] == 1 && valuesV[i] == 1) {
                c++;
                x = i;
            }
        }
        if(c == 1) return x;
        return -1;
    }
    int atLeastOneValue(int [] valuesH,int [] valuesV) {
        for(int i =0; i < 9; ++i) {
            if(valuesH[i] == 1 && valuesV[i] == 1) {
                return 1;
            }
        }
        return 0;
    }

    int [] possibleUsedValuesRow(Cell[][] board,int i,int j,int [][][] tempBoard) {
        int[] res = new int[] {0,0,0,0,0,0,0,0,0};
        int c = j;
        while( c < board[0].length && board[i][c].isWhite()){
            for(int k = 0; k < 9; ++k) {
                if(tempBoard[i][c][k] == 1) res[k] = 1;
            }
            ++c;
        }
        return res;
    }
    boolean ValueInRow(Cell[][] board,int r,int c,int x) {
        if(!board[r][c].isWhite()) {
            return false;
        }
        WhiteCell w = (WhiteCell) board[r][c];
        if(w.getCorrectValue() == x) return true;
        else return ValueInRow(board,r,c-1,x);
    }
    boolean ValueInCol(Cell[][] board,int r,int c,int x) {
        if(!board[r][c].isWhite()) {
            return false;
        }
        WhiteCell w = (WhiteCell) board[r][c];
        if(w.getCorrectValue() == x) return true;
        else return ValueInRow(board,r-1,c,x);
    }

    void printBoard(Cell[][] board) {
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board.length; ++j) {
                if(board[i][j].isWhite()) {
                    //WhiteCell w = (WhiteCell) board[i][j];
                    System.out.print('X');
                }
                else System.out.print('.');
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    boolean fillBoardAux(Cell[][] board,int r,int c, int[][] posComb, int[][][] tempBoard) {
        if(r == board.length){
            rowSums(board);
            colSums(board);
            currentKakuro = new Kakuro("0",0, board.length, board[0].length,board);
            int [] res = new int[1];
            res[0] = 0;
            int [] vec = {0,0,0,0,0,0,0,0,0,0};
            CtrlValidate.setKakuro(currentKakuro);
            CtrlValidate.validate(0,0, 0, vec, res);
            if(res[0]!=1 ){
                System.out.println("El kakuro generado no es unico");
                return false;
            }
            return true;
        }
        else if(c == board[0].length) return fillBoardAux(board,r+1,0,posComb,tempBoard);
        else if(board[r][c].isWhite()) return fillBoardAux(board,r,c+1,posComb,tempBoard);
        else {
            int nH = tempBoard[r][c][1];
            int nV = tempBoard[r][c][0];
            if(nH != 0 && nV!=0 ) {
                BlackCell b = (BlackCell) board[r][c];
                for(int i = 0; i < posComb[nH].length;++i){
                    for(int j = posComb[nV].length-1; j >= 0;--j){
                        b.setRow(posComb[nH][i]);
                        b.setColumn(posComb[nV][j]);
                        if(fillBoardAux(board,r,c+1,posComb,tempBoard)) return true;
                        b.setRow(0);
                        b.setColumn(0);
                    }
                }
            }
            else if(nH!= 0) {
                BlackCell b = (BlackCell) board[r][c];
                for(int i = 0; i < posComb[nH].length;++i){
                    b.setRow(posComb[nH][i]);
                    if(fillBoardAux(board,r,c+1,posComb,tempBoard)) return true;
                    b.setRow(0);
                }
            }
            else if(nV!= 0) {
                BlackCell b = (BlackCell) board[r][c];
                for(int i = 0; i < posComb[nV].length;++i){
                    b.setColumn(posComb[nV][i]);
                    if(fillBoardAux(board,r,c+1,posComb,tempBoard)) return true;
                    b.setColumn(0);
                }
            }
            else return fillBoardAux(board,r,c+1,posComb,tempBoard);
        }
        return false;
    }

    boolean fillBoard(Cell[][] board){
        int [][] posComb =new int[][] { //possible values for sum using n cels
                {}, //0
                {1,2,3,4,5,6,7,8,9}, //1
                {3,4,5,6,7,8,9,10,11,12,13,14,15,16,17}, //2
                {6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24}, //3
                {10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}, //4
                {15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36}, //5
                {21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,37,38,39}, //6
                {28,29,30,31,32,33,34,36,37,38,39,40,41,42}, //7
                {36,37,38,39,40,41,42,43,44}, //8
                {45} //9
        };

        //calcular totes les possibles
        //List<List<Integer>>[][] combinations = calculateCombinations();
        int tempBoard[][][] = new int[board.length][board[0].length][9];
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board.length; ++j ) {
                for(int k = 0; k < 9; ++k) {
                    tempBoard[i][j][k] = 0;
                }
            }
        }
        nineCellsRow(board,tempBoard);
        nineCellsCol(board, tempBoard);
        return fillBoardAux(board,0,0,posComb,tempBoard);
    }

    private void firstColRow(Cell[][] board) {
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

    private void randomCells(Cell[][] board) {
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

    private void checkBoard(Cell[][] board) {
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

    int DFS(Cell[][] board,int i,int j,int[][] auxBoard) {
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

    boolean connexBoard(Cell[][] board) {
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
    public void generate(int size) {
        boolean repeat = true;
        while(repeat) {
            repeat = false;
            Cell [][] board = new Cell [size][size];
            for(int i = 0; i < size; ++i){ //la primera fila y la primera columna tienen que ser negras
                board[0][i] = new BlackCell(0,i);
                board[i][0] = new BlackCell(i,0);
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
            //if(!repeat && fillBoard(board)) System.out.println("Kakuro with unique solution found");
            //else repeat = true;
        }

    }





    /* GETTERS AND SETTERS (CLASSES) */

    /** @brief Setter de valor en una celda
     *
     * Se setea el valor en la celda de la posición [x][y] con el valor "value" si la celda es blanca
     *
     * @param x Posición de fila de una celda
     * @param y Posición de columna de una celda
     * @param value Valor que el usuario quiere colocar
     * @return Devuelve cierto si la celda es blanco, en caso contrario, devuelve falso
     */
    public boolean kakuroSetValue(int x, int y, int value) {
        return currentKakuro.setValue(x, y, value);
    }

    /** @brief Getter del tamaño de fila
     *
     * @return Devuelve un entero con el tamaño de fila del tablero
     */
    public int getRowSize() {
        return currentKakuro.getRowSize();
    }

    /** @brief Getter del tamaño de columna
     *
     * @return Devuelve un entero con el tamaño de columna del tablero
     */
    public int getColumnSize() {
        return currentKakuro.getColumnSize();
    }

    /** @brief Getter del kakuro en formato String
     *
     * @return Devuelve un String con el kakuro en formato para fichero o para printear en consola
     */
    public String getKakuroToString() {
        return currentKakuro.toString();
    }

    /** @brief Getter del kakuro correcto en formato String
     *
     * @return Devuelve un String con el kakuro correcto (resuelto) en formato para fichero o para printear en consola
     */
    public String getCorrectKakuroToString() {
        return currentKakuro.correctToString();
    }


    /* READ AND WRITE (FILE) */
    /** @bief Busca un Kakuro
     *
     * Busca un kakuro que coincida con las condiciones de dificultad y tamaño
     *
     * @param difficulty Dificultad del Kakuro
     * @param kakuroSize Tamaño del tablero
     */
    public void searchKakuro(int difficulty, int kakuroSize) {
        this.currentKakuro = new Kakuro(data.searchKakuro(difficulty, kakuroSize));
    }

    /** @brief Getter de Kakuro
     *
     * Se obtiene un Kakuro y se asigna al currentKakuro a partir de una ruta relativa
     *
     * @param filePath Ruta relativa al fichero con el Kakuro que se busca
     */
    public void getKakuro(String filePath) {
        this.currentKakuro = new Kakuro(data.getKakuro(filePath));
    }

    /** @brief Getter de partidas empezadas por el usuario actual
     *
     * @return Devuelve una lista de Strings con los identificadores de las partidas que tiene empezadas el usuario
     */
    public ArrayList<Integer> getStartedGames() {
        return currentPlayer.getStartedGames();
    }

    /** @brief Guarda un Kakuro en un fichero
     *
     */
    public void saveKakuro() {
        data.saveKakuro(currentKakuro.toString(), currentKakuro.getDifficulty(), currentKakuro.getRowSize());
    }
}
