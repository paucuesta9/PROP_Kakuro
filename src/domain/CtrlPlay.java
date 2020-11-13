package domain;

public class CtrlPlay {

    private static Kakuro kakuro;

    public CtrlPlay() {

    }

    public static void startGame(Kakuro newKakuro) {
        kakuro = newKakuro;
        CtrlResolve.setKakuro(kakuro);
    }

    public static int helpMyValue(int x, int y) {
        if (kakuro.getCell(x, y).isWhite()) {
            int value = ((WhiteCell) kakuro.getCell(x, y)).getValue();
            if (value == 0) return -2;
            else return (((WhiteCell) kakuro.getCell(x, y)).getCorrectValue() == value) ? 1 : 0;
        }
        return -1;
    }

    public static boolean helpCorrectNumber(int x, int y) {
        if (kakuro.getCell(x, y).isWhite()) {
            kakuro.setValue(x, y, ((WhiteCell) kakuro.getCell(x, y)).getCorrectValue());
            return true;
        }
        return false;
    }
}
