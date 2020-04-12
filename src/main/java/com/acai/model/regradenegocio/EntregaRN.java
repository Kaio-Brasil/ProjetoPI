package com.acai.model.regradenegocio;

import com.acai.model.entidade.EnderecoEntrega;
import com.acai.model.entidadehibernatedao.EntregaHibernateDAO;
import java.util.List;
import com.acai.model.entidadedao.EntregaDAO;

public class EntregaRN {
    public static final Integer HIBERNATE_ENTREGA_DAO = 0;
    
    private EntregaDAO entregaDAO;
    
    public EntregaRN(Integer dao) {
        if(dao == 0) {
            this.entregaDAO = new EntregaHibernateDAO();
        }
    }
    
    public void salvarEntrega(EnderecoEntrega entrega) {
        this.entregaDAO.salvar(entrega);
    }
    
    public EnderecoEntrega buscarEntrega(Integer codigo) {
        return (EnderecoEntrega) this.entregaDAO.buscar(codigo);
    }
    
    public void alterarEntrega(EnderecoEntrega entrega) {
        this.entregaDAO.alterar(entrega);
    }
    
    public void deletarEntrega(EnderecoEntrega entrega) {
        this.entregaDAO.deletar(entrega);
    }
    
    public List<EnderecoEntrega> buscarTodos() {
        return this.entregaDAO.buscarTodos();
    }
    
}
