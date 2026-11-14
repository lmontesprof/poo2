package swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.*;

public class FormularioNotas extends JFrame {
    private JTextField txtEstudiante;
    private JTextField txtNota;
    private JLabel lblResultado;
    private JButton btnGuardar;

    public FormularioNotas() {
        setTitle("Registro de Notas");
        setSize(420, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Formulario de Registro de Notas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Estudiante:"));
        txtEstudiante = new JTextField();
        panel.add(txtEstudiante);

        panel.add(new JLabel("Nota:"));
        txtNota = new JTextField();
        panel.add(txtNota);

        add(panel, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new GridLayout(2, 1, 5, 5));
        btnGuardar = new JButton("Guardar");
        lblResultado = new JLabel("Esperando registro", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.ITALIC, 14));
        panelInferior.add(btnGuardar);
        panelInferior.add(lblResultado);
        add(panelInferior, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> procesarCalificacion());
    }

    private void procesarCalificacion() {
        try {
            String nombreEstudiante = txtEstudiante.getText();
            double nota = Double.parseDouble(txtNota.getText());
            if (nota < 0 || nota > 10) {
                JOptionPane.showMessageDialog(this, "La nota debe estar entre 0 y 10.", "Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            String estado = (nota >= 3.0) ? "Aprobado" : "Reprobado";
            Color colorEstado = (nota >= 3.0) ? Color.GREEN : Color.RED;
            lblResultado.setText("Estudiante: " + nombreEstudiante + ", Nota: " + nota + ", Estado: " + estado);
            lblResultado.setForeground(colorEstado);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la nota.", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioNotas formularioNotas = new FormularioNotas();
            formularioNotas.setVisible(true);
        });
    }

}
