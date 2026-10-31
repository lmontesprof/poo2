package multiplicidad.ceroamuchos;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private List<Pedido> listaPedidos= new ArrayList<>();

    public void agregarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }   
    
}
