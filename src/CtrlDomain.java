import java.io.*;

public class CtrlDomain {

    boolean checkColumn(int r,int c,int sum, boolean f,int i, Cell [][] board) {
        if(board[r][c].isWhite()){
            WhiteCell w = (WhiteCell) board[r][c];
            if( sum != 0 && w.getValue() == i )return false;
            sum += w.getValue();
            return checkColumn(r-1, c, sum, f, i, board);
        }
        else {
            BlackCell b = (BlackCell) board[r][c];
            if( ( f && b.getVertical() == sum ) || (!f && b.getVertical() > sum) ) return true;
        }
        return false;
    }

    boolean checkRow(int r,int c,int sum,boolean f,int i, Cell[][] board) {
        if(board[r][c].isWhite()){
            WhiteCell w = (WhiteCell) board[r][c];
            if( sum != 0 && w.getValue() == i )return false;
            sum += w.getValue();
            return checkRow(r, c-1, sum, f, i, board);
        }
        else {
            BlackCell b = (BlackCell) board[r][c];
            if( ( f && b.getHorizontal() == sum ) || (!f && b.getHorizontal() > sum) ) return true;
        }
        return false;
    }

    boolean resKakuro(Kakuro K, int r, int c) {
        Cell [][] board = K.getBoard();
        if(r > K.getRowSize() - 1 && c > K.getColumnSize() ) {
            //print solution
            return true;
        }
        else {
            if( !board[r][c].isWhite()) {
                if( c == K.getColumnSize()-1) return resKakuro(K,r+1, 0); //cambiamos de fila, estamos en la ultima columna
                else return resKakuro(K,r,c+1); //cambiamos de columna
            }
            else {
                for(int i = 1; i < 10; ++i) {
                    WhiteCell w  = (WhiteCell) board[r][c];
                    w.setValue(i); // no me reconoce la clase WhiteCell
                    boolean f = false;
                    if( c == K.getRowSize()-1 || !board[r][c].isWhite() ) f = true; //si es final de columna o la siguiente casilla es negra marcamos que es final (f)
                    if(checkRow(r,c,0,f,i, board)) {
                        if( r == K.getColumnSize()-1 || !board[r+1][c].isWhite() ) f = true;
                        if( checkColumn(r,c,0,f,i, board)) { //pasamos a la siguiente casilla
                            if( c == K.getColumnSize()-1) return resKakuro(K,r+1, 0); //cambiamos de fila, estamos en la ultima columna
                            else return resKakuro(K,r,c+1); //cambiamos de columna
                        }
                    }
                }
            }
        }
        return false;
    }
    //constructora
    public CtrlDomain() {

    }

    public void play(String filePath) {
        Kakuro kakuro = readKakuro(filePath);
        Cell[][] board = kakuro.getBoard();
        for (int i = 0; i < kakuro.getRowSize(); ++i) {
            for (int j = 0; j < kakuro.getColumnSize(); ++j) {
                if (board[i][j].isWhite()) System.out.print("?");
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getVertical() == 0 && bc.getHorizontal() == 0) {
                        System.out.print("*");
                    }
                    if (bc.getVertical() != 0) System.out.print("C" + bc.getVertical());
                    if (bc.getHorizontal() != 0) System.out.print("F" + bc.getHorizontal());
                }
                System.out.print(",");
            }
            System.out.println("");
        }
    }

    private Kakuro readKakuro(String filePath) {
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            int rowSize = fr.read();
            fr.read();
            int columnSize = fr.read();
            Cell[][] board = new Cell[rowSize][columnSize];
            for (int i = 0; i < rowSize; ++i) {
                for (int j = 0; j < columnSize; ++j) {
                    int currentChar = fr.read();
                    if ((char)currentChar == '*') board[i][j] = new BlackCell(i, j);
                    else if ((char)currentChar == '?') board[i][j] = new WhiteCell(i, j);
                    else if (currentChar > 0 && currentChar < 10) board[i][j] = new WhiteCell(i, j, currentChar);
                    else {
                        int verticalInt, horizontalInt;
                        verticalInt = horizontalInt = 0;
                        if (currentChar == 'C') {
                            currentChar = fr.read();
                            String vertical = "";
                            while ((char) currentChar != ',' || (char) currentChar != 'F') {
                                vertical.concat(String.valueOf(currentChar));
                                currentChar = fr.read();
                            }
                            if ((char) currentChar == 'F') {
                                currentChar = fr.read();
                                String horizontal = "";
                                while ((char) currentChar != ',') {
                                    horizontal.concat(String.valueOf(currentChar));
                                    currentChar = fr.read();
                                }
                                horizontalInt = Integer.valueOf(horizontal);
                            }
                            verticalInt = Integer.valueOf(vertical);
                        } else {
                            currentChar = fr.read();
                            String horizontal = "";
                            while ((char) currentChar != ',') {
                                horizontal.concat(String.valueOf(currentChar));
                                currentChar = fr.read();
                            }
                            horizontalInt = Integer.valueOf(horizontal);
                        }
                        board[i][j] = new BlackCell(i, j, verticalInt, horizontalInt);
                    }
                }
            }
            Kakuro kakuro = new Kakuro("0", 0, rowSize, columnSize, board);
            return kakuro;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
