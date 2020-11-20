package data;

import java.io.*;
import java.util.Random;

/** @file CtrlData.java
 @brief Clase  <em>CtrlData</em>.
 */

/** @brief Clase CtrlData para obtener y guardar datos en nuestra base de datos (ficheros)
 * @author Pau Cuesta Arcos
 */

public class CtrlData {
    /**
     * ctrlData es la instancia del CtrlData
     */
    private static CtrlData ctrlData;

    /** @brief Obtiene la instancia del CtrlData
     *
     * Si no existe una instancia singleton de esta clase, crea una y la devuelve. En caso contrario devuelve la que ya existe
     * @return la instancia
     */
    public static CtrlData getInstance() {
        if (ctrlData == null)
            ctrlData = new CtrlData();
        return ctrlData;
    }

    /** @brief Creadora por defecto
     *
     */
    public CtrlData() {

    }

    /** @brief Busca un kakuro en fichero
     *
     * @param difficulty representa la dificultad del kakuro buscado
     * @param kakuroSizeRow representa el tamaño de filas del kakuro buscado
     * @param kakuroSizeColumn representa el tamaño de columnas del kakuro buscado
     * @return un kakuro con la dificultad y el tamaño deseado en formato String
     */
    public String searchKakuro (int difficulty, int kakuroSizeRow, int kakuroSizeColumn) throws IOException {
        Random random = new Random();
        int numFile;
        try {
            numFile = getNumberOfFiles(difficulty, kakuroSizeRow, kakuroSizeColumn);
        } catch (NullPointerException e) {
            throw new IOException();
        }
        int id = random.nextInt(numFile);
        StringBuilder content = new StringBuilder();
        content.append(id).append("\n");
        content.append(getKakuro("data/diff" + difficulty + "/" + kakuroSizeRow + "_" + kakuroSizeColumn + "/" + id + ".txt"));
        return content.toString();
    }

    /** @brief Busca un kakuro en fichero a partir de una ruta relativa
     *
     * @param filePath representa una ruta relativa
     * @return el kakuro que se encuentra en la ruta relativa en formato String
     */
    public String getKakuro(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        return content.toString();
    }

    /** @brief Guarda un kakuro en fichero
     *
     * @param content representa el contenido del kakuro
     * @param solution representa el kakuro resuelto
     * @param diff representa la dificultad del kakuro
     * @param sizeRow representa el tamaño de filas del kakuro
     * @param sizeColumn representa el tamaño de columnas del kakuro
     */
    public int saveKakuro(String content, String solution, int diff, int sizeRow, int sizeColumn) {
        FileWriter file = null;
        try {
            int number;
            try {
                number = getNumberOfFiles(diff, sizeRow, sizeColumn);
            } catch (NullPointerException e) {
                number = 0;
                File folder = new File("data/diff" + diff + "/" + sizeRow + "_" + sizeColumn);
                folder.mkdir();
            }
            if (solution != null) {
                File folderSol = new File("data/solutions/diff" + diff + "/" + sizeRow + "_" + sizeColumn);
                folderSol.mkdir();
            }
            file = new FileWriter("data/diff" + diff + "/" + sizeRow + "_" + sizeColumn + "/" + number + ".txt");
            PrintWriter pw = new PrintWriter(file);
            pw.print(content);
            pw.close();
            if (solution != null) {
                file = new FileWriter("data/solutions/diff" + diff + "/" + sizeRow + "_" + sizeColumn + "/" + number + ".txt");
                PrintWriter pwSol = new PrintWriter(file);
                pwSol.print(solution);
                pwSol.close();
            }
            return number;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return -1;
    }

    /** @brief Mira cuántos ficheros tienen una dificultad y tamaño en concreto
     *
     * Busca cuantos ficheros hay con dificultad=diff y tamaño=size
     * @param diff representa la dificultad de un kakuro
     * @param sizeRow representa el tamaño de filas de un kakuro
     * @param sizeColumn representa el tamaño de columnas del kakuro
     * @return la cantidad de ficheros que cumplen las condiciones
     */
    public int getNumberOfFiles(int diff, int sizeRow, int sizeColumn) throws NullPointerException {
        File folder = new File("data/diff" + diff + "/" + sizeRow + "_" + sizeColumn);
        File[] listFiles = folder.listFiles();
        return listFiles.length;
    }

}
