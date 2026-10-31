package composicion;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    private String direccion;
    private int numCuartos;
    private List<Habitacion> listaHabitaciones;
    //Composición, relación fuerte, la casa no puede existir sin las habitaciones
    public Casa(){
        this.listaHabitaciones=new ArrayList<Habitacion>();
        this.listaHabitaciones.add(new Habitacion(1, "Cocina", 2));
        this.listaHabitaciones.add(new Habitacion(2, "Sala", 4));
        this.listaHabitaciones.add(new Habitacion(3, "Baño", 1));
        this.listaHabitaciones.add(new Habitacion(4, "Recamara", 2));
        this.listaHabitaciones.add(new Habitacion(5, "Dormitorio", 2));
    }
    
}
