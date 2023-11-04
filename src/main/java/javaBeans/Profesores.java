package javaBeans;

import java.util.Date;

public class Profesores {

    private int COD_PROF, JEFE_DEP, COD_CENTRO;
    private String NOMBRE_APE;
    private char ESPECIALIDAD, SEXO;
    private Date FECHA_NAC;

    public Profesores(int COD_PROF, int JEFE_DEP, String NOMBRE_APE, char ESPECIALIDAD, char SEXO, Date FECHA_NAC, int COD_CENTRO) {
        this.COD_PROF = COD_PROF;
        this.JEFE_DEP = JEFE_DEP;
        this.NOMBRE_APE = NOMBRE_APE;
        this.ESPECIALIDAD = ESPECIALIDAD;
        this.SEXO = SEXO;
        this.FECHA_NAC = FECHA_NAC;
        this.COD_CENTRO = COD_CENTRO;
    }

    public Profesores() {}

    public int getCOD_PROF() {
        return COD_PROF;
    }

    public void setCOD_PROF(int COD_PROF) {
        this.COD_PROF = COD_PROF;
    }

    public int getJEFE_DEP() {
        return JEFE_DEP;
    }

    public void setJEFE_DEP(int JEFE_DEP) {
        this.JEFE_DEP = JEFE_DEP;
    }

    public String getNOMBRE_APE() {
        return NOMBRE_APE;
    }

    public void setNOMBRE_APE(String NOMBRE_APE) {
        this.NOMBRE_APE = NOMBRE_APE;
    }

    public char getESPECIALIDAD() {
        return ESPECIALIDAD;
    }

    public void setESPECIALIDAD(char ESPECIALIDAD) {
        this.ESPECIALIDAD = ESPECIALIDAD;
    }

    public char getSEXO() {
        return SEXO;
    }

    public void setSEXO(char SEXO) {
        this.SEXO = SEXO;
    }

    public Date getFECHA_NAC() {
        return FECHA_NAC;
    }

    public void setFECHA_NAC(Date FECHA_NAC) {
        this.FECHA_NAC = FECHA_NAC;
    }

    public int getCOD_CENTRO() {
        return COD_CENTRO;
    }

    public void setCOD_CENTRO(int COD_CENTRO) {
        this.COD_CENTRO = COD_CENTRO;
    }
}
