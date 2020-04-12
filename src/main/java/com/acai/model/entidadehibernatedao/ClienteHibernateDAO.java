package com.acai.model.entidadehibernatedao;

import com.acai.model.entidade.Cliente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.acai.model.entidadedao.ClienteDAO;
import com.acai.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class ClienteHibernateDAO implements ClienteDAO<Cliente> {
    private final SessionFactory sessionFactory;
    private static ClienteHibernateDAO instance = null;
    
    public static ClienteHibernateDAO getInstance() {
        if(instance == null) {
            instance = new ClienteHibernateDAO();
        }
        return instance;
    }
    
    private ClienteHibernateDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
       
    @Override
    public void salvar(Cliente cliente) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.save(cliente);
            transacao.commit();
        } catch(RuntimeException ex) {
            if(transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public Cliente buscar(Integer idCliente) {
        Session session = this.sessionFactory.openSession();
        Cliente cliente = null;
        try {
            Query consulta = session.createQuery("SELECT cliente FROM Cliente cliente WHERE cliente.idCliente = :idCliente");
            consulta.setInteger("idCliente", idCliente);
            cliente = (Cliente) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
                session.close();
        }
        return cliente;
    }

    @Override
    public void alterar(Cliente cliente) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            Cliente alterarCliente = buscar(cliente.getIdCliente());
            alterarCliente.setNome(cliente.getNome());
            alterarCliente.setNome(cliente.getEmail());
            session.update(alterarCliente);
            transacao.commit();
        } catch(RuntimeException ex) {
            if(transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(Cliente cliente) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.delete(cliente);
            transacao.commit();
        } catch(RuntimeException ex) {
            if(transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        Session session = this.sessionFactory.openSession();
        List<Cliente> clientes = null;
        try {
            Query consulta = session.createQuery("SELECT cliente FROM Cliente cliente");
            clientes = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return clientes;
    }
    
    @Override
    public Cliente autenticar(String email, String senha) {
        Session session = this.sessionFactory.openSession();
        Cliente cliente = null;
        try {
            Query consulta = session.createQuery("SELECT cliente FROM Cliente cliente WHERE cliente.email = :email AND cliente.senha = :senha");
            consulta.setString("email", email);
            consulta.setString("senha", senha);
            cliente = (Cliente) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
                session.close();
        }
        return cliente;
    }
    
}
