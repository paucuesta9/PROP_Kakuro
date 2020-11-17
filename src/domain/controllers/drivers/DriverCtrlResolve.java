package domain.controllers.drivers;

/** @file DriverCtrlResolve.java
 * @brief Clase <em>DriverCtrlResolve</em>.
 * @author Pol Vallespí Soro
 */


import domain.classes.Kakuro;
import domain.controllers.CtrlResolve;

import java.util.Scanner;

/** @brief Clase DriverCtrlResolve que comprueba la correctividad de las funciones del controlador Resolve
 */
public class DriverCtrlResolve {

    private static Scanner reader = new Scanner((System.in));
    /** @brief Test de la creadora
     *
     * Comprueba si se crea correctamente
     */
    private static void testCreadora() {
        CtrlResolve ctrlResolve = new CtrlResolve();
        System.out.println("Se ha creado");
    }

    /**@Brief Test de la función resolve
     * Comprueva que la función resolve se ejecuta correctamente y da el resultado esperado
     * @param kakuroText representa un kakuro hecho por el usuario
     *
     * Queremos ver que si un kakuro tiene solución esta función la encuentra. En caso de no tener solución, nos informa de ello.
     */
    private  static void testResolve(String kakuroText) {
        Kakuro kakuro = new Kakuro(kakuroText);
        CtrlResolve.setKakuro(kakuro);
        int fila = 0;
        int columna = 0;
        int suma = 0;
        int [] vec = new int[] {0,0,0,0,0,0,0,0,0,0};
        if(!CtrlResolve.resolve(fila,columna,suma,vec)) {
            System.out.println("El kakuro no tiene solución");
        }
    }
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Creadora \n 2. Resolver \n 3. Salir");
        int value = readNumber();
        while(value != 3) {
            int x,y;
            switch(value) {
                case 1:
                    System.out.println("Se llama a la cradora");
                    testCreadora();
                    break;
                case 2:
                    System.out.println("Indique el kakuro a solucionar");
                    String kakuro = readKakuro();
                    System.out.println("Se llama a resolver");
                    testResolve(kakuro);
                    break;
                default:
                    System.out.println("El número introduciodo es incorrecto");
                    break;

            }

        }
    }

    public static String readKakuro() {
        StringBuilder content = new StringBuilder();
        while (reader.hasNext())
            content.append(reader.next()).append("\n");
        return content.toString();
    }

    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }
}
