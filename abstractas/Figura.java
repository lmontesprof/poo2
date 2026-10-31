package abstractas;

public abstract class Figura {
    protected String color;

    public Figura(String color) {
        this.color = color;
    }

    public void metdoConcreto() {
        System.out.println("Este es un método concreto en la clase abstracta Figura.");
    }

    // Método abstracto sin cuerpo, que debe ser implementado por las subclases
    // concretas.
    public abstract double calcularArea();

}
