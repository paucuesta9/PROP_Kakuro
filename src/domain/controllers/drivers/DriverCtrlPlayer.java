package domain.controllers.drivers;

import domain.classes.Exceptions.WrongPasswordException;
import domain.controllers.CtrlPlayer;
import domain.controllers.stubs.CtrlDomain;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class DriverCtrlPlayer {

    private static Scanner reader = new Scanner((System.in));

    private static void testLogin() {
        System.out.println("Existe un usuario con username 1234 y contrasena 1234, y otro con usuario polFeo y contrasena 1234");
        System.out.println("Introduzca un nombre de usuario");
        String username = reader.next();
        System.out.println("Introduzca una contraseña");
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

    private static void testSignUp() {
        System.out.println("Introduzca un nombre de usuario");
        String username = reader.next();
        System.out.println("Introduzca una contraseña");
        String password = reader.next();

        CtrlDomain cdStub = new CtrlDomain();
        CtrlPlayer ctrlPlayer = new CtrlPlayer(cdStub);
        try {
            ctrlPlayer.signUp(username, password);
        } catch (Exception e) {
            System.out.println("slhkdaskldsa");
        }
    }

    /** @brief Función principal
     *
     * Indica las opciones que hay para testear
     */
    public static void main(String[] args) {
        System.out.println("Opciones: \n1. Empezar una partida \n 2. Ayuda para el valor del usuario \n 3. Ayuda para el valor correcto de una celda blanca \n 4. Salir");
        int value = readNumber();
        while (value != 4) {
            int x, y;
            switch (value) {
                case 1:
                    System.out.println();
                    System.out.println("Se llama a la Creadora");
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Se llama a Login");
                    testLogin();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Se llama a signUp");
                    testSignUp();
                    break;
                default:
                    System.out.println("El numero introducido es incorrecto");
                    break;
            }
            System.out.println("\nOpciones: \n1. Empezar una partida \n 2. Ayuda para el valor del usuario \n 3. Ayuda para el valor correcto de una celda blanca \n 4. Salir");
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
