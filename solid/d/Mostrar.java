package solid.d;

public class Mostrar {
    public static void main(String[] args) {
        SistemaUsuario sistemaMysql = new SistemaUsuario(new MysqlConexion());
        sistemaMysql.iniciarSesion();
        SistemaUsuario sistemaPostgres = new SistemaUsuario(new PostgresConexion());
        sistemaPostgres.iniciarSesion();
        SistemaUsuario sistemaOracle = new SistemaUsuario(new OracleConexion());
        sistemaOracle.iniciarSesion();
        
    }

}
