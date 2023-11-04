package javaBeans;

public class Especialidad {

    private char ESPECIALIDAD;
    private String NOMBRE_ESPE;

    public Especialidad(char ESPECIALIDAD, String NOMBRE_ESPE) {
        this.ESPECIALIDAD = ESPECIALIDAD;
        this.NOMBRE_ESPE = NOMBRE_ESPE;
    }

    public char getESPECIALIDAD() {
        return ESPECIALIDAD;
    }

    public void setESPECIALIDAD(char ESPECIALIDAD) {
        this.ESPECIALIDAD = ESPECIALIDAD;
    }

    public String getNOMBRE_ESPE() {
        return NOMBRE_ESPE;
    }

    public void setNOMBRE_ESPE(String NOMBRE_ESPE) {
        this.NOMBRE_ESPE = NOMBRE_ESPE;
    }
}
