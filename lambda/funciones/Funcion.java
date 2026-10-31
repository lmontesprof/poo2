package lambda.funciones;

import java.util.function.Function;

public class Funcion {

    public static void main(String[] args) {
        Function<String,Integer> longitudCadena = s -> s.length();
        String cadena = "Hola, mundo!";
        int longitud = longitudCadena.apply(cadena);
        System.out.println("La longitud de la cadena \"" + cadena + "\" es: " + longitud);
        Function<String,Integer> convertirMayusculas = s -> s.toUpperCase().length();
        int longitudMayusculas = convertirMayusculas.apply(cadena);
        System.out.println("La longitud de la cadena en mayúsculas \"" + cadena.toUpperCase() + "\" es: " 
        + longitudMayusculas);
    }
    
}
