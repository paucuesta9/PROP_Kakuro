package domain.classes;/* Alvaro Armada Ruiz */

/** @file Kakuro.java
 @brief Clase  <em>Kakuro</em>.
 @author Alvaro Armada Ruiz
 */

/** @brief Clase Kakuro que contiene los atributos y metodos de un tablero
 */
public class Kakuro {

    /**
     * Es el identificador del Kakuro
     */
    private String id;
    /**
     * Es la dificultad del Kakuro, va de 1 a 3
     */
    private int difficulty;
    /**
     * Es el tamaño horizontal del Kakuro
     */
    private int rowSize;
    /**
     * Es el tamaño vertical del Kakuro
     */
    private int columnSize;
    /**
     * Es la matriz de celdas, es decir, el tablero
     */
    private Cell [][] board;

    /** @brief Creadora con todos los parámtros
     *
     * Se crea un nueva instáncia de Kakuro a partir de todos los atributos
     *
     * @param id Es el identificador del Kakuro
     * @param difficulty Es la dificultad del Kakuro
     * @param board Es el tablero del Kakuro
     */
    public Kakuro(String id, int difficulty, Cell[][] board) {
        this.id = id;
        this.difficulty = difficulty;
        this.rowSize = board.length;
        this.columnSize = board[0].length;
        this.board = board;
    }

    /** @brief Creadora a partir de String
     *
     * Se crea una nueva instáncia de Kakuro a partir de un String que se ha leido de fichero
     *
     * @param kakuro Es un String de un fichero entero de un Kakuro
     */
    public Kakuro(String kakuro) {
        String[] values = kakuro.split("\n");
        String[] valuesSize = values[0].split(",");
        rowSize = Integer.parseInt(valuesSize[0]);
        columnSize = Integer.parseInt(valuesSize[1]);
        board = new Cell[rowSize][columnSize];
        for (int i = 0; i < rowSize; ++i) {
            String[] valuesRow = values[i + 1].split(",");
            for (int j = 0; j < columnSize; ++j) {
                if (valuesRow[j].equals("*")) board[i][j] = new BlackCell();
                else if (valuesRow[j].equals("?")) board[i][j] = new WhiteCell();
                else if (valuesRow[j].charAt(0) == 'C' || valuesRow[j].charAt(0) == 'F') {
                    int vertical = 0, horizontal = 0;
                    if (valuesRow[j].charAt(0) == 'C') {
                        valuesRow[j] = valuesRow[j].substring(1);
                        if (valuesRow[j].contains("F")) {
                            String[] CF = valuesRow[j].split("F");
                            vertical = Integer.parseInt(CF[0]);
                            horizontal = Integer.parseInt(CF[1]);
                        } else {
                            vertical = Integer.parseInt(valuesRow[j]);
                        }
                    } else {
                        horizontal = Integer.parseInt(valuesRow[j].substring(1));
                    }
                    board[i][j] = new BlackCell(vertical, horizontal);
                } else board[i][j] = new WhiteCell(Integer.parseInt(valuesRow[j]));
            }
        }
    }

    /** @brief Conversor de Kakuro a String
     *
     * Se convierte el Kakuro a un String en formato fichero
     *
     * @return String en formato de fichero para guardarlo en un archivo o printear en consola
     */
    public String toString() {
        StringBuilder content = new StringBuilder();
        String line;
        line = rowSize + "," + columnSize;
        content.append(line).append("\n");
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                if (board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    if (w.getValue() != 0) content.append(w.getValue());
                    else content.append("?");
                }
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getColumn() == 0 && bc.getRow() == 0) {
                        content.append("*");
                    }
                    if (bc.getColumn() != 0) content.append("C" + bc.getColumn());
                    if (bc.getRow() != 0) content.append("F" + bc.getRow());
                }
                if (j != columnSize - 1) content.append(",");
            }
            content.append("\n");
        }
        return content.toString();
    }

    /** @brief Conversor de Kakuro a String, valores correctos
     *
     * Se convierte el Kakuro a un String en formato fichero
     *
     * @return String en formato de fichero para guardarlo en un archivo o printear en consola
     */
    public String correctToString() {
        StringBuilder content = new StringBuilder();
        String line;
        line = rowSize + "," + columnSize;
        content.append(line).append("\n");
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                if (board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    content.append(w.getCorrectValue());
                }
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getColumn() == 0 && bc.getRow() == 0) {
                        content.append("*");
                    }
                    if (bc.getColumn() != 0) content.append("C" + bc.getColumn());
                    if (bc.getRow() != 0) content.append("F" + bc.getRow());
                }
                if (j != columnSize - 1) content.append(",");
            }
            content.append("\n");
        }
        return content.toString();
    }

    /** @brief Getter del Identificador
     *
     * @return String con el identificador
     */
    public String getId() {
        return id;
    }

