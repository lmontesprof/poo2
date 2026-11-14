package swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Ventana extends JFrame {

    public Ventana() {
        super("Mi primera ventana");
        // setTitle("Mi primera ventana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        });
    }
}
