package domain;

import data.CtrlData;

public class CtrlDomain {

    private CtrlData data;
    private Game currentGame;
    private Kakuro currentKakuro;

    //constructora
    public CtrlDomain() {
        data = CtrlData.getInstance();
    }

    public boolean validate() {
        int [] res = new int[1];
        System.out.println(res[0]);
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        currentKakuro.validate(0,0, 0, vec, res);
        if (res[0]!=1) {
            System.out.println("El kakuro no es válido :c");
            return false;
        }
        else {
            System.out.println("El kakuro es válido c:");
            return true;
        }
    }

    public void resolve() {
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        boolean t = currentKakuro.resolve(0,0, 0, vec);
        System.out.println(t);
    }

    public void generate() {}

    public void searchKakuro(int difficulty, int kakuroSize) {
        this.currentKakuro = data.searchKakuro(difficulty, kakuroSize);
    }

    public String getValueCell(int i, int j) {
        if (currentKakuro.getBoard()[i][j].isWhite()) {
            WhiteCell w = (WhiteCell) currentKakuro.getBoard()[i][j];
            if (w.getValue() != 0) return String.valueOf(w.getValue());
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
}
