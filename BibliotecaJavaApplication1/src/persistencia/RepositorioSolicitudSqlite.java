/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import modelo.Estado;
import modelo.Solicitud;

public class RepositorioSolicitudSqlite implements Repositorio<Solicitud> {

    @Override public void guardar(Solicitud s) {
        String sql = "INSERT OR REPLACE INTO solicitud (id, id_libro, solicitante, fecha, estado) VALUES (?,?,?,?,?)";
        try (Connection cn = Conexion.abrir(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, s.getId());
            ps.setInt(2, s.getIdLibro());
            ps.setString(3, s.getSolicitante());
            ps.setString(4, s.getFecha().toString());
            ps.setString(5, s.getEstado().name());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al guardar la solicitud: " + ex.getMessage(), ex);
        }
    }

    @Override public Optional<Solicitud> buscarPorId(int id) {
        try (Connection cn = Conexion.abrir();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM solicitud WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar la solicitud: " + ex.getMessage(), ex);
        }
    }

    @Override public List<Solicitud> listar() {
        List<Solicitud> lista = new ArrayList<>();
        try (Connection cn = Conexion.abrir();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM solicitud ORDER BY id")) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar solicitudes: " + ex.getMessage(), ex);
        }
        return lista;
    }

    @Override public void eliminar(int id) {
        try (Connection cn = Conexion.abrir();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM solicitud WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar la solicitud: " + ex.getMessage(), ex);
        }
    }

    @Override public int siguienteId() {
        try (Connection cn = Conexion.abrir();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COALESCE(MAX(id),0)+1 FROM solicitud")) {
            return rs.next() ? rs.getInt(1) : 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al calcular el id: " + ex.getMessage(), ex);
        }
    }

    private Solicitud mapear(ResultSet rs) throws SQLException {
        return new Solicitud(rs.getInt("id"), rs.getInt("id_libro"),
                rs.getString("solicitante"),
                LocalDate.parse(rs.getString("fecha")),
                Estado.valueOf(rs.getString("estado")));
    }
}
