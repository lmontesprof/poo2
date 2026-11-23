/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import servicio.BibliotecaServicio;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(BibliotecaServicio servicio) {
        super("Biblioteca - Gestión de libros y solicitudes");
        PanelLibros libros = new PanelLibros(servicio);
        PanelSolicitudes solicitudes = new PanelSolicitudes(servicio);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Libros", libros);
        tabs.addTab("Solicitudes", solicitudes);
        // al cambiar de pestaña se actualizan ambas vistas
        tabs.addChangeListener(e -> { libros.refrescar(); solicitudes.refrescar(); });

        add(tabs);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 520);
        setLocationRelativeTo(null);
    }
}