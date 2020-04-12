package com.acai.model.regradenegocio;

import com.acai.model.entidadedao.FreteDAO;
import com.acai.model.entidade.Frete;
import com.acai.model.entidadehibernatedao.FreteHibernateDAO;
import java.util.List;

public class FreteRN {
    public static final Integer HIBERNATE_FRETE_DAO = 0;
    
    private FreteDAO freteDAO;
    
    public FreteRN(Integer dao) {
        if(dao == 0) {
            this.freteDAO = new FreteHibernateDAO();
        }
    }
    
    public void salvarFrete(Frete frete) {
        this.freteDAO.salvar(frete);
    }
    
    public Frete buscarFrete(Integer codigo) {
        return (Frete) this.freteDAO.buscar(codigo);
    }
    
    public void alterarFrete(Frete frete) {
        this.freteDAO.alterar(frete);
    }
    
    public void deletarFrete(Frete frete) {
        this.freteDAO.deletar(frete);
    }
    
    public List<Frete> buscarTodos() {
        return this.freteDAO.buscarTodos();
    }
 
}
