package lambda.funciones;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
public class Predicado {
    public static void main(String[] args) {
        List<Integer> listaNumeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> esPar = n -> n % 2 == 0;

        for (Integer numero : listaNumeros) {
            if (esPar.test(numero)) {
                System.out.println(numero + " es par");
            } else {
                System.out.println(numero + " es impar");
            }
        }
    }
}
