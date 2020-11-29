package presentation;

import javax.swing.*;
import java.awt.*;

public class KakuroBoard extends JPanel {
    KakuroBoard(int rowSize, int columnSize) {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = i;
                c.gridy = j;
                if (i % 2 == 0)
                    add(new KakuroWhiteCell(i, j), c);
                else
                    add(new KakuroBlackCell(i, j), c);
            }
        }
    }
}
