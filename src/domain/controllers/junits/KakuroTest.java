package domain.controllers.junits;

import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.WhiteCell;
import org.junit.Test;
import static org.junit.Assert.*;

/** @file KakuroTest.java
 @brief Clase  <em>JUnit Kakuro</em>.
 */

/** @brief Clase KakuroTest que comprueba la correctividad de las funciones de la clase Kakuro
 * @author Alvaro Armada Ruiz
 */

public class KakuroTest {

    public static final String KAKURO_TEXT_ENUNCIADO = "12,10\n" +
            "*,*,*,C34,C4,C11,*,*,C12,C15\n" +
            "*,C15,C21F20,?,?,?,C6,F7,?,?\n" +
            "F21,?,?,?,?,?,?,C12F12,?,?\n" +
            "F17,?,?,?,*,C16F19,?,?,?,*\n" +
            "*,C23F10,?,?,C10F6,?,?,?,C24,C23\n" +
            "F34,?,?,?,?,?,C11,F16,?,?\n" +
            "F15,?,?,F13,?,?,?,C34F9,?,?\n" +
            "F9,?,?,C9,C23F16,?,?,?,?,?\n" +
            "*,*,C7F21,?,?,?,F9,?,?,C16\n" +
            "*,C13F9,?,?,?,C4,C6F23,?,?,?\n" +
            "F13,?,?,F36,?,?,?,?,?,?\n" +
            "F5,?,?,*,F9,?,?,?,*,*\n";
    public static final String KAKURO_MAL_INDEX = "13,10\n" +
            "*,*,1,C34,C4,C11,*,*,C12,C15\n" +
            "*,C15,C21F20,?,?,?,C6,F7,?,?\n" +
            "F21,?,?,?,?,?,?,C12F12,?,?\n" +
            "F17,?,?,?,*,C16F19,?,?,?,*\n" +
            "*,C23F10,?,?,C10F6,?,?,?,C24,C23\n" +
            "F34,?,?,?,?,?,C11,F16,?,?\n" +
            "F15,?,?,F13,?,?,?,C34F9,?,?\n" +
            "F9,?,?,C9,C23F16,?,?,?,?,?\n" +
            "*,*,C7F21,?,?,?,F9,?,?,C16\n" +
            "*,C13F9,?,?,?,C4,C6F23,?,?,?\n" +
            "F13,?,?,F36,?,?,?,?,?,?\n" +
            "F5,?,?,*,F9,?,?,?,*,*\n";

    public static final String KAKURO_MAL_CHAR = "12,10\n" +
            "*,*,1,C34,C4,C11,*,*,C12,C15\n" +
            "*,C15,C21F20,?,?,?,C6,F7,?,?\n" +
            "F21,?,?,?,?,?,?,C12F12,?,?\n" +
            "F17,?,?,?,*,C16F19,?,?,?,*\n" +
            "*,C23F10,?,?,C10F6,?,?,?,C24,C23\n" +
            "F34,?,?,?,?,?,C11,F16,?,?\n" +
            "F15,?,?,F13,?,?,?,C34F9,?,?\n" +
            "F9,?,?,C9,C23F16,?,?,?,?,?\n" +
            "*,*,C7F21,?,?,?,F9,?,?,C16\n" +
            "*,C13F9,?,?,?,C4,C6F23,?,?,?\n" +
            "F13,?,?,F36,?,?,?,?,?,?\n" +
            "F5,?,?,#,F9,?,?,?,*,*\n";

    public static final String KAKURO_SMALL = "5,5\n" +
            "*,C16,C8,*,*\n"+
            "F9,?,?,C12,*\n"+
            "F22,?,?,?,C16\n"+
            "*,F11,?,?,?\n"+
            "*,*,F10,?,?\n";

    public static final String KAKURO_SMALL_SOLVED = "5,5\n" +
            "*,C16,C8,*,*\n"+
            "F9,7,2,C12,*\n"+
            "F22,9,5,8,C16\n"+
            "*,F11,1,3,7\n"+
            "*,*,F10,1,9\n";


