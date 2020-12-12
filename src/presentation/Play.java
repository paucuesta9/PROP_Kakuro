package presentation;

import domain.classes.Exceptions.NoTypeCellException;

import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Timer;

/** @file Play.java
 @brief Clase  <em>Play</em>.
 */

/** @brief Clase Play que carga una partida y contiene las funciones y atributos necesarios para jugar una partida.
 * @author Judith Almoño Gómez y Pau Cuesta Arcos
 */

public class Play {

    /**
     * panel1 es la ventana entera
     */
    private JPanel panel1;
    /**
     * pauseResume es el boton Pausar
     */
    private JButton pauseResume;
    /**
     * exit es el boton Salir
     */
    private JButton exit;
    /**
     * board es el tablero
     */
    private JPanel board;
    /**
     * menu es el conjunto de botones de la derecha
     */
    private JPanel menu;
    private JPanel logotipo;
    /**
     * help1 es el primer boton circular
     */
    private JButton help1;
    /**
     * help2 es el segundo boton circular (?)
     */
    private JButton help2;
    /**
     * timeLogo es el simbolo del cronometro
     */
    private JLabel timeLogo;
    /**
     * time es el texto del tiempo de la partida
     */
    private JLabel time;
    /**
     * config es el boton situado en la esquina superior derecha
     */
    private JButton config;
    /**
     * resolve es el boton Resolver
     */
    private JButton resolve;
    private JLabel logo;
    private JButton lapizButton;
    private JLabel textLapiz;
    /**
     * gameTime representa el tiempo de la partida
     */
    private int gameTime = 0;
    private Timer timer;
    /**
     * paused representa si la partida está pausada o no
     */
    private boolean paused = false;

    private JFrame frame;

    /**
     * rowSize representa el numero de filas del kakuro de la partida
     * columnSize representa el numero de columnas del kakuro de la partida
     */
    private int rowSize, columnSize;
    /**
     * posX representa la posición de una celda respecto a la fila
     * posY representa la posición de una celda respecto a la columna
     */
    private int posX, posY;
    private boolean training;
    private boolean isFinished = false;
    private boolean selfFinished = true;

    private KakuroBoard sg;
    private Component[] components;

    private ImageIcon switchON, switchOFF;
    private boolean pencilON = false;

    private CtrlPlayUI ctrlPlayUI;

