/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import servicio.BibliotecaServicio;

public class PanelLibros extends JPanel {

    private final BibliotecaServicio servicio;
    private final JTextField txtTitulo = new JTextField(20);
    private final JTextField txtAutor = new JTextField(20);
    private final JComboBox<String> cboCategoria =
            new JComboBox<>(new String[]{"Novela", "Ciencia", "Tecnología", "Historia", "Otra"});
    private final JTextField txtBuscar = new JTextField(15);
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Título", "Autor", "Categoría", "Disponible"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabla = new JTable(modelo);
    private Integer idSeleccionado = null;

    public PanelLibros(BibliotecaServicio servicio) {
        this.servicio = servicio;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("Título:"));    form.add(txtTitulo);
        form.add(new JLabel("Autor:"));     form.add(txtAutor);
        form.add(new JLabel("Categoría:")); form.add(cboCategoria);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(Ui.boton("Guardar", this::guardar));
        botones.add(Ui.boton("Eliminar", this::eliminar));
        botones.add(Ui.boton("Limpiar", this::limpiar));
        botones.add(new JLabel("   Buscar:"));
        botones.add(txtBuscar);
        botones.add(Ui.boton("Buscar", this::refrescar));
        txtBuscar.addActionListener(e -> refrescar());

        JPanel norte = new JPanel(new BorderLayout(0, 8));
        norte.add(form, BorderLayout.NORTH);
        norte.add(botones, BorderLayout.CENTER);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(24);
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarSeleccion();
        });

        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        refrescar();
    }

    public void refrescar() {
        modelo.setRowCount(0);
        servicio.buscarLibros(txtBuscar.getText()).forEach(l -> modelo.addRow(new Object[]{
                l.getId(), l.getTitulo(), l.getAutor(), l.getCategoria(),
                l.isDisponible() ? "Sí" : "No"}));
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return;
        idSeleccionado = (Integer) modelo.getValueAt(fila, 0);
        txtTitulo.setText((String) modelo.getValueAt(fila, 1));
        txtAutor.setText((String) modelo.getValueAt(fila, 2));
        cboCategoria.setSelectedItem(modelo.getValueAt(fila, 3));
    }

    private void guardar() {
        try {
            String cat = (String) cboCategoria.getSelectedItem();
            if (idSeleccionado == null) {
                servicio.crearLibro(txtTitulo.getText(), txtAutor.getText(), cat);
            } else {
                servicio.actualizarLibro(idSeleccionado, txtTitulo.getText(), txtAutor.getText(), cat);
            }
            limpiar();
            refrescar();
        } catch (RuntimeException ex) {
            Ui.error(this, ex.getMessage());
        }
    }

    private void eliminar() {
        if (idSeleccionado == null) { Ui.error(this, "Selecciona un libro"); return; }
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar el libro seleccionado?",
                "Confirmar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        try {
            servicio.eliminarLibro(idSeleccionado);
            limpiar();
            refrescar();
        } catch (RuntimeException ex) {
            Ui.error(this, ex.getMessage());
        }
    }

    private void limpiar() {
        idSeleccionado = null;
        txtTitulo.setText("");
        txtAutor.setText("");
        cboCategoria.setSelectedIndex(0);
        tabla.clearSelection();
    }
}