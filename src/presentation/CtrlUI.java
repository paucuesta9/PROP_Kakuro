package presentation;

import data.CtrlData;
import domain.*;

import java.util.Scanner;

public class CtrlUI {
    private CtrlDomain cd;
    private Scanner reader = new Scanner(System.in);

    public CtrlUI() {
        cd = new CtrlDomain();
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
        reader.nextLine();
        String kakuro;
        switch(choice) {
            case 1:
                play();
                break;
            case 2:
                System.out.println("Indique el Kakuro dentro de la carpeta data que desea validar");
                kakuro = readLine();
                cd.getKakuro("data/" + kakuro + ".txt");
                if (cd.validate()) {
                    System.out.println("El Kakuro es correcto");
                } else {
                    System.out.println("El Kakuro no es correcto");
                }
                menu();
                break;
            case 3:
                System.out.println("Indique el Kakuro dentro de la carpeta data que desea resolver");
                kakuro = readLine();
                cd.getKakuro("data/" + kakuro + ".txt");
                cd.resolve();
                writeKakuroInTerminal();
                menu();
                break;
            case 4:
                System.out.print("Indique la dimension n (NxN) del Kakuro que desea generar: ");
                int size = readNumber();
                System.out.println("");
                System.out.print("Indique la dificultad: Fácil (1), Medio (2), Dificil (3): ");
                int diff = readNumber();
                System.out.println("");
                //cd.generate(size, diff);
                menu();
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
        cd.searchKakuro(difficulty, kakuroSize);
        cd.resolve();
        System.out.println(" -- INSTRUCCIONES --");
        System.out.println("Para colocar un número debe colocar la posición x e y seguido del valor de la casilla.");
        System.out.println("Ejemplo: Si desea colocar un 9 en la casilla (2,6) debe poner: 2 6 9");
        System.out.println("Para salir escribe -1");
        while (!cd.isFinished()) {
            writeKakuroInTerminal();
            System.out.println("");
            int x = readNumber();
            if (x == -1) System.exit(0);
            int y = readNumber();
            int value = readNumber();
            if (!cd.kakuroSetValue(x, y, value)) {
                System.out.println("Posición de casilla incorrecto. Por favor, introduzca una posición de una casilla blanca");
            }
            else if (!cd.checkValidity(x, y, value)) {
                System.out.println("Valor de la casilla incorrecto. Por favor, introduzca un valor correcto");
            }
            System.out.println("");
        }
        System.out.println("Felicidades! Has completado el Kakuro");
        menu();
    }

    public int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }

    public String readLine() {
        return reader.nextLine();
    }

    public void writeKakuroInTerminal() {
        for (int i = 0; i < cd.getRowSize(); ++i) {
            for (int j = 0; j < cd.getColumnSize(); ++j) {
                System.out.print(cd.getValueCell(i, j));
                if (j != cd.getColumnSize() - 1) System.out.print(",");
            }
            System.out.println("");
        }
    }

}
