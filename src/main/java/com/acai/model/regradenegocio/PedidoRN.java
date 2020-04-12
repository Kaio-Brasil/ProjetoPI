package com.acai.model.regradenegocio;

import com.acai.model.entidadedao.PedidoDAO;
import com.acai.model.entidade.Pedido;
import com.acai.model.entidadehibernatedao.PedidoHibernateDAO;
import java.util.List;

public class PedidoRN {
    public static final Integer HIBERNATE_PEDIDO_DAO = 0;
    
    private PedidoDAO pedidoDAO;
    
    public PedidoRN(Integer dao) {
        if(dao == 0) {
            this.pedidoDAO = new PedidoHibernateDAO();
        }
    }
    
    public Integer salvarPedido(Pedido pedido) {
        return this.pedidoDAO.salvarPedido(pedido);
    }
    
    public Pedido buscarPedido(Integer codigo) {
        return (Pedido) this.pedidoDAO.buscar(codigo);
    }
    
    public void alterarPedido(Pedido pedido) {
        this.pedidoDAO.alterar(pedido);
    }
    
    public void deletarPedido(Pedido pedido) {
        this.pedidoDAO.deletar(pedido);
    }
    
    public List<Pedido> buscarTodos() {
        return this.pedidoDAO.buscarTodos();
    }
    
}
