public class Persona {
    // Encapsulamiento
    protected String nombre;
    private String apellido;
    public int edad;
    String correo;

    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }


}