package domain.controllers.drivers;

import data.CtrlData;

import java.util.Scanner;

/** @file DriverCtrlData.java
 @brief Clase  <em>DriverCtrlData</em>.
 @author Judith Almoño Gómez
 */

/** @brief Clase DriverCtrlData que comprueba la correctividad de las funciones del controlador Data
 */

public class DriverCtrlData {
    private static Scanner reader = new Scanner(System.in);

    /** @brief Test de la función GetInstance
     *
     * Comprueba que se coja correctamente una instancia
     */
    private static void testGetInstance() {
        CtrlData ctrlData = CtrlData.getInstance();
        System.out.println("Se ha obtenido la instancia deseada");
    }

    /** @brief Test de la creadora
     *
     * Comprueba si se crea correctamente
     */
    private static void testCreadora() {
        CtrlData ctrlData = new CtrlData();
        System.out.println("Se ha creado");
    }

    /**@brief Test de la función SearchKakuro
     *
     * Comprueba que la función searchKakuro se ejecute correctamente
     * @param diff representa la dificultad del kakuro
     * @param kakuroSize representa el tamaño del kakuro
     */
    private static void testSearchKakuro(int diff, int kakuroSize) {
        CtrlData ctrlData = new CtrlData();
        String kakuro = ctrlData.searchKakuro(diff, kakuroSize);
        System.out.println(kakuro);
    }

    /** @brief Test de la función GetKakuro
     *
     * Comprueba que la función getKakuro se ejecute correctamente
     * @param filePath representa la ruta donde se encuentra el kakuro
     * @param kakuroProbar representa el kakuro buscado
     */
    private static void testGetKakuro(String filePath, String kakuroProbar) {
        CtrlData ctrlData = new CtrlData();
        String kakuro = ctrlData.getKakuro(filePath);
        if (kakuro.equals(kakuroProbar)) System.out.println("El kakuro obtenido no es el esperado");
        else System.out.println("El kakuro obtenido no es el esperado");
    }

    /**@brief Test de la función SaveKakuro
     *
     * Comprueba que la función saveKakuro se ejecute correctamente
     * @param kakuro representa el kakuro a guardar
     * @param diff representa la dificultad del kakuro
     * @param size representa el tamaño del kakuro
     */
    private static void testSaveKakuro(String kakuro, int diff, int size) {
        CtrlData ctrlData = new CtrlData();
        ctrlData.saveKakuro(kakuro, diff, size);
        System.out.println("Se ha generado un fichero con el kakuro guardado");
    }

    /**@brief Test de la función GetNumberOfFiles
     *
     * Comprueba que la función getNumberOfFiles se ejecute correctamente
     * @param diff representa la dificultad del kakuro
     * @param size representa el tamaño del kakuro
     * @param num representa la cantidad de kakuros que hay con dificultad = diff y tamaño = size
     */
    private static void testGetNumberOfFiles(int diff, int size, int num) {
        CtrlData ctrlData = new CtrlData();
        int result = ctrlData.getNumberOfFiles(diff, size);
        if (result == num) System.out.println("Se ha obtenido el número esperado");
        else System.out.println("No se ha obtenido el número esperado");
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1.GetInstance \n 2. Creadora \n 3.Buscador de kakuros \n 4. Getter de kakuros \n 5. Guardar kakuros \n 6. Contador de kakuros en una carpeta \n 7. Salir ");
        int value = readNumber();
        while (value != 7) {
            switch(value) {
                case 1:
                    System.out.println("Se llama a GetInstance");
                    testGetInstance();
                    break;
                case 2:
                    System.out.println("Se llama a la creadora");
                    testCreadora();
                    break;
                case 3:
                    System.out.println("Introduzca la dificultad, el tamaño del kakuro");
                    int diff = readNumber();
                    int kakuroSize = readNumber();
                    System.out.println("Se llama a searchKakuro");
                    testSearchKakuro(diff, kakuroSize);
                    break;
                case 4:
                    System.out.println("Introduzca el filePath del kakuro  y el kakuro en el formato correcto");
                    String filePath = readLine();
                    String kakuroProbar = readKakuro();
                    System.out.println("Se llama a getKakuro");
                    testGetKakuro(filePath, kakuroProbar);
                    break;
                case 5:
                    System.out.println("Introduzca el kakuro a guardar, la dificultad y el tamaño del kakuro");
                    String kakuro = readKakuro();
                    diff = readNumber();
                    int size = readNumber();
                    System.out.println("Se llama saveKakuro");
                    testSaveKakuro(kakuro, diff, size);
                    break;
                case 6:
                    System.out.println("Introduzca la dificultad y el tamaño del kakuro y la cantidad de kakuros que hay con esas características");
                    diff = readNumber();
                    size = readNumber();
                    int num = readNumber();
                    System.out.println("Se llama a getNumberOfFiles");
                    testGetNumberOfFiles(diff, size, num);
                    break;
                default:
                    System.out.println("El número introducido es incorrecto");
                    break;
            }
            System.out.println("Opciones: \n 1.GetInstance \n 2. Creadora \n 3.Buscador de kakuros \n 4. Getter de kakuros \n 5. Guardar kakuros \n 6. Contador de kakuros en una carpeta \n 7. Salir ");
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
        while (reader.hasNext())
            content.append(reader.next()).append("\n");
        return content.toString();
    }

    /** @brief Lee un número de consola
     *
     * @return el número leído
     */
    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }

    /**@brief Lee una línea por consola
     *
     * @return la línea leída
     */
    public static String readLine() {
        return reader.nextLine();
    }
}
