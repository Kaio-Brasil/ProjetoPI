package com.acai.controller;

import com.acai.model.entidade.Usuario;
import com.acai.model.regradenegocio.UsuarioRN;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean
@ViewScoped
public class UsuarioBean {
    private UsuarioRN usuarioRN = null;
    private Usuario selectedUsuario;
    private Usuario cadastrarUsuario;
    

    public UsuarioBean() {
        this.usuarioRN = new UsuarioRN(UsuarioRN.HIBERNATE_USUARIO_DAO);
        this.cadastrarUsuario = new Usuario();
    }
    
    public void adicionarUsuarioAction() {
        this.cadastrarUsuario.setSenha(DigestUtils.md5Hex(this.cadastrarUsuario.getSenha()));
        this.usuarioRN.salvarUsuario(this.cadastrarUsuario);
        this.cadastrarUsuario = new Usuario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario cadastrado com sucesso!"));
    }
    
    public Usuario buscarUsuarioAction(Integer codigo) {
        return (Usuario) this.usuarioRN.buscarUsuario(codigo);
    }
    
    public void alterarUsuarioAction() {
        this.selectedUsuario.setSenha(DigestUtils.md5Hex(this.selectedUsuario.getSenha()));
        this.usuarioRN.alterarUsuario(this.selectedUsuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario alterado com sucesso!"));
    }
    
    public void excluirUsuarioAction() {
        this.usuarioRN.deletarUsuario(this.selectedUsuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario deletado com sucesso!"));
    }
    
    public List<Usuario> listarAction() {
        return this.usuarioRN.buscarTodos();
    }

    public UsuarioRN getRepositorioUsuario() {
        return usuarioRN;
    }

    public void setRepositorioUsuario(UsuarioRN repositorioUsuario) {
        this.usuarioRN = repositorioUsuario;
    }
    
    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public Usuario getCadastrarUsuario() {
        return cadastrarUsuario;
    }

    public void setCadastrarUsuario(Usuario cadastrarUsuario) {
        this.cadastrarUsuario = cadastrarUsuario;
    }
    
}
