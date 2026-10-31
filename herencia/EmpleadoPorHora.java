package herencia;

public class EmpleadoPorHora extends Empleado {
    private int horasTrabajadas;
    private double tarifaPorHora;

    public EmpleadoPorHora(String nombre, String apellido, int horasTrabajadas, double tarifaPorHora) {
        super(nombre, apellido, 0); // Sueldo inicial en 0
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    @Override
    public double calcularSueldo() {
        return this.horasTrabajadas * this.tarifaPorHora;
    }

    @Override
    public double calcularSueldo(double bonoExtra) {
        return this.horasTrabajadas * this.tarifaPorHora + bonoExtra;
    }

    public void imprimir() {
        super.imprimirDetalles();
        System.out.println("Horas trabajadas: " + this.horasTrabajadas);
        System.out.println("Tarifa por hora: " + this.tarifaPorHora);
    }
}
