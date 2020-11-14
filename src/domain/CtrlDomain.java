package domain;

import data.CtrlData;

import java.util.ArrayList;

public class CtrlDomain {

    private CtrlData data;
    private Game currentGame;
    private Kakuro currentKakuro;
    private Player currentPlayer;

    //constructora
    public CtrlDomain() {
        data = CtrlData.getInstance();
    }

    // OPTION 1 - PLAY
    public void startNewGame(int difficulty, int kakuroSize) {
        searchKakuro(difficulty, kakuroSize);
        CtrlPlay.startGame(currentKakuro);
        //currentGame = new Game(0,0, currentKakuro);
        //currentGame.startResumeTimer();
    }

    public void setGame(int game) {
        //TODO: Leer game
    }

    public boolean isFinished() {
        return currentKakuro.isFinished();
    }

    public boolean checkValidity(int x, int y, int value) {
        return currentKakuro.checkValidity(x, y, value);
    }

    public int helpMyValue(int x, int y) {
        return CtrlPlay.helpMyValue(x, y);
    }

    public boolean helpCorrectNumber(int x, int y) {
        return CtrlPlay.helpCorrectNumber(x, y);
    }


    // OPTION 2 - CREATE VALIDATE
    public boolean validate() {
        int [] res = new int[1];
        res[0] = 0;
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        CtrlValidate.validate(0,0, 0, vec, res);
        if (res[0]!=1) return false;
        else return true;
    }


    // OPTION 3 - RESOLVE
    public void resolve() {
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        CtrlResolve.setKakuro(currentKakuro);
        CtrlResolve.resolve(0,0, 0, vec);
    }


    // OPTION 4 - GENEREATE
    public boolean numberInColumn(Cell[][] board, int r, int i, int j) {
        if(!board[i][j].isWhite()) return false;
        if(((WhiteCell) board[i][j]).getValue() == r) return true;
        return numberInColumn(board, r,i - 1, j);
    }

    boolean numberInColumn(char[][] board,int r,int i,int j) {
        if(board[i][j] == '.') return false;
        if( board[i][j] == (char) (r)) return true;
        return numberInColumn(board,r,i-1,j);
    }

    public void fixRow(Cell[][] board, int i, int j, int cont) {
        if (j >= 0 && i >= 0 && cont > 9) {
            board[i][j] = new BlackCell(i, j);
            fixRow(board, i,j - 1,cont - 1);
        }
    }

    void fixRow(char [][] aux, int i, int j,int cont) {
        if (j >= 0 && i >= 0 && cont > 9) {
            aux[i][j] = '.';
            fixRow(aux,i,j-1,cont-1);
        }
    }

    public void fixCol(Cell[][] board, int i, int j, int cont) {
        if(i >= 0 && j >= 0 && cont > 9 ) {
            board[i][j] = new BlackCell(i, j);
            fixCol(board,i - 1, j,cont - 1);
        }
    }

    void fixCol(char [][] aux, int i, int j,int cont) {
        if(i >= 0 && j >= 0 && cont > 9 ) {
            aux[i][j] = '.';
            fixCol(aux,i-1,j,cont-1);
        }
    }

    public int moreThanNineC(Cell[][] board,int i,int j,int cont) {
        if(!board[i][j].isWhite()) {
            if (cont > 9) fixCol(board,i - 1, j, cont);
            return 0;
        }
        return 1 + moreThanNineC(board,i + 1, j,cont + 1);
    }

    int moreThanNineC(char[][]aux,int i,int j,int cont,int size) {
        if(aux[i][j] == '.' ) {
            if(cont > 9) fixCol(aux,i-1,j,cont);
            return 0;
        }
        return 1 + moreThanNineC(aux,i-1,j,cont+1,size);
    }

    public int moreThanNineF(Cell[][] board, int i, int j, int cont) {
        if (!board[i][j].isWhite()) {
            if (cont > 9) fixRow(board, i, j - 1, cont);
            return 0;
        }
        return 1 + moreThanNineF(board, i, j + 1, cont + 1);
    }

    int  moreThanNineF(char[][] aux,int i,int j, int cont,int size) {
        if (aux[i][j] == '.') {
            if(cont > 9) fixRow(aux,i,j-1,cont);
            return 0;
        }
        return 1 + moreThanNineF(aux,i,j+1,cont+1,size);
    }

