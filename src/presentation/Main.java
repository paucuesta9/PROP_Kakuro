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

    }

    public void drawMain() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.setResizable(false);
        frame.setVisible(true);
        center();
    }

    private void center() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width) / 2 - 600;
        int y = (screenSize.height) / 2 - 450;
        frame.setLocation(x, y);
    }
}
