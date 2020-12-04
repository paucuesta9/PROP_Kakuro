package domain.classes;

import java.io.FileNotFoundException;

public class WinsRanking extends Ranking {
    /**
     * @param type nos indica el tipo de ranking
     * @brief Constructora de un ranking
     */
    public WinsRanking(String type) throws FileNotFoundException {
        super(type);
    }
}
