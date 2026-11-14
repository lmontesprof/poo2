package base_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

   private static final String URL = "jdbc:mysql://localhost:3306/iud_academico";
   private static final String USUARIO = System.getenv().getOrDefault("DB_USER", "root");
   private static final String CONTRASENA = System.getenv().getOrDefault("DB_PASSWORD", "");

   private ConexionBD() {
       // Constructor privado para evitar instanciación
   }

   public static Connection obtenerConexion() throws SQLException {
       return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
   }

    
}
