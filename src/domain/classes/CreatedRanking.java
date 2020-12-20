package domain.classes;

import domain.controllers.CtrlDomain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** @file CreatedRanking.java
 @brief Clase  <em>CreatedRanking</em>.
 */


/** @brief Clase CreatedRanking que representa el ranking por partidas creadas e indica como ordenar a los jugadores según ese criterio
 * @author ----------------
 */

public class CreatedRanking extends Ranking {

    /** @brief Creadora de CreatedRanking
     *
     * @param d controlador de dominio
     */
    public CreatedRanking(CtrlDomain d ) {
        super(d);
    }

    /** @brief función que indica como se han de ordenar los jugadores según las partidas creadas
     *
     */
    public void ordena() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getCreated() - o1.getStats().getCreated();
            }
        });
    }

}
