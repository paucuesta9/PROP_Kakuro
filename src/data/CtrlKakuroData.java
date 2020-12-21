package data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.Random;

/** @file CtrlKakuroData.java
 * @brief Clase <em>CtrlKakuroData</em>
 *
 */

/**
 * @brief Clase CtrlKakuroData para guardar y conseguir información de fichero relacionada con kakuros.
 * @author Pau Cuesta Arcos
 */
public class CtrlKakuroData {

    /**
     * No args constructor for use in serialization
     *
     */
    public CtrlKakuroData() {
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
            numFile = getNumberOfKakuros(difficulty, kakuroSizeRow, kakuroSizeColumn);
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
     * @return un entero que representa el número del kakuro
     */
    public int saveKakuro(String content, String solution, int diff, int sizeRow, int sizeColumn) {
        FileWriter file = null;
        try {
            int number;
            try {
                number = getNumberOfKakuros(diff, sizeRow, sizeColumn);
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
    public int getNumberOfKakuros(int diff, int sizeRow, int sizeColumn) throws NullPointerException {
        File folder = new File("data/diff" + diff + "/" + sizeRow + "_" + sizeColumn);
        File[] listFiles = folder.listFiles();
        return listFiles.length;
    }

    /** @brief Retorna el número de kakuros de una cierta dificultad
     *
     * @param diff dificultad para la que se cuenta el número de kakuros
     * @return un entero que representa el número de kakuros de dificultad diff
     */
    public int getNumberKakurosTotal(int diff) {
        int kakuros = 0;
        File folder = new File("data/diff"+diff);
        File[] listFiles = folder.listFiles();
        int n = listFiles.length;
        for(int i = 0; i < n; ++i) {
            kakuros+= listFiles[i].listFiles().length;
        }
        return kakuros;
    }

    /** @brief Esta función obtiene una lista de kakuros con información.
     *
     * @return un JsonArray con información sobre todos los kakuros de la base de datos
     *
     *  La información es sobre su dificultad, tamaño, id, tiempo mínimo en resolverlo, el jugador que lo ha resuleto en ese tiempo y los puntos máximos conseguidos.
     */
    public JsonArray getListOfKakuros() {
        int n = getNumberKakurosTotal(1);
        int m = getNumberKakurosTotal(2);
        int o = getNumberKakurosTotal(3);
        String[][] t = new String[n+m+o][4];
        int cont = 0;
        File folder = new File("data/diff1");
        File[] listFiles = folder.listFiles();
        int nkak = listFiles.length;
        JsonArray arr = new JsonArray();
        for(int i = 0; i < nkak; ++i) {
            File[] files = listFiles[i].listFiles(); //p.e 3_3
           for(int j = 0; j < files.length; ++j) {
               JsonObject obj = new JsonObject();
               obj.addProperty("diff", "1");
               obj.addProperty("size",listFiles[i].getName());
               obj.addProperty("id",files[j].getName() );
               obj.addProperty("minTime",0);
               obj.addProperty("player", "");
               obj.addProperty("maxPoints", 0);
               arr.add(obj);
            }
        }
        folder = new File("data/diff2");
        listFiles = folder.listFiles();
        nkak = listFiles.length;
        for(int i = 0; i < nkak; ++i) {
            File[] files = listFiles[i].listFiles();
            for(int j = 0; j < files.length; ++j) {
                JsonObject obj = new JsonObject();
                obj.addProperty("diff", "2");
                obj.addProperty("size",listFiles[i].getName());
                obj.addProperty("id",files[j].getName() );
                obj.addProperty("minTime",0);
                obj.addProperty("player", "");
                obj.addProperty("maxPoints", 0);
                arr.add(obj);
            }
        }
        folder = new File("data/diff3");
        listFiles = folder.listFiles();
        nkak = listFiles.length;
        for(int i = 0; i < nkak; ++i) {
            File[] files = listFiles[i].listFiles();
            for(int j = 0; j < files.length; ++j) {
                JsonObject obj = new JsonObject();
                obj.addProperty("diff", "3");
                obj.addProperty("size",listFiles[i].getName());
                obj.addProperty("id",files[j].getName() );
                obj.addProperty("minTime",0);
                obj.addProperty("player", "");
                obj.addProperty("maxPoints", 0);
                arr.add(obj);
            }
        }
        return arr;
    }
}
