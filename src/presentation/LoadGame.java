package presentation;

import com.google.gson.stream.JsonReader;
import domain.classes.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.synth.SynthScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/** @file LoadGame.java
 @brief Clase  <em>LoadGame</em>.
 */

/** @brief Clase LoadGame que carga la pantalla de LoagGame y contiene las funciones y atributos necesarios para cargar una partida.
 * @author Judith Almoño Gómez
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

    private JFrame frame;
    private CtrlUI ctrlUI = CtrlUI.getInstance();

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

    /** @brief Constructora
     *
     */
    public LoadGame() {

        scroll = new JPanel();

        Utils.loadFonts();
        setListeners();
        loadGames();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,20));

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

    /** @brief Cargar partidas empezadas
     *
     * Muestra las partidas que hay empezadas y carga la que el usuario quiere jugar
     */
    private void loadGames() {
        listGames = ctrlUI.getStartedGames();
        scroll.setLayout(new BoxLayout(scroll, BoxLayout.Y_AXIS));

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
            GameBrief gb = new GameBrief (id, rowSize, columnSize, diff, timeString);
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

    /** @brief Listeners necesarios
     *
     * Funcionalidades de los botones config, play y exit.
     */
    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//                Config c = new Config();
//                c.drawConfig();
            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameSelected == -1) Utils.showError("No se ha seleccionado ninguna partida");
                else {
                    ArrayList<Integer> game = listGames.get(gameSelected);
                    ctrlUI.setGame(game.get(0));
                    String kakuro = ctrlUI.getKakuro();
                    frame.dispose();
                    Play p = new Play(kakuro, false);
                    p.drawPlay(frame);
                }

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main();
                main.drawMain(frame);
            }
        });
    }

    /** @brief Inserción Imagen Kakuro
     *
     */
    private void createUIComponents() {
        picLabel = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
    }

    /** @brief Función inicial que lanza la pantalla de LoadGames
     *
     */
    public static void main(String [] args) {
        JFrame frame = new JFrame("Load Game");
        frame.setContentPane(new LoadGame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    /** @brief Pinta LoadGames
     *
     */
    public void drawLoadGame(JFrame frame) {
        this.frame = frame;
        frame.setTitle("Load Game");
        frame.setContentPane(panel1);
        frame.setVisible(true);
    }
}
