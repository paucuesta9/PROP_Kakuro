package presentation;

import domain.classes.Exceptions.PlayerExists;
import domain.controllers.CtrlDomain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Register {
    private JTextField usuarioTextField;
    private JPasswordField contraseñaPasswordField;
    private JPanel panel2;
    private JLabel label;
    private JButton config;
    private JButton registrarseButton;
    private JPasswordField repetirContraseñaPasswordField;
    private JPanel logotipo;
    private JLabel toLogin;

    private static JFrame frame = new JFrame("Register");
    private CtrlUI ctrlUI = CtrlUI.getInstance();

    boolean mod = false;
    boolean mod1 = false;
    boolean mod2 = false;

    public Register() {

        Utils.loadFonts();
        setListeners();

        toLogin.setFont(Utils.roboto.deriveFont(12f));

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10, 0, 0, 20));

        usuarioTextField.setForeground(Color.BLACK);
        usuarioTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        usuarioTextField.setFont(Utils.roboto.deriveFont(18f));

        contraseñaPasswordField.setForeground(Color.BLACK);
        contraseñaPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        contraseñaPasswordField.setFont(Utils.roboto.deriveFont(18f));
        contraseñaPasswordField.setEchoChar((char) 0);

        repetirContraseñaPasswordField.setForeground(Color.BLACK);
        repetirContraseñaPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        repetirContraseñaPasswordField.setFont(Utils.roboto.deriveFont(18f));
        repetirContraseñaPasswordField.setEchoChar((char) 0);

        registrarseButton.setFont(Utils.roboto);
        registrarseButton.setForeground(Color.WHITE);
        registrarseButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        registrarseButton.setBorderPainted(false);
        registrarseButton.setBackground(null);
        registrarseButton.setHorizontalTextPosition(JButton.CENTER);
        registrarseButton.setVerticalTextPosition(JButton.CENTER);
    }

    private void setListeners() {
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
                    if (usuarioTextField.getText().equals("")) {
                        usuarioTextField.setText("Usuario");
                        mod = false;
                    } else mod = true;
                }
            });
            contraseñaPasswordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    contraseñaPasswordField.setEchoChar('•');
                    if (contraseñaPasswordField.getText().equals("Contraseña") && !mod1) {
                        contraseñaPasswordField.setText("");
                    }
                }
            });
            contraseñaPasswordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (contraseñaPasswordField.getText().equals("")) {
                        contraseñaPasswordField.setEchoChar((char) 0);
                        contraseñaPasswordField.setText("Contraseña");
                        mod1 = false;
                    } else mod1 = true;
                }
            });

            repetirContraseñaPasswordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    repetirContraseñaPasswordField.setEchoChar('•');
                    if (repetirContraseñaPasswordField.getText().equals("Repetir contraseña") && !mod2) {
                        repetirContraseñaPasswordField.setText("");
                    }
                }
            });
            repetirContraseñaPasswordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (repetirContraseñaPasswordField.getText().equals("")) {

                        repetirContraseñaPasswordField.setEchoChar((char) 0);
                        repetirContraseñaPasswordField.setText("Repetir contraseña");
                        mod2 = false;
                    } else mod2 = true;
                }
            });
            registrarseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!mod || !mod1 || !mod2) Utils.showError("Usuario y/o contraseña no válidos");
                    else if(repetirContraseñaPasswordField.getText().equals(contraseñaPasswordField.getText())) {
                        try {
                            ctrlUI.signUp(usuarioTextField.getText(), contraseñaPasswordField.getText());
                            frame.dispose();
                            Main main = new Main();
                            main.drawMain();
                        } catch (PlayerExists playerExists) {
                            Utils.showError("El usuario ya existe");
                        }
                    }
                    else {
                        Utils.showError("Las contraseñas no són iguales");
                    }
                }
            });

        toLogin.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                frame.dispose();
                Login l = new Login();
                l.drawLogin();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
        label.requestFocusInWindow();
    }

    public void drawRegister() {
        frame.setContentPane(panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        frame = new JFrame("Register");
        frame.setContentPane(new Register().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}