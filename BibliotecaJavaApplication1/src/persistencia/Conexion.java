/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.*;

public final class Conexion {

    private static final String URL = "jdbc:sqlite:biblioteca.db";

    private Conexion() {}

    public static Connection abrir() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializar() {
        try (Connection cn = abrir(); Statement st = cn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS libro ("
                    + "id INTEGER PRIMARY KEY, "
                    + "titulo TEXT NOT NULL, "
                    + "autor TEXT NOT NULL, "
                    + "categoria TEXT, "
                    + "disponible INTEGER NOT NULL)");
            st.execute("CREATE TABLE IF NOT EXISTS solicitud ("
                    + "id INTEGER PRIMARY KEY, "
                    + "id_libro INTEGER NOT NULL, "
                    + "solicitante TEXT NOT NULL, "
                    + "fecha TEXT NOT NULL, "
                    + "estado TEXT NOT NULL)");
        } catch (SQLException ex) {
            throw new RuntimeException("No se pudo inicializar la base de datos: " + ex.getMessage(), ex);
        }
    }

}
