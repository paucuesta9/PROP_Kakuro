package presentation;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class FinishedGame extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel gif;
    private JLabel text;
    private JLabel points;
    private JFrame frame = new JFrame("FinishedGame");

    public FinishedGame(int points) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Utils.loadFonts();

        buttonOK.setFont(Utils.roboto);
        buttonOK.setForeground(Color.WHITE);
        buttonOK.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/bottonOKError.png")));
        buttonOK.setBorderPainted(false);
        buttonOK.setBackground(null);
        buttonOK.setHorizontalTextPosition(JButton.CENTER);
        buttonOK.setVerticalTextPosition(JButton.CENTER);

        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Utils.roboto);
        this.points.setText("<html> <p style=\"text-align:center;\">HAS CONSEGUIDO:</p> <p style=\"text-align:center;\">" + points + " PUNTOS</p> </html>");
        this.points.setFont(Utils.roboto.deriveFont(Font.PLAIN, 28f));
        this.points.setForeground(Color.decode(Utils.colorDarkBlue));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        FinishedGame dialog = new FinishedGame(15);
        dialog.drawFinishedGame();
    }

    public void drawFinishedGame() {
        pack();
        setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width) / 2 - getSize().width / 2;
        int y = (screenSize.height) / 2 - getSize().height / 2;
        setLocation(x, y);
        setVisible(true);
    }

    private void createUIComponents() {
        URL url = FinishedGame.class.getClassLoader().getResource("images/winner.gif");
        ImageIcon imageIcon = new ImageIcon(url);
        gif = new JLabel(imageIcon);
    }
}
