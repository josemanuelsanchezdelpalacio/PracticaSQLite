package javaBeans;

public class Asignaturas {

    private char COD_ASIG;
    private String nombre;

    public Asignaturas(char COD_ASIG, String nombre) {
        this.COD_ASIG = COD_ASIG;
        this.nombre = nombre;
    }

    public char getCOD_ASIG() {
        return COD_ASIG;
    }

    public void setCOD_ASIG(char COD_ASIG) {
        this.COD_ASIG = COD_ASIG;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
