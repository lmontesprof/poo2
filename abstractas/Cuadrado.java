package abstractas;

public class Cuadrado extends Figura implements Dibujable {
    private double lado;

    public Cuadrado(String color, double lado) {
        super(color);
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando un cuadrado de color " + color + " y lado " + lado);
    }
}
