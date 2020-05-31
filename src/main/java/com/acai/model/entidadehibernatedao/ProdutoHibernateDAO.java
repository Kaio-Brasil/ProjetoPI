package com.acai.model.entidadehibernatedao;

import com.acai.model.entidade.Produto;
import com.acai.model.entidadedao.ProdutoDAO;
import com.acai.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProdutoHibernateDAO implements ProdutoDAO<Produto> {
    private final SessionFactory sessionFactory;

    public ProdutoHibernateDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    @Override
    public void salvar(Produto produto) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.save(produto);
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
    public Produto buscar(Integer codigoProduto) {
        Session session = this.sessionFactory.openSession();
        Produto produto = null;
        try {
            Query consulta = session.createQuery("SELECT produto FROM Produto produto WHERE produto.codigoProduto = :codigoProduto");
            consulta.setInteger("codigoProduto", codigoProduto);
            produto = (Produto) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return produto;
    }
    
    @Override
    public void alterar(Produto produto) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.update(produto);
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
    public void deletar(Produto produto) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.delete(produto);
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
    public List<Produto> buscarTodos() {
        Session session = this.sessionFactory.openSession();
        List<Produto> produtos = null;
        try {
            Query consulta = session.createQuery("SELECT produto FROM Produto produto");
            produtos = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return produtos;
    }

    @Override
    public List<Produto> listarProdutoDisponivel() {
        Session session = this.sessionFactory.openSession();
        List<Produto> produtos = null;
        
        try {
            Query consulta = session.createQuery("SELECT produto FROM Produto produto WHERE produto.statusProduto = TRUE");
            produtos = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return produtos;
    }
        
}