    public void generate(int size) {
        Cell board[][] = new Cell[size][size];
        for (int i = 0; i < size; ++i)
            board[0][i] = board[i][0] = board[size - 1][i] = board[i][size - 1] = new BlackCell(0, 0);

        for (int i = 1; i < size - 1; ++i) {
            for (int j = i; j < size - 1; ++j) {
                int random = (int) (Math.random()* 10);
                if (random < 4) {
                    board[i][j] = new BlackCell();
                    board[j][i] = new BlackCell();
                } else {
                    board[i][j] = new WhiteCell();
                    board[j][i] = new WhiteCell();
                }
            }
        }

        for (int i = 1; i < size - 1; ++i) {
            for (int j = i; j < size - 1; ++j) {
                if (!board[i][j - 1].isWhite() && !board[i][j + 1].isWhite() && board[i][j].isWhite()) {
                    if (j < size / 2) {
                        board[i][j + 1] = new WhiteCell();
                        board[j + 1][i] = new WhiteCell();
                    } else {
                        board[i][j - 1] = new WhiteCell();
                        board[j - 1][i] = new WhiteCell();
                    }
                }
                if (!board[i - 1][j].isWhite() && !board[i + 1][j].isWhite() && board[i][j].isWhite()) {
                    if (i < size / 2) {
                        board[i + 1][j] = new WhiteCell();
                        board[j][i + 1] = new WhiteCell();
                    }
                    else {
                        board[i - 1][j] = new WhiteCell();
                        board[j][i - 1] = new WhiteCell();
                    }
                }
            }
        }

        int ic = 1;
        while (ic < size) {
            int jc = 0;
            while (jc < size) {
                if (board[ic][jc].isWhite()) {
                    int cont = 0;
                    jc += moreThanNineF(board, ic, jc, cont);
                }
                ++jc;
            }
            ++ic;
        }

        int jc = 1;
        while (jc < size) {
            ic = 0;
            while (ic < size) {
                if (board[ic][jc].isWhite()) {
                    int cont = 0;
                    ic += moreThanNineC(board, ic, jc, cont);
                }
                ++ic;
            }
            ++jc;
        }

        for (int i = 0; i < size - 1; ++i) {
            for (int j = 0; j < size - 1; ++j) {
                if (!board[i][j].isWhite()){
                    if (board[i][j + 1].isWhite()) {
                        int jaux = j + 1;
                        while (board[i][jaux].isWhite())
                            ++jaux;
                        int[] usedNumbers = new int [9];
                        int sum = 0;
                        for (int k = j + 1; k < jaux; ++k){
                            int r = (int) (Math.random() * 8) + 1;
                            while(usedNumbers[r - 1] == 1  || numberInColumn(board, r,i - 1, k)) {
                                r += 1;
                                if(r == 10) r = 1;
                            }
                            board[i][k].setValue(r);
                            sum += r;
                            usedNumbers[r - 1] = 1;
                        }
                    }
                }
            }
        }
        currentKakuro = new Kakuro("0", 1, size, size, board);
    }

