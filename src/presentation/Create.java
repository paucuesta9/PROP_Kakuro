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
        config.setBorder(new EmptyBorder(10,0,0,22));

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
                    String kakuro = Integer.valueOf(numRow.getText()) + "," + Integer.valueOf(numColumn.getText())+ "\n";
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
                        Generate2 a = new Generate2("¿Desea guardar el kakuro creado?", boardKakuro, 1);
                        a.drawGenerate2(frame);
                    }
                } catch (NoTypeCellException noTypeCellException) {
                    Utils.showError("No se han rellenado todas las celdas");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main m = new Main();
                m.drawMain(frame);
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

    private void createUIComponents() {
        board = new JPanel();
    }

    public void drawCreate(JFrame frame) {
        this.frame = frame;
        frame.setTitle("Create");
        frame.setContentPane(panel1);
        frame.setVisible(true);
    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("Create");
        frame.setContentPane(new Create().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        Utils.center(frame);
        frame.setVisible(true);
    }
}
