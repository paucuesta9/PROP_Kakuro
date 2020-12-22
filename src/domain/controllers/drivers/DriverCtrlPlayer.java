package domain.controllers.drivers;

import domain.classes.Exceptions.WrongPasswordException;
import domain.controllers.CtrlPlayer;
import domain.controllers.stubs.CtrlDomain;

import java.io.FileNotFoundException;
import java.util.Scanner;

/** @file DriverCtrlPlayer.java
 * @class DriverCtrlPlayer
 *
 */

/** @brief Clase DriverCtrlPlayer que comprueba la correctividad de las funciones de la clase CtrlPlayer
 * @author Alvaro Armada Ruiz
 *
 */

public class DriverCtrlPlayer {

    private static Scanner reader = new Scanner((System.in));

    /** @brief Comprueba que la funcion de Login (inicio de sesión)funciona correctamente
     *
     */
    private static void testLogin() {
        System.out.println("Existe un usuario con username 1234 y contrasena 1234, y otro con usuario polFeo y contrasena 1234");
        System.out.println("Introduzca un nombre de usuario");
        String username = reader.next();
        System.out.println("Introduzca una contrasena");
        String password = reader.next();


        CtrlDomain cdStub = new CtrlDomain();
        try {
            CtrlPlayer ctrlPlayer = new CtrlPlayer(cdStub);
            ctrlPlayer.login(username, password);
            System.out.println("Se ha iniciado sesion");
        } catch (WrongPasswordException e) {
            System.out.println("Contrasena incorrecta");
        } catch (FileNotFoundException e) {
            System.out.println("Usuario no encontrado");
        }
    }

    /** @brief Comprueba que la funcion signUp (registro) funciona correctamente
     *
     */
    private static void testSignUp() {
        System.out.println("Introduzca un nombre de usuario");
        String username = reader.next();
        System.out.println("Introduzca una contrasena");
        String password = reader.next();

        CtrlDomain cdStub = new CtrlDomain();
        CtrlPlayer ctrlPlayer = new CtrlPlayer(cdStub);
        try {
            ctrlPlayer.signUp(username, password);
        } catch (Exception e) {
            System.out.println("Usuario ya existente");
        }
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n 1. Login \n 2. Sign Up \n 3. Salir");
        int value = readNumber();
        while (value != 3) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a Login");
                    testLogin();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a signUp");
                    testSignUp();
                    break;
                default:
                    System.out.println("El numero introducido es incorrecto");
                    break;
            }
            System.out.println("Opciones: \n 1. Login \n 2. Sign Up \n 3. Salir");
            value = readNumber();
        }
        System.exit(0);
    }


    /** @brief Lee un numero de consola
     *
     * @return el número leído
     */
    public static int readNumber() {
        int number = 0;
        number = reader.nextInt();
        return number;
    }

}
