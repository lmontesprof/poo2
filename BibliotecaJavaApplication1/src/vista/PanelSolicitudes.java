/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Estado;
import modelo.Libro;
import servicio.BibliotecaServicio;

public class PanelSolicitudes extends JPanel {

    private final BibliotecaServicio servicio;
    private final JComboBox<Libro> cboLibro = new JComboBox<>();
    private final JTextField txtSolicitante = new JTextField(20);
    private final JLabel lblResumen = new JLabel(" ");
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Libro", "Solicitante", "Fecha", "Estado"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabla = new JTable(modelo);

    public PanelSolicitudes(BibliotecaServicio servicio) {
        this.servicio = servicio;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("Libro disponible:")); form.add(cboLibro);
        form.add(new JLabel("Solicitante:"));      form.add(txtSolicitante);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(Ui.boton("Crear solicitud", this::crear));
        botones.add(Ui.boton("Aprobar",  () -> conSeleccion(id -> servicio.cambiarEstado(id, Estado.APROBADA))));
        botones.add(Ui.boton("Rechazar", () -> conSeleccion(id -> servicio.cambiarEstado(id, Estado.RECHAZADA))));
        botones.add(Ui.boton("Devolver", () -> conSeleccion(id -> servicio.cambiarEstado(id, Estado.DEVUELTA))));
        botones.add(Ui.boton("Eliminar", () -> conSeleccion(servicio::eliminarSolicitud)));

        JPanel norte = new JPanel(new BorderLayout(0, 8));
        norte.add(form, BorderLayout.NORTH);
        norte.add(botones, BorderLayout.CENTER);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(24);

        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(lblResumen, BorderLayout.SOUTH);
        refrescar();
    }

    public void refrescar() {
        cboLibro.removeAllItems();
        servicio.listarLibros().stream().filter(Libro::isDisponible).forEach(cboLibro::addItem);

        modelo.setRowCount(0);
        servicio.listarSolicitudes().forEach(s -> modelo.addRow(new Object[]{
                s.getId(), servicio.tituloDeLibro(s.getIdLibro()),
                s.getSolicitante(), s.getFecha(), s.getEstado()}));

        lblResumen.setText("Resumen: " + servicio.resumenPorEstado());
    }

    private void crear() {
        ejecutar(() -> {
            Libro libro = (Libro) cboLibro.getSelectedItem();
            if (libro == null) throw new IllegalStateException("No hay libros disponibles");
            servicio.crearSolicitud(libro.getId(), txtSolicitante.getText());
            txtSolicitante.setText("");
        });
    }

    private void conSeleccion(Consumer<Integer> accion) {
        ejecutar(() -> {
            int fila = tabla.getSelectedRow();
            if (fila < 0) throw new IllegalStateException("Selecciona una fila de la tabla");
            accion.accept((Integer) modelo.getValueAt(fila, 0));
        });
    }

    private void ejecutar(Runnable accion) {
        try {
            accion.run();
            refrescar();
        } catch (RuntimeException ex) {
            Ui.error(this, ex.getMessage());
        }
    }
}