public class BlackCell extends Cell{
    private int vertical;
    private int horizontal;

    public BlackCell(int posX, int posY) {
        super(posX, posY);
    }

    public BlackCell(int posX, int posY, int vertical, int horizontal) {
        super(posX, posY);
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

}
