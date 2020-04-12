package com.acai.model.entidadehibernatedao;

import com.acai.model.entidade.EnderecoEntrega;
import com.acai.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.acai.model.entidadedao.EntregaDAO;

public class EntregaHibernateDAO implements EntregaDAO<EnderecoEntrega> {
    private final SessionFactory sessionFactory;

    public EntregaHibernateDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    @Override
    public void salvar(EnderecoEntrega entrega) { 
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.save(entrega);
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
    public EnderecoEntrega buscar(Integer codigoEntrega) {
        Session session = this.sessionFactory.openSession();
        EnderecoEntrega entrega = null;
        try {
            Query consulta = session.createQuery("SELECT entrega FROM Entrega entrega WHERE entrega.codigoEntrega = :codigoEntrega");
            consulta.setInteger("codigoEntrega", codigoEntrega);
            entrega = (EnderecoEntrega) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return entrega;
    }

    @Override
    public void alterar(EnderecoEntrega entrega) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.update(entrega);
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
    public void deletar(EnderecoEntrega entrega) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.delete(entrega);
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
    public List<EnderecoEntrega> buscarTodos() {
        Session session = this.sessionFactory.openSession();
        List<EnderecoEntrega> enderecos = null;
        try {
            Query consulta = session.createQuery("SELECT entrega FROM Entrega entrega");
            enderecos = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return enderecos;
    }
    
}
