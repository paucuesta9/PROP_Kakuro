package domain;

import data.CtrlData;

import java.util.ArrayList;

public class CtrlDomain {

    private CtrlData data;
    private Game currentGame;
    private Kakuro currentKakuro;
    private Player currentPlayer;

    //constructora
    public CtrlDomain() {
        data = CtrlData.getInstance();
    }

    public boolean validate() {
        int [] res = new int[1];
        res[0] = 0;
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        currentKakuro.validate(0,0, 0, vec, res);
        if (res[0]!=1) return false;
        else return true;
    }

    //solo de prueba

    public void difficulty() {
        currentKakuro.setDifficulty();
    }


    public void resolve() {
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        currentKakuro.resolve(0,0, 0, vec);
    }

    public void generate() {}

    public void searchKakuro(int difficulty, int kakuroSize) {
        this.currentKakuro = new Kakuro(data.searchKakuro(difficulty, kakuroSize));
    }

    public void getKakuro(String filePath) {
        this.currentKakuro = new Kakuro(data.getKakuro(filePath));
    }

    public int getRowSize() {
        return currentKakuro.getRowSize();
    }

    public int getColumnSize() {
        return currentKakuro.getColumnSize();
    }

    public void saveKakuro() {
        data.saveKakuro(currentKakuro.toString(), currentKakuro.getDifficulty(), currentKakuro.getRowSize());
    }

    public boolean kakuroSetValue(int x, int y, int value) {
        return currentKakuro.setValue(x, y, value);
    }

    public boolean checkValidity(int x, int y, int value) {
        return currentKakuro.checkRowValidity(currentKakuro.getBoard(), x, y-1, value, value, -1, y) && currentKakuro.checkColumnValidity(currentKakuro.getBoard(), x, y, value, value, -1, x);
    }

    public boolean isFinished() {
        return currentKakuro.isFinished();
    }

    public int helpMyValue(int x, int y) {
        if (currentKakuro.getBoard()[x][y].isWhite()) {
            int value = ((WhiteCell) currentKakuro.getBoard()[x][y]).getValue();
            if (value == 0) return -2;
            else return (((WhiteCell) currentKakuro.getBoard()[x][y]).getCorrectValue() == value) ? 1 : 0;
        }
        return -1;
    }

    public boolean helpCorrectNumber(int x, int y) {
        if (currentKakuro.getBoard()[x][y].isWhite()) {
            currentKakuro.setValue(x, y, ((WhiteCell) currentKakuro.getBoard()[x][y]).getCorrectValue());
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getStartedGames() {
        return currentPlayer.getStartedGames();
    }

    public void setGame(int game) {
        //TODO: Leer game
    }

    public void startNewGame(int difficulty, int kakuroSize) {
        searchKakuro(difficulty, kakuroSize);
        currentGame = new Game(0,0,currentKakuro);
        currentGame.startResumeTimer();
    }

    public String getKakuroToString() {
        return currentKakuro.toString();
    }
}
