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

    private CtrlUI ctrlUI;
    private Font fontAwesome, roboto;
    private int gameTime = 0;
    private Timer timer;
    private boolean paused = false;

    private static JFrame frame;

    private int rowSize, columnSize;
    private int posX, posY;

    private KakuroBoard sg;
    private Component[] components;

    public Play(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        ctrlUI = CtrlUI.getInstance();
        ctrlUI.startGame(1, rowSize, columnSize);

        String kakuro = ctrlUI.getKakuro();
        sg = new KakuroBoard(kakuro);
        board.add(sg);
        components = sg.getComponents();

        loadFonts();
        listeners();
        startTimer();
        setMusic();

        config.setFont(fontAwesome);
        config.setForeground(Color.decode("#00204A"));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        pauseResume.setFont(roboto);
        pauseResume.setForeground(Color.WHITE);
        pauseResume.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        pauseResume.setBorderPainted(false);
        pauseResume.setBackground(null);
        pauseResume.setHorizontalTextPosition(JButton.CENTER);
        pauseResume.setVerticalTextPosition(JButton.CENTER);

        exit.setFont(roboto);
        exit.setForeground(Color.WHITE);
        exit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));
        exit.setBorderPainted(false);
        exit.setBackground(null);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);

        help1.setFont(fontAwesome.deriveFont(Font.PLAIN, 40f));
        help1.setForeground(Color.WHITE);
        help1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/elipse-azul.png")));
        help1.setBorderPainted(false);
        help1.setBackground(null);
        help1.setHorizontalTextPosition(JButton.CENTER);
        help1.setVerticalTextPosition(JButton.CENTER);

        help2.setFont(fontAwesome.deriveFont(Font.PLAIN, 40f));
        help2.setForeground(Color.WHITE);
        help2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/elipse-azul.png")));
        help2.setBorderPainted(false);
        help2.setBackground(null);
        help2.setHorizontalTextPosition(JButton.CENTER);
        help2.setVerticalTextPosition(JButton.CENTER);

        timeLogo.setFont(fontAwesome.deriveFont(Font.PLAIN, 40f));
        timeLogo.setForeground(Color.decode("#1976D2"));

        time.setFont(roboto.deriveFont(Font.PLAIN, 30f));
        time.setForeground(Color.decode("#1976D2"));
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
                        cell.setBackground(Color.decode("#64b5f6"));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (!cell.getBackground().equals(Color.decode("#e53935"))) cell.setBackground(color);
                        if (value != cell.getValue() && color.equals(Color.decode("#e53935"))) cell.setBackground(Color.WHITE);
                        if (cell.getValue() != 0 && !ctrlUI.checkValidity(posX, posY, cell.getValue())) {
                            cell.setBackground(Color.decode("#e53935"));
                        }
                    }
                });
                cell.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (cell.getBackground() != Color.GREEN) {
                            posX = cell.getPosX();
                            posY = cell.getPosY();
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
                            cell.setBackground(Color.decode("#64b5f6"));
                            cell.setValue(value);
                            ctrlUI.setValue(posX, posY, value);
                            boolean isFinished = ctrlUI.isFinished();
                            if (isFinished) ctrlUI.finishGame();
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
                    else if (result == 0) w.setBackground(Color.decode("#e53935"));
                    else if (result == -2) w.setBackground(Color.GRAY);
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

    private void setMusic() {
        InputStream audioSrc = getClass().getClassLoader().getResourceAsStream("MUSICA2.wav");
        AudioInputStream as1 = null;
        try {
            as1 = AudioSystem.getAudioInputStream(audioSrc);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioFormat af = as1.getFormat();
        Clip clip1 = null;
        try {
            clip1 = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        DataLine.Info info = new DataLine.Info(Clip.class, af);

        Line line1 = null;
        try {
            line1 = AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        if ( ! line1.isOpen() )
        {
            try {
                clip1.open(as1);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clip1.loop(Clip.LOOP_CONTINUOUSLY);
            FloatControl gainControl =
                    (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip1.start();
        }
    }

    private void loadFonts() {
        BufferedInputStream myStream = null;
        try {
            myStream = new BufferedInputStream(new FileInputStream("resources/fonts/fa-solid.ttf"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        Font ttfBase = null;
        try {
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (FontFormatException fontFormatException) {
            fontFormatException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        fontAwesome = ttfBase.deriveFont(Font.PLAIN, 30f);

        try {
            myStream = new BufferedInputStream(new FileInputStream("resources/fonts/Roboto-Bold.ttf"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        try {
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (FontFormatException fontFormatException) {
            fontFormatException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        roboto = ttfBase.deriveFont(Font.PLAIN, 20f);
    }

    private void createUIComponents() {
        board = new JPanel();
    }

    public static void main(String [] args) {
        frame = new JFrame("Play");
        frame.setContentPane(new Play(9, 9).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.setResizable(false);
        frame.setVisible(true);
        center();
    }

    private static void center() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width) / 2 - 600;
        int y = (screenSize.height) / 2 - 450;
        frame.setLocation(x, y);
    }
}
