/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.*;
import java.util.*;
import modelo.Libro;

public class RepositorioLibroSqlite implements Repositorio<Libro> {

    @Override public void guardar(Libro l) {
        String sql = "INSERT OR REPLACE INTO libro (id, titulo, autor, categoria, disponible) VALUES (?,?,?,?,?)";
        try (Connection cn = Conexion.abrir(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, l.getId());
            ps.setString(2, l.getTitulo());
            ps.setString(3, l.getAutor());
            ps.setString(4, l.getCategoria());
            ps.setInt(5, l.isDisponible() ? 1 : 0);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al guardar el libro: " + ex.getMessage(), ex);
        }
    }

    @Override public Optional<Libro> buscarPorId(int id) {
        try (Connection cn = Conexion.abrir();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM libro WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar el libro: " + ex.getMessage(), ex);
        }
    }

    @Override public List<Libro> listar() {
        List<Libro> lista = new ArrayList<>();
        try (Connection cn = Conexion.abrir();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM libro ORDER BY id")) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar libros: " + ex.getMessage(), ex);
        }
        return lista;
    }

    @Override public void eliminar(int id) {
        try (Connection cn = Conexion.abrir();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM libro WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar el libro: " + ex.getMessage(), ex);
        }
    }

    @Override public int siguienteId() {
        try (Connection cn = Conexion.abrir();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COALESCE(MAX(id),0)+1 FROM libro")) {
            return rs.next() ? rs.getInt(1) : 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al calcular el id: " + ex.getMessage(), ex);
        }
    }

    private Libro mapear(ResultSet rs) throws SQLException {
        Libro l = new Libro(rs.getInt("id"), rs.getString("titulo"),
                rs.getString("autor"), rs.getString("categoria"));
        l.setDisponible(rs.getInt("disponible") == 1);
        return l;
    }
}
