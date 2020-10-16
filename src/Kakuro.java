/* Alvaro Armada Ruiz */

import java.util.ArrayList;

public class Kakuro {
    private String id;
    private int difficulty;
    private int size;
    private Cell [][] board;

    public Kakuro(String id, int difficulty, int size, Cell[][] board) {
        this.id = id;
        this.difficulty = difficulty;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }
}
