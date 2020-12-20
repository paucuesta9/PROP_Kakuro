package presentation;

import domain.classes.BlackCell;
import domain.classes.Cell;
import domain.classes.Exceptions.NoTypeCellException;
import domain.classes.WhiteCell;

import javax.swing.*;
import java.awt.*;

/** @file KakuroBoard.java
 * @class KakuroBoard
 */

/** @brief Clase que carga un kakuro con sus atributos
 * @author ------------------
 */

public class KakuroBoard extends JPanel {

    private int rowSize;
    private int columnSize;

    KakuroBoard(String kakuro) {
        super(new GridBagLayout());
        String[] values = kakuro.split("\n");
        String[] valuesSize = values[0].split(",");
        rowSize = Integer.parseInt(valuesSize[0]);
        columnSize = Integer.parseInt(valuesSize[1]);
        int size = Math.max(rowSize, columnSize);
        size = 750 / size;
        GridBagConstraints c = new GridBagConstraints();
        for (int i = 0; i < rowSize; i++) {
            String[] valuesRow = null;
            if (values.length != 1) valuesRow = values[i + 1].split(",");
            for (int j = 0; j < columnSize; j++) {
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = j;
                c.gridy = i;
                if (values.length == 1) add(new KakuroCell(i, j, size), c);
                else if (valuesRow[j].equals("*")) add(new KakuroBlackCell(i, j, size), c);
                else if (valuesRow[j].equals("?")) add(new KakuroWhiteCell(i, j, size), c);
                else if (valuesRow[j].charAt(0) == 'C' || valuesRow[j].charAt(0) == 'F') {
                    int vertical = 0, horizontal = 0;
                    if (valuesRow[j].charAt(0) == 'C') {
                        valuesRow[j] = valuesRow[j].substring(1);
                        if (valuesRow[j].contains("F")) {
                            String[] CF = valuesRow[j].split("F");
                            vertical = Integer.parseInt(CF[0]);
                            horizontal = Integer.parseInt(CF[1]);
                        } else {
                            vertical = Integer.parseInt(valuesRow[j]);
                        }
                    } else {
                        horizontal = Integer.parseInt(valuesRow[j].substring(1));
                    }
                    add(new KakuroBlackCell(i, j, horizontal, vertical, size), c);
                } else add(new KakuroWhiteCell(i, j, Integer.parseInt(valuesRow[j]), size), c);
            }
        }
    }

    public String boardToString() throws NoTypeCellException {
        StringBuilder content = new StringBuilder();
        String line;
        line = rowSize + "," + columnSize;
        content.append(line).append("\n");
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                int pos = i * columnSize + j;
                if (getComponent(pos) instanceof KakuroWhiteCell) {
                    KakuroWhiteCell w = (KakuroWhiteCell) getComponent(pos);
                    if (w.getValue() != 0) content.append(w.getValue());
                    else content.append("?");
                }
                else if (getComponent(pos) instanceof KakuroBlackCell) {
                    KakuroBlackCell bc = (KakuroBlackCell) getComponent(pos);
                    if (bc.getColumn() == 0 && bc.getRow() == 0) {
                        content.append("*");
                    }
                    if (bc.getColumn() != 0) content.append("C" + bc.getColumn());
                    if (bc.getRow() != 0) content.append("F" + bc.getRow());
                } else throw new NoTypeCellException();
                if (j != columnSize - 1) content.append(",");
            }
            content.append("\n");
        }
        return content.toString();
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }
}
