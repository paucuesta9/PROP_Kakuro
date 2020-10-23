package presentation;

import data.CtrlData;
import domain.*;

import java.util.Scanner;

public class CtrlUI {
    private CtrlDomain cd;

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
        cd.searchKakuro(difficulty, kakuroSize);
        System.out.println(" -- INSTRUCCIONES --");
        System.out.println("Para colocar un número debe colocar la posición x e y seguido del valor de la casilla.");
        System.out.println("Ejemplo: Si desea colocar un 9 en la casilla (2,6) debe poner: 2 6 9");
        //TODO: Hacer la comprovación while (mientras no queden numeros por poner y sea correcto)
        while (true) {
            writeKakuroInTerminal();
            System.out.println("");
            Scanner reader = new Scanner(System.in);
            int x = reader.nextInt();
            int y = reader.nextInt();
            int value = reader.nextInt();
            if (!cd.kakuroSetValue(x, y, value)) {
                System.out.println("Posición de casilla incorrecto. Por favor, introduzca una posición de una casilla blanca");
            }
            System.out.println("");
        }
    }

    public int readNumber() {
        Scanner reader = new Scanner(System.in);
        int number = 0;
        number = reader.nextInt();
        return number;
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
