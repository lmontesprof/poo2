package solid.o;

public class MostrarDescuento {
 
    public static void main(String[] args) {
        double precio = 100.0;

        Descuentoable descuentoHalloween = new DescuentoHalloween();
        double descuentoH = descuentoHalloween.calcularDescuento(precio);
        System.out.println("Descuento de Halloween: " + descuentoH);

        Descuentoable descuentoNavidad = new DescuentoNavidad();
        double descuentoN = descuentoNavidad.calcularDescuento(precio);
        System.out.println("Descuento de Navidad: " + descuentoN);
    }
    
}
