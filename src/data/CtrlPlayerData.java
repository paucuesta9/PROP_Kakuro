package data;

import com.google.gson.stream.JsonReader;
import domain.classes.Player;

import java.io.*;
import java.util.ArrayList;

/** @file CtrlPlayerData.java
 * @brief Clase <em>CtrlPlayerData</em>
 *
 */

/** @brief Clase CtrlPlayerData para guardar información sobre los jugadores en ficheros
 * @author ------------------
 */
public class CtrlPlayerData {
    /**
     * No args constructor for use in serialization
     *
     */
    public CtrlPlayerData() {
    }

    /** @brief Mira cuántos players hay guardados
     *
     * Busca cuantos jugadores se han registrado
     * @return la cantidad jugadores registrados
     */
    public int getNumberOfPlayers() throws NullPointerException {
        File folder = new File("data/players");
        File[] listFiles = folder.listFiles();
        return listFiles.length;
    }

    /** @brief Esta función retorna una lista de los jugadores
     *
     * @return un vector con todos los jugadores en formato json
     * @throws NullPointerException
     */

    public JsonReader[] getListOfPlayers() throws NullPointerException {
        File folder = new File("data/players");
        File[] listFiles = folder.listFiles();
        int n = getNumberOfPlayers();
        JsonReader[] read = new JsonReader[n/2];
        int cont = 0;
        for(int i = 0; i < n; ++i) {
            if(listFiles[i].isFile()) {
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(listFiles[i]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                read[cont] = reader;
                ++cont;
                //players.add(gson.fromJson(reader, Player.class));
            }
        }
        return read;
    }

    /** Esta función retorna el archivo json del usuario correspondiente
     *
     * @param username nombre de usuario del que se ha de retornar el archivo json
     * @return un json con la información del usuario correspondiente
     * @throws FileNotFoundException en caso de que no exista un usuario con ese nombre de usuario
     */
    public JsonReader getUser(String username) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("data/players/" + username + ".json"));
        return reader;
    }

    /** @brief Guarda al jugador
     *
     * @param username nombre de usuario del jugador a guardar
     * @param playerJSON contenido del fichero asociado al jugador
     */
    public void savePlayer(String username, String playerJSON) {
        boolean exists = existsPlayer(username);
        if (exists) {
            try {
                FileWriter file = new FileWriter("data/players/" + username + ".json");
                PrintWriter pwSol = new PrintWriter(file);
                pwSol.print(playerJSON);
                pwSol.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter file = new FileWriter("data/players/" + username + ".json");
                PrintWriter pwSol = new PrintWriter(file);
                pwSol.print(playerJSON);
                pwSol.close();
                File folderSol = new File("data/players/" + username);
                folderSol.mkdir();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** @brief Esta función nos indica si existe un jugador con el nombre de usuario pasado por parámetro
     *
     * @param username nombre de usuario a comprobar si ya existe
     * @return cierto en caso de que ya exista un usuario con nombre de usuario username, falso en caso contrario
     */
    public boolean existsPlayer(String username) {
        File file = new File("data/players/" + username + ".json");
        return file.exists();
    }
}
