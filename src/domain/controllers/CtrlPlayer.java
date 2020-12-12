package domain.controllers;

public class CtrlPlayer {
    private static CtrlPlayer ctrlPlayer;

    public static CtrlPlayer getInstance() {
        if (ctrlPlayer == null)
            ctrlPlayer = new CtrlPlayer();
        return ctrlPlayer;
    }

    public CtrlPlayer() {

    }
}
