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
        this.currentKakuro = data.searchKakuro(difficulty, kakuroSize);
    }

    public void getKakuro(String filePath) {
        this.currentKakuro = data.getKakuro(filePath);
    }

    public String getValueCell(int i, int j) {
        if (currentKakuro.getBoard()[i][j].isWhite()) {
            WhiteCell w = (WhiteCell) currentKakuro.getBoard()[i][j];
            if (w.getCorrectValue() != 0) return String.valueOf(w.getCorrectValue());
            else return "?";
        }
        else {
            BlackCell bc = (BlackCell) currentKakuro.getBoard()[i][j];
            if (bc.getVertical() == 0 && bc.getHorizontal() == 0) {
                return "*";
            }
            if (bc.getVertical() != 0) return ("C" + bc.getVertical());
            if (bc.getHorizontal() != 0) return ("F" + bc.getHorizontal());
        }
        return null;
    }

    public int getRowSize() {
        return currentKakuro.getRowSize();
    }

    public int getColumnSize() {
        return currentKakuro.getColumnSize();
    }

    public void saveKakuro() {
        String content;
        Cell[][] board = currentKakuro.getBoard();
        content = currentKakuro.getRowSize() + "," + currentKakuro.getColumnSize() + "\n";
        for (int i = 0; i < currentKakuro.getRowSize(); ++i) {
            for (int j = 0; j < currentKakuro.getColumnSize(); ++j) {
                if (board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    if (w.getValue() != 0) content.concat(String.valueOf(w.getValue()));
                    else content.concat("?");
                }
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getVertical() == 0 && bc.getHorizontal() == 0) {
                        content.concat("*");
                    }
                    if (bc.getVertical() != 0) content.concat("C" + bc.getVertical());
                    if (bc.getHorizontal() != 0) content.concat("F" + bc.getHorizontal());
                }
                if (j != currentKakuro.getColumnSize() - 1) content.concat(",");
            }
            content.concat("\n");
        }
        data.saveKakuro(content, currentKakuro.getDifficulty(), currentKakuro.getRowSize());
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
}
