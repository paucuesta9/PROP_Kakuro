package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** @file Stats.java
 * @brief Clase <em>Stats</em>
 *
 */

/** @brief Clase que representa las estadísticas de un jugador
 * @autor ---------------------
 */
public class Stats {
    /**
     * finished representa el número de partidas acabadas
     */
    @SerializedName("finished")
    @Expose
    private int finished;
    /**
     * total representa el número de partidas que el jugador ha empezado a jugar
     */
    @SerializedName("total")
    @Expose
    private int total;
    /**
     * helps representa el número de ayudas que el jugador ha utilizado
     */
    @SerializedName("helps")
    @Expose
    private int helps;
    /**
     * points representa el número de puntos que el jugador tiene
     */
    @SerializedName("points")
    @Expose
    private int points;
    /**
     * created representa el número de kakuros creados por el jugador
     */
    @SerializedName("created")
    @Expose
    private int created;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Stats() {
    }

    /** @brief Constructora con todos los atributos necesarios
     * 
     * @param total número de partidas que el usuario ha empezado
     * @param created número de kakuros creados
     * @param minTimeEasy tiempo mínimo para un kakuro fácil
     * @param finished número de kakuros acabados
     * @param helps número de ayudas utilizadas
     * @param minTimeMedium tiempo mínimo para un kakuro mediano
     * @param minTimeDifficult tiempo mínimo para un kakuro difícil
     * @param points número de puntos
     */
    public Stats(int finished, int total, int helps, int points, int created, int minTimeEasy, int minTimeMedium, int minTimeDifficult) {
        super();
        this.finished = finished;
        this.total = total;
        this.helps = helps;
        this.points = points;
        this.created = created;
    }

    /**
     * @brief Getter de finished
     * @return entero que representa el número de partidas acabadas
     */
    public int getFinished() {
        return finished;
    }

    /** @brief Setter de finished
     *
     * @param finished valor que se sumará al número de partidas acabadas actual
     */
    public void setFinished(int finished) {
        this.finished += finished;
    }

    /** @brief Getter de total
     *
     * @return entero que representa el número de partidas que el usuario ha empezado
     */
    public int getTotal() {
        return total;
    }

    /** @brief Setter de total
     *
     * @param total valor que se sumará al número de partidas empezadas
     */
    public void setTotal(int total) {
        this.total += total;
    }

    /** @brief Getter de helps
     *
     * @return entero que representa el número de ayudas utilizadas
     */
    public int getHelps() {
        return helps;
    }

    /** @brief Setter de helps
     *
     * @param helps valor que se sumará al número de ayudas utilizadas
     */

    public void setHelps(int helps) {
        this.helps += helps;
    }

    /** @brief Getter de points
     *
     * @return entero que representa el número de puntos
     */
    public int getPoints() {
        return points;
    }

    /** @brief Setter de points
     *
     * @param points valor que sumará al número de puntos
     */
    public void setPoints(int points) {
        this.points += points;
    }

    /** @brief Getter de created
     *
     * @return entero que representa el número de kakuros creados
     */
    public int getCreated() {
        return created;
    }

    /** @brief Setter de created
     *
     * @param created valor que se sumará al número de kakuros creados
     */
    public void setCreated(int created) {
        this.created += created;
    }

}
