package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AskNewContinue {
    private JPanel panel1;
    private JLabel photo;
    private JButton config;
    private JButton loadButton;
    private JButton newButton;
    private JButton volverButton;
    private JLabel question;
    private JButton training;
    private JLabel logo;

    private CtrlUI ctrlUI = CtrlUI.getInstance();

    public AskNewContinue() {
        Utils.loadFonts();
        setListeners();

        photo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,12));

        question.setFont(Utils.roboto.deriveFont(18f));

        logo.setIcon(Utils.getLogo());

        Utils.setButtons(loadButton);
        Utils.setButtons(newButton);
        Utils.setButtons(volverButton);
        Utils.setButtons(training);

        training.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-naranja.png")));
        loadButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/bottonOkError.png")));
        newButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-cyan-little.png")));
        volverButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
    }

    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config c = new Config();
                c.drawConfig();
            }
        });
        training.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toNewGame(true);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toLoadGame();
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toNewGame(false);
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toMain();
            }
        });
    }

    public JPanel getDefaultPanel() {
        return panel1;
    }

    private void createUIComponents() {
        photo = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }
}
