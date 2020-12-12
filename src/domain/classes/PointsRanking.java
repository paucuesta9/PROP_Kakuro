package domain.classes;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;

public class PointsRanking extends Ranking {

    public PointsRanking() {
        super();
    }

    public void ordena() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getPoints() - o1.getStats().getPoints();
            }
        });
    }
}
