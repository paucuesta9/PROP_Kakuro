package presentation;

import domain.classes.Exceptions.NoTypeCellException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Create {
    private JPanel panel1;
    private JButton config;
    private JPanel board;
    private JPanel rowSize;
    private JLabel rowSizeText;
    private JTextField numRow;
    private JPanel columnSize;
    private JLabel columnSizeText;
    private JTextField numColumn;
    private JLabel textSize;
    private JButton confirm;
    private JButton cancel;
    private JButton aceptar;
    private JLabel logo;

    private JFrame frame;

    private KakuroBoard kBoard;
    private Component[] cells;

    private CtrlUI ctrlUI = CtrlUI.getInstance();

    private int posX, posY;
    private int sizeRow, sizeColumn;

    private JPopupMenu popupMenu;
    private JPopupMenu popupMenuOnlyBlackLeft;
    private JPopupMenu popupMenuOnlyBlackTop;

    public Create() {
        $$$setupUI$$$();
        Utils.loadFonts();
        setListeners();

        board.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        textSize.setFont(Utils.roboto.deriveFont(18f));
        textSize.setForeground(Color.BLACK);

        rowSizeText.setForeground(Color.BLACK);
        rowSizeText.setFont(Utils.roboto.deriveFont(18f));

        logo.setIcon(Utils.getLogo());

        numRow.setForeground(Color.BLACK);
        numRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        numRow.setFont(Utils.roboto.deriveFont(16f));

        columnSizeText.setForeground(Color.BLACK);
        columnSizeText.setFont(Utils.roboto.deriveFont(18f));

        numColumn.setForeground(Color.BLACK);
        numColumn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        numColumn.setFont(Utils.roboto.deriveFont(18f));

        config.setFont(Utils.fontAwesome);
        config.setForeground(Color.decode(Utils.colorDarkBlue));
        config.setBackground(null);
        config.setBorder(new EmptyBorder(10, 0, 0, 22));

        Utils.setButtons(aceptar);
        Utils.setButtons(confirm);
        Utils.setButtons(cancel);

        setPopUpCells();

        aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Volver.png")));
        confirm.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));
        cancel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-rojo.png")));
    }

    private void setPopUpCells() {
        popupMenu = new JPopupMenu();
        popupMenuOnlyBlackLeft = new JPopupMenu();
        popupMenuOnlyBlackTop = new JPopupMenu();
        JMenuItem whiteCellItem = new JMenuItem("Asignar Celda Blanca");
        whiteCellItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                int pos = posX * sizeColumn + posY;
                cells[pos] = new KakuroWhiteCell(posX, posY, cells[pos].getSize().width);
                SetValues setValues = new SetValues(1);
                int[] value = setValues.drawSetValues();
                if (value[0] != -1) {
                    ((KakuroWhiteCell) cells[pos]).setValue(value[0]);
                    GridBagConstraints c = new GridBagConstraints();
                    c.weightx = 1.0;
                    c.weighty = 1.0;
                    c.fill = GridBagConstraints.BOTH;
                    c.gridx = posY;
                    c.gridy = posX;
                    kBoard.remove(pos);
                    kBoard.add(cells[pos], c, pos);
                    kBoard.validate();
                    board.validate();
                }
            }
        });
        popupMenu.add(whiteCellItem);

        JMenuItem blackCellItem = new JMenuItem("Asignar Celda Negra");
        blackCellItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                int pos = posX * sizeColumn + posY;
                cells[pos] = new KakuroBlackCell(posX, posY, cells[pos].getSize().width);
                SetValues setValues = new SetValues(2);
                int[] value = setValues.drawSetValues();
                if (value[0] != -1) {
                    ((KakuroBlackCell) cells[pos]).setRow(value[0]);
                    ((KakuroBlackCell) cells[pos]).setColumn(value[1]);
                    GridBagConstraints c = new GridBagConstraints();
                    c.weightx = 1.0;
                    c.weighty = 1.0;
                    c.fill = GridBagConstraints.BOTH;
                    c.gridx = posY;
                    c.gridy = posX;
                    kBoard.remove(pos);
                    kBoard.add(cells[pos], c, pos);
                    kBoard.validate();
                    board.validate();
                    listenersCells();
                }
            }
        });

        JMenuItem blackCellItemLeft = new JMenuItem("Asignar Celda Negra");
        blackCellItemLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                int pos = posX * sizeColumn + posY;
                cells[pos] = new KakuroBlackCell(posX, posY, cells[pos].getSize().width);
                SetValues setValues = new SetValues(3);
                int[] value = setValues.drawSetValues();
                if (value[0] != -1) {
                    ((KakuroBlackCell) cells[pos]).setRow(value[0]);
                    GridBagConstraints c = new GridBagConstraints();
                    c.weightx = 1.0;
                    c.weighty = 1.0;
                    c.fill = GridBagConstraints.BOTH;
                    c.gridx = posY;
                    c.gridy = posX;
                    kBoard.remove(pos);
                    kBoard.add(cells[pos], c, pos);
                    kBoard.validate();
                    board.validate();
                    listenersCells();
                }
            }
        });

        JMenuItem blackCellItemTop = new JMenuItem("Asignar Celda Negra");
        blackCellItemTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                int pos = posX * sizeColumn + posY;
                cells[pos] = new KakuroBlackCell(posX, posY, cells[pos].getSize().width);
                SetValues setValues = new SetValues(4);
                int[] value = setValues.drawSetValues();
                if (value[0] != -1) {
                    ((KakuroBlackCell) cells[pos]).setColumn(value[0]);
                    GridBagConstraints c = new GridBagConstraints();
                    c.weightx = 1.0;
                    c.weighty = 1.0;
                    c.fill = GridBagConstraints.BOTH;
                    c.gridx = posY;
                    c.gridy = posX;
                    kBoard.remove(pos);
                    kBoard.add(cells[pos], c, pos);
                    kBoard.validate();
                    board.validate();
                    listenersCells();
                }
            }
        });
        popupMenuOnlyBlackLeft.add(blackCellItemLeft);
        popupMenuOnlyBlackTop.add(blackCellItemTop);
        popupMenu.add(blackCellItem);
    }

    private void setListeners() {
        numRow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    numRow.setEditable(true);
                } else {
                    numRow.setEditable(false);
                    Utils.showError("Introduzca solo números");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        numColumn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    numColumn.setEditable(true);
                } else {
                    numColumn.setEditable(false);
                    Utils.showError("Introduzca solo números");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numRow.getText().isEmpty() || numColumn.getText().isEmpty()) {
                    Utils.showError("No se ha indicado alguno de los tamaños solicitados");
                } else if (Integer.valueOf(numRow.getText()) < 3 || Integer.valueOf(numColumn.getText()) < 3) {
                    Utils.showError("El tamaño debe ser mínimo 3x3");
                } else {
                    numRow.setFocusable(false);
                    numColumn.setFocusable(false);
                    String kakuro = Integer.valueOf(numRow.getText()) + "," + Integer.valueOf(numColumn.getText()) + "\n";
                    aceptar.setVisible(false);
                    textSize.setVisible(false);
                    setBoard(kakuro);
                    sizeRow = Integer.valueOf(numRow.getText());
                    sizeColumn = Integer.valueOf(numColumn.getText());
                }
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String boardKakuro = null;
                try {
                    boardKakuro = ((KakuroBoard) board.getComponent(0)).boardToString();
                    ctrlUI.setKakuro(boardKakuro);
                    if (!ctrlUI.validate()) {
                        Utils.showError("El kakuro creado no es válido");
                    } else {
                        ctrlUI.toAskSave("¿Desea guardar el kakuro creado?", boardKakuro, 1);
                    }
                } catch (NoTypeCellException noTypeCellException) {
                    Utils.showError("No se han rellenado todas las celdas");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUI.toMain();
            }
        });
    }

    private void listenersCells() {
        for (int i = 0; i < cells.length; ++i) {
            int finalI = i;
            cells[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showMenu(e);
                    cells[finalI].requestFocus();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    popupMenu.setVisible(false);
                    popupMenuOnlyBlackLeft.setVisible(false);
                    popupMenuOnlyBlackTop.setVisible(false);
                    showMenu(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    showMenu(e);
                }

                private void showMenu(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        if (((KakuroCell) cells[finalI]).getPosX() == 0) {
                            popupMenuOnlyBlackTop.setLocation(e.getLocationOnScreen());
                            popupMenuOnlyBlackTop.setVisible(true);
                        } else if (((KakuroCell) cells[finalI]).getPosY() == 0) {
                            popupMenuOnlyBlackLeft.setLocation(e.getLocationOnScreen());
                            popupMenuOnlyBlackLeft.setVisible(true);
                        } else {
                            popupMenu.setLocation(e.getLocationOnScreen());
                            popupMenu.setVisible(true);
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    showMenu(e);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    showMenu(e);
                }
            });

            cells[i].addFocusListener(new FocusListener() {
                private Color color;

                @Override
                public void focusGained(FocusEvent e) {
                    color = cells[finalI].getBackground();
                    posX = ((KakuroCell) cells[finalI]).getPosX();
                    posY = ((KakuroCell) cells[finalI]).getPosY();
                    cells[finalI].setBackground(Color.decode(Utils.colorSelectedCell));
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (cells[finalI].getBackground().equals(Color.decode(Utils.colorSelectedCell)))
                        cells[finalI].setBackground(color);
                }
            });
            if (cells[i] instanceof KakuroWhiteCell) {
                KakuroWhiteCell whiteCell = (KakuroWhiteCell) cells[i];
                whiteCell.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        int value = 0;
                        int keyCode = e.getKeyCode();
                        if (keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1) value = 1;
                        if (keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2) value = 2;
                        if (keyCode == KeyEvent.VK_3 || keyCode == KeyEvent.VK_NUMPAD3) value = 3;
                        if (keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) value = 4;
                        if (keyCode == KeyEvent.VK_5 || keyCode == KeyEvent.VK_NUMPAD5) value = 5;
                        if (keyCode == KeyEvent.VK_6 || keyCode == KeyEvent.VK_NUMPAD6) value = 6;
                        if (keyCode == KeyEvent.VK_7 || keyCode == KeyEvent.VK_NUMPAD7) value = 7;
                        if (keyCode == KeyEvent.VK_8 || keyCode == KeyEvent.VK_NUMPAD8) value = 8;
                        if (keyCode == KeyEvent.VK_9 || keyCode == KeyEvent.VK_NUMPAD9) value = 9;
                        if (value != 0) {
                            whiteCell.setBackground(Color.decode(Utils.colorSelectedCell));
                            whiteCell.setValue(value);
                        }
                    }
                });
            }
            if (cells[i] instanceof KakuroBlackCell) {
                KakuroBlackCell blackCell = (KakuroBlackCell) cells[i];
                blackCell.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        int value = 0;
                        int keyCode = e.getKeyCode();
                        if (keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1) value = 1;
                        if (keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2) value = 2;
                        if (keyCode == KeyEvent.VK_3 || keyCode == KeyEvent.VK_NUMPAD3) value = 3;
                        if (keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) value = 4;
                        if (keyCode == KeyEvent.VK_5 || keyCode == KeyEvent.VK_NUMPAD5) value = 5;
                        if (keyCode == KeyEvent.VK_6 || keyCode == KeyEvent.VK_NUMPAD6) value = 6;
                        if (keyCode == KeyEvent.VK_7 || keyCode == KeyEvent.VK_NUMPAD7) value = 7;
                        if (keyCode == KeyEvent.VK_8 || keyCode == KeyEvent.VK_NUMPAD8) value = 8;
                        if (keyCode == KeyEvent.VK_9 || keyCode == KeyEvent.VK_NUMPAD9) value = 9;
                        if (value != 0) {
                            blackCell.setBackground(Color.decode(Utils.colorSelectedCell));
                            blackCell.setRow(value);
                        }
                    }
                });
            }
        }
    }

    private void setBoard(String kakuro) {
        kBoard = new KakuroBoard(kakuro);
        board.add(kBoard);
        board.validate();
        cells = kBoard.getComponents();
        listenersCells();
    }

    public JPanel getDefaultPanel() {
        return panel1;
    }

    private void createUIComponents() {
        board = new JPanel();
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
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973532));
        panel1.setMaximumSize(new Dimension(1200, 800));
        panel1.setMinimumSize(new Dimension(1200, 800));
        panel1.setPreferredSize(new Dimension(1200, 800));
        board.setOpaque(true);
        panel1.add(board, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(800, -1), new Dimension(800, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(32, -1), new Dimension(32, -1), new Dimension(32, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(11, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setAutoscrolls(false);
        panel2.setBackground(new Color(-12828863));
        panel2.setDoubleBuffered(false);
        panel2.setForeground(new Color(-4473925));
        panel2.setOpaque(false);
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rowSize = new JPanel();
        rowSize.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        rowSize.setBackground(new Color(-1973532));
        panel2.add(rowSize, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rowSizeText = new JLabel();
        rowSizeText.setText("Número de filas:");
        rowSize.add(rowSizeText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numRow = new JTextField();
        numRow.setHorizontalAlignment(0);
        numRow.setOpaque(false);
        rowSize.add(numRow, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        columnSize = new JPanel();
        columnSize.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        columnSize.setAutoscrolls(true);
        columnSize.setBackground(new Color(-1973532));
        panel2.add(columnSize, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        columnSizeText = new JLabel();
        columnSizeText.setText("Número de columnas:");
        columnSize.add(columnSizeText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numColumn = new JTextField();
        numColumn.setHorizontalAlignment(0);
        numColumn.setOpaque(false);
        columnSize.add(numColumn, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), new Dimension(-1, 15), new Dimension(-1, 15), 0, false));
        textSize = new JLabel();
        textSize.setText("Indique el tamaño del tablero a crear:");
        panel2.add(textSize, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 25), new Dimension(-1, 25), new Dimension(-1, 25), 0, false));
        confirm = new JButton();
        confirm.setBorderPainted(false);
        confirm.setContentAreaFilled(false);
        confirm.setFocusPainted(false);
        confirm.setText("Confirmar");
        confirm.setVerticalAlignment(0);
        panel2.add(confirm, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancel = new JButton();
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.setFocusPainted(false);
        cancel.setText("Cancelar");
        panel2.add(cancel, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 150), new Dimension(-1, 150), new Dimension(-1, 200), 0, false));
        aceptar = new JButton();
        aceptar.setBorderPainted(false);
        aceptar.setContentAreaFilled(false);
        aceptar.setFocusPainted(false);
        aceptar.setText("Aceptar");
        aceptar.setVerticalAlignment(0);
        panel2.add(aceptar, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        logo = new JLabel();
        logo.setText("");
        panel1.add(logo, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 35), new Dimension(-1, 35), new Dimension(-1, 35), 0, false));
        config = new JButton();
        config.setBorderPainted(false);
        config.setContentAreaFilled(false);
        config.setFocusPainted(false);
        config.setFocusable(false);
        config.setText("\uF013");
        panel1.add(config, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, 40), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
