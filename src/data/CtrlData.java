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
    private CtrlPlayerData ctrlPlayerData;
    private CtrlGameData ctrlGameData;
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

    public String searchKakuro (int difficulty, int kakuroSizeRow, int kakuroSizeColumn) throws IOException {
        return ctrlKakuroData.searchKakuro(difficulty, kakuroSizeRow, kakuroSizeColumn);
    }

    public String getKakuro(String filePath) throws IOException {
        return ctrlKakuroData.getKakuro(filePath);
    }

    public int saveKakuro(String content, String solution, int diff, int sizeRow, int sizeColumn) {
        return ctrlKakuroData.saveKakuro(content, solution, diff, sizeRow, sizeColumn);
    }

    public int getNumberOfPlayers() throws NullPointerException {
        return ctrlPlayerData.getNumberOfPlayers();
    }

    /*public File[] getListOfPlayers() throws NullPointerException {
        return ctrlPlayerData.getListOfPlayers();
    }*/

    public JsonReader[] getListOfPlayers() throws NullPointerException {
        return ctrlPlayerData.getListOfPlayers();
    }

    public JsonReader getUser(String username) throws FileNotFoundException {
        return ctrlPlayerData.getUser(username);
    }


    public void savePlayer(String username, String playerJSON) {
        ctrlPlayerData.savePlayer(username, playerJSON);
    }

    public boolean existsPlayer(String username) {
        return ctrlPlayerData.existsPlayer(username);
    }

    public int getNewGameId(String username) {
        return ctrlGameData.getNewGameId(username);
    }

    public void saveKakuroGame(String kakuro, String username, int id) {
        ctrlGameData.saveKakuroGame(kakuro, username, id);
    }

    public String[][] getListOfKakuros() {
        return ctrlKakuroData.getListOfKakuros();
    }

    public String getThisKakuro(String s) {
        String k = "";
        try {
            k = ctrlKakuroData.getKakuro(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return k;
    }

    public JsonArray getkakuroRecord() {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("data/kakuros.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonArray l = new Gson().fromJson(reader, JsonArray.class);
        sortJson(l);
        return l;
    }

    public void sortJson(JsonArray l) {
// Create a JsonArray to a List view instance
        List<JsonObject> list = new ArrayList<>();
        for(int i = 0; i < l.size(); ++i) {
            list.add(l.get(i).getAsJsonObject());
        }
// Sorting the jsonElements object
        list.sort((e1, e2) -> {
            final String i1 = e1.getAsJsonObject().get("diff").getAsString();
            final String i2 = e2.getAsJsonObject().get("diff").getAsString();
            final int i3 = CharSequence.compare(i1, i2);
            if(!i1.equals(i2)) return i3;
            final String i4 = e1.getAsJsonObject().get("size").getAsString();
            final String i5 = e2.getAsJsonObject().get("size").getAsString();
            final int i6 = CharSequence.compare(i4, i5);
            if(!i4.equals(i5) ) return i6;
            final String i7 = e1.getAsJsonObject().get("id").getAsString();
            final String i8 = e2.getAsJsonObject().get("id").getAsString();
            final int i9 = CharSequence.compare(i7, i8);
            return i9;
            /*final int i10 = Integer.compare(i3, i6);
            return Integer.compare(i10, i9);*/
        });
    }

}
