package code;

import javaBeans.Profesores;
import libs.Leer;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertarNuevoProfesor {

    public static void insertarProf() {
        Profesores nuevoProfesor = new Profesores();

        try (Connection conexion = ConexionSQLite.conectar()) {
            if (validarCodigos(nuevoProfesor.getCOD_PROF(), conexion) && validarEspecialidad(String.valueOf(nuevoProfesor.getESPECIALIDAD()), conexion) && validarCodigos(nuevoProfesor.getESPECIALIDAD(), conexion) && validarFechaNacimiento(nuevoProfesor.getFECHA_NAC()) && validarSexo(String.valueOf(nuevoProfesor.getSEXO())) && validarCodigos(nuevoProfesor.getCOD_CENTRO(), conexion)) {

                nuevoProfesor.setCOD_PROF(Leer.pedirEntero("Introduce codigo de profesor: "));
                nuevoProfesor.setJEFE_DEP(Leer.pedirEntero("Introduce codigo del jefe de departamento: "));
                nuevoProfesor.setNOMBRE_APE(Leer.pedirCadena("Introduce nombre del profesor: "));
                nuevoProfesor.setESPECIALIDAD(Leer.pedirChar("Introduce especialidad: "));
                nuevoProfesor.setSEXO(Leer.pedirChar("Introduce sexo del profesor (H/M): "));
                nuevoProfesor.setFECHA_NAC(Leer.pedirFecha("Introduce fecha de nacimiento (yyyy-MM-dd): ", "yyyy-MM-dd"));
                nuevoProfesor.setCOD_PROF(Leer.pedirEntero("Introduce codigo del centro: "));

                PreparedStatement ps = conexion.prepareStatement("INSERT INTO C1_PROFESORES (COD_PROF, NOMBRE_APE, ESPECIALIDAD, JEFE_DEP, FECHA_NAC, SEXO, COD_CENTRO) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, nuevoProfesor.getCOD_PROF());
                ps.setString(2, nuevoProfesor.getNOMBRE_APE());
                ps.setString(3, String.valueOf(nuevoProfesor.getESPECIALIDAD()));
                ps.setInt(4, nuevoProfesor.getJEFE_DEP());
                ps.setString(5, String.valueOf(nuevoProfesor.getFECHA_NAC()));
                ps.setString(6, String.valueOf(nuevoProfesor.getSEXO()));
                ps.setInt(7, nuevoProfesor.getCOD_CENTRO());
                ps.execute();
                System.out.println("Profesor insertado correctamente.");
            } else {
                System.out.println("ERROR. No se pudo insertar el profesor debido a errores de validacion.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar o insertar profesor: " + e.getMessage());
        }
    }

    private static boolean validarCodigos(int codigo, Connection conexion) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("SELECT COD_PROF FROM C1_PROFESORES WHERE COD_PROF = ?;");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("El codigo ya existe: " + codigo);
            return false;
        }
        if (String.valueOf(codigo).length() > 4) {
            System.out.println("El codigo no puede tener más de 4 dígitos: " + codigo);
            return false;
        }
        return true;
    }

    private static boolean validarEspecialidad(String especialidad, Connection conexion) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("SELECT ESPECIALIDAD FROM C1_ESPECIALIDAD WHERE ESPECIALIDAD = ?;");
        ps.setString(1, especialidad);
        ResultSet rs = ps.executeQuery();

        // listo las especialidades para mostrarlas por pantalla
        ResultSet datosEspecialidad = ps.executeQuery("SELECT * FROM C1_ESPECIALIDAD");
        while (datosEspecialidad.next()) {
            System.out.println(datosEspecialidad.getString("ESPECIALIDAD"));
        }

        if (!rs.next()) {
            System.out.println("La especialidad no existe: " + especialidad);
            return false;
        }
        if (especialidad.length() > 2) {
            System.out.println("La especialidad no puede tener mas de 2 caracteres: " + especialidad);
            return false;
        }
        return true;
    }

    private static boolean validarFechaNacimiento(Date fechaNacimiento) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "";

        try {
            dateFormat.parse(String.valueOf(fechaNacimiento));
            if (fecha.length() != 10) {
                System.out.println("La longitud de la fecha debe ser de 10 caracteres: " + fecha);
                return false;
            }
            return true;
        } catch (ParseException e) {
            System.out.println("La fecha de nacimiento no es válida: " + fechaNacimiento);
            return false;
        }
    }

    private static boolean validarSexo(String sexo) {
        if (!sexo.equals("H") && !sexo.equals("M")) {
            System.out.println("El sexo debe ser H o M: " + sexo);
            return false;
        }
        if (sexo.length() > 1) {
            System.out.println("El sexo no puede ser mas largo que una letra (H/M): " + sexo);
            return false;
        }
        return true;
    }
}
