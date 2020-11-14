package domain;

/** @file CtrlResolve.java
 @brief Clase  <em>CtrlResolve</em>.
 */
public class CtrlResolve {

    /**
     * Instància del kakuro con el que se trabaja en cada momento
     */
    private static Kakuro kakuro;

    /** @brief Creadora por defecto
     *
     */
    public CtrlResolve() {}

    /** @brief Setter de Kakuro
     *
     * Settea un Kakuro al controlador para poder trabajar con él
     *
     * @param currentKakuro instància de Kakuro
     */
    public static void setKakuro(Kakuro currentKakuro) {
        kakuro = currentKakuro;
    }

    /** @brief Resuelve un Kakuro
     *
     * @param r
     * @param c
     * @param sum
     * @param vec
     * @return
     */
    public static boolean resolve(int r, int c, int sum, int [] vec) {
        if( r == kakuro.getRowSize() ) { return true; } //hemos llegado al final, la solucion es correcta
        else {
            Cell[][] board = kakuro.getBoard();
            if( !board[r][c].isWhite() ) { // estamos en una casilla negra; queremos cambiar de columna o de casilla
                if (sum != 0) return false;
                BlackCell b = (BlackCell) board[r][c];

                if (c != kakuro.getColumnSize() - 1) { // si no estem a la utlima columna
                    if (board[r][c + 1].isWhite()) {
                        sum = b.getRow();
                    }
                }
                int [] aux = {0,0,0,0,0,0,0,0,0,0};
                if (c == kakuro.getColumnSize() - 1) return resolve(r + 1, 0, 0, aux); //cambiamos de fila, estamos en la ultima columna }
                else return resolve(r,c+1, sum, aux); //cambiamos de columna
            }
            else { // si estamos en una casilla blanca
                vec[0] = 1; //indica que s'ha modificat
                WhiteCell w = (WhiteCell) board[r][c];
                for(int i = 1; i < 10 && sum-i >= 0; ++i ) {
                    if ( vec[i] == 0) {
                        vec[i] = 1;
                        w.setCorrectValue(i);
                        boolean f = false;
                        if(r == kakuro.getRowSize() - 1 || !board[r+1][c].isWhite()) f =true;
                        if(kakuro.checkColumn(r-1, c, i, f, i)) {
                            if (c == kakuro.getColumnSize() - 1 && sum-i != 0) { }
                            else if (c == kakuro.getColumnSize() - 1 && sum-i == 0) { // estamos en la ultima casilla de la fila pero la suma es correcta
                                if (resolve( r + 1, 0, 0, vec)) {
                                    return true; // quiere decir que hemos llegado al final y esta bien
                                }
                            }
                            else if (resolve(r, c + 1, sum-i, vec)) { return true; }
                        }
                        vec[i] = 0;
                    }
                }
            }
        }
        return false;
    }
}
