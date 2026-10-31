package cohesion;

public class FormateadorTexto {
    //alta cohesion
    public String pasarMayusculas(String texto) {
        // Lógica para formatear el texto
        return texto.toUpperCase(); // Ejemplo de formateo: convertir a mayúsculas
    }

    public String pasarMinusculas(String texto) {
        // Lógica para formatear el texto
        return texto.toLowerCase(); // Ejemplo de formateo: convertir a minúsculas
    }

    public String capitalizar(String texto) {
        // Lógica para formatear el texto
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase(); // Ejemplo de formateo: capitalizar la primera letra
    }
    
}
