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
    private JPanel logotipo;
    private JButton config;
    private JLabel toRegister;


    private CtrlUI ctrlUI;
    private static JFrame frame = new JFrame("Login");

    boolean mod = false;
    boolean mod1 = false;

    public Login() {
        Utils.loadFonts();
        setListeners();
        ctrlUI = CtrlUI.getInstance();

        toRegister.setFont(Utils.roboto.deriveFont(12f));

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
                        Utils.updateVolume(Utils.volume);
                        frame.dispose();
                        Main main = new Main();
                        main.drawMain();
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
                frame.dispose();
                Register r = new Register();
                r.drawRegister();
            }
        });
    }


    private void createUIComponents() {
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
        label.requestFocusInWindow();
    }

    public void drawLogin() {
        frame.setContentPane(panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new Login().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}
