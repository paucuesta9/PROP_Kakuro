package data;

import com.google.gson.stream.JsonReader;
import domain.classes.Player;

import java.io.*;
import java.util.ArrayList;

public class CtrlPlayerData {

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

    public JsonReader getUser(String username) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("data/players/" + username + ".json"));
        return reader;
    }

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

    public boolean existsPlayer(String username) {
        File file = new File("data/players/" + username + ".json");
        return file.exists();
    }
}
