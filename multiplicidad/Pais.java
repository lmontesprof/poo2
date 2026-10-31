package multiplicidad;
public class Pais {
    private final Capital capital;


    public Pais(Capital capital) {
        if (capital == null) {
            throw new IllegalArgumentException("Todo país debe tener una capital válida.");
        }
        this.capital = capital;

    }
}
