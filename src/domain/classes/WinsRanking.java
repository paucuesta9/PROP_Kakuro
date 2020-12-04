package domain.classes;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;

public class WinsRanking extends Ranking {

    public WinsRanking() {
        super();
    }

    public void ordena() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getFinished() - o1.getStats().getFinished();
            }
        });
    }
}
