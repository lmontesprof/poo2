package agregacion;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private String nombre;
    private String direccion;
    private int anioFundacion;
   private List<Profesor> listaProfesores = new ArrayList<>();

   public Universidad(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public Universidad(String nombre, String direccion, int anioFundacion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.anioFundacion = anioFundacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    
}
