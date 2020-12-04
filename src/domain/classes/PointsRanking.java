package domain.classes;

import java.io.FileNotFoundException;

public class PointsRanking extends Ranking {
    /**
     * @param type nos indica el tipo de ranking
     * @brief Constructora de un ranking
     */

    public PointsRanking(String type) throws FileNotFoundException {
        super(type);
    }
}
