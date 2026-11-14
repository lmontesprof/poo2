package proyecto.edu.co.iud.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import proyecto.edu.co.iud.excepciones.ReglaNegocioException;
import proyecto.edu.co.iud.modelo.Estudiante;
import proyecto.edu.co.iud.servicio.IEstudianteServiceable;

public class EstudianteServiceImpl implements IEstudianteServiceable {
    // Implementación de los métodos de la interfaz IEstudianteServiceable

    private final List<Estudiante> repositorio = new ArrayList<>();

    @Override
    public void registrarEstudiante(Estudiante est) throws ReglaNegocioException {

        if (est.id() == null || est.id().isBlank()) {
            throw new ReglaNegocioException("El id del estudiante no puede ser nulo o vacío");
        }
        if (est.nombre() == null || est.nombre().isBlank()) {
            throw new ReglaNegocioException("El nombre del estudiante no puede ser nulo o vacío");
        }
        if (est.programa() == null || est.programa().isBlank()) {
            throw new ReglaNegocioException("El programa del estudiante no puede ser nulo o vacío");
        }

        if (est.notaDefinitiva() < 0 || est.notaDefinitiva() > 5) {
            throw new ReglaNegocioException("La nota definitiva del estudiante debe estar entre 0 y 5");
        }

        boolean existe = repositorio.stream()
                .anyMatch(estudiante -> estudiante.id().equals(est.id()));

        if (existe) {
            throw new ReglaNegocioException("Ya existe un estudiante con el mismo id " + est.id());
        }

        if (est.creditos() <= 0 || est.creditos() > 22) {
            throw new ReglaNegocioException("Los creditos del estudiante deben estar entre 1 y 22");
        }

        repositorio.add(est);

    }

    @Override
    public List<Estudiante> listarTodos() {
        return Collections.unmodifiableList(repositorio);
    }

    @Override
    public List<Estudiante> filtrar(Predicate<Estudiante> criterio) {
        return repositorio.stream()
                .filter(criterio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Estudiante> obtenerCandidatosBeca() {
        return filtrar(Estudiante::isCandidatoBeca);
    }

    public List<Estudiante> obtenerReprobados() {
        return filtrar(estudiante -> !estudiante.isAprobado());
    }

    @Override
    public double calcularPromedioGeneral() {
        return repositorio.stream()
                .mapToDouble(Estudiante::notaDefinitiva)
                .average()
                .orElse(0.0);
    }

    @Override
    public Map<String, Double> calcularPromedioPorPrograma() {
        return repositorio.stream()
                .collect(Collectors.groupingBy(
                        Estudiante::programa,
                        Collectors.averagingDouble(Estudiante::notaDefinitiva)));
    }

}
