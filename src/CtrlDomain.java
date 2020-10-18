import java.io.*;
import java.util.Scanner;

public class CtrlDomain {

    //constructora
    public CtrlDomain() {

    }

    public void run () {
        System.out.println("########## BIENVENIDO A KAKURO ##########");
        System.out.println("");
        menu();
    }

    public void menu () {
        System.out.println("MENÚ:");
        System.out.println("1. Jugar");
        System.out.println("2. Crear/Validar");
        System.out.println("3. Resolver");
        System.out.println("4. Generar Automáticamente");
        System.out.println("5. Salir");
        System.out.println("");
        int choice = readNumber();
        switch(choice) {
            case 1:
                play();
                break;
            case 2:
                //TODO: Hacer la función crear/validar
                break;
            case 3:
                //TODO: Hacer la función resolver
                break;
            case 4:
                //TODO: Hacer la función generar
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("El número introducido es incorrecto");
                System.out.println("Debe introducir un número entre 1 y 4");
                menu();
                break;
        }
    }

    public void play() {
        System.out.print("Escoge dificultad: Fácil (1), Medio (2), Dificil (3): ");
        int difficulty = readNumber();
        System.out.print("Escoge tamaño N (NxN): ");
        int kakuroSize = readNumber();
        Kakuro k = searchKakuro(difficulty, kakuroSize);
        System.out.println(" -- INSTRUCCIONES --");
        System.out.println("Para colocar un número debe colocar la posición x e y seguido del valor de la casilla.");
        System.out.println("Ejemplo: Si desea colocar un 9 en la casilla (2,6) debe poner: 2 6 9");
        while (true) {
            writeKakuroInTerminal(k);
            System.out.println("");
            Scanner reader = new Scanner(System.in);
            int x = reader.nextInt();
            int y = reader.nextInt();
            int value = reader.nextInt();
            //k.setValue(x, y, value);
            System.out.println("");
        }
    }

    public void createValidate() {}

    public void resolve() {
        Kakuro kakuro = null;
        int [] vec = {0,0,0,0,0,0,0,0,0,0};
        boolean t = resKakuro(kakuro,0,0, 0, vec);
        System.out.println(t);
    }

    public void generate() {}

    public Kakuro searchKakuro (int difficulty, int kakuroSize) {
        //TODO: Hacer función searchKakuro
        return readKakuro("data/Kakuro4.txt");
    }

    public boolean checkColumn(Cell[][] board, int r, int c, int i, boolean f, int sum) {
        if( !board[r][c].isWhite() ) {
            BlackCell b = (BlackCell) board[r][c];
            if(f && b.getVertical() == sum) return true;
            else if (!f && b.getVertical() > sum) return true;
        }
        else {
            WhiteCell w = (WhiteCell) board[r][c];
            if( w.getValue() == i ) return false;
            sum += w.getValue();
            return checkColumn(board,r-1,c,i,f,sum);
        }
        return false;
    }

    public boolean resKakuro(Kakuro k, int r, int c, int sum, int [] vec) {
        System.out.print(r);
        System.out.print(" ");
        System.out.print(c);
        System.out.println();

        if( r == k.getRowSize() ) { return true; } //hemos llegado al final, la solucion es correcta
        else {
            Cell[][] board = k.getBoard();
            if( !board[r][c].isWhite() ) { // estamos en una casilla negra; queremos cambiar de columna o de casilla
                if (sum != 0) return false;
                BlackCell b = (BlackCell) board[r][c];

                if (c != k.getColumnSize() - 1) { // si no estem a la utlima columna
                    if (board[r][c + 1].isWhite()) {
                        sum = b.getHorizontal();
                    }
                }
                int [] aux = {0,0,0,0,0,0,0,0,0,0};
                if (c == k.getColumnSize() - 1) return resKakuro(k, r + 1, 0, 0, aux); //cambiamos de fila, estamos en la ultima columna }
                else return resKakuro(k,r,c+1, sum, aux); //cambiamos de columna
            }
            else { // si estamos en una casilla blanca
                vec[0] = 1; //indica que s'ha modificat
                WhiteCell w = (WhiteCell) board[r][c];
                for(int i = 1; i < 10 && sum-i >= 0; ++i ) {
                    if ( vec[i] == 0) {
                        vec[i] = 1;
                        w.setValue(i);
                        boolean f = false;
                        if(r == k.getRowSize() - 1 || !board[r+1][c].isWhite()) f =true;
                        if(checkColumn(board, r-1, c, i, f, i)) {
                            if (c == k.getColumnSize() - 1 && sum-i != 0) { }
                            else if (c == k.getColumnSize() - 1 && sum-i == 0) { // estamos en la ultima casilla de la fila pero la suma es correcta
                                if (resKakuro(k, r + 1, 0, 0, vec)) {
                                    return true; // quiere decir que hemos llegado al final y esta bien
                                }
                            }
                            else if (resKakuro(k, r, c + 1, sum-i, vec)) { return true; }
                        }
                        vec[i] = 0;
                    }
                }
            }
        }
        return false;
    }






