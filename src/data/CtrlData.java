package data;

import domain.BlackCell;
import domain.Cell;
import domain.Kakuro;
import domain.WhiteCell;

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

    public Kakuro searchKakuro (int difficulty, int kakuroSize) {
        Random random = new Random();
        return getKakuro("data/diff" + difficulty + "/" + kakuroSize + "/" + random.nextInt(getNumberOfFiles(difficulty, kakuroSize)) + ".txt");
    }

    public Kakuro getKakuro(String filePath) {
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] values = line.split(",");
            int rowSize = Integer.parseInt(values[0]);
            int columnSize = Integer.parseInt(values[1]);
            Cell[][] board = new Cell[rowSize][columnSize];
            for (int i = 0; i < rowSize; ++i) {
                line = br.readLine();
                values = line.split(",");
                for (int j = 0; j < columnSize; ++j) {
                    if (values[j].equals("*")) board[i][j] = new BlackCell(i, j);
                    else if (values[j].equals("?")) board[i][j] = new WhiteCell(i, j);
                    else if (values[j].charAt(0) == 'C' || values[j].charAt(0) == 'F') {
                        int vertical = 0, horizontal = 0;
                        if (values[j].charAt(0) == 'C') {
                            values[j] = values[j].substring(1);
                            if (values[j].contains("F")) {
                                String[] CF = values[j].split("F");
                                vertical = Integer.parseInt(CF[0]);
                                horizontal = Integer.parseInt(CF[1]);
                            } else {
                                vertical = Integer.parseInt(values[j]);
                            }
                        } else {
                            horizontal = Integer.parseInt(values[j].substring(1));
                        }
                        board[i][j] = new BlackCell(i, j, vertical, horizontal);
                    } else board[i][j] = new WhiteCell(i, j, Integer.parseInt(values[j]));
                }
            }
            return new Kakuro("0", 0, rowSize, columnSize, board);
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
