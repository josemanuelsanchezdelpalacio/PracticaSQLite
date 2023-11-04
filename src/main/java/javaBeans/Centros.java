package javaBeans;

public class Centros {

    private int COD_CENTRO, DIRECTOR;
    private String NOM_CENTRO, DIRECCION, LOCALIDAD, PROVINCIA;

    public Centros(int COD_CENTRO, int DIRECTOR, String NOM_CENTRO, String DIRECCION, String LOCALIDAD, String PROVINCIA) {
        this.COD_CENTRO = COD_CENTRO;
        this.DIRECTOR = DIRECTOR;
        this.NOM_CENTRO = NOM_CENTRO;
        this.DIRECCION = DIRECCION;
        this.LOCALIDAD = LOCALIDAD;
        this.PROVINCIA = PROVINCIA;
    }

    public int getCOD_CENTRO() {
        return COD_CENTRO;
    }

    public void setCOD_CENTRO(int COD_CENTRO) {
        this.COD_CENTRO = COD_CENTRO;
    }

    public int getDIRECTOR() {
        return DIRECTOR;
    }

    public void setDIRECTOR(int DIRECTOR) {
        this.DIRECTOR = DIRECTOR;
    }

    public String getNOM_CENTRO() {
        return NOM_CENTRO;
    }

    public void setNOM_CENTRO(String NOM_CENTRO) {
        this.NOM_CENTRO = NOM_CENTRO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getLOCALIDAD() {
        return LOCALIDAD;
    }

    public void setLOCALIDAD(String LOCALIDAD) {
        this.LOCALIDAD = LOCALIDAD;
    }

    public String getPROVINCIA() {
        return PROVINCIA;
    }

    public void setPROVINCIA(String PROVINCIA) {
        this.PROVINCIA = PROVINCIA;
    }
}
