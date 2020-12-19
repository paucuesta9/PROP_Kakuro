package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domain.classes.Exceptions.WrongPasswordException;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Locale;

public class Login {
    private JPasswordField contrasenaPasswordField;
    private JTextField usuarioTextField;
    private JButton iniciarSesionButton;
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
        $$$setupUI$$$();
        setListeners();

        toRegister.setFont(Utils.roboto.deriveFont(12f));

        logo.setIcon(Utils.getLogo());

        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        usuarioTextField.setForeground(Color.darkGray);
        usuarioTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        usuarioTextField.setFont(Utils.roboto.deriveFont(18f));

        contrasenaPasswordField.setForeground(Color.darkGray);
        contrasenaPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        contrasenaPasswordField.setFont(Utils.roboto.deriveFont(18f));
        contrasenaPasswordField.setEchoChar((char) 0);

        iniciarSesionButton.setFont(Utils.roboto);
        iniciarSesionButton.setForeground(Color.WHITE);
        iniciarSesionButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        iniciarSesionButton.setBorderPainted(false);
        iniciarSesionButton.setBackground(null);
        iniciarSesionButton.setHorizontalTextPosition(JButton.CENTER);
        iniciarSesionButton.setVerticalTextPosition(JButton.CENTER);
    }

    private void setListeners() {
        usuarioTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesionButton.doClick();
            }
        });

        contrasenaPasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesionButton.doClick();
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
                    usuarioTextField.setForeground(Color.darkGray);
                    usuarioTextField.setText("Usuario");
                    mod = false;
                } else mod = true;
            }
        });
        contrasenaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                contrasenaPasswordField.setEchoChar('•');
                if (contrasenaPasswordField.getText().equals("Contraseña") == !mod1) {
                    contrasenaPasswordField.setText("");
                    contrasenaPasswordField.setForeground(Color.BLACK);
                }
            }
        });
        contrasenaPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (contrasenaPasswordField.getText().equals("")) {
                    contrasenaPasswordField.setText("Contraseña");
                    contrasenaPasswordField.setEchoChar((char) 0);
                    contrasenaPasswordField.setForeground(Color.darkGray);
                    mod1 = false;
                } else mod1 = true;
            }
        });

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ctrlPlayerUI.login(usuarioTextField.getText(), contrasenaPasswordField.getPassword());
                    ctrlUI.toMain();
                } catch (FileNotFoundException fileNotFoundException) {
                    Utils.showError("Usuario y/o contraseña incorrecta");
                } catch (WrongPasswordException wrongPasswordException) {
                    Utils.showError("Usuario y/o contraseña incorrecta");
                }
            }
        });

        toRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        panel1.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973532));
        panel2.add(panel1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        iniciarSesionButton = new JButton();
        iniciarSesionButton.setBackground(new Color(-15108398));
        iniciarSesionButton.setBorderPainted(false);
        iniciarSesionButton.setContentAreaFilled(false);
        iniciarSesionButton.setEnabled(true);
        iniciarSesionButton.setFocusPainted(false);
        iniciarSesionButton.setFocusable(true);
        iniciarSesionButton.setText("Iniciar Sesión");
        panel1.add(iniciarSesionButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, -1), new Dimension(252, 40), new Dimension(252, -1), 0, false));
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
        panel1.add(spacer3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        toRegister = new JLabel();
        toRegister.setText("¿Aún no tiene cuenta? Regístrese aquí");
        panel1.add(toRegister, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(50, -1), null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(50, -1), null, 0, false));
        label.setText("");
        panel2.add(label, new GridConstraints(0, 0, 4, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(800, 800), new Dimension(800, 800), new Dimension(800, 800), 0, false));
        final Spacer spacer6 = new Spacer();
        panel2.add(spacer6, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 85), null, 0, false));
        logo = new JLabel();
        logo.setText("");
        panel2.add(logo, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
        final Spacer spacer7 = new Spacer();
        panel2.add(spacer7, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(60, -1), new Dimension(60, -1), new Dimension(60, -1), 0, false));
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
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel2;
    }

}
