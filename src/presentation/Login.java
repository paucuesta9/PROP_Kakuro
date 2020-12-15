package presentation;

import domain.classes.Exceptions.WrongPasswordException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class Login {
    private JPasswordField contraseñaPasswordField;
    private JTextField usuarioTextField;
    private JButton iniciarSesiónButton;
    private JLabel label;
    private JPanel panel2;
    private JButton config;
    private JLabel toRegister;
    private JLabel logo;

    private CtrlUI ctrlUI = CtrlUI.getInstance();
    private CtrlPlayerUI ctrlPlayerUI = CtrlPlayerUI.getInstance();

    boolean mod = false;
    boolean mod1 = false;

    public Login() {
        Utils.loadFonts();
        setListeners();

        toRegister.setFont(Utils.roboto.deriveFont(12f));

        logo.setIcon(Utils.getLogo());

        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        usuarioTextField.setForeground(Color.darkGray);
        usuarioTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        usuarioTextField.setFont(Utils.roboto.deriveFont(18f));

        contraseñaPasswordField.setForeground(Color.darkGray);
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
                if(usuarioTextField.getText().equals("")) {
                    usuarioTextField.setForeground(Color.darkGray);
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
                        contraseñaPasswordField.setForeground(Color.BLACK);
                    }
            }
        });
            contraseñaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(contraseñaPasswordField.getText().equals("")) {
                    contraseñaPasswordField.setText("Contraseña");
                    contraseñaPasswordField.setEchoChar((char)0);
                    contraseñaPasswordField.setForeground(Color.darkGray);
                    mod1 = false;
                }
                else mod1 = true;
            }
        });

            iniciarSesiónButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ctrlPlayerUI.login(usuarioTextField.getText(), contraseñaPasswordField.getPassword());
                        ctrlUI.toMain();
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
                ctrlUI.toRegister();
            }
        });
    }


    private void createUIComponents() {
        label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage()));
        label.requestFocusInWindow();
    }

    public JPanel getDefaultPanel() {
        return panel2;
    }
}
