package code;

import libs.Leer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static libs.FicheroEscribible.ficheroLegible;

public class CrearBD {

    public static void crearBD() {
        try (Connection conexion = ConexionSQLite.conectar()) {
            if (conexion != null) {
                //objeto DatabaseMetaData para obtener la informacion de la BD
                DatabaseMetaData meta = conexion.getMetaData();
                System.out.println("BD creada");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la BD: " + e.getMessage());
        }
    }

    public static void borrarBD() {
        Path bd = Path.of(Leer.pedirCadena("Introduce la ruta de la BD: "));

        if (ficheroLegible(bd)) {
            try {
                Files.delete(bd);
                System.out.println("La BD ha sido eliminada");
            } catch (IOException e) {
                System.err.println("Error al intentar eliminar la BD: " + e.getMessage());
            }
        }
    }

    public static void cargarAsignaturas() {
        try (Connection con = ConexionSQLite.conectar()) {
            try (Statement crearTablaC1AsigProf = con.createStatement()) {
                // Borrar datos anteriores si existen
                crearTablaC1AsigProf.executeUpdate("DROP TABLE IF EXISTS C1_ASIGNATURAS");

                // Crear la tabla C1_ASIGNATURAS
                String tablaC1Asignaturas = "CREATE TABLE IF NOT EXISTS C1_ASIGNATURAS (" +
                        "COD_ASIG CHAR(6) NOT NULL PRIMARY KEY," +
                        "NOMBRE_ASI VARCHAR(30)" +
                        ");";
                crearTablaC1AsigProf.executeUpdate(tablaC1Asignaturas);

                ArrayList<String> inserciones = DatosTablas.getAsignaturas();
                for (String d : inserciones) {
                    crearTablaC1AsigProf.executeUpdate(d);
                }
            } catch (SQLSyntaxErrorException e) {
                System.out.println("Error en la sintaxis de la sentencia SQL: " + e.getMessage());
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("La sentencia SQL no cumple con los requisitos de integridad de la base de datos: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void cargarCentros() {
        try (Connection con = ConexionSQLite.conectar()) {
            try (Statement crearTablaC1Centros = con.createStatement()) {
                // Borrar datos anteriores si existen
                crearTablaC1Centros.executeUpdate("DROP TABLE IF EXISTS C1_CENTROS");

                // Crear la tabla C1_CENTROS
                String tablaC1Centros = "CREATE TABLE IF NOT EXISTS C1_CENTROS (" +
                        "COD_CENTRO NUMERIC(4) NOT NULL PRIMARY KEY," +
                        "NOM_CENTRO VARCHAR(20)," +
                        "DIRECTOR NUMERIC(4)," +
                        "DIRECCION VARCHAR(25)," +
                        "LOCALIDAD VARCHAR(20)," +
                        "PROVINCIA VARCHAR(20)" +
                        ");";
                crearTablaC1Centros.executeUpdate(tablaC1Centros);

                ArrayList<String> inserciones = DatosTablas.getCentros();
                for (String d : inserciones) {
                    crearTablaC1Centros.executeUpdate(d);
                }
            } catch (SQLSyntaxErrorException e) {
                System.out.println("Error en la sintaxis de la sentencia SQL: " + e.getMessage());
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("La sentencia SQL no cumple con los requisitos de integridad de la base de datos: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void cargarEspecialidad() {
        try (Connection con = ConexionSQLite.conectar()) {
            try (Statement crearTablaC1Especialidad = con.createStatement()) {
                // Borrar datos anteriores si existen
                crearTablaC1Especialidad.executeUpdate("DROP TABLE IF EXISTS C1_ESPECIALIDAD");

                // Crear la tabla C1_ESPECIALIDAD
                String tablaC1Especialidad = "CREATE TABLE IF NOT EXISTS C1_ESPECIALIDAD (" +
                        "ESPECIALIDAD CHAR(2) NOT NULL PRIMARY KEY," +
                        "NOMBRE_ESPE VARCHAR(25)" +
                        ");";
                crearTablaC1Especialidad.executeUpdate(tablaC1Especialidad);

                ArrayList<String> inserciones = DatosTablas.getEspecialidad();
                for (String d : inserciones) {
                    crearTablaC1Especialidad.executeUpdate(d);
                }
            } catch (SQLSyntaxErrorException e) {
                System.out.println("Error en la sintaxis de la sentencia SQL: " + e.getMessage());
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("La sentencia SQL no cumple con los requisitos de integridad de la base de datos: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void cargarProfesores() {
        try (Connection con = ConexionSQLite.conectar()) {
            try (Statement crearTablaC1Profesores = con.createStatement()) {
                // Borrar datos anteriores si existen
                crearTablaC1Profesores.executeUpdate("DROP TABLE IF EXISTS C1_PROFESORES");

                // Crear la tabla C1_PROFESORES
                String tablaC1Profesores = "CREATE TABLE IF NOT EXISTS C1_PROFESORES (" +
                        "COD_PROF NUMERIC(4) NOT NULL PRIMARY KEY," +
                        "NOMBRE_APE VARCHAR(30)," +
                        "ESPECIALIDAD CHAR(2) REFERENCES C1_ESPECIALIDAD(ESPECIALIDAD)," +
                        "JEFE_DEP NUMERIC(4)," +
                        "FECHA_NAC DATE," +
                        "SEXO CHAR(1)," +
                        "COD_CENTRO NUMERIC(4) NOT NULL REFERENCES C1_CENTROS (COD_CENTRO)" +
                        ");";
                crearTablaC1Profesores.executeUpdate(tablaC1Profesores);

                ArrayList<String> inserciones = DatosTablas.getProfesores();
                for (String d : inserciones) {
                    crearTablaC1Profesores.executeUpdate(d);
                }
            } catch (SQLSyntaxErrorException e) {
                System.out.println("Error en la sintaxis de la sentencia SQL: " + e.getMessage());
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("La sentencia SQL no cumple con los requisitos de integridad de la base de datos: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void cargarAsignaturasProfesores() {
        try (Connection con = ConexionSQLite.conectar()) {
            try (Statement crearTablaC1AsigProf = con.createStatement()) {
                // Borrar datos anteriores si existen
                crearTablaC1AsigProf.executeUpdate("DROP TABLE IF EXISTS C1_ASIGPROF");

                // Crear la tabla C1_ASIGPROF
                String tablaC1AsigProf = "CREATE TABLE IF NOT EXISTS C1_ASIGPROF (" +
                        "COD_ASIG CHAR(6) NOT NULL REFERENCES C1_ASIGNATURAS (COD_ASIG)," +
                        "COD_PROF NUMERIC(4) NOT NULL REFERENCES C1_PROFESORES (COD_PROF)," +
                        "PRIMARY KEY (COD_ASIG, COD_PROF)" +
                        ");";
                crearTablaC1AsigProf.executeUpdate(tablaC1AsigProf);

                ArrayList<String> inserciones = DatosTablas.getAsigProf();
                for (String d : inserciones) {
                    crearTablaC1AsigProf.executeUpdate(d);
                }
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

