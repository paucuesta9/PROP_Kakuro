package presentation;

import domain.classes.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rankings implements ListSelectionListener {
    private JFrame frame;
    private JPanel menu;
    private JButton volver;
    //private JList list;
    private JButton config;
    private JPanel panel;
    private JLabel text;
    private JScrollPane scrollPane;
    private JButton creadas;
    private JButton wins;
    private JButton puntos;
    private JPanel panel2;
    private JPanel ButtonPan;
    private JTable table;
    private JLabel logo;
    private CtrlUI ctrlUI;
    private Font fontAwesome, roboto;

    public Rankings() {
        ctrlUI = new CtrlUI();
        loadFonts();
        menu.setBackground(null);
        panel2.setBackground(null);

        table.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setIcon(Utils.getLogo());

        //rankings

        String [] titles = {"Posición", "Nombre", "Puntos"}; //Nombre de cada columna
        List<Player> p = ctrlUI.getListOfPlayers("puntos");
        int n = p.size();
        String[][] t = new String[n][3];
        for(int i = 0; i < n; ++i) {
            String num = String.valueOf(i+1);
            Player a = p.get(i);
            t[i][0] = num;
            t[i][1] = a.getUsername();
            String s = String.valueOf(a.getStats().getPoints());
            t[i][2] = s;
        }
        showTable(t, titles);

        config.setFont(fontAwesome);
        config.setForeground(Color.decode("#00204A"));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,10));

        volver.setFont(roboto);
        volver.setForeground(Color.WHITE);
        volver.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        volver.setBorderPainted(false);
        volver.setBackground(null);
        volver.setHorizontalTextPosition(JButton.CENTER);
        volver.setVerticalTextPosition(JButton.CENTER);

        ButtonPan.setBackground(null);

        puntos.setFont(roboto);
        puntos.setForeground(Color.WHITE);
        puntos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));
        puntos.setBorderPainted(false);
        puntos.setBackground(null);
        puntos.setText("Puntos");
        puntos.setHorizontalTextPosition(JButton.CENTER);
        puntos.setVerticalTextPosition(JButton.CENTER);

        wins.setFont(roboto);
        wins.setForeground(Color.BLACK);
        wins.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Partidas_ganadas.png")));
        wins.setBorderPainted(false);
        wins.setBackground(null);
        //wins.setText("Acabada");
        wins.setHorizontalTextPosition(JButton.CENTER);
        wins.setVerticalTextPosition(JButton.CENTER);

        creadas.setFont(roboto);
        creadas.setForeground(Color.BLACK);
        creadas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
        creadas.setBorderPainted(false);
        creadas.setBackground(null);
        creadas.setText("Creados");
        creadas.setHorizontalTextPosition(JButton.CENTER);
        creadas.setVerticalTextPosition(JButton.CENTER);

        text.setFont(roboto);
        text.setForeground(Color.BLACK);
        text.setBackground(null);


        puntos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.setForeground(Color.WHITE);
                puntos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 6.png")));
                wins.setForeground(Color.BLACK);
                wins.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Partidas_ganadas.png")));
                creadas.setForeground(Color.BLACK);
                creadas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                //Construir la tabla con la lista conseguida
                List<Player> points = new ArrayList<>();
                points = ctrlUI.getListOfPlayers("puntos");
                int n = points.size();
                String[][] t = new String[n][3];
                for(int i = 0; i < n; ++i) {
                    String num = String.valueOf(i+1);
                    Player a = points.get(i);
                    t[i][0] = num;
                    t[i][1] = a.getUsername();
                    String s = String.valueOf(a.getStats().getPoints());
                    t[i][2] = s;
                }
                showTable(t, titles);
                String [] titles = {"Posición", "Nombre", "Puntos"};
                showTable(t, titles);
            }
        });
        wins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.setForeground(Color.BLACK);
                puntos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                wins.setForeground(Color.WHITE);
                wins.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ganadas-azul.png")));
                creadas.setForeground(Color.BLACK);
                creadas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo -5.png")));
                //Construir la tabla con la lista conseguida
                List<Player> wins = new ArrayList<>();
                wins = ctrlUI.getListOfPlayers("wins");
                int n = wins.size();
                String[][] t = new String[n][3];
                for(int i = 0; i < n; ++i) {
                    String num = String.valueOf(i+1);
                    Player a = wins.get(i);
                    t[i][0] = num;
                    t[i][1] = a.getUsername();
                    String s = String.valueOf(a.getStats().getFinished());
                    t[i][2] = s;
                }
                String [] titles = {"Posición", "Nombre", "Acabados"};
                showTable(t, titles);
            }
        });
        creadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.setForeground(Color.BLACK);
                puntos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Trazado 1.png")));
                wins.setForeground(Color.BLACK);
                wins.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Partidas_ganadas.png")));
                creadas.setForeground(Color.WHITE);
                creadas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Rectángulo 8.png")));
                //Construir la tabla con la lista conseguida
                List<Player> created = new ArrayList<>();
                created = ctrlUI.getListOfPlayers("creadas");
                int n = created.size();
                String[][] t = new String[n][3];
                for(int i = 0; i < n; ++i) {
                    String num = String.valueOf(i+1);
                    Player a = created.get(i);
                    t[i][0] = num;
                    t[i][1] = a.getUsername();
                    String s = String.valueOf(a.getStats().getCreated());
                    t[i][2] = s;
                }
                String [] titles = {"Posición", "Nombre", "Creados"};
                showTable(t, titles);
            }
        });


        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main m = new Main();
                m.drawMain(frame);
            }
        });

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config conf = new Config();
                conf.drawConfig();
            }
        });
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

    void showTable(String[][] t, String[] titles) {

        table = new JTable(t,titles) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(600);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setFont(new Font("Verdana",Font.BOLD,20));
        scrollPane.setViewportView(table);
        scrollPane.setVisible(true);
    }

    public void drawRanking(JFrame frame) {
        this.frame = frame;
        frame.setTitle("Rankings");
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main(String [] args)  {
        JFrame frame = new JFrame("Rankings");
        frame.setContentPane(new Rankings().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setVisible(true);
        Utils.center(frame);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
