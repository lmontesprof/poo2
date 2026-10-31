package solid.i;

public class Humano implements Trabajable, Comible {
    @Override
    public void trabajar() {
        // Lógica para que el humano trabaje
        System.out.println("El humano está trabajando.");
    }

    @Override
    public void comer() {
        // Lógica para que el humano coma
        System.out.println("El humano está comiendo.");
    }
}
