package presentation;

import domain.controllers.CtrlPlay;

public class CtrlCreateUI {
    private CtrlUI ctrlUI = CtrlUI.getInstance();

    private static CtrlCreateUI ctrlCreateUI;

    public static CtrlCreateUI getInstance() {
        if (ctrlCreateUI == null)
            ctrlCreateUI = new CtrlCreateUI();
        return ctrlCreateUI;
    }

    public CtrlCreateUI() {

    }


}
