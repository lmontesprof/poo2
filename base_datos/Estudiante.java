package base_datos;

public final class Estudiante {
    private final int id;
    private final String documento;
    private final String nombre;
    private final String programa;
    private final double nota;

    public Estudiante(int id, String documento, String nombre, String programa, double nota) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.programa = programa;
        this.nota = nota;
    }

    public Estudiante(String documento, String nombre, String programa, double nota) {
        this(0, documento, nombre, programa, nota);
    }

    public int id() { return id; }
    public String documento() { return documento; }
    public String nombre() { return nombre; }
    public String programa() { return programa; }
    public double nota() { return nota; }
}
