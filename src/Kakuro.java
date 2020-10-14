/* Alvaro Armada Ruiz */

public class Kakuro {
    private String id;
    private int difficulty;
    private int size;

    public Kakuro(String id, int difficulty, int size) {
        this.id = id;
        this.difficulty = difficulty;
        this.size = size;
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
}
