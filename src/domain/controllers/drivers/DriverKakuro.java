package domain.controllers.drivers;

import domain.classes.BlackCell;
import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.WhiteCell;

import java.util.*;

/** @file DriverKakuro.java
 @brief Clase  <em>DriverKakuro</em>.
 */

/** @brief Clase DriverKakuro que comprueba la correctividad de las funciones de la clase Kakuro
 * @author Pau Cuesta Arcos
 */
public class DriverKakuro {
    private static Scanner reader = new Scanner(System.in);
    public static final String KAKURO_TEXT_ENUNCIADO = "9,9\n" +
            "*,*,C19,C12,*,*,*,C7,C10\n" +
            "*,F14,?,?,C4,C11,C17F4,?,?\n" +
            "*,C7F36,?,?,?,?,?,?,?\n" +
            "F12,?,?,F10,?,?,?,C25,C14\n" +
            "F3,?,?,C20,C11F20,?,?,?,?\n" +
            "F17,?,?,?,?,C8,F6,?,?\n" +
            "*,C11,C7F13,?,?,?,C4F10,?,?\n" +
            "F28,?,?,?,?,?,?,?,*\n" +
            "F6,?,?,*,*,F8,?,?,*";

    /** @brief Test de la creadora a partir de todos los atributos
     *
     * Comprueba si se crea correctamente un kakuro a partir de un identificador, una dificultad y un tablero
     */
    private static void testCreadora1() {
        Cell[][] board = new Cell[10][10];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (i == 0 | j == 0) board[i][j] = new BlackCell();
                else if (i % 2 == 0 && j % 2 == 0) board[i][j] = new WhiteCell();
                else board[i][j] = new BlackCell();
            }
        }
        Kakuro k = new Kakuro(0, 1, board);
        System.out.println("El Kakuro se ha creado");
    }

    /** @brief Test de la creadora String
     *
     * Comprueba si se crea correctamente un Kakuro a partir de un String
     *
     * @param kakuroText String del Kakuro que se desea crear
     */
    private static void testCreadora2(String kakuroText) {
        try {
            Kakuro k = new Kakuro(kakuroText);
            System.out.println("El Kakuro se ha creado");
        }catch (IndexOutOfBoundsException e) {
            System.out.println("El tamaño indicado del Kakuro no corresponde con el tamaño real");
        } catch (NumberFormatException e) {
            System.out.println("El kakuro contiene alguna o varias casillas con un formato incorrecto");
        }

    }

    /** @brief Test de conversor de Kakuro a String
     *
     * Comprueba si se convierte a String un Kakuro de forma correcta
     *
     */
    private static void testToString() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        System.out.println(k.toString());
    }

    /** @brief Test de conversor de Kakuro correcto a String
     *
     * Comprueba si se convierte a String un Kakuro con los valores correctos de forma correcta
     *
     */
    private static void testCorrectToString() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell[][] board = k.getBoard();
        ((WhiteCell) board[1][2]).setCorrectValue(5);
        ((WhiteCell) board[1][3]).setCorrectValue(9);
        ((WhiteCell) board[1][7]).setCorrectValue(1);
        ((WhiteCell) board[1][8]).setCorrectValue(3);
        ((WhiteCell) board[2][2]).setCorrectValue(2);
        ((WhiteCell) board[2][3]).setCorrectValue(3);
        ((WhiteCell) board[2][4]).setCorrectValue(1);
        ((WhiteCell) board[2][5]).setCorrectValue(8);
        ((WhiteCell) board[2][6]).setCorrectValue(9);
        ((WhiteCell) board[2][7]).setCorrectValue(6);
        ((WhiteCell) board[2][8]).setCorrectValue(7);
        ((WhiteCell) board[3][1]).setCorrectValue(4);
        ((WhiteCell) board[3][2]).setCorrectValue(8);
        ((WhiteCell) board[3][4]).setCorrectValue(3);
        ((WhiteCell) board[3][5]).setCorrectValue(2);
        ((WhiteCell) board[3][6]).setCorrectValue(5);
        ((WhiteCell) board[4][1]).setCorrectValue(2);
        ((WhiteCell) board[4][2]).setCorrectValue(1);
        ((WhiteCell) board[4][5]).setCorrectValue(1);
        ((WhiteCell) board[4][6]).setCorrectValue(3);
        ((WhiteCell) board[4][7]).setCorrectValue(9);
        ((WhiteCell) board[4][8]).setCorrectValue(7);
        ((WhiteCell) board[5][1]).setCorrectValue(1);
        ((WhiteCell) board[5][2]).setCorrectValue(3);
        ((WhiteCell) board[5][3]).setCorrectValue(9);
        ((WhiteCell) board[5][4]).setCorrectValue(4);
        ((WhiteCell) board[5][7]).setCorrectValue(1);
        ((WhiteCell) board[5][8]).setCorrectValue(5);
        ((WhiteCell) board[6][3]).setCorrectValue(7);
        ((WhiteCell) board[6][4]).setCorrectValue(1);
        ((WhiteCell) board[6][5]).setCorrectValue(5);
        ((WhiteCell) board[6][7]).setCorrectValue(8);
        ((WhiteCell) board[6][8]).setCorrectValue(2);
        ((WhiteCell) board[7][1]).setCorrectValue(7);
        ((WhiteCell) board[7][2]).setCorrectValue(5);
        ((WhiteCell) board[7][3]).setCorrectValue(4);
        ((WhiteCell) board[7][4]).setCorrectValue(6);
        ((WhiteCell) board[7][5]).setCorrectValue(3);
        ((WhiteCell) board[7][6]).setCorrectValue(1);
        ((WhiteCell) board[7][7]).setCorrectValue(2);
        ((WhiteCell) board[8][1]).setCorrectValue(4);
        ((WhiteCell) board[8][2]).setCorrectValue(2);
        ((WhiteCell) board[8][6]).setCorrectValue(3);
        ((WhiteCell) board[8][7]).setCorrectValue(5);
        System.out.println(k.correctToString());
    }

    /** @brief Test de getter de identificador
     *
     * Comprueba si se obtiene el identificador de forma correcta
     *
     */
    private static void testGetId() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        System.out.println("ID: " + k.getId());
    }

    /** @brief Test de setter de identificador
     *
     * Comprueba si se settea el identificador de forma correcta
     *
     */
    private static void testSetId(int id) {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        k.setId(id);
        System.out.println("Se ha actualizado el valor id");
    }

    /** @brief Test de getter de dificultad
     *
     * Comprueba si se obtiene la dificultad de forma correcta
     *
     * @param diff dificultad que se va a querer obtener
     */
    private static void testGetDifficulty(int diff) {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        k.setDifficulty(diff);
        System.out.println("Difficulty: " + k.getDifficulty());
    }

    /** @brief Test de setter de dificultad
     *
     * Comprueba si se coloca la dificultad de forma correcta
     *
     * @param difficulty dificultad a colocar
     */
    private static void testSetDifficulty(int difficulty) {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        k.setDifficulty(difficulty);
        System.out.println("Se ha actualizado el valor difficulty");
    }

    /** @brief Test de getter de tamaño de filas
     *
     * Comprueba si se obtiene el tamaño de filas correctamente
     *
     */
    private static void testGetRowSize() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        System.out.println("RowSize: " + k.getRowSize());
    }

    /** @brief Test de getter de tamaño de columnas
     *
     * Comprueba si se obtiene correctamente el tamaño de columnas
     *
     */
    private static void testGetColumnSize() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        System.out.println("ColumnSize: " + k.getColumnSize());
    }

    /** @brief Test de getter de tablero
     *
     * Comprueba si se obtiene el tablero correctamente
     *
     */
    private static void testGetBoard() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell[][] board = k.getBoard();
        System.out.println("Se ha obtenido el tablero y se imprime: ");
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    if (w.getValue() != 0) System.out.print(w.getValue());
                    else System.out.print("?");
                }
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getColumn() == 0 && bc.getRow() == 0) {
                        System.out.print("*");
                    }
                    if (bc.getColumn() != 0) System.out.print("C" + bc.getColumn());
                    if (bc.getRow() != 0) System.out.print("F" + bc.getRow());
                }
                if (j != board[0].length - 1) System.out.print(",");
            }
            System.out.println("");
        }
    }

    /** @brief  Test de getter de Celda
     *
     * @param x posición x de la celda
     * @param y posición y de la celda
     */
    private static void testGetCell(int x, int y) {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        k.getCell(x,y);
        System.out.println("Se ha obtenido la celda buscada");
    }

    /** @brief Test de colocar valor en celda
     *
     * Comprueba si se coloca el valor en una celda blanca correctamente
     *
     * @param x posición x de la celda
     * @param y posición y de la celda
     * @param value valor que se quiere colocar en la celda
     */
    private static void testSetValue(int x, int y, int value) {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Boolean set = k.setValue(x, y, value);
        if (set) System.out.println("La celda en la posicion x e y es blanca y se ha actualizado correctamente");
        else System.out.println("La celda en la posicion x e y es negra y por lo tanto no se ha actualizado");
    }

    /** @brief Test de checkColumn
     *
     * Comprueba si un valor es posible para una celda teniendo en cuenta el estado en ese momento del tablero
     *
     */
    private static void testCheckColumn() {
        System.out.println("Introduce un kakuro para realizar el test:");
        String kakuro1 = readKakuro();
        System.out.println("Indica la fila de la casilla para hacer el test:");
        int f = readNumber();
        System.out.println("Indica la columna de la casilla para hacer el test:");
        int c = readNumber();
        System.out.println("Indica el valor a comprobar:");
        int n = readNumber();
        System.out.println("Es la ultima celda de la run vertical?");
        int end = readNumber();
        System.out.println("El valor deberia ser correcto?");
        int s = readNumber();
        boolean b = false;
        if(end == 1) b = true;
        Kakuro k = new Kakuro(kakuro1);
        Cell[][] board = k.getBoard();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j].isWhite()) ((WhiteCell) board[i][j]).setCorrectValue(((WhiteCell) board[i][j]).getValue());
            }
        }
        boolean r = k.checkColumn(f,c,n,b,n);
        if((r && s == 1) || (!r && s == 0)) System.out.println("Solucion correcta");
        else System.out.println("Solucion incorrecta");
    }

    /** @brief Test de CheckRowValidity
     *
     * Comprueba si se está haciendo correctamente la verificación de que el valor es correcto en la fila
     *
     * @param x posicion x de la celda
     * @param y posición y de la celda
     * @param value valor que se ha introducido
     * @param kakuro String del kakuro que se está jugando
     */
    private static void testCheckRowValidity(int x, int y, int value, String kakuro) {
        Kakuro k = new Kakuro(kakuro);
        k.setValue(x,y,value);
        if (k.checkRowValidity(x, y-1, value, 0, -1, y, true)) System.out.println("El valor introducido cumple las condiciones de la fila");
        else System.out.println("El valor introducido no cumple las condiciones de la fila");
    }

    /** @brief Test de CheckColumnValidity
     *
     * Comprueba si se está haciendo correctamente la verificación de que el valor es correcto en la columna
     *
     * @param x posicion x de la celda
     * @param y posición y de la celda
     * @param value valor que se ha introducido
     * @param kakuro String del kakuro que se está jugando
     */
    private static void testCheckColumnValidity(int x, int y, int value, String kakuro) {
        Kakuro k = new Kakuro(kakuro);
        k.setValue(x,y,value);
        if (k.checkColumnValidity(x-1,y,value,0,-1,x,true)) System.out.println("El valor introducido cumple las condiciones de la columna");
        else System.out.println("El valor introducido no cumple las condiciones de la columna");
    }

    /** @brief Test de si se ha acabado la partida
     *
     * Comprueba si se ha verificado que se ha acabado la partida de forma correcta
     *
     * @param kakuro1 kakuro que se quiere comprobar
     * @param kakuroFinished el mismo kakuro pero resuleto para ver si se ha terminado
     */
    private static void testIsFinished(String kakuro1, String kakuroFinished) {
        Kakuro k = new Kakuro(kakuro1);
        Kakuro kFin = new Kakuro(kakuroFinished);
        Cell[][] board = k.getBoard();
        Cell[][] boardFinished = kFin.getBoard();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j].isWhite()) ((WhiteCell) board[i][j]).setCorrectValue(((WhiteCell)boardFinished[i][j]).getValue());
            }
        }
        Boolean finished = k.isFinished();
        if (finished) System.out.println("El kakuro se ha terminado");
        else System.out.println("El kakuro aun no se ha terminado");
    }

    /** @brief Test de settear los valores correctos al kakuro
     *
     * Comprueba si se han copiado los valores correctos al kakuro indicado
     *
     * @param kakuro kakuro que se quiere comprobar
     * @param kakuroFinished el mismo kakuro pero resuelto para copiar los valores correctos
     */
    private static void testSetCorrectValues(String kakuro, String kakuroFinished) {
        Kakuro k = new Kakuro(kakuro);
        Kakuro kFin = new Kakuro(kakuroFinished);

        k.setCorrectValues(kFin);

        System.out.println("Se imprime el kakuro con las soluciones");
        String sol = k.correctToString();
        System.out.println(sol);
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora con valores \n 2. Creadora a partir de String \n 3. Convertir Kakuro a String \n 4. Convertir Kakuro correcto a String \n 5. Getter de id \n 6. Setter de id \n 7. Getter difficulty" +
                "\n 8. Setter difficulty \n 9. Getter rowSize \n 10. Getter columnSize \n 11. Getter board \n 12. Getter cell \n 13. Setter value en celda blanca \n 14. Check column \n 15. Mirar validez de fila \n 16. Mirar validez de la columna" +
                "\n 17. Mirar si se ha terminado \n 18. Mirar si ha puesto valores correctos \n 19. Salir");
        int value = readNumber();
        while (value != 19) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println("Se llama a la Creadora 1");
                    testCreadora1();
                    break;
                case 2:
                    System.out.println("Indique un Kakuro en el formato correcto");
                    String kakuroText = readKakuro();
                    System.out.println("Se llama a la creadora 2");
                    testCreadora2(kakuroText);
                    break;
                case 3:
                    System.out.println("Se llama a convertir Kakuro a String");
                    testToString();
                    break;
                case 4:
                    System.out.println("Se llama a convertir Kakuro correcto a String");
                    testCorrectToString();
                    break;
                case 5:
                    System.out.println("Se llama a getId");
                    testGetId();
                    break;
                case 6:
                    System.out.println("Introduzca un id");
                    int id = readNumber();
                    System.out.println("Se llama a setId");
                    testSetId(id);
                    break;
                case 7:
                    System.out.println("Introduzca una dificultad");
                    int diff = readNumber();
                    System.out.println("Se llama a getDifficulty");
                    testGetDifficulty(diff);
                    break;
                case 8:
                    System.out.println("Indique una dificultad");
                    int difficulty = readNumber();
                    System.out.println("Se llama a setDifficulty ");
                    testSetDifficulty(difficulty);
                    break;
                case 9:
                    System.out.println("Se llama a getRowSize");
                    testGetRowSize();
                    break;
                case 10:
                    System.out.println("Se llama a getColumnSize");
                    testGetColumnSize();
                    break;
                case 11:
                    System.out.println("Se llama a getBoard");
                    testGetBoard();
                    break;
                case 12:
                    System.out.println("Indique la posicion x e y de la celda que quiere obtener (0 < x, y < 10)");
                    x = readNumber();
                    y = readNumber();
                    System.out.println("Se llama a getCell");
                    testGetCell(x, y);
                    break;
                case 13:
                    System.out.println("Indique la posicion x e y de la celda que quiere colocar (0 < x, y < 10) y el valor");
                    x = readNumber();
                    y = readNumber();
                    value = readNumber();
                    System.out.println("Se llama a setValue");
                    testSetValue(x, y, value);
                    break;
                case 14:
                    System.out.println("Se llama a checkColumn");
                    testCheckColumn();
                    break;
                case 15:
                    System.out.println("Introduzca la posicion x e y de la celda, el valor a poner en esa celda y el kakuro a jugar");
                    x = readNumber();
                    y = readNumber();
                    value = readNumber();
                    String kakuro = readKakuro();
                    System.out.println("Se llama a checkRowValidity");
                    testCheckRowValidity(x,y,value,kakuro);
                    break;
                case 16:
                    System.out.println("Introduzca la posicion x e y de la celda, el valor a poner en esa celda y el kakuro a jugar");
                    x = readNumber();
                    y = readNumber();
                    value = readNumber();
                    kakuro = readKakuro();
                    System.out.println("Se llama a checkColumnValidity");
                    testCheckColumnValidity(x,y,value,kakuro);
                    break;
                case 17:
                    System.out.println("Indique un Kakuro en el formato correcto (este en la fase de juego que se quiera y el mismo kakuro resuelto");

                    String kakuro1 = readKakuro();
                    String kakuroFinished = readKakuro();

                    System.out.println("Se llama a isFinished");
                    testIsFinished(kakuro1, kakuroFinished);
                    break;
                case 18:
                    System.out.println("Indique un Kakuro en el formato correcto y el mismo kakuro resuelto");

                    String kakuro2 = readKakuro();
                    String kakuro2Finished = readKakuro();

                    System.out.println("Se llama a isFinished");
                    testSetCorrectValues(kakuro2, kakuro2Finished);
                    break;
                default:
                    System.out.println("El numero introducido es incorrecto");
                    break;
            }
            System.out.println("Opciones: \n 1. Creadora con valores \n 2. Creadora a partir de String \n 3. Convertir Kakuro a String \n 4. Convertir Kakuro correcto a String \n 5. Getter de id \n 6. Setter de id \n 7. Getter difficulty" +
                    "\n 8. Setter difficulty \n 9. Getter rowSize \n 10. Getter columnSize \n 11. Getter board \n 12. Getter cell \n 13. Setter value en celda blanca \n 14. Check column \n 15. Mirar validez de fila \n 16. Mirar validez de la columna" +
                    "\n 17. Mirar si se ha terminado \n 18. Mirar si ha puesto valores correctos \n 19. Salir");
            value = readNumber();
        }
        System.exit(0);
    }

    /** @brief Lee un kakuro de consola
     *
     * @return el kakuro leído
     */
    public static String readKakuro() {
        StringBuilder content = new StringBuilder();
        String[] valuesSize = reader.next().split(",");
        int row = Integer.parseInt(valuesSize[0]);
        int column = Integer.parseInt(valuesSize[1]);
        content.append((row) + "," + (column) + "\n");
        for (int i = 0; i < row; ++i)
            content.append(reader.next()).append("\n");
        return content.toString();
    }

    /** @brief Lee un numero de consola
     *
     * @return el número leído
     */
    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }
}
