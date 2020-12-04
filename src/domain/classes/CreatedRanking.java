package domain.classes;

import java.io.FileNotFoundException;

public class CreatedRanking extends Ranking {
    /**
     * @param type nos indica el tipo de ranking
     * @brief Constructora de un ranking
     */
    public CreatedRanking(String type) throws FileNotFoundException {
        super(type);
    }
}
