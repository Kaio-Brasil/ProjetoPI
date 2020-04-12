package com.acai.model.regradenegocio;

import com.acai.model.entidadedao.ClienteDAO;
import com.acai.model.entidade.Cliente;
import com.acai.model.entidadehibernatedao.ClienteHibernateDAO;
import java.util.List;

public class ClienteRN {
    public static final Integer HIBERNATE_CLIENTE_DAO = 0;
    
    private ClienteDAO clienteDAO;
    
    public ClienteRN(Integer dao) {
        if(dao == 0) {
            this.clienteDAO = ClienteHibernateDAO.getInstance();
        }
    }
    
    public void salvarCliente(Cliente cliente) {
        this.clienteDAO.salvar(cliente);
    }
    
    public Cliente buscarCliente(Integer codigo) {
        return (Cliente) this.clienteDAO.buscar(codigo);
    }
    
    public void alterarCliente(Cliente cliente) {
        this.clienteDAO.alterar(cliente);
    }
    
    public void deletarCliente(Cliente cliente) {
        this.clienteDAO.deletar(cliente);
    }
    
    public List<Cliente> buscarTodos() {
        return this.clienteDAO.buscarTodos();
    }
    
    public Cliente autenticar(String email, String senha) {
        return (Cliente) this.clienteDAO.autenticar(email, senha);
    }
    
}
