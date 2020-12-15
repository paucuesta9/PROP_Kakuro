package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private JPanel panel1;
    private JButton playButton;
    private JButton createKakuroButton;
    private JButton generateButton;
    private JButton rankingButton;
    private JButton logoutButton;
    private JButton config;
    private JLabel photo;
    private JLabel logo;

    private CtrlUI ctrlUI = CtrlUI.getInstance();

    public Main() {
        Utils.setMusic();
        Utils.updateVolume(Utils.volume);
        Utils.loadFonts();
        setListeners();

        ctrlUI.resetParameters();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        photo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setIcon(Utils.getLogo());

        Utils.setButtons(playButton);
        playButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(createKakuroButton);
        createKakuroButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        Utils.setButtons(generateButton);
        generateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        Utils.setButtons(rankingButton);
        rankingButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        Utils.setButtons(logoutButton);
        logoutButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));

    }

    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config config = new Config();
                config.drawConfig();
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toAskNewContinue();
            }
        });

        createKakuroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toCreate();
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toGenerate();
            }
        });

        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toRankings();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toLogin();
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
