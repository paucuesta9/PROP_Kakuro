/* Alvaro Armada Ruiz */

import java.util.ArrayList;

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
}
