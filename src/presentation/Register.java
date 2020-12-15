package presentation;

import domain.classes.Exceptions.PlayerExists;

import javax.swing.*;
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
    private JLabel toLogin;
    private JLabel logo;

    private CtrlUI ctrlUI = CtrlUI.getInstance();
    private CtrlPlayerUI ctrlPlayerUI = CtrlPlayerUI.getInstance();

    boolean mod = false;
    boolean mod1 = false;
    boolean mod2 = false;

    public Register() {

        Utils.loadFonts();
        setListeners();

        toLogin.setFont(Utils.roboto.deriveFont(12f));
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setIcon(Utils.getLogo());

        usuarioTextField.setForeground(Color.DARK_GRAY);
        usuarioTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        usuarioTextField.setFont(Utils.roboto.deriveFont(18f));

        contraseñaPasswordField.setForeground(Color.darkGray);
        contraseñaPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        contraseñaPasswordField.setFont(Utils.roboto.deriveFont(18f));
        contraseñaPasswordField.setEchoChar((char) 0);

        repetirContraseñaPasswordField.setForeground(Color.darkGray);
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

            usuarioTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    usuarioTextField.setForeground(Color.BLACK);
                    registrarseButton.doClick();
                }
            });

            contraseñaPasswordField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarseButton.doClick();
                }
            });

            repetirContraseñaPasswordField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarseButton.doClick();
                }
            });

            usuarioTextField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (usuarioTextField.getText().equals("Usuario") && !mod) {
                        usuarioTextField.setText("");
                        usuarioTextField.setForeground(Color.BLACK);
                    }
                }
            });
            usuarioTextField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (usuarioTextField.getText().equals("")) {
                        usuarioTextField.setText("Usuario");
                        usuarioTextField.setForeground(Color.DARK_GRAY);
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
                        contraseñaPasswordField.setForeground(Color.BLACK);
                    }
                }
            });
            contraseñaPasswordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (contraseñaPasswordField.getText().equals("")) {
                        contraseñaPasswordField.setEchoChar((char) 0);
                        contraseñaPasswordField.setText("Contraseña");
                        contraseñaPasswordField.setForeground(Color.darkGray);
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
                        repetirContraseñaPasswordField.setForeground(Color.BLACK);
                    }
                }
            });
            repetirContraseñaPasswordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (repetirContraseñaPasswordField.getText().equals("")) {

                        repetirContraseñaPasswordField.setEchoChar((char) 0);
                        repetirContraseñaPasswordField.setText("Repetir contraseña");
                        repetirContraseñaPasswordField.setForeground(Color.darkGray);
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
                            ctrlPlayerUI.signUp(usuarioTextField.getText(), contraseñaPasswordField.getText());
                            ctrlUI.toMain();
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
                ctrlUI.toLogin();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
        label.requestFocusInWindow();
    }

    public JPanel getDefaultPanel() {
        return panel2;
    }
}