    /** @brief Constructora
     *
     * @param kakuro representa el kakuro de la partida
     */
    public Play(String kakuro, boolean training) {
        String[] values = kakuro.split("\n");
        String[] valuesSize = values[0].split(",");
        this.rowSize =  Integer.parseInt(valuesSize[0]);
        this.columnSize = Integer.parseInt(valuesSize[1]);
        this.training = training;

        this.ctrlPlayUI = CtrlPlayUI.getInstance();

        Utils.loadFonts();
        sg = new KakuroBoard(kakuro);
        board.add(sg);
        components = sg.getComponents();
        listeners();
        startTimer();
        setListenerBoard();

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        Utils.setButtons(pauseResume);
        pauseResume.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        Utils.setButtons(exit);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));

        logo.setIcon(Utils.getLogo());

        try {
            BufferedImage buffer = ImageIO.read(new File("resources/images/switch-off.png"));
            Image scaled = buffer.getScaledInstance(90, 50, java.awt.Image.SCALE_SMOOTH);
            switchOFF = new ImageIcon(scaled);
            lapizButton.setIcon(switchOFF);
            textLapiz.setFont(Utils.roboto.deriveFont(30f));
            textLapiz.setForeground(Color.BLACK);

            buffer = ImageIO.read(new File("resources/images/switch-on.png"));
            scaled = buffer.getScaledInstance(90, 50, java.awt.Image.SCALE_SMOOTH);
            switchON = new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Utils.setButtons(help1);
        help1.setFont(Utils.fontAwesome.deriveFont(Font.PLAIN, 40f));
        help1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/elipse-azul.png")));

        Utils.setButtons(help2);
        help2.setFont(Utils.fontAwesome.deriveFont(Font.PLAIN, 40f));
        help2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/elipse-azul.png")));

        timeLogo.setFont(Utils.fontAwesome.deriveFont(Font.PLAIN, 40f));
        timeLogo.setForeground(Color.decode(Utils.colorBlue));

        time.setFont(Utils.digital.deriveFont(Font.PLAIN, 30f));
        time.setForeground(Color.decode(Utils.colorBlue));

        Utils.setButtons(resolve);
        resolve.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));

        board.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));
    }

    /** @brief Listeners
     *
     * Funcionalidades de los botones help1, help2, resolve, pauseResume, exit y config
     */
    private void listeners() {

        lapizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pencilON) {
                    lapizButton.setIcon(switchOFF);
                    textLapiz.setForeground(Color.BLACK);
                    pencilON = false;
                    help1.setEnabled(true);
                    help2.setEnabled(true);
                }
                else {
                    lapizButton.setIcon(switchON);
                    textLapiz.setForeground(Color.decode(Utils.colorDarkBlue));
                    pencilON = true;
                    help1.setEnabled(false);
                    help2.setEnabled(false);
                }
            }
        });

        help1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = ctrlPlayUI.help1(posX, posY);
                if (result != -1) {
                    KakuroWhiteCell w = (KakuroWhiteCell) sg.getComponent(posX * columnSize + posY);
                    if (result == 1) w.setBackground(Utils.colorCorrectCell);
                    else if (result == 0) w.setBackground(Utils.colorIncorrectCell);
                }

            }
        });
        help2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int correctNumber = ctrlPlayUI.help2(posX, posY);
                KakuroWhiteCell w = (KakuroWhiteCell) sg.getComponent(posX * columnSize + posY);
                w.setValue(correctNumber);
                w.setBackground(Utils.colorCorrectCell);
                checkContinousCells(posX, posY);

            }
        });

        resolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kakuroSolved = ctrlPlayUI.getCorrectKakuro();
                sg = new KakuroBoard(kakuroSolved);
                board.removeAll();
                board.add(sg);
                components = sg.getComponents();
                board.validate();
                help1.setEnabled(false);
                help2.setEnabled(false);
                selfFinished = false;
                pauseResume.setEnabled(false);
                finishGame(false);
            }
        });

        pauseResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paused) {
                    board.setVisible(true);
                    startTimer();
                    pauseResume.setText("Pausar");
                    paused = false;
                } else {
                    board.setVisible(false);
                    stopTimer();
                    pauseResume.setText("Continuar");
                    paused = true;
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!training && selfFinished) {
                    Generate2 as = new Generate2("¿Desea guardar la partida?", ctrlPlayUI.getKakuro(), 2);
                    ctrlPlayUI.setTimeToGame(gameTime);
                    as.drawGenerate2(frame);
                }
                else {
                    Main m = new Main();
                    m.drawMain(frame);
                }

            }
        });

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config config = new Config();
                config.drawConfig();
                config.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        boolean[][] pencil = new boolean[rowSize*columnSize][10];
                        for (int i = 0; i < rowSize*columnSize; ++i) {
                            if (sg.getComponent(i) instanceof KakuroWhiteCell) {
                                pencil[i] = ((KakuroWhiteCell) sg.getComponent(i)).getAllPencil();
                            }
                        }
                        try {
                            sg = new KakuroBoard(sg.boardToString());
                        } catch (NoTypeCellException noTypeCellException) {
                            noTypeCellException.printStackTrace();
                        }
                        board.removeAll();
                        board.add(sg);
                        for (int i = 0; i < rowSize*columnSize; ++i) {
                            if (sg.getComponent(i) instanceof KakuroWhiteCell) {
                                ((KakuroWhiteCell) sg.getComponent(i)).setAllPencil(pencil[i]);
                            }
                        }
                        components = sg.getComponents();
                        setListenerBoard();
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });
    }

    /** @brief Listener del tablero
     *
     */
    private void setListenerBoard() {
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof KakuroWhiteCell) {
                KakuroWhiteCell cell = (KakuroWhiteCell) components[i];
                cell.addFocusListener(new FocusListener() {
                    Color color;
                    int value;
                    @Override
                    public void focusGained(FocusEvent e) {
                        color = cell.getBackground();
                        value = cell.getValue();
                        cell.setBackground(Utils.colorSelCell);
                        posX = cell.getPosX();
                        posY = cell.getPosY();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (!cell.getBackground().equals(Utils.colorIncorrectCell) && !cell.getBackground().equals(Utils.colorCorrectCell)) cell.setBackground(color);
                        if (value != cell.getValue() && color.equals(Utils.colorIncorrectCell) && !cell.getBackground().equals(Utils.colorCorrectCell)) cell.setBackground(Utils.colorWhiteCell);
                        if (!pencilON && !isFinished) checkValidityCell(cell, posX, posY);
                        if (!pencilON) checkContinousCells(posX, posY);
                    }
                });
                cell.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (cell.getBackground() != Utils.colorCorrectCell) {
                            cell.requestFocus();
                        }
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
                cell.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        int value = 0;
                        int keyCode = e.getKeyCode();
                        if (keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1) value = 1;
                        if (keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2) value = 2;
                        if (keyCode == KeyEvent.VK_3 || keyCode == KeyEvent.VK_NUMPAD3) value = 3;
                        if (keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) value = 4;
                        if (keyCode == KeyEvent.VK_5 || keyCode == KeyEvent.VK_NUMPAD5) value = 5;
                        if (keyCode == KeyEvent.VK_6 || keyCode == KeyEvent.VK_NUMPAD6) value = 6;
                        if (keyCode == KeyEvent.VK_7 || keyCode == KeyEvent.VK_NUMPAD7) value = 7;
                        if (keyCode == KeyEvent.VK_8 || keyCode == KeyEvent.VK_NUMPAD8) value = 8;
                        if (keyCode == KeyEvent.VK_9 || keyCode == KeyEvent.VK_NUMPAD9) value = 9;
                        if (value != 0) {
                            if (pencilON) {
                                cell.setPencil(value);
                            }
                            else {
                                cell.setBackground(Utils.colorSelCell);
                                cell.setValue(value);
                                ctrlPlayUI.setValue(posX, posY, value);
                                isFinished = ctrlPlayUI.isFinished();
                                if (isFinished) {
                                    help1.setEnabled(false);
                                    help2.setEnabled(false);
                                    finishGame(selfFinished);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void checkContinousCells(int positionX, int positionY) {
        int posXAux = positionX;
        int posYAux = positionY;
        while (true) {
            --posYAux;
            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
            else if (!isFinished){
                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                checkValidityCell(whiteCell, posXAux, posYAux);
            }
        }
        posYAux = positionY;
        while (true) {
            ++posYAux;
            if (posYAux == columnSize) break;
            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
            else if (!isFinished){
                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                checkValidityCell(whiteCell, posXAux, posYAux);
            }
        }
        posYAux = positionY;
        while (true) {
            --posXAux;
            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
            else if (!isFinished){
                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                checkValidityCell(whiteCell, posXAux, posYAux);
            }
        }
        posXAux = positionX;
        while (true) {
            ++posXAux;
            if (posXAux == rowSize) break;
            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
            else if (!isFinished){
                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                checkValidityCell(whiteCell, posXAux, posYAux);
            }
        }
    }

    /** @brief Comprueba que si el tablero se ha acabado
     *
     */
    private void finishGame(boolean selfFinished) {
        stopTimer();
        if (!training) {
            int points = ctrlPlayUI.finishGame(selfFinished);
            if (selfFinished) {
                FinishedGame fg = new FinishedGame(points);
                fg.drawFinishedGame();
                fg.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        Main m = new Main();
                        m.drawMain(frame);
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        }

    }

    /** @brief Pinta las casillas dependiendo de la validez de su valor
     *
     * @param cell representa la celda a pintar
     * @param positionX representa la posición de la celda respecto la fila
     * @param positionY representa la posición de la celda respecto la columna
     */
    private void checkValidityCell(KakuroWhiteCell cell, int positionX, int positionY) {
        if (cell.getBackground() != Utils.colorCorrectCell && cell.getBackground() != Utils.colorIncorrectCell) {
            if (cell.getValue() != 0 && !ctrlPlayUI.checkValidity(positionX, positionY, cell.getValue())) {
                cell.setBackground(Utils.colorIncorrectCell);
            } else cell.setBackground(Utils.colorWhiteCell);
        }

    }

    /** @brief Empieza/reanuda el timer
     *
     */
    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ++gameTime;
                time.setText(Utils.setTimeToLabel(gameTime));
            }
        }, 0, 1000);
    }

    /** @brief Para el timer
     *
     */
    private void stopTimer() {
        timer.cancel();
    }

    /** @brief Inserción del tablero
     *
     */
    private void createUIComponents() {
        board = new JPanel();
    }

    /** @brief Pinta Play
     *
     */
    public void drawPlay(JFrame frame) {
        this.frame = frame;
        frame.setTitle("Main");
        frame.setContentPane(panel1);
        frame.setVisible(true);
    }

}
