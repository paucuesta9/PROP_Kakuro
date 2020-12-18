package domain.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/** @file Game.java
 @brief Clase  <em>Game</em>.
 */

/** @brief Clase Game que identifica y contiene los atributos necesarios de una partida
 * @author Judith Almoño Gómez
 */

public class Game {

    /**
     * id representa el id de la partida
     */
    @SerializedName("id")
    @Expose
    private int id;
    /**
     * time representa el tiempo de la partida
     */
    @SerializedName("time")
    @Expose
    private int time;
    /**
     * points representa los puntos de la partida
     */
    @SerializedName("points")
    @Expose
    private int points;
    /**
     * kakuroId representa el Id del kakuro de la partida
     */
    @SerializedName("kakuroId")
    @Expose
    private int kakuroId;
    /**
     * kakuro representa el kakuro de la partida
     */
    private transient Kakuro kakuro;
    /**
     * rowSize representa el número de filas del kakuro de la partida
     */
    @SerializedName("rowSize")
    @Expose
    private int rowSize;
    /**
     * columnSize representa el número de columnas del kakuro de la partida
     */
    @SerializedName("columnSize")
    @Expose
    private int columnSize;
    /**
     * diff representa la dificultad del kakuro de la partida
     */
    @SerializedName("diff")
    @Expose
    private int diff;
    /**
     * helps representa una lista de las ayudas utilizadas en la partida
     */
    @SerializedName("helps")
    @Expose
    private List<String> helps = null;

    /** @brief Constructora por defecto
     * No args constructor for use in serialization
     *
     */
    public Game() {
    }

    /** @brief Constructora con valores
     *
     * @param id representa el id de la partida
     * @param time representa el tiempo de la partida
     * @param points representa los puntos de la partida
     * @param kakuroId representa el id del kakuro de la partida
     */
    public Game(int id, int time, int points, int kakuroId, int rowSize, int columnSize, int diff, List<String> helps) {
        super();
        this.id = id;
        this.time = time;
        this.points = points;
        this.kakuroId = kakuroId;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.diff = diff;
        this.helps = helps;
    }

    /** @brief Getter del Id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /** @brief Setter del Id
     *
     * @param id representa el id de la partida
     */
    public void setId(int id) {
        this.id = id;
    }

    /** @brief Getter del time
     *
     * @return time
     */
    public int getTime() {
        return time;
    }

    /** @brief Setter del time
     *
     * @param time representa el tiempo de la partida
     */
    public void setTime(int time) {
        this.time = time;
    }

    /** @brief Getter de points
     *
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /** @brief Setter de points
     *
     * @param points representan los puntos de la partida
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /** @brief Getter de kakuroId
     *
     * @return kakuroId
     */
    public int getKakuroId() {
        return kakuroId;
    }

    /** @brief Setter de kakuroId
     *
     * @param kakuroId representa el id del kakuro de la partida
     */
    public void setKakuroId(int kakuroId) {
        this.kakuroId = kakuroId;
    }

    /** @brief Getter de kakuro
     *
     * @return kakuro
     */
    public Kakuro getKakuro() {
        return kakuro;
    }

    /** @brief Setter de kakuro
     *
     * @param kakuro representa el kakuro de la partida
     */
    public void setKakuro(Kakuro kakuro) {
        this.kakuro = kakuro;
    }

    /** @brief Getter de rowSize
     *
     * @return número de filas
     */
    public int getRowSize() {
        return rowSize;
    }

    /** @brief Setter de rowSize
     *
     * @param rowSize representa el numero de filas del kakuro de la partida
     */
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    /** @brief Getter de columnSize
     *
     * @return número de columnas
     */
    public int getColumnSize() {
        return columnSize;
    }

    /** @brief Setter de columnSize
     *
     * @param columnSize representa el numero de columnas del kakuro de la partida
     */
    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    /** @brief Gettr de diff
     *
     * @return la dificultad del kakuro de la partida
     */
    public int getDiff() {
        return diff;
    }

    /** Setter de diff
     *
     * @param diff representa la dificultad del kakuro de la partida
     */
    public void setDiff(int diff) {
        this.diff = diff;
    }

    /** @brief Getter de helps
     *
     * @return la lista de las ayudas
     */
    public List<String> getHelps() {
        return helps;
    }

    /** @brief Setter de helps
     *
     * @param helps representa una lista de ayudas
     */
    public void setHelps(List<String> helps) {
        this.helps = helps;
    }

    /** @brief Añadir una ayuda a helps
     *
     * @param posX representa la posición de la celda respecto a las filas
     * @param posY representa la posición de la celda respecto a las columnas
     * @param isCorrect representa si la ayuda ha dado correcto o incorrecto
     */
    public void addHelp(int posX, int posY, int isCorrect) {
        for (int i = 0; i < helps.size(); ++i) {
            if (helps.get(i).contains(posX + "_" + posY)) {
                helps.remove(i);
            }
        }
        helps.add(posX + "_" + posY + "_" + isCorrect);
    }

    /** @brief Lista los atributos de una partida
     *
     * @return la lista de los atributos de una partida
     */
    public ArrayList<Integer> gameToArrayList () {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(id);
        list.add(time);
        list.add(diff);
        list.add(points);
        list.add(kakuroId);
        list.add(rowSize);
        list.add(columnSize);
        return list;
    }
}


