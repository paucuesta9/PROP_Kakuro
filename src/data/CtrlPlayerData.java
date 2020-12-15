package data;

import com.google.gson.stream.JsonReader;

import java.io.*;

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

    public File[] getListOfPlayers() throws NullPointerException {
        File folder = new File("data/players");
        File[] listFiles = folder.listFiles();
        return listFiles;
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
