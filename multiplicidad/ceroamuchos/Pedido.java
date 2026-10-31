package multiplicidad.ceroamuchos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String numeroPedido;
    private String fechaPedido;
    private List<DetallePedido> listaDetalles = new ArrayList<>();

    public Pedido(String numeroPedido, String fechaPedido) {
        this.numeroPedido = numeroPedido;
        this.fechaPedido = fechaPedido;
    }

    public Pedido(DetallePedido primerDetalle) {
        if (primerDetalle == null) {
            throw new IllegalArgumentException("Un pedido debe tener al menos un detalle de pedido");
        }
        this.listaDetalles.add(primerDetalle);

    }

}
