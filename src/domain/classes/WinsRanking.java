package domain.classes;

import domain.controllers.CtrlDomain;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;

/** @file WinsRanking.java
 @brief Clase  <em>WinsRanking</em>.
 */


/** @brief Clase WinsRanking que representa el ranking por victorias e indica como ordenar a los jugadores según ese criterio
 * @author ----------------
 */

public class WinsRanking extends Ranking {

    /** @brief Creadora de WinsRanking
     *
     * @param d controlador de dominio
     */
    public WinsRanking(CtrlDomain d) {
        super(d);
    }

    /** @brief función que indica como se han de ordenar los jugadores según su número de victorias
     *
     */
    public void ordena() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getFinished() - o1.getStats().getFinished();
            }
        });
    }
}
