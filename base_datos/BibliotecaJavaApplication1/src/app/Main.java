/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import persistencia.Conexion;
import persistencia.RepositorioLibroSqlite;
import persistencia.RepositorioSolicitudSqlite;
import servicio.BibliotecaServicio;
import vista.VentanaPrincipal;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignorada) { }

            try {
                Conexion.inicializar();   // crea biblioteca.db y las tablas si no existen
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error de base de datos", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BibliotecaServicio servicio = new BibliotecaServicio(
                    new RepositorioLibroSqlite(),
                    new RepositorioSolicitudSqlite());

            new VentanaPrincipal(servicio).setVisible(true);
        });
    }
}
