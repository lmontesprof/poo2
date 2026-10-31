package acoplamiento;

import abstractas.Dibujable;

public class Lienzo {
//Bajo acoplamiento, la clase Lienzo depende de la interfaz Dibujable en lugar de depender directamente de las clases Circulo y Cuadrado. Esto permite que Lienzo pueda trabajar con cualquier objeto que implemente la interfaz Dibujable, promoviendo la flexibilidad y la extensibilidad del código.
    public void actualizar(Dibujable dibujable) {
        dibujable.dibujar();
    }
    
}
