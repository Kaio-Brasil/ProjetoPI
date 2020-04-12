package com.acai.model.entidadehibernatedao;

import com.acai.model.entidadedao.ItemPedidoDAO;
import com.acai.model.entidade.ItemPedido;
import com.acai.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ItemPedidoHibernateDAO implements ItemPedidoDAO<ItemPedido> {
    private final SessionFactory sessionFactory;
    
    public ItemPedidoHibernateDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
       
    @Override
    public void salvar(ItemPedido itemPedido) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.save(itemPedido);
            transacao.commit();
        } catch(RuntimeException ex) {
            if(transacao != null) {
                transacao.rollback();
            }
        } finally {
            session.close();
        }
    }
    
    @Override
    public ItemPedido buscar(Integer codigoItem) {
        Session session = this.sessionFactory.openSession();
        ItemPedido itemPedido = null;
        try {
            Query consulta = session.createQuery("SELECT item FROM ItemPedido item WHERE item.codigoItem = :codigoItem");
            consulta.setInteger("codigoItem", codigoItem);
            itemPedido = (ItemPedido) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return itemPedido;
    }

    @Override
    public void alterar(ItemPedido itemPedido) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.update(itemPedido);
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
    public void deletar(ItemPedido itemPedido) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.delete(itemPedido);
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
    public List<ItemPedido> buscarTodos() {
        Session session = this.sessionFactory.openSession();
        List<ItemPedido> itemPedidos = null;
        try {
            Query consulta = session.createQuery("SELECT item FROM ItemPedido item");
            itemPedidos = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return itemPedidos;
    }
    
}
