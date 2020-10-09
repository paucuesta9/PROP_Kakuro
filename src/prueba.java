import java.util.List;

public class prueba {
    private String nombre;
    private int num;
    private List<Integer> lista_nums;
    private String nuevo_string;

    public prueba(String nombre, int num, List<Integer> lista_nums) {
        this.nombre = nombre;
        this.num = num;
        this.lista_nums = lista_nums;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Integer> getLista_nums() {
        return lista_nums;
    }

    public void setLista_nums(List<Integer> lista_nums) {
        this.lista_nums = lista_nums;
    }

    public void nuevaFuncion() {
        int sum = 2 * 1;
    }
}
