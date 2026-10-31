package multiplicidad;
public class Capital {
    private String nombre;
    private int poblacion;
    private String idioma;

    public Capital(String nombre, int poblacion, String idioma) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.idioma = idioma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

}