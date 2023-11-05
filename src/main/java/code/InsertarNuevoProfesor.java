package code;

import javaBeans.Profesores;
import libs.Leer;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InsertarNuevoProfesor {

    public static void insertarProf() {

        int COD_PROF = 0, JEF_DEP = 0, COD_CENTRO = 0;
        String NOMBRE_APE = "", ESPECIALIDAD = "", SEXO = "", FECHA_NAC = null;
        PreparedStatement ps;
        ResultSet rs;
        boolean validacion;
        try (Connection conexion = ConexionSQLite.conectar()) {
            try (Statement consultaEspecialidad = conexion.createStatement()) {

                //comprobaciones para el codigo del profesor
                do {
                    validacion = true;
                    COD_PROF = Leer.pedirEntero("Introduzca codigo de profesor: ");
                    ps = conexion.prepareStatement("SELECT COD_PROF FROM C1_PROFESORES WHERE COD_PROF = ?;");
                    ps.setInt(1, COD_PROF);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        validacion = false;
                        System.out.println("El codigo ya existe");
                    }
                    if ((COD_PROF + "").length() > 4) {
                        validacion = false;
                        System.out.println("El codigo no puede tener mas de 4 numeros");
                    }
                } while (!validacion);

                //comprobaciones para la especialidad del profesor
                ResultSet datosEspecialidad = consultaEspecialidad.executeQuery("SELECT * FROM C1_ESPECIALIDAD");
                System.out.println("Lista de especialidades");
                while (datosEspecialidad.next()) {
                    System.out.print(datosEspecialidad.getString("ESPECIALIDAD") + " - ");
                }
                do {
                    validacion = true;
                    ESPECIALIDAD = Leer.pedirCadena("\nIntroduzca especialidad: ");
                    ps = conexion.prepareStatement("SELECT ESPECIALIDAD FROM C1_ESPECIALIDAD WHERE ESPECIALIDAD = ?;");
                    ps.setString(1, ESPECIALIDAD);
                    rs = ps.executeQuery();

                    if (!rs.next()) {
                        validacion = false;
                        System.out.println("Esa especialidad no existe");
                    }

                    if (ESPECIALIDAD.length() > 2) {
                        validacion = false;
                        System.out.println("Tiene que ser como maximo dos letras");
                    }
                } while (!validacion);

                //comprobaciones nombre del profesor
                do {
                    validacion = true;
                    NOMBRE_APE = Leer.pedirCadena("Introduzca nombre del profesor: ");
                    if (NOMBRE_APE.length() > 30) {
                        validacion = false;
                        System.out.println("El nombre no puede tener mas de 30 letras");
                    }
                } while (!validacion);

                //comprobaciones para el codigo del jefe de departamento
                do {
                    validacion = true;
                    JEF_DEP = Leer.pedirEntero("Introduzca codigo del jefe de departamento: ");
                    ps = conexion.prepareStatement("SELECT JEFE_DEP FROM C1_PROFESORES WHERE JEFE_DEP = ?;");
                    ps.setInt(1, JEF_DEP);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        validacion = false;
                        System.out.println("El codigo ya existe");
                    }
                    if ((JEF_DEP + "").length() > 4) {
                        validacion = false;
                        System.out.println("El codigo tiene mas de 4 cifras");
                    }
                } while (!validacion);

                //comprobaciones para la fecha de nacimiento
                do {
                    validacion = true;
                    FECHA_NAC = Leer.pedirCadena("Introduzca fecha de nacimiento (yyyy-MM-dd): ");

                    if (FECHA_NAC.length() != 10) {
                        validacion = false;
                        System.out.println("La fecha debe tener exactamente 10 caracteres (yyyy-MM-dd).");
                    }
                } while (!validacion);

                //comprobaciones del sexo del profesor
                do {
                    validacion = true;
                    SEXO = Leer.pedirCadena("Introduzca sexo del profesor: ");
                    if (!SEXO.equals("H") && !SEXO.equals("M")) {
                        validacion = false;
                        System.out.println("Tiene que ser H o M");
                    }
                    if (SEXO.length() > 1) {
                        validacion = false;
                        System.out.println("No puede ser mas largo que una letra (H-M)");
                    }
                } while (!validacion);

                //comprobaciones para el codigo del profesor
                do {
                    validacion = true;
                    COD_CENTRO = Leer.pedirEntero("Introduzca codigo del centro: ");
                    ps = conexion.prepareStatement("SELECT COD_CENTRO FROM C1_PROFESORES WHERE COD_CENTRO = ?;");
                    ps.setInt(1, COD_CENTRO);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        validacion = false;
                        System.out.println("El codigo ya existe");
                    }
                    if ((COD_CENTRO + "").length() > 4) {
                        validacion = false;
                        System.out.println("El codigo no puede tener mas de 4 numeros");
                    }
                } while (!validacion);

                //insertamos los datos del profesor en la base de datos
                ps = conexion.prepareStatement("INSERT INTO C1_PROFESORES VALUES (?,?,?,?,?,?,?)");
                ps.setInt(1, COD_PROF);
                ps.setString(2, NOMBRE_APE);
                ps.setString(3, ESPECIALIDAD);
                ps.setInt(4, JEF_DEP);
                ps.setString(5, String.valueOf(FECHA_NAC));
                ps.setString(6, SEXO);
                ps.setInt(7, COD_CENTRO);
                ps.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

