package presentation;

import javax.swing.*;
import java.awt.*;

public class Main {

    private JPanel panel1;
    private JButton jugarButton;
    private JButton crearKakuroButton;
    private JButton resolverButton;
    private JButton generarAutomáticamenteButton;
    private JButton rankingButton;
    private JButton cerrarSesiónButton;

    JFrame frame = new JFrame("Main");

    public Main() {
        Utils.loadFonts();
    }

    public void drawMain() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.setResizable(false);
        frame.setVisible(true);
        Utils.center(frame);
    }
}
