package domain.controllers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domain.classes.Exceptions.PlayerExists;
import domain.classes.Exceptions.WrongPasswordException;
import domain.classes.Player;

import java.io.FileNotFoundException;

/** @file CtrlPlayer.java
 * @brief Clase <em>CtrlPlayer</em>
 */

/** @brief Controlador de jugador
 * @author ----------------
 */
public class CtrlPlayer {

    /**
     * Instancia del controlador de dominio
     */
    private CtrlDomain cd;

    /**
     * Instancia del jugadora actual
     */
    private Player currentPlayer;
    /**
     * gson contiene información de los jugadores en formato json
     */
    private Gson gson;

    /** @brief Creadora de CtrlPlayer
     *
     * @param cd controlador de dominio
     */
    public CtrlPlayer(CtrlDomain cd) {
        this.cd = cd;
        this.gson = new Gson();
    }

    /** @brief Función para que el usuario incie sesión
     *
     * @param username nombre de usuario del jugador
     * @param password contraseña del jugador
     * @throws FileNotFoundException en caso de que no exista un usuario con ese usuario
     * @throws WrongPasswordException en caso de que la contraseña no coincida con la correcta
     */
    public void login(String username, String password) throws FileNotFoundException, WrongPasswordException {
        JsonReader reader = cd.getUser(username);
        currentPlayer = gson.fromJson(reader, Player.class);
        if (!password.equals(currentPlayer.getPassword())) {
            currentPlayer = null;
            throw new WrongPasswordException();
        }
    }

    /** @brief Función para que el usuario se registre
     *
     * @param username nombre de usuario del jugador
     * @param password contraseña del jugador
     * @throws PlayerExists en caso de que ya exista un usuario con el mismo nombre de usuario
     */
    public void signUp(String username, String password) throws PlayerExists {
        currentPlayer = new Player(username, password);
        String playerJSON = gson.toJson(currentPlayer);
        cd.saveNewPlayer(username, playerJSON);
    }

    /** @brief función que retorna al jugador actual
     *
     * @return el jugador actual
     */
    public Player getPlayer() {
        return currentPlayer;
    }
}
