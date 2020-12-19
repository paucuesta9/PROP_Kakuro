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

    public void getListOfKakuros() {
        saveKakuroRecord( ctrlKakuroData.getListOfKakuros());
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
