package composicion;

public class Habitacion {
    private int numHabitacion;
    private String tipoHabitacion;
    private int capacidad;
    private boolean disponible;
    
    public Habitacion(int numHabitacion, String tipoHabitacion, int capacidad) {
        this.numHabitacion = numHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.capacidad = capacidad;
        this.disponible = true;
    }
    
    public int getNumHabitacion() {
        return numHabitacion;
    }
    
    public String getTipoHabitacion() {
        return tipoHabitacion;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
}
