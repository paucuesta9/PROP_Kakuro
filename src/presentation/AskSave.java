package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AskSave {
    private JPanel panel1;
    private JLabel question;
    private JButton síButton;
    private JButton noButton;
    private JButton volverButton;
    private JButton config;
    private JPanel board;

    private JFrame frame = new JFrame("Save");

    public AskSave(String text, KakuroBoard kBoard) {
        Utils.loadFonts();
        setListeners();

        board.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        board.add(kBoard);
        board.validate();
        
        question.setFont(Utils.roboto.deriveFont(18f));
        question.setText(text);

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        Utils.setButtons(síButton);
        Utils.setButtons(noButton);
        Utils.setButtons(volverButton);

        síButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/bottonOkError.png")));
        noButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-cyan-little.png")));
        volverButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
    }

    private void setListeners() {
        síButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Save Kakuro
                frame.dispose();
                Main m = new Main();
                m.drawMain();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main m = new Main();
                m.drawMain();
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main m = new Main();
                m.drawMain();
            }
        });
    }

    public void drawAskSave() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frameMain = new JFrame("Save");
        frameMain.setContentPane(new AskSave("Pregunta", new KakuroBoard("9,9\n")).panel1);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(1200,800);
        frameMain.setResizable(false);
        Utils.center(frameMain);
        frameMain.setVisible(true);
    }

    private void createUIComponents() {
        board = new JPanel();
    }
}
