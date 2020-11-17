import presentation.CtrlUI;

/**
 * @mainpage Proyecto PROP: Kakuro

En este proyecto de desarrolla una aplicación para jugar, resolver, validar y generar Kakuros.

 */

/** @file Main.java
 @brief Programa principal del proyecto  <em>Kakuro</em>.
 */

/** @brief Clase Main que lanza el juego
 */
public class Main {

    /** @brief Función inicial que lanza la interfaz (en este caso por consola)
     */
    public static void main(String[] args) {
        CtrlUI ui = new CtrlUI();
        ui.run();
    }
}
