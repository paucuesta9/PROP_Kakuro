package domain.controllers.drivers;

/** @file DriverCtrlResolve.java
 * @brief Clase <em>DriverCtrlResolve</em>.
 */


import domain.classes.Kakuro;
import domain.controllers.CtrlResolve;

import java.util.Scanner;

/** @brief Clase DriverCtrlResolve que comprueba la correctividad de las funciones del controlador CtrlResolve
 * @author Pol Vallespí Soro
 */
public class DriverCtrlResolve {

    private static Scanner reader = new Scanner((System.in));

    /**@Brief Test de la función resolve
     * Comprueva que la función resolve se ejecuta correctamente y da el resultado esperado
     *
     *
     * Queremos ver que si un kakuro tiene solución esta función la encuentra. En caso de no tener solución, nos informa de ello.
     */
    private  static void testResolve() {
        System.out.println("Indique un kakuro para realizar el test: ");
        String kakuroText = readKakuro();
        Kakuro kakuro = new Kakuro(kakuroText);
        CtrlResolve c = new CtrlResolve(kakuro);
        int fila = 0;
        int columna = 0;
        int suma = 0;
        int [] vec = new int[] {0,0,0,0,0,0,0,0,0,0};
        boolean b = c.resolve();

        if(b) {
            System.out.println("Se ha encontrado la siguiente solucion: ");
            String s = kakuro.correctToString();
            System.out.print(s);
        }
        else System.out.println("No se ha encontrado solucion");
    }

    /**@brief función main del driver CtrlResolve
     *
     * Nos permite escoger el test que queremos realizar
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Resolver \n 3. Salir");
        int value = readNumber();
        while(value != 3) {
            switch(value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a la creadora");
                   // testCreadora();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a resolver");
                    testResolve();
                    break;
                default:
                    System.out.println();
                    System.out.println("El numero introduciodo es incorrecto");
                    break;

            }
            System.out.println();
            System.out.println("Opciones: \n 1. Creadora \n 2. Resolver \n 3. Salir");
            value = readNumber();
        }
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
