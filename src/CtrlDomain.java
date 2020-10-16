import java.io.*;

public class CtrlDomain {

    //constructora
    public CtrlDomain() {

    }

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

    public void play(String filePath) {
        Kakuro kakuro = readKakuro(filePath);
        writeKakuroInTerminal(kakuro);
    }

    public Kakuro readKakuro(String filePath) {
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] values = line.split(",");
            int rowSize = Integer.parseInt(values[0]);
            int columnSize = Integer.parseInt(values[1]);
            Cell[][] board = new Cell[rowSize][columnSize];
            for (int i = 0; i < rowSize; ++i) {
                line = br.readLine();
                values = line.split(",");
                for (int j = 0; j < columnSize; ++j) {
                    if (values[j].equals("*")) board[i][j] = new BlackCell(i, j);
                    else if (values[j].equals("?")) board[i][j] = new WhiteCell(i, j);
                    else if (values[j].charAt(0) == 'C' || values[j].charAt(0) == 'F') {
                        int vertical = 0, horizontal = 0;
                        if (values[j].charAt(0) == 'C') {
                            values[j] = values[j].substring(1);
                            if (values[j].contains("F")) {
                                String[] CF = values[j].split("F");
                                vertical = Integer.parseInt(CF[0]);
                                horizontal = Integer.parseInt(CF[1]);
                            } else {
                                vertical = Integer.parseInt(values[j]);
                            }
                        } else {
                            horizontal = Integer.parseInt(values[j].substring(1));
                        }
                        board[i][j] = new BlackCell(i, j, vertical, horizontal);
                    } else board[i][j] = new WhiteCell(i, j, Integer.parseInt(values[j]));
                }
            }
            return new Kakuro("0", 0, rowSize, columnSize, board);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeKakuroInTerminal(Kakuro k) {
        Cell[][] board = k.getBoard();
        for (int i = 0; i < k.getRowSize(); ++i) {
            for (int j = 0; j < k.getColumnSize(); ++j) {
                if (board[i][j].isWhite()) System.out.print("?");
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getVertical() == 0 && bc.getHorizontal() == 0) {
                        System.out.print("*");
                    }
                    if (bc.getVertical() != 0) System.out.print("C" + bc.getVertical());
                    if (bc.getHorizontal() != 0) System.out.print("F" + bc.getHorizontal());
                }
                if (j != k.getColumnSize() - 1) System.out.print(",");
            }
            System.out.println("");
        }
    }
}
