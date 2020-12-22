package domain.classes;

import java.util.Objects;

/** @file Pair.java
 @brief Clase  <em>Pair</em>.
 */

/** @brief Clase Pair, donde ambos elementos son enteros
 * @author Alvaro Armada Ruiz
 */

public class Pair {
    /**
     * first representa al primer entero
     */
    private int first;
    /**
     * second representa al segundo enteor
     */
    private int second;

    /** @brief Creadora de Pair
     *
     * @param first primer entero
     * @param second segundo entero
     */

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    /** @brief getter del primer entero
     *
     * @return el primer entero
     */
    public int getFirst() {
        return first;
    }

    /** @brief setter del primer entero
     *
     * @param first valor para el primer entero
     */
    public void setFirst(int first) {
        this.first = first;
    }

    /** @brief getter del segundo entero
     *
     * @return el segundo entero
     */

    public int getSecond() {
        return second;
    }

    /** @brief setter del segundo entero
     *
     * @param second valor para el segundo entero
     */
    public void setSecond(int second) {
        this.second = second;
    }

    /** @brief función para saber si dos Pairs son iguales
     *
     * @param o Pair a comparar
     * @return cierto si son iguales, falso en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first &&
                second == pair.second;
    }

    /** @brief función para poder introducir Pairs en tablas de Hash y otras estructuras de datos
     *
     * @return identifacador de hash del pair.
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
