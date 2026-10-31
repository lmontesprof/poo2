package solid.l;
public class Pinguino extends Ave implements Voladorable {
    @Override
    public void volar() {
        // Lógica para que el pingüino vuele
        System.out.println("El pingüino está volando.");
    }
    public void nadar() {
        // Lógica para que el pingüino nade
        System.out.println("El pingüino está nadando.");
    }
}