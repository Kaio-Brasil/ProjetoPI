package com.acai.controller;

import com.acai.model.entidade.Cliente;
import com.acai.model.entidade.Usuario;
import com.acai.model.regradenegocio.ClienteRN;
import com.acai.model.regradenegocio.UsuarioRN;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

/**
*
* @author kaio
*/

@ManagedBean
@SessionScoped
public class AutenticarBean implements Serializable {
    private Cliente clienteLogin;
    private Usuario usuarioLogin;
    
    public Cliente getClienteLogin() {
        if(clienteLogin == null) {
            clienteLogin = new Cliente();
        }
        return clienteLogin;
    }

    public void setClienteLogin(Cliente clienteLogin) {
        this.clienteLogin = clienteLogin;
    }

    public Usuario getUsuarioLogin() {
        if(usuarioLogin == null) {
            usuarioLogin = new Usuario();
        }
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }
    
    public String entrar() {
        ClienteRN clienteRN = new ClienteRN(ClienteRN.HIBERNATE_CLIENTE_DAO);
        clienteLogin = clienteRN.autenticar(clienteLogin.getEmail(), DigestUtils.md5Hex(clienteLogin.getSenha()));

        if(clienteLogin != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você esta Logado!"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clienteLogado", clienteLogin);
            return "comofazer.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente não encontrado!", "Erro ao fazer login!"));
        }
        return "index.xhtml";
    }
    
    public String entrarAdmin() {
        if(usuarioLogin.getLogin().equals("admin") && usuarioLogin.getSenha().equals("admin")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você esta Logado!"));
            return "principal.xhtml?faces-redirect=true";
        }
        
        UsuarioRN usuarioRN = new UsuarioRN(UsuarioRN.HIBERNATE_USUARIO_DAO);
        usuarioLogin = usuarioRN.autenticar(usuarioLogin.getLogin(), DigestUtils.md5Hex(usuarioLogin.getSenha()));
       
        if(usuarioLogin != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você esta Logado!"));
            return "principal.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro ao fazer login!"));
        }
        return "index.xhtml";
    }
    
    public String sair() {
        usuarioLogin = null;
        return "index.xhtml?faces-redirect=true";
    }
    
    public String sairCliente() {
        clienteLogin = null;
        return "index.xhtml?faces-redirect=true";
    }
    
}
