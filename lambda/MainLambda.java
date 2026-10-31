package lambda;

import java.util.function.Function;

@FunctionalInterface
interface OperacionMatematica {
    double calcular(double a, double b);
}

public class MainLambda {

    public static void main(String[] args) {
        // Implementación de la interfaz OperacionMatematica usando una expresión lambda
        OperacionMatematica suma = (a, b) -> a + b;
        
        OperacionMatematica resta = (a, b) -> a - b;
        OperacionMatematica multiplicacion = (a, b) -> a * b;
        OperacionMatematica division = (a, b) -> a / b;

        double num1 = 10;
        double num2 = 5;

        System.out.println("Suma: " + suma.calcular(num1, num2));
        System.out.println("Resta: " + resta.calcular(num1, num2));
        System.out.println("Multiplicación: " + multiplicacion.calcular(num1, num2));
        System.out.println("División: " + division.calcular(num1, num2));


        OperacionMatematica potencia = (a, b) -> Math.pow(a, b);
       
        OperacionMatematica potencia1= new OperacionMatematica() {
            @Override
            public double calcular(double a, double b) {
                return Math.pow(a,b);
            }
        };
    }

}
