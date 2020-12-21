package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** @file CtrlGameData.java
 * @brief Clase <em>CtrlGameData</em>
 *
 */

/** @brief Clase CtrlGameData para guardar en ficheros la información correspondiente a partidas
 * @autor Pau Cuesta Arcos
 */

public class CtrlGameData {

    /**
     * No args constructor for use in serialization
     *
     */
    public CtrlGameData() {
    }

    /** @brief Función que retorna un id nuevo para una partida de un usuario
     *
     * @param username nombre de usuario del jugador para el que se retorna el id
     * @return un entero que representa el identificador de la partida
     */
    public int getNewGameId(String username) {
        File folder = new File("data/players/" + username);
        File[] listFiles = folder.listFiles();
        int size = listFiles.length;
        if (size != 0) {
            String lastFile = listFiles[size-1].getName();
            int i = lastFile.lastIndexOf('.');
            String name = lastFile.substring(0,i);
            return Integer.parseInt(name)+1;
        }
        return 0;
    }

    /** @brief Esta función guarda una partida del usuario
     *
     * @param kakuro string que representa el kakuro a guardar
     * @param username nombre de usuario del jugador
     * @param id entero que representa el identifcador de la partida
     */
    public void saveKakuroGame(String kakuro, String username, int id) {
        FileWriter file = null;
        try {
            file = new FileWriter("data/players/" + username + "/" + id + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(file);
        pw.print(kakuro);
        pw.close();
    }
}
