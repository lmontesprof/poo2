package solid.s;
//Clase que representa una factura y NO implementa el principio de responsabilidad única (SRP) ya que tiene múltiples responsabilidades: calcular el total, enviar por correo, imprimir y guardar en la base de datos.
public class FacturaMal {
    public void calcularTotal() {
        // Lógica para calcular el total de la factura
    }
    public void enviarPorCorreo() {
        // Lógica para enviar la factura por correo electrónico
    }
    public void imprimir() {
        // Lógica para imprimir la factura
    }
    public void guardarEnBaseDeDatos() {
        // Lógica para guardar la factura en la base de datos
    }
}
