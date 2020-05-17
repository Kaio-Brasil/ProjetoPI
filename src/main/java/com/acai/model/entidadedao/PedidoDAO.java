package com.acai.model.entidadedao;

import com.acai.model.dao.DAOGenerico;
import com.acai.model.entidade.Cliente;
import java.util.List;

public interface PedidoDAO<Pedido> extends DAOGenerico<Pedido> {
    public Integer salvarPedido(Pedido pedido);
    public List<Pedido> buscarPedidoPorCliente(Cliente cliente);
}
