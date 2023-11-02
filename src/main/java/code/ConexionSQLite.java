package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {

    //definimos como constantes al archivo con la BD
    private static final String URL = "jdbc:sqlite:target/centros.dat";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL);
            System.err.println("Conexión OK.");
        } catch (SQLException e) {
            System.err.println("Error en la conexion: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error PAM: " + e.getMessage());
        }
        return conexion;
    }

}
