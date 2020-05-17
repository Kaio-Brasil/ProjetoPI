package com.acai.model.entidadehibernatedao;

import com.acai.model.entidade.Cliente;
import com.acai.model.entidadedao.PedidoDAO;
import com.acai.model.entidade.Pedido;
import com.acai.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PedidoHibernateDAO implements PedidoDAO<Pedido> {
    private final SessionFactory sessionFactory;
    
    public PedidoHibernateDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
        
    @Override
    public void salvar(Pedido pedido) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.save(pedido);
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
    public Pedido buscar(Integer codigoPedido) {
        Session session = this.sessionFactory.openSession();
        Pedido pedido = null;
        try {
            Query consulta = session.createQuery("SELECT pedido FROM Pedido pedido WHERE pedido.codigoPedido = :codigoPedido");
            consulta.setInteger("codigoPedido", codigoPedido);
            pedido = (Pedido) consulta.uniqueResult();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return pedido;
    }

    @Override
    public void alterar(Pedido pedido) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.update(pedido);
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
    public void deletar(Pedido pedido) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        try {
            transacao = session.beginTransaction();
            session.delete(pedido);
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
    public List<Pedido> buscarTodos() {
        Session session = this.sessionFactory.openSession();
        List<Pedido> pedidos = null;
        try {
            Query consulta = session.createQuery("SELECT pedido FROM Pedido pedido");
            pedidos = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return pedidos;
    }
    
    @Override
    public Integer salvarPedido(Pedido pedido) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;
        Integer codigo = null;
        try {
            transacao = session.beginTransaction();
            codigo = (Integer) session.save(pedido);
            transacao.commit();
        } catch(RuntimeException ex) {
            if(transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
        return codigo;
    }
    
    @Override
    public List<Pedido> buscarPedidoPorCliente(Cliente cliente) {
        Session session = this.sessionFactory.openSession();
        List<Pedido> pedidos = null;
        try {
            Query consulta = session.createQuery("SELECT pedido FROM Pedido pedido WHERE pedido.cliente = :cliente");
            consulta.setEntity("cliente", cliente);
            pedidos = consulta.list();
        } catch(RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return pedidos;
    }
    
}
