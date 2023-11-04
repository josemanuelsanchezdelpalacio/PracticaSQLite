package code;

import libs.Leer;

import java.sql.*;
import java.util.ArrayList;

/**listar los profesores que imparten clase en una determinada especialidad que el usuario elegirá de la lista
 * de especialidades posibles que se le mostrarán por pantalla**/
public class ListarProfesores {

    public static void consultarProfesores() {
        String especialidad = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean validacion = true;

        try (Connection con = ConexionSQLite.conectar()) {
            try (Statement consultaEspecialidad = con.createStatement()) {

                //listo las especialidades para mostrarlas
                ResultSet datosEspecialidad = consultaEspecialidad.executeQuery("SELECT * FROM C1_ESPECIALIDAD");
                while (datosEspecialidad.next()) {
                    System.out.println(datosEspecialidad.getString("ESPECIALIDAD"));
                }

                do {
                    validacion = true;
                    //pido al usuario una especialidad para buscarla a traves de una consulta
                    especialidad = Leer.pedirCadena("Elije una especialidad para mostrar a los profesores con esa esp. :");

                    //comprueba la especialidad usando las tablas C1_PROFESORES Y C1_ESPECIALIDAD buscando la especialidad por posicion
                    ps = con.prepareStatement("SELECT * FROM C1_PROFESORES p, C1_ESPECIALIDAD e WHERE p.ESPECIALIDAD=e.ESPECIALIDAD AND e.ESPECIALIDAD=?;");
                    ps.setString(1, especialidad);
                    rs = ps.executeQuery();
                    boolean especialidadEncontrada = false;

                    // muestro los profesores con esa especialidad
                    while (rs.next()) {
                        especialidadEncontrada = true;
                        System.out.println("Codigo profesor: " + rs.getInt("COD_PROF") + ", Nombre Profesor: " + rs.getString("NOMBRE_APE") + ", ESPECIALIDAD: " + rs.getString("ESPECIALIDAD"));
                    }

                    // hago las validaciones si no existe y si lo que introduce el usuario no sea mayor a dos letras
                    if (!especialidadEncontrada) {
                        validacion = false;
                        System.out.println("Esa especialidad no existe");
                    }
                    if (especialidad.length() > 2) {
                        validacion = false;
                        System.out.println("Tiene que ser como máximo dos letras");
                    }
                } while (!validacion);

            } catch (SQLSyntaxErrorException e) {
                System.out.println("Error en la sintaxis de la sentencia SQL: " + e.getMessage());
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("La sentencia SQL no cumple con los requisitos de integridad de la base de datos: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos: " + e.getMessage());
        }
    }
}
