package domain;

/* Judith Almoño Gómez */
public class BlackCell extends Cell{
    private int column;
    private int row;

    public BlackCell(int posX, int posY) {
        super(posX, posY);
        this.column = 0;
        this.row = 0;
    }

    public BlackCell(int posX, int posY, int column, int row) {
        super(posX, posY);
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean setValue(int value) { return false; }
}
