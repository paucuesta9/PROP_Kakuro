public class Cell {
    private int posX;
    private int posY;

    public Cell(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isWhite() {return false;}

    public void setPosX(int posX) { this.posX = posX; }

    public int getPosX() { return posX; }

    public void setPosY(int posY) { this.posY = posY; }

    public int getPosY() { return posY; }
}
