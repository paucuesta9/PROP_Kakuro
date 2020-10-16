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

    public void play() {

    }
}
