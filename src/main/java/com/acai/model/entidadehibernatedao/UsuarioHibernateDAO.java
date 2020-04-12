package com.acai.model.entidadehibernatedao;

import com.acai.model.entidade.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.acai.model.entidadedao.UsuarioDAO;
import com.acai.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class UsuarioHibernateDAO implements UsuarioDAO<Usuario> {
    private final SessionFactory sessionFactory;
    private static UsuarioHibernateDAO instance = null;
    
    public static UsuarioHibernateDAO getInstance() {
        if(instance == null) {
            instance = new UsuarioHibernateDAO();
        }
        return instance;
    }
    
    private UsuarioHibernateDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
   
    @Override
    public void salvar(Usuario usuario) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.save(usuario);
            transacao.commit();
        } catch(Exception ex) {
            if(transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public Usuario buscar(Integer idUsuario) {
        Session session = this.sessionFactory.openSession();
        Usuario usuario = null;
        try {
            Query consulta = session.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.idUsuario = :idUsuario");
            consulta.setInteger("idUsuario", idUsuario);
            usuario = (Usuario) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return usuario;
    }

    @Override
    public void alterar(Usuario usuario) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.update(usuario);
            transacao.commit();
        } catch(Exception ex) {
            if(transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(Usuario usuario) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.delete(usuario);
            transacao.commit();
        } catch(Exception ex) {
            if(transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Usuario> buscarTodos() {
        Session session = this.sessionFactory.openSession();
        List<Usuario> usuarios = null;
        try {
            Query consulta = session.createQuery("SELECT usuario FROM Usuario usuario");
            usuarios = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return usuarios;
    }
    
    @Override
    public Usuario autenticar(String login, String senha) {
        Session session = this.sessionFactory.openSession();
        Usuario usuario = null;
        try {
            Query consulta = session.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.login = :login AND usuario.senha = :senha");
            consulta.setString("login", login);
            consulta.setString("senha", senha);
            usuario = (Usuario) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
                session.close();
        }
        return usuario;
    }
    
}
