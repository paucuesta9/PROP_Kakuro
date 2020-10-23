package domain;/* Alvaro Armada Ruiz */

public class Kakuro {
    private String id;
    private int difficulty;
    private int rowSize;
    private int columnSize;
    private Cell [][] board;

    public Kakuro(String id, int difficulty, int rowSize, int columnSize, Cell[][] board) {
        this.id = id;
        this.difficulty = difficulty;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.board = board;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public boolean checkColumn(Cell[][] board, int r, int c, int i, boolean f, int sum) {
        if( !board[r][c].isWhite() ) {
            BlackCell b = (BlackCell) board[r][c];
            if(f && b.getVertical() == sum) return true;
            else if (!f && b.getVertical() > sum) return true;
        }
        else {
            WhiteCell w = (WhiteCell) board[r][c];
            if( w.getValue() == i ) return false;
            sum += w.getValue();
            return checkColumn(board,r-1,c,i,f,sum);
        }
        return false;
    }

    public boolean resolve(int r, int c, int sum, int [] vec) {
        System.out.print(r);
        System.out.print(" ");
        System.out.print(c);
        System.out.println();

        if( r == this.getRowSize() ) { return true; } //hemos llegado al final, la solucion es correcta
        else {
            Cell[][] board = this.getBoard();
            if( !board[r][c].isWhite() ) { // estamos en una casilla negra; queremos cambiar de columna o de casilla
                if (sum != 0) return false;
                BlackCell b = (BlackCell) board[r][c];

                if (c != this.getColumnSize() - 1) { // si no estem a la utlima columna
                    if (board[r][c + 1].isWhite()) {
                        sum = b.getHorizontal();
                    }
                }
                int [] aux = {0,0,0,0,0,0,0,0,0,0};
                if (c == this.getColumnSize() - 1) return resolve(r + 1, 0, 0, aux); //cambiamos de fila, estamos en la ultima columna }
                else return resolve(r,c+1, sum, aux); //cambiamos de columna
            }
            else { // si estamos en una casilla blanca
                vec[0] = 1; //indica que s'ha modificat
                WhiteCell w = (WhiteCell) board[r][c];
                for(int i = 1; i < 10 && sum-i >= 0; ++i ) {
                    if ( vec[i] == 0) {
                        vec[i] = 1;
                        w.setValue(i);
                        boolean f = false;
                        if(r == this.getRowSize() - 1 || !board[r+1][c].isWhite()) f =true;
                        if(checkColumn(board, r-1, c, i, f, i)) {
                            if (c == this.getColumnSize() - 1 && sum-i != 0) { }
                            else if (c == this.getColumnSize() - 1 && sum-i == 0) { // estamos en la ultima casilla de la fila pero la suma es correcta
                                if (resolve( r + 1, 0, 0, vec)) {
                                    return true; // quiere decir que hemos llegado al final y esta bien
                                }
                            }
                            else if (resolve(r, c + 1, sum-i, vec)) { return true; }
                        }
                        vec[i] = 0;
                    }
                }
            }
        }
        return false;
    }

    public void validate(int r, int c, int sum, int [] vec, int [] res) {

        if( r == this.getRowSize() ) { res[0]++; } //hemos llegado al final, la solucion es correcta
        else {
            Cell[][] board = this.getBoard();
            if( !board[r][c].isWhite() ) { // estamos en una casilla negra; queremos cambiar de columna o de casilla
                if (sum != 0) return;
                BlackCell b = (BlackCell) board[r][c];

                if (c != this.getColumnSize() - 1) { // si no estem a la utlima columna
                    if (board[r][c + 1].isWhite()) {
                        sum = b.getHorizontal();
                    }
                }
                int [] aux = {0,0,0,0,0,0,0,0,0,0};
                if (c == this.getColumnSize() - 1) validate(r + 1, 0, 0, aux, res); //cambiamos de fila, estamos en la ultima columna }
                else validate(r,c+1, sum, aux, res); //cambiamos de columna
            }
            else { // si estamos en una casilla blanca
                vec[0] = 1; //indica que s'ha modificat
                WhiteCell w = (WhiteCell) board[r][c];
                for(int i = 1; i < 10 && sum-i >= 0; ++i ) {
                    if ( vec[i] == 0) {
                        vec[i] = 1;
                        w.setValue(i);
                        boolean f = false;
                        if(r == this.getRowSize() - 1 || !board[r+1][c].isWhite()) f =true;
                        if(checkColumn(board, r-1, c, i, f, i)) {
                            if (c == this.getColumnSize() - 1 && sum-i != 0) { }
                            else if (c == this.getColumnSize() - 1 && sum-i == 0) { // estamos en la ultima casilla de la fila pero la suma es correcta
                                validate(r + 1, 0, 0, vec, res);
                            }
                            else validate(r, c + 1, sum-i, vec, res);
                        }
                        vec[i] = 0;
                    }
                }
            }
        }
        return;
    }

    public boolean setValue(int x, int y, int value)
    {
        return board[x][y].setValue(value);
    }
}
