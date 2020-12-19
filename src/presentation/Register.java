package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domain.classes.Exceptions.PlayerExists;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class Register {
    private JTextField usuarioTextField;
    private JPasswordField contrasenaPasswordField;
    private JPanel panel2;
    private JLabel label;
    private JButton config;
    private JButton registrarseButton;
    private JPasswordField repetirContrasenaPasswordField;
    private JLabel toLogin;
    private JLabel logo;

    private CtrlUI ctrlUI = CtrlUI.getInstance();
    private CtrlPlayerUI ctrlPlayerUI = CtrlPlayerUI.getInstance();

    boolean mod = false;
    boolean mod1 = false;
    boolean mod2 = false;

    public Register() {
        $$$setupUI$$$();
        setListeners();

        toLogin.setFont(Utils.roboto.deriveFont(12f));
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        logo.setIcon(Utils.getLogo());

        usuarioTextField.setForeground(Color.DARK_GRAY);
        usuarioTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        usuarioTextField.setFont(Utils.roboto.deriveFont(18f));

        contrasenaPasswordField.setForeground(Color.darkGray);
        contrasenaPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        contrasenaPasswordField.setFont(Utils.roboto.deriveFont(18f));
        contrasenaPasswordField.setEchoChar((char) 0);

        repetirContrasenaPasswordField.setForeground(Color.darkGray);
        repetirContrasenaPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        repetirContrasenaPasswordField.setFont(Utils.roboto.deriveFont(18f));
        repetirContrasenaPasswordField.setEchoChar((char) 0);

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

        contrasenaPasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarseButton.doClick();
            }
        });

        repetirContrasenaPasswordField.addActionListener(new ActionListener() {
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
        contrasenaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                contrasenaPasswordField.setEchoChar('•');
                if (contrasenaPasswordField.getText().equals("Contraseña") && !mod1) {
                    contrasenaPasswordField.setText("");
                    contrasenaPasswordField.setForeground(Color.BLACK);
                }
            }
        });
        contrasenaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (contrasenaPasswordField.getText().equals("")) {
                    contrasenaPasswordField.setEchoChar((char) 0);
                    contrasenaPasswordField.setText("Contraseña");
                    contrasenaPasswordField.setForeground(Color.darkGray);
                    mod1 = false;
                } else mod1 = true;
            }
        });

        repetirContrasenaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repetirContrasenaPasswordField.setEchoChar('•');
                if (repetirContrasenaPasswordField.getText().equals("Repetir contraseña") && !mod2) {
                    repetirContrasenaPasswordField.setText("");
                    repetirContrasenaPasswordField.setForeground(Color.BLACK);
                }
            }
        });
        repetirContrasenaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (repetirContrasenaPasswordField.getText().equals("")) {

                    repetirContrasenaPasswordField.setEchoChar((char) 0);
                    repetirContrasenaPasswordField.setText("Repetir contraseña");
                    repetirContrasenaPasswordField.setForeground(Color.darkGray);
                    mod2 = false;
                } else mod2 = true;
            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mod || !mod1 || !mod2) Utils.showError("Usuario y/o contraseña no válidos");
                else if (repetirContrasenaPasswordField.getText().equals(contrasenaPasswordField.getText())) {
                    try {
                        ctrlPlayerUI.signUp(usuarioTextField.getText(), contrasenaPasswordField.getText());
                        ctrlUI.toMain();
                    } catch (PlayerExists playerExists) {
                        Utils.showError("El usuario ya existe");
                    }
                } else {
                    Utils.showError("Las contraseñas no són iguales");
                }
            }
        });

        toLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1973532));
        Font panel2Font = this.$$$getFont$$$(null, -1, -1, panel2.getFont());
        if (panel2Font != null) panel2.setFont(panel2Font);
        panel2.setMaximumSize(new Dimension(1200, 800));
        panel2.setMinimumSize(new Dimension(1200, 800));
        panel2.setPreferredSize(new Dimension(1200, 800));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973532));
        panel2.add(panel1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        registrarseButton = new JButton();
        registrarseButton.setBackground(new Color(-15108398));
        registrarseButton.setBorderPainted(false);
        registrarseButton.setContentAreaFilled(false);
        registrarseButton.setEnabled(true);
        registrarseButton.setFocusPainted(false);
        registrarseButton.setText("Registrarse");
        panel1.add(registrarseButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 50), null, 0, false));
        usuarioTextField = new JTextField();
        usuarioTextField.setFocusable(true);
        usuarioTextField.setOpaque(false);
        usuarioTextField.setText("Usuario");
        panel1.add(usuarioTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        contrasenaPasswordField = new JPasswordField();
        contrasenaPasswordField.setOpaque(false);
        contrasenaPasswordField.setText("Contraseña");
        panel1.add(contrasenaPasswordField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        repetirContrasenaPasswordField = new JPasswordField();
        repetirContrasenaPasswordField.setOpaque(false);
        repetirContrasenaPasswordField.setText("Repetir contraseña");
        panel1.add(repetirContrasenaPasswordField, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        toLogin = new JLabel();
        toLogin.setText("¿Ya tiene cuenta? Inicie sesión aquí ");
        panel1.add(toLogin, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(50, -1), null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel2.add(spacer6, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(50, -1), null, 0, false));
        label.setText("");
        panel2.add(label, new GridConstraints(0, 0, 4, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(800, 800), new Dimension(800, 800), new Dimension(800, 800), 0, false));
        final Spacer spacer7 = new Spacer();
        panel2.add(spacer7, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 85), null, 0, false));
        logo = new JLabel();
        logo.setText("");
        panel2.add(logo, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
        final Spacer spacer8 = new Spacer();
        panel2.add(spacer8, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(60, -1), new Dimension(60, -1), new Dimension(60, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel2;
    }

}