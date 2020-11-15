package data;

import java.io.*;
import java.util.Random;

/** @file CtrlData.java
 @brief Clase  <em>CtrlData</em>.
 */

/** @brief Clase CtrlData para obtener y guardar datos en nuestra base de datos (ficheros)
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
     * @param kakuroSize representa el tamaño del kakuro buscado
     * @return un kakuro con la dificultad y el tamaño deseado en formato String
     */
    public String searchKakuro (int difficulty, int kakuroSize) {
        Random random = new Random();
        return getKakuro("../data/diff" + difficulty + "/" + kakuroSize + "/" + random.nextInt(getNumberOfFiles(difficulty, kakuroSize)) + ".txt");
    }

    /** @brief Busca un kakuro en fichero a partir de una ruta relativa
     *
     * @param filePath representa una ruta relativa
     * @return el kakuro que se encuentra en la ruta relativa en formato String
     */
    public String getKakuro(String filePath) {
        try {
            StringBuilder content = new StringBuilder();
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** @brief Guarda un kakuro en fichero
     *
     * @param content representa el contenido del kakuro
     * @param diff representa la dificultad del kakuro
     * @param size representa el tamaño del kakuro
     */
    public void saveKakuro(String content, int diff, int size) {
        FileWriter file = null;
        try {
            file = new FileWriter("../data/diff" + diff + "/" + size + "/" + getNumberOfFiles(diff, size));
            PrintWriter pw = new PrintWriter(file);
            pw.print(content);
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /** @brief Mira cuántos ficheros tienen una dificultad y tamaño en concreto
     *
     * Busca cuantos ficheros hay con dificultad=diff y tamaño=size
     * @param diff representa la dificultad de un kakuro
     * @param size representa el tamaño de un kakuro
     * @return la cantidad de ficheros que cumplen las condiciones
     */
    public int getNumberOfFiles(int diff, int size) {
        File folder = new File("../data/diff" + diff + "/" + size);
        File[] listFiles = folder.listFiles();
        return listFiles.length;
    }

}
