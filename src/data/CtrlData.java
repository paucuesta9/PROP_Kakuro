package data;

import com.google.gson.stream.JsonReader;
import domain.classes.Game;

import java.io.*;
import java.util.ArrayList;
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
}
