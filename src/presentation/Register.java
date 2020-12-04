package presentation;

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

    private static JFrame frame;

    int a = 0;
    boolean mod = false;
    boolean mod1 = false;
    boolean mod2 = false;

    public Register() {

        Utils.loadFonts();
        setListeners();

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
            /*clickToLoginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    Login m = new Login();
                    m.drawLogin();
                }
            });*/
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        frame = new JFrame("Register");
        frame.setContentPane(new Register().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}