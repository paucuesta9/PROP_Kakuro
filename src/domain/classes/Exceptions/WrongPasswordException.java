package domain.classes.Exceptions;

public class WrongPasswordException extends Exception {
    public static final long serialVersionUID = 1L;

    /* Mètode creador de la classe, el qual invoca a super que  és la cosntructura
     * de la classe Exception */
    public WrongPasswordException() {
        super();
    }
}
