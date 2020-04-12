package com.acai.model.regradenegocio;

import com.acai.model.entidadedao.ItemPedidoDAO;
import com.acai.model.entidade.ItemPedido;
import com.acai.model.entidadehibernatedao.ItemPedidoHibernateDAO;
import java.util.List;

public class ItemPedidoRN {
    public static final Integer HIBERNATE_ITEMPEDIDO_DAO = 0;
    
    private ItemPedidoDAO itemPedidoDAO;
    
    public ItemPedidoRN(Integer dao) {
        if(dao == 0) {
            this.itemPedidoDAO = new ItemPedidoHibernateDAO();
        }
    }
    
    public void salvarItemPedido(ItemPedido itemPedido) {
        this.itemPedidoDAO.salvar(itemPedido);
    }
    
    public ItemPedido buscarItemPedido(Integer codigo) {
        return (ItemPedido) this.itemPedidoDAO.buscar(codigo);
    }
    
    public void alterarItemPedido(ItemPedido itemPedido) {
        this.itemPedidoDAO.alterar(itemPedido);
    }
    
    public void deletarItemPedido(ItemPedido itemPedido) {
        this.itemPedidoDAO.deletar(itemPedido);
    }
    
    public List<ItemPedido> buscarTodos() {
        return this.itemPedidoDAO.buscarTodos();
    }
    
}
