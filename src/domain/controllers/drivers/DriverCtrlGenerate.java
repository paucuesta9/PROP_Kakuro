package domain.controllers.drivers;

import domain.classes.BlackCell;
import domain.classes.Cell;
import domain.classes.Kakuro;
import domain.classes.WhiteCell;
import domain.controllers.CtrlGenerate;
import domain.controllers.CtrlValidate;

import java.util.Scanner;

/** @file DriverCtrlGenerate.java
 @brief Clase  <em>DriverCtrlGenerate</em>.
 */

/** @brief Clase DriverCtrlGenerate que comprueba la correctividad de las funciones del controlador CtrlGenerate
 * @author Pol Vallespí Soro
 */
public class DriverCtrlGenerate {
    private static Scanner reader = new Scanner(System.in);
    private static Kakuro currentKakuro;

    /** @Brief Test de la función setkakuro
     *  Comprueba si se asigna el kakuro correctamente
     */
    private static void testSetKakuro() {
        System.out.println("Indica un kakuro para hacer el test: ");
        String kakuroString = readKakuro();
        currentKakuro = new Kakuro(kakuroString);
        CtrlGenerate.setKakuro(currentKakuro);
        System.out.println("Se ha hecho el set");
    }

    /**
     * @brief Test de la función countWhiteCellsV
     * Comprueba si la función countWhiteCellsV cuenta correctamente el número de casillas blancas de la misma run vertical, situadas antes que una celda en concreto
     *
     */
    private static void testCountWhiteCellsV() {
        System.out.println("Introduce un kakuro para hacer el test:");
        String kakuroString = readKakuro();
        System.out.println("Hay que tener en cuenta que no se puede introducir como posicion una casilla situada a la primera fila o columna");
        System.out.println("Introduce el numero de fila de la casilla a testear:");
        int fila = readNumber();
        System.out.println("Introduce el numero de columna de la casilla a testear:");
        int columna = readNumber();
        currentKakuro = new Kakuro(kakuroString);
        int r = CtrlGenerate.countWhiteCellsV(currentKakuro.getBoard(), fila-1,columna);
        System.out.println(r);
    }

    /**
     * @brief Test de la función countWhiteCellsH
     *
     * Comprueba si la función countWhiteCellsH cuenta correctamente el número de casillas blancas de la misma run horizontal, situadas antes que una celda en concreto
     */
    private static void testCountWhiteCellsH() {
        System.out.println("Esta funcion cuenta cuantas casillas blancas, de la misma run horizontal, hay antes de un celda en la posicion (x,y)");
        System.out.println("Introduce un kakuro para hacer el test:");
        String kakuroString = readKakuro();
        System.out.println("Hay que tener en cuenta que no se puede introducir como posicion una casilla situada a la primera fila o columna");
        System.out.println("Introduce el numero de fila de la casilla a testear:");
        int fila = readNumber();
        System.out.println("Introduce el numero de columna de la casilla a testear:");
        int columna = readNumber();
        currentKakuro = new Kakuro(kakuroString);
        int r = CtrlGenerate.countWhiteCellsH(currentKakuro.getBoard(), fila,columna-1);
        System.out.println(r);
    }