    public void generate(int size,  int dif) {
        // Cell [][] board = new Cell [size][size];
        char [][] aux = new char [size][size]; //identificamos con W una casilla blanca, con . una negra y con - una por decidir
        for(int i = 0; i < size; ++i){ //la primera fila y la primera columna tienen que ser negras
            aux[0][i] = '.';
            aux[i][0] = '.';
            aux[size-1][i] = '.';
            aux[i][size-1] = '.';
        }
        //hacemos el tablero simetrico
        for(int i = 1; i < size-1; ++i) {
            for( int j = i; j < size-1; ++j) {
                int random = (int) (Math.random()*10);
                if (random < 4) {
                    aux[i][j] = '.';
                    aux[j][i] = '.';
                }
                else {
                    aux[i][j] = '_';
                    aux[j][i] = '_';
                }
            }
        }
        for(int i = 1; i < size - 1; ++i) {
            for(int j = i; j < size - 1; ++j) {
                if(aux[i][j-1] == '.' && aux[i][j+1] == '.' && aux[i][j] == '_') { //evitamos que haya casillas blancas sin vecinos por fila
                    if(j < size/2) {
                        aux[i][j+1] = '_';
                        aux[j+1][i] = '_';
                    }
                    else {
                        aux[i][j-1] = '_';
                        aux[j-1][i] = '_';
                    }
                }
                if(aux[i-1][j] == '.' && aux[i+1][j] == '.' && aux[i][j] == '_') { //evitamos que haya casillas blancas sin vecinos por columna
                    if( i < size/2) {
                        aux[i+1][j] = '_';
                        aux[j][i+1] = '_';
                    }
                    else {
                        aux[i-1][j] = '_';
                        aux[j][i-1] = '_';
                    }
                }
            }
        }

        int ic = 1;
        while(ic < size) {
            int jc = 0;
            while(jc < size) {
                if(aux[ic][jc] != '.') {
                    int cont = 0;
                    jc += moreThanNineF(aux,ic, jc, cont, size);
                }
                ++jc;
            }
            ++ic;
        }

        //miramos que no haya mas de 9 casillas blancas seguidas por columna
        int jc = 1;
        while(jc < size) {
            ic = 0;
            while(ic < size) {
                if(aux[ic][jc] != '.') {
                    int cont = 0;
                    ic += moreThanNineC(aux,ic, jc, cont, size);
                }
                ++ic;
            }
            ++jc;
        }

        //miramos que se complan algunas reglas


        //miramos que no haya mas de 9 casillas blancas seguidas por fila
        //queremos rellenar el tablero de numeros (trabajo con aux pese a tener el tablero montado ya que es menos costoso pera hacer consultas)
        for(int i = 0; i < size-1; ++i){
            for(int j = 0; j < size-1; ++j) {
                if(aux[i][j] == '.'){
                    if(aux[i][j+1] != '.') { //si el siguiente es blanco, queremos mirar cuantas casillas se tienen que rellenar
                        int jaux = j + 1;
                        while(aux[i][jaux] != '.') { ++jaux; } //se tienen que rellenar jaux-j-1 casilla
                        int usedNumbers[] = new int [9];
                        int sum = 0;
                        for(int k = j+1; k < jaux; ++k){
                            int r = (int) (Math.random()*8)+1;

                            while(usedNumbers[r - 1] == 1  || numberInColumn(aux,r,i-1,k)) {
                                r += 1;
                                if(r == 10) r = 1;
                            }

                            aux[i][k] = (char) (r+'0');
                            sum += r;
                            usedNumbers[r - 1] = 1;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < size; ++i) {
            for( int j = 0; j < size; ++j) {
                System.out.print(aux[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        //CASILLAS NEGRAS
        //primera fila y columna son si o si negras
        /*for(int i = 0; i < size; ++i ) {
            board[0][i] = new BlackCell(0, i); //primera fila
            board[i][0] = new BlackCell(i,0); //priemra columna
        }
        //miramos que casillas pueden ser negras y cuales no
        for(int i = 1; i < size-1; ++i) {
            for(int j = 1; j < size-1; ++j) {

            }
        }*/

    }


    /* GETTERS AND SETTERS (CLASSES) */
    public boolean kakuroSetValue(int x, int y, int value) {
        return currentKakuro.setValue(x, y, value);
    }

    public int getRowSize() {
        return currentKakuro.getRowSize();
    }

    public int getColumnSize() {
        return currentKakuro.getColumnSize();
    }

    public String getKakuroToString() {
        return currentKakuro.toString();
    }

    public String getCorrectKakuroToString() {
        return currentKakuro.correctToString();
    }


    /* READ AND WRITE (FILE) */
    public void searchKakuro(int difficulty, int kakuroSize) {
        this.currentKakuro = new Kakuro(data.searchKakuro(difficulty, kakuroSize));
    }

    public void getKakuro(String filePath) {
        this.currentKakuro = new Kakuro(data.getKakuro(filePath));
    }

    public ArrayList<Integer> getStartedGames() {
        return currentPlayer.getStartedGames();
    }

    public void saveKakuro() {
        data.saveKakuro(currentKakuro.toString(), currentKakuro.getDifficulty(), currentKakuro.getRowSize());
    }
}
