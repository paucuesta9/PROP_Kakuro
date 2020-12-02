package presentation;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;

public class Utils {
    public final static String colorBlue = "#1976D2";
    public final static String colorDarkBlue = "#00204A";
    public final static String colorBlueSelectedCell = "#64b5f6";
    public final static String colorRedCell = "#E53935";

    public static Font fontAwesome;
    public static Font roboto;

    public Utils() {

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

    private void setMusic() {
        InputStream audioSrc = getClass().getClassLoader().getResourceAsStream("MUSICA2.wav");
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
            FloatControl gainControl =
                    (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip1.start();
        }
    }

    public static void center(Frame frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width) / 2 - 600;
        int y = (screenSize.height) / 2 - 450;
        frame.setLocation(x, y);
    }
}
