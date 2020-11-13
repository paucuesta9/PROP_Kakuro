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

    public Kakuro(String kakuro) {
        String[] values = kakuro.split("\n");
        String[] valuesSize = values[0].split(",");
        rowSize = Integer.parseInt(valuesSize[0]);
        columnSize = Integer.parseInt(valuesSize[1]);
        board = new Cell[rowSize][columnSize];
        for (int i = 0; i < rowSize; ++i) {
            String[] valuesRow = values[i + 1].split(",");
            for (int j = 0; j < columnSize; ++j) {
                if (valuesRow[j].equals("*")) board[i][j] = new BlackCell(i, j);
                else if (valuesRow[j].equals("?")) board[i][j] = new WhiteCell(i, j);
                else if (valuesRow[j].charAt(0) == 'C' || valuesRow[j].charAt(0) == 'F') {
                    int vertical = 0, horizontal = 0;
                    if (valuesRow[j].charAt(0) == 'C') {
                        valuesRow[j] = valuesRow[j].substring(1);
                        if (valuesRow[j].contains("F")) {
                            String[] CF = valuesRow[j].split("F");
                            vertical = Integer.parseInt(CF[0]);
                            horizontal = Integer.parseInt(CF[1]);
                        } else {
                            vertical = Integer.parseInt(valuesRow[j]);
                        }
                    } else {
                        horizontal = Integer.parseInt(valuesRow[j].substring(1));
                    }
                    board[i][j] = new BlackCell(i, j, vertical, horizontal);
                } else board[i][j] = new WhiteCell(i, j, Integer.parseInt(valuesRow[j]));
            }
        }
    }

    public String toString() {
        StringBuilder content = new StringBuilder();
        String line;
        line = rowSize + "," + columnSize;
        content.append(line).append("\n");
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                if (board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    if (w.getValue() != 0) content.append(w.getValue());
                    else content.append("?");
                }
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getVertical() == 0 && bc.getHorizontal() == 0) {
                        content.append("*");
                    }
                    if (bc.getVertical() != 0) content.append("C" + bc.getVertical());
                    if (bc.getHorizontal() != 0) content.append("F" + bc.getHorizontal());
                }
                if (j != columnSize - 1) content.append(",");
            }
            content.append("\n");
        }
        return content.toString();
    }

    public String correctToString() {
        StringBuilder content = new StringBuilder();
        String line;
        line = rowSize + "," + columnSize;
        content.append(line).append("\n");
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                if (board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    content.append(w.getCorrectValue());
                }
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getVertical() == 0 && bc.getHorizontal() == 0) {
                        content.append("*");
                    }
                    if (bc.getVertical() != 0) content.append("C" + bc.getVertical());
                    if (bc.getHorizontal() != 0) content.append("F" + bc.getHorizontal());
                }
                if (j != columnSize - 1) content.append(",");
            }
            content.append("\n");
        }
        return content.toString();
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

    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public boolean setValue(int x, int y, int value) {
        return board[x][y].setValue(value);
    }

    public boolean checkColumn(int r, int c, int i, boolean f, int sum) {
        if(!board[r][c].isWhite()) {
            BlackCell b = (BlackCell) board[r][c];
            if(f && b.getVertical() == sum) return true;
            else if (!f && b.getVertical() > sum) return true;
        }
        else {
            WhiteCell w = (WhiteCell) board[r][c];
            if( w.getCorrectValue() == i ) return false;
            sum += w.getCorrectValue();
            return checkColumn(r-1,c,i,f,sum);
        }
        return false;
    }

    public boolean checkRowValidity(int r, int c, int value, int sum, int total, int posy) {
        if (!board[r][c].isWhite()) {
            if (total != -1) {
                sum -= value;
                return total >= sum || total == 0;
            }
            else {
                BlackCell b = (BlackCell) board[r][c];
                total = b.getHorizontal();
                return checkRowValidity(r, c + 1, value, sum, total, posy);
            }
        }
        else {
            if (total == -1) return checkRowValidity(r, c - 1, value, sum, total, posy);
            else {
                WhiteCell w = (WhiteCell) board[r][c];
                if (w.getValue() == value && c != posy) return false;
                sum += w.getValue();
                return checkRowValidity(r, c + 1, value, sum, total, posy);
            }
        }
    }

    public boolean checkColumnValidity(int r, int c, int value, int sum, int total, int posx) {
        if (!board[r][c].isWhite()) {
            if (total != -1) {
                sum -= value;
                return total >= sum || total == 0;
            }
            else {
                BlackCell b = (BlackCell) board[r][c];
                total = b.getVertical();
                return checkColumnValidity(r+1, c, value, sum, total, posx);
            }
        }
        else {
            if (total == -1) return checkColumnValidity(r -1, c, value, sum, total, posx);
            else {
                WhiteCell w = (WhiteCell)  board[r][c];
                if (w.getValue() == value && r != posx) return false;
                sum += w.getValue();
                return checkColumnValidity(r+1, c, value, sum, total, posx);
            }
        }
    }

    public boolean checkValidity(int x, int y, int value) {
        return checkRowValidity(x, y-1, value, value, -1, y) && checkColumnValidity(x, y, value, value, -1, x);
    }

    public boolean isFinished() {
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                if (board[i][j].isWhite() && ((WhiteCell) board[i][j]).getValue() != ((WhiteCell) board[i][j]).getCorrectValue()) return false;
            }
        }
        return true;
    }
}
