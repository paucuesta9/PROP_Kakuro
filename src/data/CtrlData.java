package data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import domain.classes.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    /**
     * ctrlPlayerData es la instancia de ctrlPlayuerData
     */
    private CtrlPlayerData ctrlPlayerData;
    /**
     * ctrlGameData es la instancia del ctrlGameData
     */
    private CtrlGameData ctrlGameData;
    /**
     * ctrlKakuroData es la instancia del ctrlKakuroData
     */
    private CtrlKakuroData ctrlKakuroData;

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
        ctrlPlayerData = new CtrlPlayerData();
        ctrlGameData = new CtrlGameData();
        ctrlKakuroData = new CtrlKakuroData();
    }

    /** @bief Busca un kakuro con las características pedidas
     *
     * @param difficulty dificultad del kakuro
     * @param kakuroSizeRow número de filas del kakuro
     * @param kakuroSizeColumn número de columnas del kakuro
     * @return un string que representa el kakuro encontrado
     * @throws IOException en caso de que no exista un kakuro de esas características
     */
    public String searchKakuro (int difficulty, int kakuroSizeRow, int kakuroSizeColumn) throws IOException {
        return ctrlKakuroData.searchKakuro(difficulty, kakuroSizeRow, kakuroSizeColumn);
    }

    /** @brief Esta función retorna el kakuro de la ruta establecida
     *
     * @param filePath ruta en la que se encuentra el kakuro
     * @return un string que representa el kakuro
     * @throws IOException en caso de que no se encuentre el kakuro
     */
    public String getKakuro(String filePath) throws IOException {
        return ctrlKakuroData.getKakuro(filePath);
    }

    /** @brief Esta función guarda el kakuro y su solución en fichero
     *
     * @param content contenido del kakuro
     * @param solution solución del kakuro
     * @param diff dificultad del kakuro
     * @param sizeRow número de filas del kakuro
     * @param sizeColumn número de columnas del kakuro
     * @return un entero que representa el número del kakuro
     */
    public int saveKakuro(String content, String solution, int diff, int sizeRow, int sizeColumn) {
        return ctrlKakuroData.saveKakuro(content, solution, diff, sizeRow, sizeColumn);
    }

    /** @brief Esta función retorna el número de jugadores
     *
     * @return un entero que representa el número de jugadores
     * @throws NullPointerException en caso de que no existan jugadores
     */
    public int getNumberOfPlayers() throws NullPointerException {
        return ctrlPlayerData.getNumberOfPlayers();
    }

    /*public File[] getListOfPlayers() throws NullPointerException {
        return ctrlPlayerData.getListOfPlayers();
    }*/

    /** @brief Esta función retorna una lista de los jugadores
     *
     * @return un vector con todos los jugadores en formato json
     * @throws NullPointerException
     */
    public JsonReader[] getListOfPlayers() throws NullPointerException {
        return ctrlPlayerData.getListOfPlayers();
    }

    /** Esta función retorna el archivo json del usuario correspondiente
     *
     * @param username nombre de usuario del que se ha de retornar el archivo json
     * @return un json con la información del usuario correspondiente
     * @throws FileNotFoundException en caso de que no exista un usuario con ese nombre de usuario
     */
    public JsonReader getUser(String username) throws FileNotFoundException {
        return ctrlPlayerData.getUser(username);
    }

    /** @brief Guarda al jugador
     *
     * @param username nombre de usuario del jugador a guardar
     * @param playerJSON contenido del fichero asociado al jugador
     */
    public void savePlayer(String username, String playerJSON) {
        ctrlPlayerData.savePlayer(username, playerJSON);
    }

    /** @brief Esta función nos indica si existe un jugador con el nombre de usuario pasado por parámetro
     *
     * @param username nombre de usuario a comprobar si ya existe
     * @return cierto en caso de que ya exista un usuario con nombre de usuario username, falso en caso contrario
     */
    public boolean existsPlayer(String username) {
        return ctrlPlayerData.existsPlayer(username);
    }

    /** @brief Esta función retorna un id válido para una partida
     *
     * @param username nombre del usuario que necesita el id
     * @return un entero que representa un id válido para una partida del usuario username
     */
    public int getNewGameId(String username) {
        return ctrlGameData.getNewGameId(username);
    }

    /** @brief Esta función guarda una partida del usuario
     *
     * @param kakuro string que representa el kakuro a guardar
     * @param username nombre de usuario del jugador
     * @param id entero que representa el identifcador de la partida
     */
    public void saveKakuroGame(String kakuro, String username, int id) {
        ctrlGameData.saveKakuroGame(kakuro, username, id);
    }

    /**
     * @brief Esta función actualiza los records de kakuros
     */
    public void getListOfKakuros() {
        saveKakuroRecord( ctrlKakuroData.getListOfKakuros());
    }

    /** @brief Busca un kakuro a partir de una ruta relativa
     *
     * @param s ruta relativa donde se encuentra el kakuro
     * @return string que representa el kakuro encontrado
     */
    public String getThisKakuro(String s) {
        String k = "";
        try {
            k = ctrlKakuroData.getKakuro(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return k;
    }

    /** @brief Función que actualiza los records de kakuros
     *
     * @param record información de records a guardar en el fichero json correspondiente
     */
    public void saveKakuroRecord(JsonArray record) {
        FileWriter file = null;
        try {
            file = new FileWriter("data/kakuros.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(file);
        pw.print(new Gson().toJson(record));
        pw.close();
    }

    /** @brief Función que obtiene la información de records del fichero json correspondiente
     *
     * @return un JsonArray con información sobre los records de kakuro
     */

    public JsonArray getkakuroRecord() {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("data/kakuros.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonArray l = new Gson().fromJson(reader, JsonArray.class);
        String newObject = new Gson().toJson(sortJson(l));
        l = new Gson().fromJson(newObject, JsonArray.class);
        return l;
    }

    /** @brief Esta función ordenada los kakuros en formato json según dificultad y tamaño.
     *
     * @param l información de los kakuros
     * @return una lista de json ordenados
     */
    public List<JsonObject> sortJson(JsonArray l) {
        List<JsonObject> list = new ArrayList<>();
        for(int i = 0; i < l.size(); ++i) {
            list.add(l.get(i).getAsJsonObject());
        }
        list.sort((e1, e2) -> {
            final int i1 = Integer.parseInt(e1.getAsJsonObject().get("diff").getAsString());
            final int i2 = Integer.parseInt(e2.getAsJsonObject().get("diff").getAsString());
            final int i3 = Integer.compare(i1, i2);
            if (i1 != i2) return i3;
            String size1 = e1.getAsJsonObject().get("size").getAsString();
            String[] sizes1 = size1.split("_");
            int size1_1 = Integer.parseInt(sizes1[0]);
            int size1_2 = Integer.parseInt(sizes1[1]);
            String size2 = e2.getAsJsonObject().get("size").getAsString();
            String[] sizes2 = size2.split("_");
            int size2_1 = Integer.parseInt(sizes2[0]);
            int size2_2 = Integer.parseInt(sizes2[1]);
            final int i6 = Integer.compare(size1_1, size2_1);
            if (size1_1 != size2_1) return i6;
            final int i10 = Integer.compare(size1_2, size2_2);
            if (size1_2 != size2_2) return i10;
            String id1 = e1.getAsJsonObject().get("id").getAsString();
            String id2 = e2.getAsJsonObject().get("id").getAsString();
            int intId1 = Integer.parseInt(id1.substring(0, id1.indexOf(".")));
            int intId2 = Integer.parseInt(id2.substring(0, id2.indexOf(".")));
            final int i9 = Integer.compare(intId1, intId2);
            return i9;
        });
        return list;
    }

}
