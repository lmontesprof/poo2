package solid.d;

public class SistemaUsuario {
    private ConexionDBable baseDatos;

    public SistemaUsuario(ConexionDBable baseDatos) {
        this.baseDatos = baseDatos;
    }

    public void iniciarSesion() {
        baseDatos.conectar();
        // Lógica para iniciar sesión del usuario
        baseDatos.desconectar();
    }

}
