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
public class Ranking {
    private CtrlData data;
    private String type;
    private static List<Player> players;
    private Gson gson;
    /**@brief Constructora de un ranking
     *
     * @param type nos indica el tipo de ranking
     */
    public Ranking(String type) throws FileNotFoundException {
        data = CtrlData.getInstance();
        gson = new Gson();
        this.type = type;
        listOfPlayers();
    }

    public Ranking() throws FileNotFoundException {
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

    public void listOfPlayers() throws FileNotFoundException {
        players = new ArrayList<>();
        File[] playersList = data.getListOfPlayers();
        int n = data.getNumberOfPlayers();
        for(int i = 0; i < n; ++i) {
            if(playersList[i].isFile()) {
                JsonReader reader = new JsonReader(new FileReader(playersList[i]));
                players.add(gson.fromJson(reader, Player.class));
                //System.out.println(players.get(players.size()-1).getUsername());
            }

        }
    }


    public static void ordenaPerPunts() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getPoints() - o1.getStats().getPoints();
            }
        });
    }

    public static void ordenaPerWins() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getFinished() - o1.getStats().getFinished();
            }
        });
    }

    public static void ordenaPerCreades() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getCreated() - o1.getStats().getCreated();
            }
        });
    }

    public static List<Player> getList(String s) {
        if( s == "puntos") ordenaPerPunts();
        else if( s == "wins") ordenaPerWins();
        else ordenaPerCreades();
        return players;
    }
}

/* Faltan por hacer las subclases de ranking: por "victorias", puntos y generados.
 */