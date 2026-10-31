package multiplicidad;
//Multiplicidad 0..1 Cero a uno, opcional, un empleado puede tener un vehiculo asignado o no
public class Empleado {
    private Vehiculo vehiculoAsigando;

    public void asignarVehiculo(Vehiculo vehiculo) {
        this.vehiculoAsigando = vehiculo;
    }
    
}