//    /** @brief Setter del indentificador
//     *
//     * @param id Identificador del Kakuro
//     */
//    public void setId(String id) {
//        this.id = id;
//    }

    /** @brief Getter de la dificultad
     *
     * @return entero con la dificultad (De 1 a 3)
     */
    public int getDifficulty() {
        return difficulty;
    }

    /** @brief Setter de la dificultad
     *
     * @param difficulty Dificultad del Kakuro, entero entre 1 y 3
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /** @brief Getter del tamaño de fila
     *
     * @return entero con el tamaño de la fila
     */
    public int getRowSize() {
        return rowSize;
    }

//    /** @brief Setter del tamaño de fila
//     *
//     * @param rowSize Tamaño de fila, entero
//     */
//    public void setRowSize(int rowSize) {
//        this.rowSize = rowSize;
//    }

    /** @brief Getter del tamaño de columna
     *
     * @return entero con el tamaño de la columna
     */
    public int getColumnSize() {
        return columnSize;
    }

//    /** @brief Setter del tamaño de columna
//     *
//     * @param columnSize Tamaño de columna, entero
//     */
//    public void setColumnSize(int columnSize) {
//        this.columnSize = columnSize;
//    }

    /** @brief Getter del tablero
     *
     * @return una matriz de celdas, es decir el tablero del kakuro
     */
    public Cell[][] getBoard() {
        return board;
    }

    /** @brief Getter de celda
     *
     * @param x Posición de fila de la celda
     * @param y Posición de columna de la celda
     * @return la Celda de nuestro tablero en la posición [x][y]
     */
    public Cell getCell(int x, int y) {
        return board[x][y];
    }

