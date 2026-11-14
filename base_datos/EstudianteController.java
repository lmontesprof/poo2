package base_datos;

import java.sql.SQLException;
import java.util.List;

public class EstudianteController {

    private final EstudianteDAO estudianteDAO;

    public EstudianteController() {
        this.estudianteDAO = new EstudianteDAO();
    }

    public String registrarEstudiante(String documento, String nombre, String programa, double nota) {
        if (documento == null) {
            return "Error: El documento no puede esta vacío";
        }

        try {
            Estudiante nuevo = new Estudiante(documento, nombre, programa, nota);
            boolean guardado = estudianteDAO.insertarEstudiante(nuevo);
            return guardado ? "Estudiante registrado": "Estudiante No registrado";

        } catch (SQLException e) {
            return "Error basee de datos " + e.getMessage();
        }

        
    }

    public List<Estudiante> obtenerEstudiantes() throws SQLException{
        return estudianteDAO.listarTodos();
    }

}
