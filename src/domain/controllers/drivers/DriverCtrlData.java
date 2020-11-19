package domain.controllers.drivers;

import data.CtrlData;

import java.io.IOException;
import java.util.Scanner;

/** @file DriverCtrlData.java
 @brief Clase  <em>DriverCtrlData</em>.
 */

/** @brief Clase DriverCtrlData que comprueba la correctividad de las funciones del controlador CtrlData
 * @author Judith Almoño Gómez
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

    /**@brief Test de la función SearchKakuro
     *
     * Comprueba que la función searchKakuro se ejecute correctamente
     * @param diff representa la dificultad del kakuro
     * @param kakuroSizeRow representa el tamaño de filas del kakuro
     * @param kakuroSizeColumn representa el tamaño de columnas del kakuro
     */
    private static void testSearchKakuro(int diff, int kakuroSizeRow, int kakuroSizeColumn) {
        CtrlData ctrlData = new CtrlData();
        String kakuro = null;
        try {
            kakuro = ctrlData.searchKakuro(diff, kakuroSizeRow, kakuroSizeColumn);
            System.out.println(kakuro);
        } catch (IOException e) {
            System.out.println("No se ha encontrado un Kakuro que cumpla las condiciones");
        }
    }

    /** @brief Test de la función GetKakuro
     *
     * Comprueba que la función getKakuro se ejecute correctamente
     * @param filePath representa la ruta donde se encuentra el kakuro
     */
    private static void testGetKakuro(String filePath) {
        CtrlData ctrlData = new CtrlData();
        String kakuro = null;
        try {
            kakuro = ctrlData.getKakuro("data/" + filePath + ".txt");
            System.out.println("Este es el kakuro que se encuentra en la ruta: " + filePath + "\n" + kakuro + "\n");
        } catch (IOException e) {
            System.out.println("No se ha podido obtener un kakuro con esta ruta");
        }
    }

    /**@brief Test de la función SaveKakuro
     *
     * Comprueba que la función saveKakuro se ejecute correctamente
     * @param kakuro representa el kakuro a guardar
     * @param diff representa la dificultad del kakuro
     * @param sizeRow representa el tamaño de filas del kakuro
     * @param sizeColumn representa el tamaño de columnas del kakuro
     */
    private static void testSaveKakuro(String kakuro, int diff, int sizeRow, int sizeColumn) {
        CtrlData ctrlData = new CtrlData();
        ctrlData.saveKakuro(kakuro, diff, sizeRow, sizeColumn);
        System.out.println("Se ha generado un fichero con el kakuro guardado");
    }

    /**@brief Test de la función GetNumberOfFiles
     *
     * Comprueba que la función getNumberOfFiles se ejecute correctamente
     * @param diff representa la dificultad del kakuro
     * @param sizeRow representa el tamaño de filas del kakuro
     * @param sizeColumn representa el tamaño de columnas del kakuro
     */
    private static void testGetNumberOfFiles(int diff, int sizeRow, int sizeColumn) {
        CtrlData ctrlData = new CtrlData();
        try {
            int result = ctrlData.getNumberOfFiles(diff, sizeRow, sizeColumn);
            System.out.println("Hay " + result + " kakuro(s) con esta dificultad y tamaño");
        } catch (NullPointerException e) {
            System.out.println("No hay ningún kakuro con estas condiciones");
        }
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("\n Opciones: \n 1. GetInstance \n 2. Buscador de kakuros \n 3. Getter de kakuros \n 4. Guardar kakuros \n 5. Contador de kakuros en una carpeta \n 6. Salir ");
        int value = readNumber();
        while (value != 6) {
            switch(value) {
                case 1:
                    System.out.println("Se llama a GetInstance");
                    testGetInstance();
                    break;
                case 2:
                    System.out.println("Introduzca la dificultad, el tamaño del kakuro (NxM)");
                    int diff = readNumber();
                    int kakuroSizeRow = readNumber();
                    int kakuroSizeColumn = readNumber();
                    System.out.println("Se llama a searchKakuro");
                    testSearchKakuro(diff, kakuroSizeRow, kakuroSizeColumn);
                    break;
                case 3:
                    System.out.println("Introduzca el filePath del kakuro");
                    String filePath = readLine();
                    System.out.println("Se llama a getKakuro");
                    testGetKakuro(filePath);
                    break;
                case 4:
                    System.out.println("Introduzca el kakuro a guardar, la dificultad y el tamaño del kakuro (NxM)");
                    String kakuro = readKakuro();
                    diff = readNumber();
                    int sizeRow = readNumber();
                    int sizeColumn = readNumber();
                    System.out.println("Se llama saveKakuro");
                    testSaveKakuro(kakuro, diff, sizeRow, sizeColumn);
                    break;
                case 5:
                    System.out.println("Introduzca la dificultad y el tamaño del kakuro (NxM)");
                    diff = readNumber();
                    sizeRow = readNumber();
                    sizeColumn = readNumber();
                    System.out.println("Se llama a getNumberOfFiles");
                    testGetNumberOfFiles(diff, sizeRow, sizeColumn);
                    break;
                default:
                    System.out.println("El número introducido es incorrecto");
                    break;
            }
            System.out.println("\n Opciones: \n 1. GetInstance \n 2. Buscador de kakuros \n 3. Getter de kakuros \n 4. Guardar kakuros \n 5. Contador de kakuros en una carpeta \n 6. Salir ");
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
        return reader.next().trim();
    }
}
