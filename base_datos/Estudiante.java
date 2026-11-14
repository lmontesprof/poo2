package base_datos;

public record Estudiante(
        int id,
        String documento,
        String nombre,
        String programa,
        double nota) {
    public Estudiante(String documento, String nombre, String programa, double nota) {
        this(0, documento, nombre, programa, nota);
    }
}