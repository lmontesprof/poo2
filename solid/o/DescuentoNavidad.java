package solid.o;

public class DescuentoNavidad implements Descuentoable {
    private double precio;
    
    @Override
    public double calcularDescuento(double precio) {
        // Lógica para calcular el descuento de Navidad
        return precio * 0.1; // Ejemplo: 10% de descuento
    }
    
}
