package solid.o;

public class DescuentoHalloween implements Descuentoable {
    @Override
    public double calcularDescuento(double precio) {
        // Lógica para calcular el descuento de Halloween
        return precio * 0.2; // Ejemplo: 20% de descuento
    }
    
}
