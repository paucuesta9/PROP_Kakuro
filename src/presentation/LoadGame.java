package presentation;

import com.google.gson.stream.JsonReader;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domain.classes.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;

/** @file LoadGame.java
 @brief Clase  <em>LoadGame</em>.
 */

/**
 * @author Judith Almoño Gómez
 * @brief Clase LoadGame que carga la pantalla de LoagGame y contiene las funciones y atributos necesarios para cargar una partida.
 */

public class LoadGame {
    /**
     * panel1 es la ventana entera
     */
    private JPanel panel1;
    /**
     * logo es el ""
     */
    private JLabel logo;
    /**
     * config es el boton de configuración situado en la esquina superior derecha
     */
    private JButton config;
    /**
     * picLabel es la imagen de un kakuro de la izquierda
     */
    private JLabel picLabel;
    /**
     * play es el boton de jugar que inicia la partida
     */
    private JButton play;
    /**
     * exit es el boton de volver situado en la zona inferior de la derecha
     */
    private JButton exit;
    /**
     * games es el conjunto de partidas cargadas que aparecen en la derecha
     */
    private JScrollPane games;
    /**
     * text es el "Seleccionar partida:"
     */
    private JLabel text;

    private CtrlUI ctrlUI = CtrlUI.getInstance();
    private CtrlPlayUI ctrlPlayUI = CtrlPlayUI.getInstance();

    /**
     * scroll es el conjunto de partidas cargadas dentro del games
     */
    private JPanel scroll;
    /**
     * listGames es el conjunto partidas cargadas con todos sus atributos
     */
    ArrayList<ArrayList<Integer>> listGames;

    /**
     * gameSelected indica si la partida esta seleccionada o no
     */
    private int gameSelected = -1;

    /**
     * @brief Constructora
     */
    public LoadGame() {
        $$$setupUI$$$();
        setListeners();
        loadGames();

        scroll = new JPanel();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10, 0, 0, 20));

        logo.setIcon(Utils.getLogo());

        picLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setFont(Utils.roboto.deriveFont(40f));
        logo.setForeground(Color.decode(Utils.colorDarkBlue));

        text.setForeground(Color.BLACK);
        text.setFont(Utils.roboto.deriveFont(18f));

        Utils.setButtons(play);
        play.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(exit);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        games.setBorder(null);
        games.getVerticalScrollBar().setBackground(Color.white);
        games.getVerticalScrollBar().setUI(new Scroll());
        games.getVerticalScrollBar().setUnitIncrement(16);

    }

    /**
     * @brief Cargar partidas empezadas
     * <p>
     * Muestra las partidas que hay empezadas y carga la que el usuario quiere jugar
     */
    private void loadGames() {
        listGames = ctrlPlayUI.getStartedGames();
        scroll.setLayout(new BoxLayout(scroll, BoxLayout.Y_AXIS));
        scroll.setBackground(Color.decode("#E1E4E2"));

        if (listGames.size() == 0) {
            JLabel noGames = new JLabel("No hay partidas");
            noGames.setFont(Utils.roboto.deriveFont(20f));
            noGames.setForeground(Color.RED);
            play.setEnabled(false);
            scroll.add(noGames);
        }
        for (int i = 0; i < listGames.size(); ++i) {
            int id = listGames.get(i).get(0);
            int time = listGames.get(i).get(1);
            int diff = listGames.get(i).get(2);
            int rowSize = listGames.get(i).get(5);
            int columnSize = listGames.get(i).get(6);

            String timeString = Utils.setTimeToLabel(time);
            GameBrief gb = new GameBrief(id, rowSize, columnSize, diff, timeString);
            gb.panel1.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    gb.panel1.requestFocus();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            int finalI = i;
            gb.panel1.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    gb.changeImage(1);
                    gameSelected = finalI;

                }

                @Override
                public void focusLost(FocusEvent e) {
                    gb.changeImage(0);


                }
            });
            scroll.add(gb.panel1);
            scroll.validate();
            scroll.add(new JSeparator(SwingConstants.VERTICAL));
        }

        games.setViewportView(scroll);
        games.validate();

    }

    /**
     * @brief Listeners necesarios
     * <p>
     * Funcionalidades de los botones config, play y exit.
     */
    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config c = new Config();
                c.drawConfig();
            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameSelected == -1) Utils.showError("No se ha seleccionado ninguna partida");
                else {
                    ArrayList<Integer> game = listGames.get(gameSelected);
                    ctrlPlayUI.setGame(game.get(0));
                    String kakuro = ctrlPlayUI.getKakuro();
                    ctrlUI.toPlay(kakuro, false);
                }

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toMain();
            }
        });
    }

    public JPanel getDefaultPanel() {
        return panel1;
    }

    /**
     * @brief Inserción Imagen Kakuro
     */
    private void createUIComponents() {
        picLabel = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
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
        panel1.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973022));
        panel1.setMaximumSize(new Dimension(1200, 800));
        panel1.setMinimumSize(new Dimension(1200, 800));
        panel1.setPreferredSize(new Dimension(1200, 800));
        picLabel.setRequestFocusEnabled(true);
        picLabel.setText("");
        panel1.add(picLabel, new GridConstraints(0, 0, 3, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(800, 800), new Dimension(800, 800), new Dimension(800, 800), 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(40, -1), new Dimension(40, -1), new Dimension(40, -1), 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1973022));
        panel1.add(panel2, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        text = new JLabel();
        text.setText("Seleccione partida:");
        panel2.add(text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 25), new Dimension(-1, 25), new Dimension(-1, 25), 0, false));
        exit = new JButton();
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.setText("Volver");
        panel2.add(exit, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        play = new JButton();
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.setText("Jugar");
        panel2.add(play, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        games = new JScrollPane();
        games.setBackground(new Color(-1973022));
        games.setForeground(new Color(-4473925));
        games.setHorizontalScrollBarPolicy(31);
        games.setOpaque(false);
        panel2.add(games, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 350), new Dimension(252, 350), new Dimension(252, 350), 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 75), new Dimension(-1, 75), new Dimension(-1, 75), 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        config = new JButton();
        config.setBorderPainted(false);
        config.setContentAreaFilled(false);
        config.setEnabled(true);
        config.setFocusPainted(false);
        Font configFont = this.$$$getFont$$$(null, -1, -1, config.getFont());
        if (configFont != null) config.setFont(configFont);
        config.setMargin(new Insets(0, 0, 0, 50));
        config.setText("\uF013");
        panel1.add(config, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        logo = new JLabel();
        logo.setText("");
        panel1.add(logo, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
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
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