    /** @brief Test de la creadora
     *
     * Comprueba que se crea correctamente dado un Id, dificultad y un tablero.
     */

    @Test
    public void KakuroBoard() {
        Cell[][] c = new Cell[1][1];
        try {
            new Kakuro(0, 0, c);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /** @brief Test de la creadora
     *
     * Comprueba que se crea correctamente dado un String que representa un Kakuro
     */

    @Test
    public void KakuroString() {
        try {
            new Kakuro(KAKURO_TEXT_ENUNCIADO);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /** @brief Test de la creadora
     *
     * Comprueba que se genera una excpeción si se introduce un Kakuro en formato String no válido
     */

    @Test (expected=IndexOutOfBoundsException.class)
    public void testKakuroInvalidIndex() {
            new Kakuro(KAKURO_MAL_INDEX);
    }

    @Test (expected=NumberFormatException.class)
    public void testKakuroInvalidCharacter() {
        new Kakuro(KAKURO_MAL_CHAR);
    }

    /** @brief Test de kakuro a string
     *
     * Comprueba que a partir de un Kakuro se genera el string que lo representa
     */
    @Test
    public void testToString() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        assertEquals("Expected equal", KAKURO_TEXT_ENUNCIADO, k.toString());
    }

    /** @brief Test de kakuro a string con valores
     *
     * Comprueba que a partir de un Kakuro se genera el string que lo representa con valores correctos en vez de interrogantes
     */
    @Test
    public void testToStringCorrect() {
        Kakuro k = new Kakuro(KAKURO_SMALL);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[1][1];
        w.setCorrectValue(7);
        w = (WhiteCell) b[1][2];
        w.setCorrectValue(2);
        w = (WhiteCell) b[2][1];
        w.setCorrectValue(9);
        w = (WhiteCell) b[2][2];
        w.setCorrectValue(5);
        w = (WhiteCell) b[2][3];
        w.setCorrectValue(8);
        w = (WhiteCell) b[3][2];
        w.setCorrectValue(1);
        w = (WhiteCell) b[3][3];
        w.setCorrectValue(3);
        w = (WhiteCell) b[3][4];
        w.setCorrectValue(7);
        w = (WhiteCell) b[4][3];
        w.setCorrectValue(1);
        w = (WhiteCell) b[4][4];
        w.setCorrectValue(9);
        assertEquals("Expected equal", KAKURO_SMALL_SOLVED, k.correctToString());
    }

    /** @brief Test de comprobar columna
     *
     * Comprueba que la función retorna falso cuando se da un valor que hace que se supere la suma de la columna
     */
    @Test
    public void testCheckColumnFalseSumTooBig() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        boolean f = k.checkColumn(10-1, 5, 9, false, 9);
        assertFalse("Expected false", f);
    }

    /** @brief Test de comprobar columna
     *
     * Comprueba que la función retorna falso cuando se da el último valor de la columna pero no se llega al valor de la suma de esta
     */
    @Test
    public void testCheckColumnFalseEndSumLow() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[10][5];
        w.setCorrectValue(3);
        boolean f = k.checkColumn(11-1, 5, 2, true, 2);
        assertFalse("Expected false", f);
    }

    /** @brief Test de comprobar columna
     *
     * Comprueba que la función retorna verdadero cuando se completa la columna, no se repiten numeros i el valor de los números de la columna suma exactamente el valor correcto
     */
    @Test
    public void testCheckColumnTrueEnd() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[10][5];
        w.setCorrectValue(3);
        boolean f = k.checkColumn(11-1, 5, 1, true, 1);
        assertTrue("Expected true", f);
    }

