package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domain.classes.Exceptions.NoTypeCellException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/** @file Create.java
 @class Create
 */

/**
 * @brief Pantalla que permite crear un kakuro
 * @author Pau Cuesta Arcos
 */

public class Create {
    private JPanel panel1;
    private JButton config;
    private JPanel panelB;
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
    private JButton ImportButton;
    private JLabel labelO;
    private JPanel board;

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
        setListeners();

        panelB.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode(Utils.colorDarkBlue)));

        Utils.setButtons(ImportButton);
        ImportButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rectangulo-azul.png")));

        labelO.setFont(Utils.roboto);
        labelO.setForeground(Color.BLACK);

        textSize.setFont(Utils.roboto.deriveFont(18f));
        textSize.setForeground(Color.BLACK);

        rowSizeText.setForeground(Color.BLACK);
        rowSizeText.setFont(Utils.roboto.deriveFont(18f));

        logo.setIcon(Utils.getLogo());

        numRow.setForeground(Color.BLACK);
        numRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        numRow.setFont(Utils.roboto.deriveFont(18f));

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
        confirm.setEnabled(false);
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
                SetValues setValues = new SetValues(1);
                int[] value = setValues.drawSetValues();
                if (value[0] > 0 && value[0] < 10) {
                    cells[pos] = new KakuroWhiteCell(posX, posY, cells[pos].getSize().width);
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
                    listenersCells();
                }
                panel1.requestFocus();
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
                SetValues setValues = new SetValues(2);
                int[] value = setValues.drawSetValues();
                if (value[0] != -1) {
                    cells[pos] = new KakuroBlackCell(posX, posY, cells[pos].getSize().width);
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
                panel1.requestFocus();
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
                SetValues setValues = new SetValues(3);
                int[] value = setValues.drawSetValues();
                if (value[0] != -1) {
                    cells[pos] = new KakuroBlackCell(posX, posY, cells[pos].getSize().width);
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
                panel1.requestFocus();
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
                if (posY != 0) {
                    SetValues setValues = new SetValues(4);
                    int[] value = setValues.drawSetValues();
                    if (value[0] != -1) {
                        cells[pos] = new KakuroBlackCell(posX, posY, cells[pos].getSize().width);
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
                } else {
                    cells[pos] = new KakuroBlackCell(posX, posY, cells[pos].getSize().width);
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
                panel1.requestFocus();
            }
        });
        popupMenuOnlyBlackLeft.add(blackCellItemLeft);
        popupMenuOnlyBlackTop.add(blackCellItemTop);
        popupMenu.add(blackCellItem);
    }

    private void setListeners() {
        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                Config configMen = new Config();
                configMen.drawConfig();
                configMen.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        String kakuroBoard = null;
                        try {
                            kakuroBoard = ((KakuroBoard) board.getComponent(0)).boardToString(0);
                        } catch (NoTypeCellException noTypeCellException) {
                            noTypeCellException.printStackTrace();
                        }
                        board.removeAll();
                        setBoard(kakuroBoard);
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });

        ImportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "TXT Files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(panel1);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String absolutePath = chooser.getSelectedFile().getAbsolutePath();
                    if (absolutePath.contains(System.getProperty("user.dir")))
                        Utils.showError("Por favor, indique un Kakuro suyo y no de la base de datos de la aplicación");
                    else {
                        try {
                            numRow.setFocusable(false);
                            numColumn.setFocusable(false);
                            ctrlUI.findKakuro(absolutePath);
                            aceptar.setVisible(false);
                            textSize.setVisible(false);
                            setBoard(ctrlUI.getKakuro());
                            sizeRow = kBoard.getRowSize();
                            sizeColumn = kBoard.getColumnSize();
                            numRow.setText(String.valueOf(sizeRow));
                            numColumn.setText(String.valueOf(sizeColumn));
                        } catch (NumberFormatException exception) {
                            Utils.showError("El fichero contiene carácteres no permitidos");
                        }
                    }
                }
            }
        });

        numRow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
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

        numRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
            }
        });

        numColumn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
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

        numColumn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
            }
        });

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                if (numRow.getText().isEmpty() || numColumn.getText().isEmpty()) {
                    Utils.showError("No se ha indicado alguno de los tamaños solicitados");
                } else if (Integer.parseInt(numRow.getText()) < 3 || Integer.parseInt(numColumn.getText()) < 3) {
                    Utils.showError("El tamaño debe ser mínimo 3x3");
                } else if (Integer.parseInt(numRow.getText()) > 60 || Integer.parseInt(numColumn.getText()) > 60) {
                    Utils.showError("El tamaño debe ser máximo 60");
                } else {
                    numRow.setFocusable(false);
                    numColumn.setFocusable(false);
                    ImportButton.setVisible(false);
                    labelO.setVisible(false);
                    String kakuro = Integer.valueOf(numRow.getText()) + "," + Integer.valueOf(numColumn.getText()) + "\n";
                    aceptar.setVisible(false);
                    textSize.setVisible(false);
                    setBoard(kakuro);
                    sizeRow = Integer.valueOf(numRow.getText());
                    sizeColumn = Integer.valueOf(numColumn.getText());
                    confirm.setEnabled(true);
                }
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                String boardKakuro = null;
                try {
                    boardKakuro = ((KakuroBoard) board.getComponent(0)).boardToString(1);
                    ctrlUI.setKakuro(boardKakuro);
                    if (!ctrlUI.validate()) {
                        Utils.showError("El kakuro creado no es válido");
                    } else {
                        ctrlUI.resolve();
                        ctrlUI.addCreatedToPlayer();
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
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
                ctrlUI.toMain();
            }
        });

        panel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.setVisible(false);
                popupMenuOnlyBlackLeft.setVisible(false);
                popupMenuOnlyBlackTop.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
                    cells[finalI].requestFocus();
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
        panel1.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1973532));
        panel1.setForeground(new Color(-4473925));
        panel1.setMaximumSize(new Dimension(1200, 800));
        panel1.setMinimumSize(new Dimension(1200, 800));
        panel1.setPreferredSize(new Dimension(1200, 800));
        panelB = new JPanel();
        panelB.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelB.setBackground(new Color(-1118482));
        panelB.setForeground(new Color(-4473925));
        panelB.setOpaque(true);
        panel1.add(panelB, new GridConstraints(0, 0, 7, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        board.setBackground(new Color(-1118482));
        panelB.add(board, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(800, -1), null, new Dimension(800, 800), 0, false));
        final Spacer spacer1 = new Spacer();
        panelB.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelB.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(4, 1, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(32, -1), new Dimension(32, -1), new Dimension(32, -1), 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(2, 3, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(11, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setAutoscrolls(false);
        panel2.setBackground(new Color(-12828863));
        panel2.setDoubleBuffered(false);
        panel2.setForeground(new Color(-4473925));
        panel2.setOpaque(false);
        panel1.add(panel2, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rowSize = new JPanel();
        rowSize.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        rowSize.setBackground(new Color(-1973532));
        panel2.add(rowSize, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rowSizeText = new JLabel();
        rowSizeText.setText("Número de filas:");
        rowSize.add(rowSizeText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numRow = new JTextField();
        numRow.setHorizontalAlignment(0);
        numRow.setOpaque(false);
        rowSize.add(numRow, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        columnSize = new JPanel();
        columnSize.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        columnSize.setAutoscrolls(true);
        columnSize.setBackground(new Color(-1973532));
        panel2.add(columnSize, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        columnSizeText = new JLabel();
        columnSizeText.setText("Número de columnas:");
        columnSize.add(columnSizeText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numColumn = new JTextField();
        numColumn.setHorizontalAlignment(0);
        numColumn.setOpaque(false);
        columnSize.add(numColumn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), new Dimension(-1, 15), new Dimension(-1, 15), 0, false));
        textSize = new JLabel();
        textSize.setText("Indique el tamaño del tablero a crear:");
        panel2.add(textSize, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 25), new Dimension(-1, 25), new Dimension(-1, 25), 0, false));
        confirm = new JButton();
        confirm.setBorderPainted(false);
        confirm.setContentAreaFilled(false);
        confirm.setFocusPainted(false);
        confirm.setText("Confirmar");
        confirm.setVerticalAlignment(0);
        panel2.add(confirm, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancel = new JButton();
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.setFocusPainted(false);
        cancel.setText("Cancelar");
        panel2.add(cancel, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel2.add(spacer6, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        final Spacer spacer7 = new Spacer();
        panel2.add(spacer7, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        aceptar = new JButton();
        aceptar.setBorderPainted(false);
        aceptar.setContentAreaFilled(false);
        aceptar.setFocusPainted(false);
        aceptar.setText("Aceptar");
        aceptar.setVerticalAlignment(0);
        panel2.add(aceptar, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        panel2.add(spacer8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        panel2.add(spacer9, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        logo = new JLabel();
        logo.setText("");
        panel1.add(logo, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(252, 200), new Dimension(252, 200), new Dimension(252, 200), 1, false));
        final Spacer spacer10 = new Spacer();
        panel1.add(spacer10, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 35), new Dimension(-1, 35), new Dimension(-1, 35), 0, false));
        config = new JButton();
        config.setBorderPainted(false);
        config.setContentAreaFilled(false);
        config.setFocusPainted(false);
        config.setFocusable(false);
        config.setText("\uF013");
        panel1.add(config, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, 40), null, 0, false));
        ImportButton = new JButton();
        ImportButton.setActionCommand("Importar Kakuro");
        ImportButton.setBorderPainted(false);
        ImportButton.setContentAreaFilled(false);
        ImportButton.setFocusable(false);
        ImportButton.setText("Añadir Kakuro");
        panel1.add(ImportButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelO = new JLabel();
        labelO.setText("o");
        panel1.add(labelO, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        panel1.add(spacer11, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 20), new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
