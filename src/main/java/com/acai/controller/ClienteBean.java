package com.acai.controller;

import com.acai.model.regradenegocio.ClienteRN;
import com.acai.model.entidade.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
    private ClienteRN clienteRN = null;
    private Cliente selectedCliente;
    private Cliente cadastrarCliente;

    public ClienteBean() {
        this.clienteRN = new ClienteRN(ClienteRN.HIBERNATE_CLIENTE_DAO);
        this.cadastrarCliente = new Cliente();
        this.selectedCliente = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clienteLogado");
    }
    
    public void adicionarClienteAction() {
        this.cadastrarCliente.setSenha(DigestUtils.md5Hex(this.cadastrarCliente.getSenha()));
        this.clienteRN.salvarCliente(this.cadastrarCliente);
        this.cadastrarCliente = new Cliente();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente cadastrado com sucesso!"));
    }
    
    public Cliente buscarClienteAction(Integer codigo) {
        return (Cliente) this.clienteRN.buscarCliente(codigo);
    }
    
    public void alterarClienteAction() {
        this.selectedCliente.setSenha(DigestUtils.md5Hex(this.selectedCliente.getSenha()));
        this.clienteRN.alterarCliente(this.selectedCliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente alterado com sucesso!"));
    }
    
    public void excluirClienteAction() {
        this.clienteRN.deletarCliente(this.selectedCliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente deletado com sucesso!"));
    }
    
    public List<Cliente> listarAction() {
        return this.clienteRN.buscarTodos();
    }

    public ClienteRN getClienteRN() {
        return clienteRN;
    }

    public void setClienteRN(ClienteRN clienteRN) {
        this.clienteRN = clienteRN;
    }
    
    public Cliente getSelectedCliente() {
        return this.selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public Cliente getCadastrarCliente() {
        return cadastrarCliente;
    }

    public void setCadastrarCliente(Cliente cadastrarCliente) {
        this.cadastrarCliente = cadastrarCliente;
    }
    
}
