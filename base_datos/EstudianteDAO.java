package base_datos;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class EstudianteDAO {

    public boolean insertarEstudiante(Estudiante estudiantes) throws SQLException {
        String sql = "INSERT INTO estudiantes (documento, nombre, programa, nota) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, estudiantes.documento());
            pstmt.setString(2, estudiantes.nombre());
            pstmt.setString(3, estudiantes.programa());
            pstmt.setDouble(4, estudiantes.nota());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public List<Estudiante> listarTodos() throws SQLException {
        List<Estudiante> lista = new ArrayList<>();
        String sql = " SELECT * FROM estudiantes";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery()){

                while(rs.next()){
                    Estudiante est=new Estudiante(
                        rs.getInt("id"), 
                        rs.getString("documento"), 
                        rs.getString("nombre"), 
                        rs.getString("programa"), 
                        rs.getDouble("nota"));
                        lista.add(est);
                }
                return lista;
            }
            


                
    }

}
