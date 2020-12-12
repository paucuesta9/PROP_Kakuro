package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;

/** @file NewGame.java
 @brief Clase  <em>NewGame</em>.
 */

/** @brief Clase NewGame que carga la pantalla de NewGame y contiene las funciones y atributos necesarios para empezar una partida.
 * @author Judith Almoño Gómez
 */

public class NewGame {
    /**
     * panel1 es la ventana entera
     */
    private JPanel panel1;
    /**
     * picLabel es la imagen de un kakuro de la izquierda
     */
    private JLabel picLabel;
    /**
     * config es el boton de configuración situado en la esquina superior derecha
     */
    private JButton config;
    /**
     * easy es el boton Fácil que indica la dificultad del kakuro
     */
    private JButton easy;
    /**
     * easy es el boton Medio que indica la dificultad del kakuro
     */
    private JButton medium;
    /**
     * easy es el boton Difícil que indica la dificultad del kakuro
     */
    private JButton hard;
    /**
     * play es el boton de jugar que inicia la partida
     */
    private JButton play;
    /**
     * exit es el boton de volver situado en la zona inferior de la derecha
     */
    private JButton exit;

    /**
     * numColumn es el campo donde se introduce el número de columnas
     */
    private JTextField numColumn;
    /**
     * textSize es el "Indique el tamaño del tablero a jugar:"
     */
    private JLabel textSize;
    /**
     * rowSizeText es el "Número de filas:"
     */
    private JLabel rowSizeText;
    /**
     * columnSizeText es el "Número de columnas:"
     */
    private JLabel columnSizeText;
    /**
     * numColumn es el campo donde se introduce el número de filas
     */
    private JTextField numRow;
    /**
     * difficulty es el "Indique la dificultad del tablero:"
     */
    private JLabel difficulty;
    /**
     * logo es el ""
     */
    private JLabel logo;

    private JFrame frame;

    /**
     * diff indica la dificultad del kakuro
     */
    private int diff = 1;
    private boolean training;

    private CtrlPlayUI ctrlPlayUI = CtrlPlayUI.getInstance();

    /** @brief Constructora
     *
     */
    public NewGame(boolean training) {

        Utils.loadFonts();
        setListeners();

        this.training = training;

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,54));

        picLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setFont(Utils.roboto.deriveFont(40f));
        logo.setForeground(Color.decode(Utils.colorDarkBlue));

        logo.setIcon(Utils.getLogo());

        textSize.setFont(Utils.roboto);
        textSize.setForeground(Color.BLACK);

        rowSizeText.setForeground(Color.BLACK);
        rowSizeText.setFont(Utils.roboto.deriveFont(18f));

        numRow.setForeground(Color.BLACK);
        numRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        numRow.setFont(Utils.roboto.deriveFont(18f));

        columnSizeText.setForeground(Color.BLACK);
        columnSizeText.setFont(Utils.roboto.deriveFont(18f));

        numColumn.setForeground(Color.BLACK);
        numColumn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        numColumn.setFont(Utils.roboto.deriveFont(18f));

        difficulty.setFont(Utils.roboto);
        difficulty.setForeground(Color.BLACK);

        Utils.setButtons(easy);
        easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));

        Utils.setButtons(medium);
        medium.setForeground(Color.decode(Utils.colorDarkBlue));
        medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));

        Utils.setButtons(hard);
        hard.setForeground(Color.decode(Utils.colorDarkBlue));
        hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));

        Utils.setButtons(play);
        play.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(exit);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

    }

    /** @brief Listeners
     *
     * Funcionalidades de los botones config, easy, medium, hard, play exit y de los campos numRow y numColumn
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

        numRow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                numRow.setEditable(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        numColumn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                numColumn.setEditable(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));
                easy.setForeground(Color.WHITE);
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
                medium.setForeground(Color.decode(Utils.colorDarkBlue));
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                hard.setForeground(Color.decode(Utils.colorDarkBlue));
                diff = 1;
            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                easy.setForeground(Color.decode(Utils.colorDarkBlue));
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -4.png")));
                medium.setForeground(Color.WHITE);
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                hard.setForeground(Color.decode(Utils.colorDarkBlue));
                diff = 2;
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                easy.setForeground(Color.decode(Utils.colorDarkBlue));
                medium.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 7.png")));
                medium.setForeground(Color.decode(Utils.colorDarkBlue));
                hard.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 8.png")));
                hard.setForeground(Color.WHITE);
                diff = 3;
            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numRow.getText().isEmpty() || numColumn.getText().isEmpty()) Utils.showError("No se ha indicado alguno de los tamaños solicitados");
                else {
                    int rowSize = Integer.parseInt(numRow.getText());
                    int columnSize = Integer.parseInt(numColumn.getText());
                    if (rowSize >= 3 && columnSize >= 3) {
                        ctrlPlayUI.startGame(diff, rowSize, columnSize);
                        String kakuro = ctrlPlayUI.getKakuro();
                        Play play = new Play(kakuro, training);
                        play.drawPlay(frame);
                    }
                    else Utils.showError("El tamaño debe ser minimo 3x3");
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
        JFrame frame = new JFrame("New Game");
        frame.setContentPane(new NewGame(false).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    /** @brief Pinta NewGame
     *
     */
    public void drawNewGame(JFrame frame) {
        this.frame = frame;
        frame.setTitle("New Game");
        frame.setContentPane(panel1);
        frame.setVisible(true);
    }
}