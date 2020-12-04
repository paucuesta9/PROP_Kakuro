package presentation;

import java.util.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Timer;

public class Play {
    private JPanel panel1;
    private JButton pauseResume;
    private JButton exit;
    private JPanel board;
    private JPanel menu;
    private JPanel logotipo;
    private JButton help1;
    private JButton help2;
    private JLabel timeLogo;
    private JLabel time;
    private JButton config;

    private static CtrlUI ctrlUI;
    private int gameTime = 0;
    private Timer timer;
    private boolean paused = false;

    private JFrame frame = new JFrame("Play");

    private int rowSize, columnSize;
    private int posX, posY;

    private KakuroBoard sg;
    private Component[] components;

    public Play(String kakuro) {
        String[] values = kakuro.split("\n");
        String[] valuesSize = values[0].split(",");
        this.rowSize =  Integer.parseInt(valuesSize[0]);
        this.columnSize = Integer.parseInt(valuesSize[1]);

        Utils.loadFonts();

        sg = new KakuroBoard(kakuro);
        board.add(sg);
        components = sg.getComponents();
        listeners();
        startTimer();
        //Utils.setMusic();


        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        pauseResume.setFont(Utils.roboto);
        pauseResume.setForeground(Color.WHITE);
        pauseResume.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        pauseResume.setBorderPainted(false);
        pauseResume.setBackground(null);
        pauseResume.setHorizontalTextPosition(JButton.CENTER);
        pauseResume.setVerticalTextPosition(JButton.CENTER);

        exit.setFont(Utils.roboto);
        exit.setForeground(Color.WHITE);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));
        exit.setBorderPainted(false);
        exit.setBackground(null);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);

        help1.setFont(Utils.fontAwesome.deriveFont(Font.PLAIN, 40f));
        help1.setForeground(Color.WHITE);
        help1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/elipse-azul.png")));
        help1.setBorderPainted(false);
        help1.setBackground(null);
        help1.setHorizontalTextPosition(JButton.CENTER);
        help1.setVerticalTextPosition(JButton.CENTER);

        help2.setFont(Utils.fontAwesome.deriveFont(Font.PLAIN, 40f));
        help2.setForeground(Color.WHITE);
        help2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/elipse-azul.png")));
        help2.setBorderPainted(false);
        help2.setBackground(null);
        help2.setHorizontalTextPosition(JButton.CENTER);
        help2.setVerticalTextPosition(JButton.CENTER);

        timeLogo.setFont(Utils.fontAwesome.deriveFont(Font.PLAIN, 40f));
        timeLogo.setForeground(Color.decode(Utils.colorBlue));

        time.setFont(Utils.roboto.deriveFont(Font.PLAIN, 30f));
        time.setForeground(Color.decode(Utils.colorBlue));

        menu.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.decode(Utils.colorDarkBlue)));
    }

    private void listeners() {
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
                        cell.setBackground(Color.decode(Utils.colorBlueSelectedCell));
                        posX = cell.getPosX();
                        posY = cell.getPosY();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (!cell.getBackground().equals(Color.decode(Utils.colorRedCell))) cell.setBackground(color);
                        if (value != cell.getValue() && color.equals(Color.decode(Utils.colorRedCell))) cell.setBackground(Color.WHITE);
                        checkValidityCell(cell, posX, posY);
                        int posXAux = posX;
                        int posYAux = posY;
                        while (true) {
                            --posYAux;
                            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
                            else {
                                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                                checkValidityCell(whiteCell, posXAux, posYAux);
                            }
                        }
                        posYAux = posY;
                        while (true) {
                            ++posYAux;
                            if (posYAux == columnSize) break;
                            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
                            else {
                                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                                checkValidityCell(whiteCell, posXAux, posYAux);
                            }
                        }
                        posYAux = posY;
                        while (true) {
                            --posXAux;
                            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
                            else {
                                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                                checkValidityCell(whiteCell, posXAux, posYAux);
                            }
                        }
                        posXAux = posX;
                        while (true) {
                            ++posXAux;
                            if (posXAux == rowSize) break;
                            if (components[posXAux * columnSize + posYAux] instanceof KakuroBlackCell) break;
                            else {
                                KakuroWhiteCell whiteCell = (KakuroWhiteCell) components[posXAux * columnSize + posYAux];
                                checkValidityCell(whiteCell, posXAux, posYAux);
                            }
                        }
                    }
                });
                cell.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (cell.getBackground() != Color.GREEN) {
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
                            cell.setBackground(Color.decode(Utils.colorBlueSelectedCell));
                            cell.setValue(value);
                            ctrlUI.setValue(posX, posY, value);
                            boolean isFinished = ctrlUI.isFinished();
                            if (isFinished) finishGame();
                        }
                    }
                });
            }
        }

        help1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = ctrlUI.help1(posX, posY);
                if (result != -1) {
                    KakuroWhiteCell w = (KakuroWhiteCell) sg.getComponent(posX * columnSize + posY);
                    if (result == 1) w.setBackground(Color.GREEN);
                    else if (result == 0) w.setBackground(Color.decode(Utils.colorRedCell));
                }

            }
        });
        help2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int correctNumber = ctrlUI.help2(posX,posY);
                KakuroWhiteCell w = (KakuroWhiteCell) sg.getComponent(posX * columnSize + posY);
                w.setValue(correctNumber);
                w.setBackground(Color.GREEN);

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
                frame.dispose();
                Main m = new Main();
                m.drawMain();
            }
        });

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void finishGame() {
        stopTimer();
        ctrlUI.finishGame();
    }

    private void checkValidityCell(KakuroWhiteCell cell, int positionX, int positionY) {
        if (cell.getBackground() != Color.GREEN) {
            if (cell.getValue() != 0 && !ctrlUI.checkValidity(positionX, positionY, cell.getValue())) {
                cell.setBackground(Color.decode(Utils.colorRedCell));
            } else cell.setBackground(Color.WHITE);
        }

    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ++gameTime;
                setTimeToLabel();
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        timer.cancel();
    }

    private void setTimeToLabel() {
        int sec = 0;
        int min = 0;
        int hours = 0;
        String timeString = "";
        if (gameTime >= 60) {
            if (gameTime / 60 >= 60) {
                hours = gameTime / 3600;
                min = (gameTime / 60) % 60;
                sec = (gameTime % 3600) % 60;
            } else {
                min = gameTime / 60;
                sec = gameTime % 60;
            }
        } else {
            sec = gameTime;
        }
        if (hours != 0) {
            if (hours < 10) timeString = timeString.concat("0" + hours);
            else timeString = timeString.concat(String.valueOf(hours));
            timeString = timeString.concat(":");
        }
        if (min < 10) timeString = timeString.concat("0" + min);
        else timeString = timeString.concat(String.valueOf(min));
        timeString = timeString.concat(":");
        if (sec < 10) timeString = timeString.concat("0" + sec);
        else timeString = timeString.concat(String.valueOf(sec));
        time.setText(timeString);
    }

    private void createUIComponents() {
        board = new JPanel();
    }

    public void drawPlay() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Play");
        ctrlUI = CtrlUI.getInstance();
        ctrlUI.startGame(2, 8, 8);
        String kakuro = ctrlUI.getKakuro();
        frame.setContentPane(new Play(kakuro).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}
