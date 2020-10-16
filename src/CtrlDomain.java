import java.io.*;

public class CtrlDomain {

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
