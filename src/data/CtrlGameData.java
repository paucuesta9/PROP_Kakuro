package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CtrlGameData {

    public CtrlGameData() {
    }

    public int getNewGameId(String username) {
        File folder = new File("data/players/" + username);
        File[] listFiles = folder.listFiles();
        int size = listFiles.length;
        if (size != 0) {
            String lastFile = listFiles[size-1].getName();
            int i = lastFile.lastIndexOf('.');
            String name = lastFile.substring(0,i);
            return Integer.parseInt(name)+1;
        }
        return 0;
    }

    public void saveKakuroGame(String kakuro, String username, int id) {
        FileWriter file = null;
        try {
            file = new FileWriter("data/players/" + username + "/" + id + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(file);
        pw.print(kakuro);
        pw.close();
    }
}
