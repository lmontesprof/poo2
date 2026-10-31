package abstractas;

public class Circulo extends Figura implements Dibujable {

    private double radio;

    public Circulo(String color, double radio) {
        super(color);
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando un círculo de color " + color + " y radio " + radio);
    }
}