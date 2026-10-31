package herencia;

public class Empleado {
    protected String nombre;
    protected String apellido;
    protected double sueldo;
    
    public Empleado(String nombre, String apellido, double sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sueldo = sueldo;
    }
    //Sobrescritura (Overriding)
    public void imprimirDetalles() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Apellido: " + this.apellido);
        System.out.println("Sueldo: " + this.sueldo);
    }
    

    //Sobrecarga (Overloading)
    public double calcularSueldo(double bonoExtra) {
        return this.sueldo+bonoExtra;
    }
   
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getApellido() {
        return this.apellido;
    }
    
    public double getSueldo() {
        return this.sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
    public double calcularSueldo() {
        return this.sueldo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
