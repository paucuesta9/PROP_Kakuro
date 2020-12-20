package domain.classes;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** @file Player.java
 * @brief Clase <em>Player</em>
 */

/** @brief Clase Player que representa un jugador y contiene los atributos necesarios de un jugador
 * @autor -----------------
 */

public class Player {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    private transient Game currentGame;
    @SerializedName("savedGames")
    @Expose
    private List<Game> savedGames = null;
    @SerializedName("config")
    @Expose
    private Config config;

    /**
     * @brief No args constructor for use in serialization
     *
     */
    public Player() {
    }

    /** @brief Creadora de Player con usuario y contraseña
     *
     * @param username nombre de usuario del jugador
     * @param password contraseña del jugador
     */
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.stats = new Stats();
        this.savedGames = new ArrayList<Game>();
        this.config = new Config();
    }

    /** @brief Creadora en la que se pasan todos los atributos necesarios
     *
     * @param password contraseña del jugador
     * @param stats estadísticas del jugador
     * @param savedGames partidas guardadas del jugador
     * @param config configuración del jugador
     * @param username nombre de usuario del jugador
     */
    public Player(String username, String password, Stats stats, Game currentGame, List<Game> savedGames, Config config) {
        super();
        this.username = username;
        this.password = password;
        this.stats = stats;
        this.currentGame = currentGame;
        this.savedGames = savedGames;
        this.config = config;
    }

    /** @brief Getter del nombre de usuario
     *
     * @return string que representa el nombre de usuario del jugador
     */
    public String getUsername() {
        return username;
    }

    /**  @brief Setter del nombre de usuario
     *
     * @param username nombre de usuario que tendrá el jugador
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /** @brief Getter de la contraseña
     *
     * @return string que representa contraseña del jugador
     */
    public String getPassword() {
        return password;
    }

    /** @brief Setter de la contraseña
     *
     * @param password contraseña que tendrá el jugador
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** @brief Getter de las estadísticas
     *
     * @return estadísticas del jugador
     */
    public Stats getStats() {
        return stats;
    }

    /** @brief Setter de las estadísticas
     *
     * @param stats estadísticas que tendrá el jugador
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /** @brief Getter de la partida actual
     *
     * @return la partida actual del jugador
     */
    public Game getCurrentGame() {
        return currentGame;
    }

    /**  @brief Setter de la partida actual
     *
     * @param currentGame la partida actual del juador
     */
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    /** @brief Getter de las partidas guardadas
     *
     * @return Una lista con todas las partidas guardadas del jugador
     */
    public List<Game> getSavedGames() {
        return savedGames;
    }

    /** @brief Setter de las partidas guardadas
     *
     * @param savedGames lista con las partidas guardadas que tendrá el jugador
     */
    public void setSavedGames(List<Game> savedGames) {
        this.savedGames = savedGames;
    }

    /**@brief Getter de la configuración
     *
     * @return la configuración del jugador
     */
    public Config getConfig() {
        return config;
    }

    /** @brief Setter de la configuración
     *
     * @param config la configuración que tendrá el jugador
     */
    public void setConfig(Config config) {
        this.config = config;
    }

    /** @brief Retorna la configuración
     *
     * @return Una lista con la configuración del usuario en formato string
     */
    public ArrayList<String> getConfigToArray() {
        return config.getConfig();
    }

    /** @brief Función que reseta los colores
     *
     */
    public void resetCongifColors() {
        config.resetColors();
    }

    /** @brief Función que añade la partida actual a las partidas guardadas
     *
     */
    public void addSavedGame() {
        savedGames.add(currentGame);
    }

    /** @brief Función que retorna las estadísticas
     *
     * @return una lista de enteros que representan las estadísticas del jugador
     */
    public ArrayList<Integer> getStatsInt() {
        ArrayList<Integer> a = new ArrayList<>();

        a.add(stats.getFinished());
        a.add(stats.getTotal());
        a.add(stats.getHelps());
        a.add(stats.getPoints());
        a.add(stats.getCreated());

        return a;
    }

    /** @brief Función que retorna una partida de la lista de guardadas
     *
     * @param game el número de partida del jugador a retornar
     * @return la partida del jugador número game.
     */
    public Game getGame(int game) {
        for (int i = 0; i < savedGames.size(); ++i) {
            if (savedGames.get(i).getId() == game) {
                Game loadGame = savedGames.get(i);
                savedGames.remove(i);
                return loadGame;
            }
        }
        return null;
    }
}
