package domain.classes;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CreatedRanking extends Ranking {


    public CreatedRanking() {
        super();
    }

    public void ordena() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getStats().getCreated() - o1.getStats().getCreated();
            }
        });
    }

}
