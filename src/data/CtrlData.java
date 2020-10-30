package data;

import java.io.*;
import java.util.Random;

public class CtrlData {

    private static CtrlData ctrlData;

    public static CtrlData getInstance() {
        if (ctrlData == null)
            ctrlData = new CtrlData();
        return ctrlData;
    }

    public CtrlData() {

    }

    public String searchKakuro (int difficulty, int kakuroSize) {
        Random random = new Random();
        return getKakuro("data/diff" + difficulty + "/" + kakuroSize + "/" + random.nextInt(getNumberOfFiles(difficulty, kakuroSize)) + ".txt");
    }

    public String getKakuro(String filePath) {
        try {
            StringBuilder content = new StringBuilder();
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveKakuro(String content, int diff, int size) {
        FileWriter file = null;
        try {
            file = new FileWriter("data/diff" + diff + "/" + size + "/" + getNumberOfFiles(diff, size));
            PrintWriter pw = new PrintWriter(file);
            pw.print(content);
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public int getNumberOfFiles(int diff, int size) {
        File folder = new File("data/diff" + diff + "/" + size);
        File[] listFiles = folder.listFiles();
        return listFiles.length;
    }

}
