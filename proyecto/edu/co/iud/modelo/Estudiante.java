package proyecto.edu.co.iud.modelo;

public record Estudiante(
        String id,
        String nombre,
        String programa,
        int creditos,
        double notaDefinitiva) {
    public boolean isAprobado() {
        return notaDefinitiva >= 3.0;
    }

    public boolean isCandidatoBeca() {
        return notaDefinitiva >= 4.5 && creditos >= 12;
    }
}
