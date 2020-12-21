package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/** @file Main.java
 @class Main
 */

/**
 * @brief Pantalla principal que muestra todas las opciones disponibles
 * @author Pau Cuesta Arcos
 */

public class Main {

    private JPanel panel1;
    private JButton playButton;
    private JButton createKakuroButton;
    private JButton generateButton;
    private JButton rankingButton;
    private JButton logoutButton;
    private JButton config;
    private JButton statsButton;
    private JLabel photo;
    private JLabel logo;
    private JButton recordsButton;

    private CtrlUI ctrlUI = CtrlUI.getInstance();

    public Main() {
        $$$setupUI$$$();
        Utils.setMusic();
        Utils.updateVolume(Utils.volume);
        setListeners();

        ctrlUI.resetParameters();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10, 0, 0, 10));

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

        Utils.setButtons(statsButton);
        statsButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        Utils.setButtons(recordsButton);
        recordsButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

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
                Utils.updateVolume(-70);
                ctrlUI.toLogin();
            }
        });
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statistics s = new Statistics();
                s.drawStatistics();
            }
        });

        recordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toRecords();
            }
        });
    }

    public JPanel getDefaultPanel() {
        return panel1;
    }

    private void createUIComponents() {
        photo = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973532));
        Font panel1Font = this.$$$getFont$$$(null, -1, -1, panel1.getFont());
        if (panel1Font != null) panel1.setFont(panel1Font);
        panel1.setMaximumSize(new Dimension(1200, 800));
        panel1.setMinimumSize(new Dimension(1200, 800));
        panel1.setPreferredSize(new Dimension(1200, 800));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1973532));
        panel1.add(panel2, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playButton = new JButton();
        playButton.setBackground(new Color(-15108398));
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setEnabled(true);
        playButton.setFocusPainted(false);
        playButton.setFocusable(false);
        playButton.setText("Jugar");
        panel2.add(playButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
        createKakuroButton = new JButton();
        createKakuroButton.setBorderPainted(false);
        createKakuroButton.setContentAreaFilled(false);
        createKakuroButton.setFocusPainted(false);
        createKakuroButton.setFocusable(false);
        createKakuroButton.setText("Crear Kakuro");
        panel2.add(createKakuroButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
        generateButton = new JButton();
        generateButton.setBorderPainted(false);
        generateButton.setContentAreaFilled(false);
        generateButton.setFocusPainted(false);
        generateButton.setFocusable(false);
        generateButton.setText("Generar");
        panel2.add(generateButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
        rankingButton = new JButton();
        rankingButton.setBorderPainted(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.setFocusPainted(false);
        rankingButton.setFocusable(false);
        rankingButton.setText("Ranking");
        panel2.add(rankingButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        logoutButton = new JButton();
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setFocusable(false);
        logoutButton.setText("Cerrar sesión");
        panel2.add(logoutButton, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 50), null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 40), null, null, 0, false));
        statsButton = new JButton();
        statsButton.setBorderPainted(false);
        statsButton.setContentAreaFilled(false);
        statsButton.setFocusPainted(false);
        statsButton.setFocusable(false);
        statsButton.setText("Estadísticas");
        panel2.add(statsButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
        recordsButton = new JButton();
        recordsButton.setBackground(new Color(-1973532));
        recordsButton.setBorderPainted(false);
        recordsButton.setContentAreaFilled(false);
        recordsButton.setFocusable(false);
        recordsButton.setText("Repositorio");
        panel2.add(recordsButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(50, -1), null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(50, -1), null, 0, false));
        config = new JButton();
        config.setBorderPainted(false);
        config.setContentAreaFilled(false);
        config.setFocusPainted(false);
        config.setFocusable(false);
        config.setText("\uF013");
        panel1.add(config, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, 40), null, 0, false));
        photo.setBackground(new Color(-1973532));
        photo.setText("");
        panel1.add(photo, new GridConstraints(0, 0, 4, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(800, 800), new Dimension(800, 800), new Dimension(800, 800), 0, false));
        final Spacer spacer6 = new Spacer();
        panel1.add(spacer6, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 35), null, 0, false));
        logo = new JLabel();
        logo.setText("");
        panel1.add(logo, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
