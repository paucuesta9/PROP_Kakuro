package domain.classes;

/** @file Ranking.java
 * @brief Clase  <em>Ranking</em>.
 */


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import data.CtrlData;
import domain.controllers.CtrlDomain;
import domain.controllers.CtrlValidate;
import presentation.Play;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/** @brief Clase Cell que contiene los métodos necesarios para cualquier tipo de ranking
 * @author Pol Vallespí Soro
 */

public abstract class Ranking {
    /**
     * Lista con todos los jugadores
     */
    protected List<Player> players;
    /**
     * Gson con información de los jugadores
     */
    private Gson gson;
    /**
     * Instancia del controlador de dominio
     */
    private CtrlDomain domain;


    /** @brief Creadora de Ranking
     *
     * @param d controlador de dominio
     */
    public Ranking(CtrlDomain d) {
        domain = d;
        gson = new Gson();
        listOfPlayers();
    }


    /** @brief Copia todos los jugadores del controlador de dominio a su atributo de jugadores
     *
     */
    public void listOfPlayers()  {
        JsonReader[] read = domain.getListOfPlayers();
        players = new ArrayList<>();
        for(int i = 0; i < read.length; ++i) {
            players.add(gson.fromJson(read[i], Player.class));
        }

    }

    /** @brief Función que cada subclase implementará para ordenar a los jugadores según algún criterio
     *
     */
    public abstract void ordena();

    /** @brief Función que retorna una lista de jugadores ordenada según el criterio de clasificación
     *
     * @param s string que representa si se han de ordenar los jugadores por puntos, victorias o creadas
     * @return Matriz donde se guarda la posición del jugador, su usuario y el número de puntos, victorias o credas.
     */
    public String[][] getList(String s) {
        ordena();
        int n = players.size();
        String[][] t = new String[n][3];
        for(int i = 0; i < n; ++i) {
            String num = String.valueOf(i + 1);
            Player a = players.get(i);
            t[i][0] = num;
            t[i][1] = a.getUsername();
            String p = new String();
            if(s == "puntos") p = String.valueOf(a.getStats().getPoints());
            else if( s == "wins") p = String.valueOf(a.getStats().getFinished());
            else p = String.valueOf(a.getStats().getCreated());
            t[i][2] = p;
        }
        return t;
    }
}

/* Faltan por hacer las subclases de ranking: por "victorias", puntos y generados.
 */