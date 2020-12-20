package domain.classes;

import domain.controllers.CtrlDomain;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;

/** @file PointsRanking.java
 @brief Clase  <em>PointsRanking</em>.
 */


/** @brief Clase PointsRanking que representa el ranking por puntos e indica como ordenar a los jugadores según ese criterio
 * @author ----------------
 */

public class PointsRanking extends Ranking {

    /** @brief Creadora de PointsRanking
     *
     * @param d controlador de dominio
     */
    public PointsRanking(CtrlDomain d) {
        super(d);
    }

    /** @brief función que indica como se han de ordenar los jugadores según las partidas creadas
     *
     */
    public void ordena() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getPoints() - o1.getStats().getPoints();
            }
        });
    }
}
