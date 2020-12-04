package domain.classes;

/** @file Cell.java
 * @brief Clase  <em>Cell</em>.
 */


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import data.CtrlData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/** @brief Clase Cell que contiene los métodos necesarios para cualquier tipo de ranking
 * @author Pol Vallespí Soro
 */
public abstract class Ranking {
    private CtrlData data;
    private String type;
    protected List<Player> players;
    private Gson gson;


    public Ranking() {
        data = CtrlData.getInstance();
        gson = new Gson();
        listOfPlayers();
    }

    /**@brief nos devuelve el tipo de ranking
     *
     * @return el tipo de ranking
     */
    public String getType() {
        return type;
    }

    public void listOfPlayers()  {
        players = new ArrayList<>();
        File[] playersList = data.getListOfPlayers();
        int n = data.getNumberOfPlayers();
        for(int i = 0; i < n; ++i) {
            if(playersList[i].isFile()) {
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(playersList[i]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                players.add(gson.fromJson(reader, Player.class));
            }

        }
    }

    public abstract void ordena();

    public List<Player> getList(String s) {
        ordena();
        return players;
    }
}

/* Faltan por hacer las subclases de ranking: por "victorias", puntos y generados.
 */