package presentation;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Utils {
    public final static String colorBlue = "#1976D2";
    public final static String colorDarkBlue = "#00204A";
    public static String colorSelectedCell = "#64b5f6";
    public static String colorErrorCell = "#E53935";
    public static Color colorBlackCell = Color.decode("#000000");
    public static Color colorWhiteCell = Color.decode("#FFFFFF");
    public static Color colorNumbersBlackCell = Color.decode("#FFFFFF");
    public static Color colorNumbersWhiteCell = Color.decode("#000000");
    public static Color colorCorrectCell = Color.decode("#00FF00");
    public static Color colorIncorrectCell = Color.decode("#E53935");
    public static Color colorSelCell = Color.decode("#64b5f6");
    public static Color colorBorde = Color.decode("#808080");

    public static int volume= -10;

    public static Font fontAwesome;
    public static Font roboto;

    private static FloatControl gainControl;

    public Utils() {

    }

    public static void setButtons(JButton button) {
        button.setFont(Utils.roboto);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setBackground(null);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
    }

    public static void showError(String errorText) {
        Error error = new Error(errorText);
        error.drawError();
    }

    public static void loadFonts() {
        BufferedInputStream myStream = null;
        try {
            myStream = new BufferedInputStream(new FileInputStream("resources/fonts/fa-solid.ttf"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        Font ttfBase = null;
        try {
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (FontFormatException fontFormatException) {
            fontFormatException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        fontAwesome = ttfBase.deriveFont(Font.PLAIN, 30f);

        try {
            myStream = new BufferedInputStream(new FileInputStream("resources/fonts/Roboto-Bold.ttf"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        try {
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (FontFormatException fontFormatException) {
            fontFormatException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        roboto = ttfBase.deriveFont(Font.PLAIN, 20f);
    }

    public static void setMusic() {
        InputStream audioSrc = Utils.class.getClassLoader().getResourceAsStream("MUSICA2.wav");
        AudioInputStream as1 = null;
        try {
            as1 = AudioSystem.getAudioInputStream(audioSrc);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioFormat af = as1.getFormat();
        Clip clip1 = null;
        try {
            clip1 = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        DataLine.Info info = new DataLine.Info(Clip.class, af);

        Line line1 = null;
        try {
            line1 = AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        if ( ! line1.isOpen() )
        {
            try {
                clip1.open(as1);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clip1.loop(Clip.LOOP_CONTINUOUSLY);
            gainControl =
                    (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clip1.start();
        }
    }

    public static void updateVolume(int vol) {
        gainControl.setValue(vol);
        volume = vol;
    }

    public static void center(Frame frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width) / 2 - frame.getWidth()/2;
        int y = (screenSize.height) / 2 - frame.getHeight()/2;
        frame.setLocation(x, y);
    }
}
