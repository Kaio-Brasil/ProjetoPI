package com.acai.model.regradenegocio;

import com.acai.model.entidadedao.UsuarioDAO;
import com.acai.model.entidade.Usuario;
import com.acai.model.entidadehibernatedao.UsuarioHibernateDAO;
import java.util.List;

public class UsuarioRN {
    public static final Integer HIBERNATE_USUARIO_DAO = 0;
    
    private UsuarioDAO usuarioDAO;
    
    public UsuarioRN(Integer dao) {
        if(dao == 0) {
            this.usuarioDAO = UsuarioHibernateDAO.getInstance();
        }
    }
    
    public void salvarUsuario(Usuario usuario) {
        this.usuarioDAO.salvar(usuario);
    }
    
    public Usuario buscarUsuario(Integer codigo) {
        return (Usuario) this.usuarioDAO.buscar(codigo);
    }
    
    public void alterarUsuario(Usuario usuario) {
        this.usuarioDAO.alterar(usuario);
    }
    
    public void deletarUsuario(Usuario usuario) {
        this.usuarioDAO.deletar(usuario);
    }
    
    public List<Usuario> buscarTodos() {
        return this.usuarioDAO.buscarTodos();
    }
    
    public Usuario autenticar(String login, String senha) {
        return (Usuario) this.usuarioDAO.autenticar(login, senha);
    }
    
}