    /** @brief Test de comprobar columna
     *
     * Comprueba que la función retorna verdadero cuando no se completa la columna pero no se incumple nada
     */
    @Test
    public void testCheckColumnTrueNoEnd() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        boolean f = k.checkColumn(10-1, 5, 1, false, 1);
        assertTrue("Expected true", f);
    }

    /** @brief Test de comprobar validez de fila y columna
     *
     * Comprueba que la función retorna verdadero cuando se completa fila y columna con el valor correcto
     */
    @Test
    public void CheckValidityRowColumnCompleteCorrect() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[11][6];
        w.setValue(2);
        w = (WhiteCell) b[11][7];
        w.setValue(6);
        w = (WhiteCell) b[10][5];
        w.setValue(3);
        assertTrue("Expected true", k.checkValidity(11,5,1));
    }

    /** @brief Test de comprobar validez de fila y columna
     *
     * Comprueba que la función retorna falso cuando se completa fila y columna con el valor incorrecto
     */
    @Test
    public void CheckValidityRowColumnCompleteIncorrect() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[11][6];
        w.setValue(2);
        w = (WhiteCell) b[11][7];
        w.setValue(6);
        w = (WhiteCell) b[10][5];
        w.setValue(3);
        w = (WhiteCell) b[11][5];
        w.setValue(5);
        assertFalse("Expected false", k.checkValidity(11,5,5));
    }

    /** @brief Test de comprobar validez de fila y columna
     *
     * Comprueba que la función retorna falso cuando se completa fila y columna con un valor repetido
     */
    @Test
    public void CheckValidityRowColumnCompleteIncorrectRepeated() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[11][5];
        w.setValue(1);
        w = (WhiteCell) b[11][7];
        w.setValue(6);
        w = (WhiteCell) b[10][5];
        w.setValue(3);
        w = (WhiteCell) b[11][6];
        w.setValue(1);
        assertFalse("Expected false", k.checkValidity(11,6,1));
    }

    /** @brief Test de comprobar validez de fila y columna
     *
     * Comprueba que la función retorna falso cuando se completa fila y columna con un valor incorrecto más bajo que el correcto
     */
    @Test
    public void CheckValidityRowColumnCompleteIncorrectLow() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[1][8];
        w.setValue(1);
        w = (WhiteCell) b[2][9];
        w.setValue(9);
        w = (WhiteCell) b[1][9];
        w.setValue(2);
        assertFalse("Expected false", k.checkValidity(1,9,2));
    }

    /** @brief Test de comprobar validez de fila y columna
     *
     * Comprueba que la función retorna falso cuando se completa fila y columna con un valor incorrecto más alto que el correcto
     */
    @Test
    public void CheckValidityRowColumnCompleteIncorrectHigh() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[1][8];
        w.setValue(1);
        w = (WhiteCell) b[2][9];
        w.setValue(9);
        w = (WhiteCell) b[1][9];
        w.setValue(8);
        assertFalse("Expected false", k.checkValidity(1,9,8));
    }

    /** @brief Test de comprobar validez de fila y columna
     *
     * Comprueba que la función retorna verdadero cuando no se completa fila y columna y el valor  es correcto
     */
    @Test
    public void CheckValidityRowColumnCompleteCorrectNotFinishedCorrectValue() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[1][8];
        w.setValue(1);
        assertTrue("Expected true", k.checkValidity(1,8,1));
    }

    /** @brief Test de comprobar validez de fila y columna
     *
     * Comprueba que la función retorna verdadero cuando no se completa fila y columna y el valor es incorrecto pero no incumple ninguna restricción
     */
    @Test
    public void CheckValidityRowColumnCompleteCorrectNotFinishedIncorrectValue() {
        Kakuro k = new Kakuro(KAKURO_TEXT_ENUNCIADO);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[1][8];
        w.setValue(2);
        assertTrue("Expected true", k.checkValidity(1,8,2));
    }

    /** @brief Test de comprobar si hemos terminado de rellenar el kakuro
     *
     * Comprueba que la función retorna verdadero cuando hemos completado el kakuro con los valores correctos
     */
    @Test
    public void testIsFinishedFinishCorrect() {
        Kakuro k = new Kakuro(KAKURO_SMALL);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[1][1];
        w.setValue(7);
        w.setCorrectValue(7);
        w = (WhiteCell) b[1][2];
        w.setValue(2);
        w.setCorrectValue(2);
        w = (WhiteCell) b[2][1];
        w.setValue(9);
        w.setCorrectValue(9);
        w = (WhiteCell) b[2][2];
        w.setValue(5);
        w.setCorrectValue(5);
        w = (WhiteCell) b[2][3];
        w.setValue(8);
        w.setCorrectValue(8);
        w = (WhiteCell) b[3][2];
        w.setValue(1);
        w.setCorrectValue(1);
        w = (WhiteCell) b[3][3];
        w.setValue(3);
        w.setCorrectValue(3);
        w = (WhiteCell) b[3][4];
        w.setValue(7);
        w.setCorrectValue(7);
        w = (WhiteCell) b[4][3];
        w.setValue(1);
        w.setCorrectValue(1);
        w = (WhiteCell) b[4][4];
        w.setValue(9);
        w.setCorrectValue(9);

        assertTrue("Expected true", k.isFinished());
    }

    /** @brief Test de comprobar si hemos terminado de rellenar el kakuro
     *
     * Comprueba que la función retorna falso cuando hemos completado el kakuro pero hay algun valor no correcto
     */
    @Test
    public void testIsFinishedFinishIncorrect() {
        Kakuro k = new Kakuro(KAKURO_SMALL);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[1][1];
        w.setValue(7);
        w.setCorrectValue(7);
        w = (WhiteCell) b[1][2];
        w.setValue(2);
        w.setCorrectValue(2);
        w = (WhiteCell) b[2][1];
        w.setValue(9);
        w.setCorrectValue(9);
        w = (WhiteCell) b[2][2];
        w.setValue(5);
        w.setCorrectValue(5);
        w = (WhiteCell) b[2][3];
        w.setValue(8);
        w.setCorrectValue(8);
        w = (WhiteCell) b[3][2];
        w.setValue(1);
        w.setCorrectValue(1);
        w = (WhiteCell) b[3][3];
        w.setValue(3);
        w.setCorrectValue(3);
        w = (WhiteCell) b[3][4];
        w.setValue(7);
        w.setCorrectValue(7);
        w = (WhiteCell) b[4][3];
        w.setValue(1);
        w.setCorrectValue(1);
        w = (WhiteCell) b[4][4];
        w.setValue(8);
        w.setCorrectValue(9);

        assertFalse("Expected false", k.isFinished());
    }

    /** @brief Test de comprobar si hemos terminado de rellenar el kakuro
     *
     * Comprueba que la función retorna falso cuando aun no hemos terminado de rellenar el kakuro, aunque los valores que hemos introducido hasta el momento sean correctos
     */
    @Test
    public void testIsFinishedNotFinished() {
        Kakuro k = new Kakuro(KAKURO_SMALL);
        Cell [][] b = k.getBoard();
        WhiteCell w = (WhiteCell) b[1][1];
        w.setValue(7);
        w.setCorrectValue(7);
        w = (WhiteCell) b[1][2];
        w.setValue(2);
        w.setCorrectValue(2);
        w = (WhiteCell) b[2][1];
        w.setValue(9);
        w.setCorrectValue(9);
        w = (WhiteCell) b[2][2];
        w.setValue(5);
        w.setCorrectValue(5);
        w = (WhiteCell) b[2][3];
        w.setCorrectValue(8);
        w = (WhiteCell) b[3][2];
        w.setCorrectValue(1);
        w = (WhiteCell) b[3][3];
        w.setCorrectValue(3);
        w = (WhiteCell) b[3][4];
        w.setCorrectValue(7);
        w = (WhiteCell) b[4][3];
        w.setCorrectValue(1);
        w = (WhiteCell) b[4][4];
        w.setCorrectValue(9);

        assertFalse("Expected false", k.isFinished());
    }

}