    //Inputs/Outputs
    public int readNumber() {
        Scanner reader = new Scanner(System.in);
        int number = 0;
        number = reader.nextInt();
        return number;
    }

    public Kakuro readKakuro(String filePath) {
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] values = line.split(",");
            int rowSize = Integer.parseInt(values[0]);
            int columnSize = Integer.parseInt(values[1]);
            Cell[][] board = new Cell[rowSize][columnSize];
            for (int i = 0; i < rowSize; ++i) {
                line = br.readLine();
                values = line.split(",");
                for (int j = 0; j < columnSize; ++j) {
                    if (values[j].equals("*")) board[i][j] = new BlackCell(i, j);
                    else if (values[j].equals("?")) board[i][j] = new WhiteCell(i, j);
                    else if (values[j].charAt(0) == 'C' || values[j].charAt(0) == 'F') {
                        int vertical = 0, horizontal = 0;
                        if (values[j].charAt(0) == 'C') {
                            values[j] = values[j].substring(1);
                            if (values[j].contains("F")) {
                                String[] CF = values[j].split("F");
                                vertical = Integer.parseInt(CF[0]);
                                horizontal = Integer.parseInt(CF[1]);
                            } else {
                                vertical = Integer.parseInt(values[j]);
                            }
                        } else {
                            horizontal = Integer.parseInt(values[j].substring(1));
                        }
                        board[i][j] = new BlackCell(i, j, vertical, horizontal);
                    } else board[i][j] = new WhiteCell(i, j, Integer.parseInt(values[j]));
                }
            }
            return new Kakuro("0", 0, rowSize, columnSize, board);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeKakuro (Kakuro k, String filePath) {
        FileWriter file = null;
        try {
            file = new FileWriter("data/" + filePath);
            PrintWriter pw = new PrintWriter(file);
            Cell[][] board = k.getBoard();
            pw.println(k.getRowSize() + "," + k.getColumnSize());
            for (int i = 0; i < k.getRowSize(); ++i) {
                for (int j = 0; j < k.getColumnSize(); ++j) {
                    if (board[i][j].isWhite()) {
                        WhiteCell w = (WhiteCell) board[i][j];
                        if (w.getValue() != 0) pw.print(w.getValue());
                        else pw.print("?");
                    }
                    else {
                        BlackCell bc = (BlackCell) board[i][j];
                        if (bc.getVertical() == 0 && bc.getHorizontal() == 0) {
                            pw.print("*");
                        }
                        if (bc.getVertical() != 0) pw.print("C" + bc.getVertical());
                        if (bc.getHorizontal() != 0) pw.print("F" + bc.getHorizontal());
                    }
                    if (j != k.getColumnSize() - 1) pw.print(",");
                }
                pw.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (null != file)
                    file.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void writeKakuroInTerminal(Kakuro k) {
        Cell[][] board = k.getBoard();
        for (int i = 0; i < k.getRowSize(); ++i) {
            for (int j = 0; j < k.getColumnSize(); ++j) {
                if (board[i][j].isWhite()) {
                    WhiteCell w = (WhiteCell) board[i][j];
                    if (w.getValue() != 0) System.out.print(w.getValue());
                    else System.out.print("?");
                }
                else {
                    BlackCell bc = (BlackCell) board[i][j];
                    if (bc.getVertical() == 0 && bc.getHorizontal() == 0) {
                        System.out.print("*");
                    }
                    if (bc.getVertical() != 0) System.out.print("C" + bc.getVertical());
                    if (bc.getHorizontal() != 0) System.out.print("F" + bc.getHorizontal());
                }
                if (j != k.getColumnSize() - 1) System.out.print(",");
            }
            System.out.println("");
        }
    }
}
