import code.CrearBD;
import code.InsertarNuevoProfesor;
import code.ListarProfesores;
import libs.Leer;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        String opcion;
        do {
            System.out.println("0. Salir \n" +
                    "1. Crear BD y datos adjuntos \n" +
                    "2. Borrar BD \n" +
                    "3. Listar profesores a traves de especialidad \n" +
                    "4. Insertar nuevo profesor \n"
            );

            opcion = Leer.pedirCadena("Introduce una opción: ");

            switch (opcion) {
                case "0" -> {salir = true;}
                case "1" -> {CrearBD.crearBD(); CrearBD.cargarAsignaturas(); CrearBD.cargarCentros(); CrearBD.cargarEspecialidad(); CrearBD.cargarProfesores(); CrearBD.cargarAsignaturasProfesores();}
                case "2" -> {CrearBD.borrarBD();}
                case "3" -> {ListarProfesores.consultarProfesores();}
                case "4" -> {InsertarNuevoProfesor.insertarProf();}
                default -> {System.out.println("La opcion seleccionada no existe");}
            }
        } while (!salir);
    }
}