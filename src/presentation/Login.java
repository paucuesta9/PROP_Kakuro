package presentation;

import domain.classes.Exceptions.WrongPasswordException;
import domain.controllers.CtrlDomain;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login {
    private JPasswordField contraseñaPasswordField;
    private JTextField usuarioTextField;
    private JButton iniciarSesiónButton;
    private JLabel label;
    private JPanel panel2;
    private JButton config;
    private JLabel toRegister;
    private JLabel logo;


    private CtrlUI ctrlUI;
    private JFrame frame = new JFrame("Login");

    boolean mod = false;
    boolean mod1 = false;

    public Login() {
        Utils.loadFonts();
        setListeners();
        ctrlUI = CtrlUI.getInstance();

        toRegister.setFont(Utils.roboto.deriveFont(12f));

        logo.setIcon(Utils.getLogo());

        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10,0,0,20));

        usuarioTextField.setForeground(Color.BLACK);
        usuarioTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        usuarioTextField.setFont(Utils.roboto.deriveFont(18f));

        contraseñaPasswordField.setForeground(Color.BLACK);
        contraseñaPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        contraseñaPasswordField.setFont(Utils.roboto.deriveFont(18f));
        contraseñaPasswordField.setEchoChar((char)0);

        iniciarSesiónButton.setFont(Utils.roboto);
        iniciarSesiónButton.setForeground(Color.WHITE);
        iniciarSesiónButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        iniciarSesiónButton.setBorderPainted(false);
        iniciarSesiónButton.setBackground(null);
        iniciarSesiónButton.setHorizontalTextPosition(JButton.CENTER);
        iniciarSesiónButton.setVerticalTextPosition(JButton.CENTER);
    }
    private void setListeners() {

            usuarioTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    iniciarSesiónButton.doClick();
                }
            });

            contraseñaPasswordField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    iniciarSesiónButton.doClick();
                }
            });

            config.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Config config = new Config();
                    config.drawConfig();
                }
            });

            usuarioTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usuarioTextField.getText().equals("Usuario") && !mod) {
                    usuarioTextField.setText("");
                }
            }
        });
            usuarioTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(usuarioTextField.getText().equals("")) {
                    usuarioTextField.setText("Usuario");
                    mod = false;
                }
                else mod = true;
            }
        });
            contraseñaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                    contraseñaPasswordField.setEchoChar('•');
                    if (contraseñaPasswordField.getText().equals("Contraseña") == !mod1) {
                        contraseñaPasswordField.setText("");
                    }
            }
        });
            contraseñaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(contraseñaPasswordField.getText().equals("")) {
                    contraseñaPasswordField.setText("Contraseña");
                    contraseñaPasswordField.setEchoChar((char)0);
                    mod1 = false;
                }
                else mod1 = true;
            }
        });

            iniciarSesiónButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ctrlUI.login(usuarioTextField.getText(), contraseñaPasswordField.getPassword());
                        Main main = new Main();
                        main.drawMain(frame);
                    } catch (FileNotFoundException fileNotFoundException) {
                        Utils.showError("Usuario y/o contraseña incorrecta");
                    } catch (WrongPasswordException wrongPasswordException) {
                        Utils.showError("Usuario y/o contraseña incorrecta");
                    }
                }
            });

        toRegister.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Register r = new Register();
                r.drawRegister(frame);
            }
        });
    }


    private void createUIComponents() {
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
        label.requestFocusInWindow();
    }

    public void drawLogin(JFrame frame) {
        this.frame = frame;
        frame.setTitle("Login");
        frame.setContentPane(panel2);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}