    /**
     * @brief Test de la función nineCellsRow
     *
     * Comprueba si la función NineCellsRow cuanta adecuadamente el número de casillas blancas de cada run horizontal
     */
    private static void testNineCellsRow() {
        System.out.println("Esta funcion cuenta el numero de casillas blancas de cada run en horizontal");
        System.out.println("Introduce un kakuro valido: ");
        String kakuroString = readKakuro();
        currentKakuro = new Kakuro(kakuroString);
        int rows = currentKakuro.getColumnSize();
        int cols = currentKakuro.getRowSize();
        int [][][] tempBoard = new int [rows][cols][9];
        for(int i = 0; i < rows;++i){
            for(int j = 0; j < cols;++j) {
                tempBoard[i][j] = new int[] {0,0,0,0,0,0,0,0,0};
            }
        }
        CtrlGenerate.nineCellsRow(currentKakuro.getBoard(), tempBoard);
        Cell[][] board = currentKakuro.getBoard();
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols;++j) {
                if(board[i][j].isWhite()) System.out.print("X");
                else System.out.print(tempBoard[i][j][1]);
            }
            System.out.println();
        }
        System.out.println("Se ha imprimido un tablero por pantalla indicando el valor de cada run en verftical");
    }

    /**
     * @brief Test de la función nineCellsCol
     *
     * Comprueba si la función NineCellsCol cuanta adecuadamente el número de casillas blancas de cada run vertical
     */
    private static void testNineCellsCol() {
        System.out.println("Esta funcion cuenta el numero de casillas blancas de cada run en vertical");
        System.out.println("Introduce un kakuro valido: ");
        String kakuroString = readKakuro();
        currentKakuro = new Kakuro(kakuroString);
        int rows = currentKakuro.getColumnSize();
        int cols = currentKakuro.getRowSize();
        int [][][] tempBoard = new int [rows][cols][9];
        for(int i = 0; i < rows;++i){
            for(int j = 0; j < cols;++j) {
                tempBoard[i][j] = new int[] {0,0,0,0,0,0,0,0,0};
            }
        }
        CtrlGenerate.nineCellsCol(currentKakuro.getBoard(), tempBoard);
        Cell[][] board = currentKakuro.getBoard();
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols;++j) {
                if(board[i][j].isWhite()) System.out.print("X");
                else System.out.print(tempBoard[i][j][0]);
            }
            System.out.println();
        }
        System.out.println("Se ha imprimido un tablero por pantalla indicando el valor de cada run en horizontal");
    }

    /**
     * @brief Test de la función computePosSums
     *
     * Comprueba si la función computePosSums retorna los valores correctos
     */
    private static void testComputePosSums() {
        System.out.println("Indica el numero que queremos sumar:");
        int n = readNumber();
        System.out.println("Indica el numero de valores: ");
        int d = readNumber();
        System.out.println("Quieres algun valor fijo? (0 no, 1..9 es el valor fijo)");
        int m = readNumber();
        int [] posComb = CtrlGenerate.computePosSums(n,d,m);
        System.out.print("Combinando estos numeros puedes sumar ");
        System.out.println(n);
        if(m != 0) {
            System.out.print(m);
            System.out.print(' ');
        }
        for(int i = 0; i < posComb.length;++i){
            if(posComb[i]== 1) {
                System.out.print(i+1);
                System.out.print(' ');
            }
        }
        System.out.println();

    }

    /**@brief Test de la función allZero
     * Comprueba si la función allZero detecta correctamente si todos los elementos de un vector son 0
     */
    private static void testAllZero() {
        System.out.println("Introduce un vector de 9 valores");
        int [][][] tempBoard = new int[1][1][9];
        int i = 0;
        while( i < 9) {
            int n = readNumber();
            tempBoard[0][0][i] = n;
            ++i;
        }
        boolean r = CtrlGenerate.allZero(tempBoard,0,0);
        if( r ) System.out.println("Todos los valores son 0");
        else System.out.println("No todos los valores son 0");

    }

    /**@brief Test de la función intersection3
     * Comprueba si la función intersection3 hace bien la interseccion de los dos vectores y modifica adecuadamente el segundo
     */
    private static void testIntersection3() {
        System.out.println("Introduce un vector de 9 elementos, que pueden ser 0 o 1");
        int [] vec1 = new int[9];
        int [] vec2 = new int[9];
        int i = 0;
        while( i < 9) {
            int n = readNumber();
            vec1[i] = n;
            ++i;
        }
        System.out.println("Introduce otro vector de 9 elementos, que pueden ser 0 o 1");
        i = 0;
        while( i < 9) {
            int n = readNumber();
            vec2[i] = n;
            ++i;
        }
        boolean b = CtrlGenerate.intersection3(vec1,vec2);
        if( b ) System.out.println("True -> o todos los elementos del segundo vector son 0 o tienen algun 1 en alguna posicion en comun");
        else System.out.println("False");
    }

    /**@brief Test de la función intersection2
     * Comprueba si la función intersection2 hace bien la interseccion de los dos vectores y modifica adecuadamente el segundo
     */
    private static void testIntersection2() {
        System.out.println("Introduce un vector de 9 elementos, que pueden ser 0 o 1");
        int [] vec1 = new int[9];
        int [] vec2 = new int[9];
        int i = 0;
        while( i < 9) {
            int n = readNumber();
            vec1[i] = n;
            ++i;
        }
        System.out.println("Introduce otro vector de 9 elementos, que pueden ser 0 o 1");
        i = 0;
        while( i < 9) {
            int n = readNumber();
            vec2[i] = n;
            ++i;
        }
        boolean b = CtrlGenerate.intersection2(vec1,vec2);
        if( b ) System.out.println("Tienen algun 1 en alguna posicion en comun");
        else System.out.println("No tienen ningun 1 en alguna posicion en comun");
    }

    /**@brief Test de la función intersection
     * Comprueba si la función intersection encuentra correctamente si hay un solo valor en común entre los dos vectores y retorna la posición indicada
     */
    private static void testIntersection() {
        System.out.println("Introduce un vector de 9 elementos, que pueden ser 0 o 1");
        int [] vec1 = new int[9];
        int [] vec2 = new int[9];
        int i = 0;
        while( i < 9) {
            int n = readNumber();
            vec1[i] = n;
            ++i;
        }
        System.out.println("Introduce otro vector de 9 elementos, que pueden ser 0 o 1");
        i = 0;
        while( i < 9) {
            int n = readNumber();
            vec2[i] = n;
            ++i;
        }
        int r = CtrlGenerate.intersection(vec1,vec2);
        if( r != -1) {
            System.out.print("Tienen un unico 1 en comun, en la posicion: ");
            System.out.println(r);
        }
        else System.out.println("O no tienen 1s en comun o tienen mas de uno");

    }

    /**@brief Test de la función isUnique
     * Comprueba si la función isUnique encuentra de manera satisfactoria si hay un único valor posible en el vector
     */
    private static void testIsUnique() {
        System.out.println("Esta funcion comprueba si en un vector solo hay un numero disponible (vec[i]= 1)");
        System.out.println("Introduce 9 valores 0 o 1:");
        int [] vec = new int[] {0,0,0,0,0,0,0,0,0};
        int i = 0;
        while(i < 9) {
            int n = readNumber();
            vec[i] = n;
            ++i;
        }
        boolean b = CtrlGenerate.isUnique(vec);
        if( b ) System.out.println("Hay un unico 1 en el vector");
        else System.out.println("O no hay 1s o no son unicos");
    }

    /**@brief Test de la función fillBoard
     * Comprueba si la función filBoard crea un kakuro único dado un tablero sin sumas
     */
    private static void testFillBoard() {
        System.out.println("Este test puede tardar un tiempo elevado en dar respuesta");
        System.out.println("Introduce un kakuro con celdas blancas y negras sin sumas: ");
        String kakuroString = readKakuro();
        currentKakuro = new Kakuro(kakuroString);
        boolean b = CtrlGenerate.fillBoard(currentKakuro.getBoard(),0);
        if( b ) System.out.println("Se ha podido generar un kakuro unico con este tablero");
        else System.out.println("No se ha podido generar un kakuro unico con este tablero");
    }

    /**@brief Test de la función howManyWhites
     * Comprueba si la función howManyWhites cuenta correctamente el número de casillas blancas del tablero
     */
    private static void testHowManyWhites() {
        System.out.println("Indica un kakuro para hacer el test:");
        String kakuroString = readKakuro();
        currentKakuro = new Kakuro(kakuroString);
        int r = CtrlGenerate.howManyWhites(currentKakuro.getBoard());
        System.out.print("El kakuro tiene ");
        System.out.print(r);
        System.out.println(" celdas blancas");
    }

    /**@brief Test de la función Generate
     * Comprueba si la función generate genera kakuros válidos dado un número n de filas y columnas
     */
    private static void  testGenerate() {
        System.out.println("Indica el tamano de tablero deseado: ");
        int n = readNumber();
        System.out.println("Indica la dificultad deseada: ");
        int d = readNumber();
        currentKakuro = CtrlGenerate.generate(n,d);
        System.out.println(currentKakuro.toString());
        System.out.println("Se ha generado un kakuro del tamano deseado con solucion unica");
    }

    /**@brief Test de la función firstColRow
     * Comprueba que la función firstColRow genera correctamente las casillas de la primera fila y columna
     */
    private static void testFirstColRow() {
        System.out.println("Introduce el tamano del tablero (nxn): ");
        int n = readNumber();
        System.out.println("Introduce la dificultad: ");
        int d = readNumber();
        Cell[][] board = new Cell[n][n];
        for(int i = 0; i < n; ++i){
            board[0][i] = new BlackCell();
            board[i][0] = new BlackCell();
        }
        CtrlGenerate.firstColRow(board,d);
        System.out.println("Se ha generado la primera y ultima fila del tablero");
    }

    /**@brief Test de la función firstColRow
     * Comprueba que la función firstColRow genera correctamente las casillas de la primera fila y columna
     */
    private static void testRandomCells() {
        System.out.println("Introduce el tamano del tablero (nxn): ");
        int n = readNumber();
        System.out.println("Introduce la dificultad: ");
        int d = readNumber();
        Cell[][] board = new Cell[n][n];
        for(int i = 0; i < n; ++i){
            board[0][i] = new BlackCell();
            board[i][0] = new BlackCell();
        }
        currentKakuro = new Kakuro(0,0,board);
        CtrlGenerate.firstColRow(currentKakuro.getBoard(),d); //Es necessario para evitar que pete
        CtrlGenerate.randomCells(currentKakuro.getBoard(),d);
        System.out.println(currentKakuro.toString());
        System.out.println("Se ha generado un tablero del tamano deseado con casillas blancas y negras");
    }

    /**@brief Test de la función checkBoard
     * Combrueba que la función Checkboard elimina todas las casillas blancas solitarias
     */
    private static void testCheckBoard() {
        System.out.println("Escribe un kakuro para realizar el test: ");
        System.out.println("El kakuro introducido puede contener casillas blancas solitarias o puede no tenerlas");
        System.out.println("En caso de no tenerlas, el tablero no se vera modificado");
        String kakuroString = readKakuro();
        currentKakuro = new Kakuro(kakuroString);
        CtrlGenerate.checkBoard(currentKakuro.getBoard());
        System.out.println(currentKakuro.toString());
        System.out.println("El kakuro no contiene casillas blancas solitarias");
    }

    /**@brief Test de la función DFS
     * Comprueba que la función DFS cuenta correctamente el número de casillas blancas conectadas a una celda dada
     */
    private static void testDFS() {
        System.out.println("Escribe un kakuro para realizar el test:");
        String kakuroString = readKakuro();
        System.out.println("Fila de una casilla blanca: ");
        int r = readNumber();
        System.out.println("Columna de una casilla blanca: ");
        int c = readNumber();

        currentKakuro = new Kakuro(kakuroString);
        int[][] board = new int[currentKakuro.getColumnSize()][currentKakuro.getRowSize()];
        for(int i = 0; i <currentKakuro.getColumnSize(); ++i ) {
            for(int j = 0; j < currentKakuro.getRowSize();++j) {
                board[i][j] = 0;
            }
        }
        int res = CtrlGenerate.DFS(currentKakuro.getBoard(),r,c,board );
        System.out.print("Se pueden alcanzar ");
        System.out.print(res);
        System.out.println(" celdas blancas desde la celda dada");
    }

    /**@brief Test de la función connexBoard
     * Comprueba si la función connexBoard retorna adecuadamente si un talbero es conexo o no
     */
    private static void testConnexBoard() {
        System.out.println("Escribe un kakuro para realizar el test");
        String kakuroString = readKakuro();
        currentKakuro = new Kakuro(kakuroString);
        boolean b = CtrlGenerate.connexBoard(currentKakuro.getBoard());
        if(b) System.out.println("El tablero es conexo");
        else System.out.println("El tablero no es conexo");
    }

    /**@brief función main del driver CtrlGenerate
     * Nos permite escoger una función a testear
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. setKakuro \n 2. Casillas blancas en vertical anteriores a una casilla en concreto \n 3. Casillas blancas en horizontal anteriores a una casilla en concreto \n 4. Calcular el tamano de todas las runs horizontales \n 5. Calcular el tamano de todas las runs verticales"+
                "\n 6. ComputePosSums \n 7. Comprobar si todos los elementos de un vector son 0  \n 8. Interseccion entre vectores 3 \n 9. Interseccion entre vectores 2" +
                "\n 10. Interseccion entre vectores 1 \n 11. Comprobar si un solo hay un valor posible \n 12. Rellenar un tablero para que sea unico \n 13 Cuenta casillas blancas \n 14. Generar un kakuro unico \n 15. Generar la primera fila y columna de un tablero \n 16. Rellenar el tablero con casillas blancas y negras" +
                "\n 17. Comprobar que no hay casillas blancas solitarias \n 18. Mirar cuantas casillas blancas estan conectadas con una casilla blanca \n 19. Mirar si el tablero es conexo");

        int value = readNumber();
        while (value != 20) {
            switch (value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a setkakuro");
                    testSetKakuro();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a countWhiteCellsV");
                    testCountWhiteCellsV();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Se llama a la countWhiteCellsH");
                    testCountWhiteCellsH();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Se llama a NineCellsRow");
                    testNineCellsRow();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Se llama a NineCellsCol");
                    testNineCellsCol();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Se llama a ComputePosSums");
                    testComputePosSums();
                    break;
                case 7:
                    System.out.println();
                    System.out.println("Se llama a AllZero");
                    testAllZero();
                    break;
                case 8:
                    System.out.println();
                    System.out.println("Se llama a Intersection3");
                    testIntersection3();
                    break;
                case 9:
                    System.out.println();
                    System.out.println("Se llama a Intersection2");
                    testIntersection2();
                    break;
                case 10:
                    System.out.println();
                    System.out.println("Se llama a Intersection");
                    testIntersection();
                    break;
                case 11:
                    System.out.println();
                    System.out.println("Se llama a isUnique");
                    testIsUnique();
                    break;
                case 12:
                    System.out.println();
                    System.out.println("Se llama a fillBoard");
                    testFillBoard();
                    break;
                case 13:
                    System.out.println();
                    System.out.println("Se llama a howManyWhites");
                    testHowManyWhites();
                    break;
                case 14:
                    System.out.println();
                    System.out.println("Se llama a generate");
                    testGenerate();
                    break;
                case 15:
                    System.out.println();
                    System.out.println("Se llama a firstColRow");
                    testFirstColRow();
                    break;
                case 16:
                    System.out.println();
                    System.out.println("Se llama a randomCells");
                    testRandomCells();
                    break;
                case 17:
                    System.out.println();
                    System.out.println("Se llama a checkBoard");
                    testCheckBoard();
                    break;
                case 18:
                    System.out.println();
                    System.out.println("Se llama a DFS");
                    testDFS();
                    break;
                case 19:
                    System.out.println();
                    System.out.println("Se llama a connexBoard");
                    testConnexBoard();
                    break;
                default:
                    System.out.println();
                    System.out.println("El numero introducido es incorrecto");
                    break;
            }
            System.out.println();
            System.out.println("Opciones: \n 1. setKakuro \n 2. Casillas blancas en vertical anteriores a una casilla en concreto \n 3. Casillas blancas en horizontal anteriores a una casilla en concreto \n 4. Calcular el tamano de todas las runs horizontales \n 5. Calcular el tamano de todas las runs verticales"+
                    "\n 6. ComputePosSums \n 7. Comprobar si todos los elementos de un vector son 0  \n 8. Interseccion entre vectores 3 \n 9. Interseccion entre vectores 2" +
                    "\n 10. Interseccion entre vectores 1 \n 11. Comprobar si un solo hay un valor posible \n 12. Rellenar un tablero para que sea unico \n 13 Cuenta casillas blancas \n 14. Generar un kakuro unico \n 15. Generar la primera fila y columna de un tablero \n 16. Rellenar el tablero con casillas blancas y negras" +
                    "\n 17. Comprobar que no hay casillas blancas solitarias \n 18. Mirar cuantas casillas blancas estan conectadas con una casilla blanca \n 19. Mirar si el tablero es conexo");
            value = readNumber();
        }
        System.exit(0);
    }

    /**@brief función que nos permite leer un kakuro en formato string
     *
     * @return kakuro en formato string
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

    /**@brief función que nos permite leer un entero del terminal o un fichero
     *
     * @return el entero leido
     */
    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }

}