//    /** @brief Setter del tablero
//     *
//     * @param board Matriz de Celdas
//     */
//    public void setBoard(Cell[][] board) {
//        this.board = board;
//    }

    /** @brief Setter de un valor en una celda de tipo blanca
     *
     * @param x Posición de fila de la celda
     * @param y Posición de la columna de la celda
     * @param value Valor que se quiere settear a la celda
     * @return true si la llamada a la celda devuelve true, en este caso, si la celda es blanca, en caso contrario devuelve false
     */
    public boolean setValue(int x, int y, int value) {
        return board[x][y].setValue(value);
    }

    /**@brief Comprueba si un valor es posible para una celda teniendo en cuenta el estado en ese momento del tablero
     *
     * @param r representa el número de fila del tablero
     * @param c representa el número de columna del tablero
     * @param i representa el valor añadido a la celda blanca
     * @param f indica si la casilla con la que se ha hecho la primera casilla es la última de la run vertical
     * @param sum suma de los valores ya visitados de la run vertical
     * @return true si, f es false y la suma de todas las casillas es inferior a lo que debería sumar la run, si f es true si la suma es igual a lo que deberia
     * sumar. False si hay valores repetidos o, si f es false y la suma es demasiado grande o, si f es true, y la suma es diferente
     */
    public boolean checkColumn(int r, int c, int i, boolean f, int sum) {
        if(!board[r][c].isWhite()) {
            BlackCell b = (BlackCell) board[r][c];
            if(f && b.getColumn() == sum) return true;
            else if (!f && b.getColumn() > sum) return true;
        }
        else {
            WhiteCell w = (WhiteCell) board[r][c];
            if( w.getCorrectValue() == i ) return false;
            sum += w.getCorrectValue();
            return checkColumn(r-1,c,i,f,sum);
        }
        return false;
    }

    /** @brief Comprueba la validez de la fila
     *
     * Comprueba que en la fila no se repita ningún número y la suma de todos los valores no supere el valor de la celda negra que marca esa fila
     * @param r representa el número de fila del tablero
     * @param c representa el número de columna del tablero
     * @param value representa el valor añadido a la celda blanca
     * @param sum representa la suma total de los valores de las celdas blancas de la fila
     * @param total representa el valor máximo que puede tener la suma de las celdas blancas de la fila
     * @param posy representa el número de columna de la celda blanca a la que se le ha añadido un valor
     * @param all representa si todas las casillas de la fila ya tienen un valor
     * @return devuelve cierto si se cumplen las condiciones y falso si no se cumplen
     */
    public boolean checkRowValidity(int r, int c, int value, int sum, int total, int posy, boolean all) {
        if (!board[r][c].isWhite()) {
            if (total != -1) {
                //sum -= value;
                if (all) return total == sum || total == 0;
                else return total >= sum || total == 0;
            }
            else {
                BlackCell b = (BlackCell) board[r][c];
                total = b.getRow();
                return checkRowValidity(r, c + 1, value, sum, total, posy, all);
            }
        }
        else {
            if (total == -1 && c-1 >= 0) return checkRowValidity(r, c - 1, value, sum, total, posy, all);
            else {
                WhiteCell w = (WhiteCell) board[r][c];
                if (w.getValue() == value && c != posy) return false;
                if (w.getValue() == 0) all = false;
                sum += w.getValue();
                if (c+1 < board[0].length) return checkRowValidity(r, c + 1, value, sum, total, posy, all);
                else {
                    if (all) return total == sum || total == 0;
                    else return total >= sum || total == 0;
                }
            }
        }
    }

    /** @brief Comprueba la validez de la columna
     *
     * Comprueba que en la columna no se repita ningún número y la suma de todos los valores no supere el valor de la celda negra que marca esa columna
     * @param r representa el número de fila del tablero
     * @param c representa el número de columna del tablero
     * @param value representa el valor añadido a la celda blanca
     * @param sum representa la suma total de los valores de las celdas blancas de la columna
     * @param total representa el valor máximo que puede tener la suma de las celdas blancas de la columna
     * @param posx representa el número de fila de la celda blanca a la que se le ha añadido un valor
     * @param all representa si todas las casillas de la columna ya tienen un valor
     * @return devuelve cierto si se cumplen las condiciones y falso si no se cumplen
     */
    public boolean checkColumnValidity(int r, int c, int value, int sum, int total, int posx, boolean all) {
        if (!board[r][c].isWhite()) {
            if (total != -1) {
                //sum -= value;
                if (all) return total == sum || total == 0;
                else return total >= sum || total == 0;
            }
            else {
                BlackCell b = (BlackCell) board[r][c];
                total = b.getColumn();
                return checkColumnValidity(r+1, c, value, sum, total, posx, all);
            }
        }
        else {
            if (total == -1 && r-1 >= 0) return checkColumnValidity(r -1, c, value, sum, total, posx, all);
            else {
                WhiteCell w = (WhiteCell)  board[r][c];
                if (w.getValue() == value && r != posx) return false;
                if (w.getValue() == 0) all = false;
                sum += w.getValue();
                if (r+1 < board.length) return checkColumnValidity(r+1, c, value, sum, total, posx, all);
                else {
                    if (all) return total == sum || total == 0;
                    else return total >= sum || total == 0;
                }
            }
        }
    }

    /** @brief Comprueba la validez de un número añadido a una celda blanca
     *
     * Comprueba si el valor añadido a la celda blanca cumple las condiciones de la fila y de la columna
     * @param x representa el número de la fila del tablero
     * @param y representa el número de la columna del tablero
     * @param value representa el valor añadido a la celda blanca
     * @return devuelve cierto si se cumplen las condiciones tanto en la fila como en la columna y falso si no se cumplen en la fila, la columna o ambas
     */
    public boolean checkValidity(int x, int y, int value) {
        return checkRowValidity(x, y-1, value, 0, -1, y, true) && checkColumnValidity(x-1, y, value, 0, -1, x, true);
    }

    /** @brief Comprueba si se ha rellenado el tablero entero y correctamente
     *
     * @return cierto si el usuario ha completado el tablero de forma correcto, en caso que falte algun valor por colocar o alguna no sea correcto devuelve falso
     */
    public boolean isFinished() {
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                if (board[i][j].isWhite() && ((WhiteCell) board[i][j]).getValue() != ((WhiteCell) board[i][j]).getCorrectValue()) return false;
            }
        }
        return true;
    }
}
