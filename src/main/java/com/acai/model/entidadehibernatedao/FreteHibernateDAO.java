package com.acai.model.entidadehibernatedao;

import com.acai.model.entidade.Frete;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.acai.model.entidadedao.FreteDAO;
import com.acai.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class FreteHibernateDAO implements FreteDAO<Frete> {
    private final SessionFactory sessionFactory;
    
    public FreteHibernateDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void salvar(Frete frete) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.save(frete);
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
    public Frete buscar(Integer codigoFrete) {
        Session session = this.sessionFactory.openSession();
        Frete frete = null;
        try {
            Query consulta = session.createQuery("SELECT frete FROM Frete frete WHERE frete.codigoFrete = :codigoFrete");
            consulta.setInteger("codigoFrete", codigoFrete);
            frete = (Frete) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return frete;
    }

    @Override
    public void alterar(Frete frete) {
        this.sessionFactory.getCurrentSession().close();
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.update(frete);
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
    public void deletar(Frete frete) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.delete(frete);
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
    public List<Frete> buscarTodos() {
        Session session = this.sessionFactory.openSession();
        List<Frete> fretes = null;
        
        try {
            Query consulta = session.createQuery("SELECT frete FROM Frete frete");
            fretes = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return fretes;
    }
    
}
