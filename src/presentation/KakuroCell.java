package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/** @file KakuroCell.java
 @class KakuroCell
 */

/** @brief Clase KakuroCell que carga una celda con sus atributos.
 * @author Judith Almoño Gómez
 */

public class KakuroCell extends JPanel {
    private int posX, posY;

    /** @brief Constructora
     *
     * @param posX representa la posición de la fila
     * @param posY representa la posición de la columna
     * @param size representa el tamaño de la celda
     */
    KakuroCell(int posX, int posY, int size) {
        super();
        this.posX = posX;
        this.posY = posY;

        setBorder(BorderFactory.createLineBorder(Utils.colorBorde));

        setPreferredSize(new Dimension(size, size));
    }

    /** @brief Getter de posX
     *
     * @return la posición de la fila
     */
    public int getPosX() {
        return posX;
    }

    /** @brief Setter de posX
     *
     * @param posX representa la posición de la fila
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /** Getter de posY
     *
     * @return la posición de la columna
     */
    public int getPosY() {
        return posY;
    }

    /** @brief Setter de posY
     *
     * @param posY representa la posición de la columna
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
