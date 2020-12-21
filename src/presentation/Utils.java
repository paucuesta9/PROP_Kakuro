package presentation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import netscape.javascript.JSObject;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/** @file Utils.java
 @class Utils
 */

/** @brief Clase Utils que implementa métodos útiles para la presentación
 * @author --------------
 */

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
    public static Color colorIncorrectHelp1Cell = Color.decode("#E63935");
    public static Color colorSelCell = Color.decode("#64b5f6");
    public static Color colorBorde = Color.decode("#808080");
    public static ImageIcon logo = new ImageIcon(Utils.class.getClassLoader().getResource("images/logo.png"));

    public static int volume= -10;

    public static Font fontAwesome;
    public static Font roboto;
    public static Font digital;

    private static FloatControl gainControl;

    public Utils() {

    }

    public static ImageIcon getLogo() {
        Image logoImage = Utils.logo.getImage().getScaledInstance(252, 200, Image.SCALE_SMOOTH);
        return new ImageIcon(logoImage);
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

        try {
            myStream = new BufferedInputStream(new FileInputStream("resources/fonts/digital-clock.ttf"));
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
        digital = ttfBase.deriveFont(Font.PLAIN, 20f);
    }

    public static void setMusic() {
        if (gainControl == null) {
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

    public static void setConfig(ArrayList<String> config) {
        colorBlackCell = Color.decode(config.get(0));
        colorWhiteCell = Color.decode(config.get(1));
        colorNumbersBlackCell = Color.decode(config.get(2));
        colorNumbersWhiteCell = Color.decode(config.get(3));
        colorCorrectCell = Color.decode(config.get(4));
        colorIncorrectCell = Color.decode(config.get(5));
        int r = colorIncorrectCell.getRed();
        int g = colorIncorrectCell.getGreen();
        int b = colorIncorrectCell.getBlue();
        int a = colorIncorrectCell.getAlpha();
        colorIncorrectHelp1Cell = new Color(r + 1, g, b, a);
        colorSelCell = Color.decode(config.get(6));
        colorBorde = Color.decode(config.get(7));
        volume = Integer.parseInt(config.get(8));
    }

    public static ArrayList<String> getConfig() {
        ArrayList config = new ArrayList();
        config.add("#"+Integer.toHexString(colorBlackCell.getRGB()).substring(2));
        config.add("#"+Integer.toHexString(colorWhiteCell.getRGB()).substring(2));
        config.add("#"+Integer.toHexString(colorNumbersBlackCell.getRGB()).substring(2));
        config.add("#"+Integer.toHexString(colorNumbersWhiteCell.getRGB()).substring(2));
        config.add("#"+Integer.toHexString(colorCorrectCell.getRGB()).substring(2));
        config.add("#"+Integer.toHexString(colorIncorrectCell.getRGB()).substring(2));
        config.add("#"+Integer.toHexString(colorSelCell.getRGB()).substring(2));
        config.add("#"+Integer.toHexString(colorBorde.getRGB()).substring(2));
        config.add(String.valueOf(volume));
        return config;
    }

    public static String setTimeToLabel(int gameTime) {
        int sec = 0;
        int min = 0;
        int hours = 0;
        String timeString = "";
        if (gameTime >= 60) {
            if (gameTime / 60 >= 60) {
                hours = gameTime / 3600;
                min = (gameTime / 60) % 60;
                sec = (gameTime % 3600) % 60;
            } else {
                min = gameTime / 60;
                sec = gameTime % 60;
            }
        } else {
            sec = gameTime;
        }
        if (hours != 0) {
            if (hours < 10) timeString = timeString.concat("0" + hours);
            else timeString = timeString.concat(String.valueOf(hours));
            timeString = timeString.concat(":");
        }
        if (min < 10) timeString = timeString.concat("0" + min);
        else timeString = timeString.concat(String.valueOf(min));
        timeString = timeString.concat(":");
        if (sec < 10) timeString = timeString.concat("0" + sec);
        else timeString = timeString.concat(String.valueOf(sec));
        return timeString;
    }
}
