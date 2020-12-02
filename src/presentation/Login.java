package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Login {
    private JPasswordField contraseñaPasswordField;
    private JTextField usuarioTextField;
    private JButton iniciarSesiónButton;
    private JLabel label;
    private JPanel panel2;
    private JPanel picLabel;
    private JPanel Prueba;

    int a = 0;
    boolean mod = false;
    boolean mod1 = false;

    public Login() {

        picLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                /*ImageIcon icon = new ImageIcon("C:\\Users\\zatch\\IdeaProjects\\Projecte_Prop\\src\\resources\\images\\Captura.png"); //se ajusta perfecto a la pantalla
                Image img = icon.getImage();
                Image imgScale = img.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_FAST);
                ImageIcon scaledIcon = new ImageIcon(imgScale);
                label.setIcon(scaledIcon);*/

                ImageIcon icon = new ImageIcon("resources/images/Captura.PNG"); //se ajusta cuadrado
                Image img = icon.getImage();
                int w = picLabel.getWidth(); int h = picLabel.getHeight();
                if (w>h) w = h;
                else h = w;
                Image imgScale = img.getScaledInstance(w, h, Image.SCALE_FAST);
                ImageIcon scaledIcon = new ImageIcon(imgScale);
                label.setIcon(scaledIcon);
            }
        });
        usuarioTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (a != 0) {
                    if (usuarioTextField.getText().equals("Usuario") && !mod) {
                        usuarioTextField.setFont(new Font("Roboto" ,Font.PLAIN, usuarioTextField.getFont().getSize()));
                        usuarioTextField.setText("");
                    }
                }
                else {
                    contraseñaPasswordField.requestFocusInWindow();
                }
            }
        });
        usuarioTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (a==0);
                else if(usuarioTextField.getText().equals("")) {
                    usuarioTextField.setFont(new Font("Roboto" ,Font.ITALIC, usuarioTextField.getFont().getSize()));
                    usuarioTextField.setText("Usuario");
                    mod = false;
                }
                else mod = true;
            }
        });
        contraseñaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (a==0) {
                    contraseñaPasswordField.setEchoChar((char)0);
                    iniciarSesiónButton.requestFocusInWindow();
                }
                else {
                    contraseñaPasswordField.setEchoChar('•');
                    if (contraseñaPasswordField.getText().equals("Contraseña")) {
                        contraseñaPasswordField.setFont(new Font("Roboto" ,Font.PLAIN, contraseñaPasswordField.getFont().getSize()));
                        contraseñaPasswordField.setText("");
                    }
                }
            }
        });
        contraseñaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (a==0) ++a;
                else if(contraseñaPasswordField.getText().equals("")) {
                    contraseñaPasswordField.setFont(new Font("Roboto" ,Font.ITALIC, contraseñaPasswordField.getFont().getSize()));
                    contraseñaPasswordField.setEchoChar((char)0);
                    contraseñaPasswordField.setText("Contraseña");
                    mod1 = false;
                }
                else mod1 = true;
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
         label = new JLabel(new ImageIcon(new ImageIcon("resources/images/Captura.PNG").getImage().getScaledInstance(600,600, Image.SCALE_DEFAULT)));
         label.requestFocusInWindow();
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Login().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,700);
        frame.setVisible(true);
    }
}
