package com.acai.model.entidadedao;

import com.acai.model.dao.DAOGenerico;

public interface PedidoDAO<Pedido> extends DAOGenerico<Pedido> {
    public Integer salvarPedido(Pedido pedido);
}
