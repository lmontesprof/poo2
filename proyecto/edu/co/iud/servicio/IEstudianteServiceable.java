package proyecto.edu.co.iud.servicio;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import proyecto.edu.co.iud.excepciones.ReglaNegocioException;
import proyecto.edu.co.iud.modelo.Estudiante;

public interface IEstudianteServiceable {
    void registrarEstudiante(Estudiante est) 
    throws ReglaNegocioException;

    List<Estudiante> listarTodos();

    List<Estudiante> filtrar(Predicate<Estudiante> criterio);

    List<Estudiante> obtenerCandidatosBeca();
    
    List<Estudiante> obtenerReprobados();

    double calcularPromedioGeneral();

    Map<String, Double> calcularPromedioPorPrograma();


}
