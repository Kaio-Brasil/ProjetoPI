package com.acai.model.regradenegocio;

import com.acai.model.entidade.Produto;
import com.acai.model.entidadedao.ProdutoDAO;
import com.acai.model.entidadehibernatedao.ProdutoHibernateDAO;
import java.util.List;

public class ProdutoRN {
    public static final Integer HIBERNATE_PRODUTO_DAO = 0;
    
    private ProdutoDAO produtoDAO;
    
    public ProdutoRN(Integer dao) {
        if(dao == 0) {
            this.produtoDAO = new ProdutoHibernateDAO();
        }
    }
    
    public void salvarProduto(Produto produto) {
        this.produtoDAO.salvar(produto);
    }
    
    public Produto buscarProduto(Integer codigo) {
        return (Produto) this.produtoDAO.buscar(codigo);
    }
    
    public void alterarProduto(Produto produto) {
        this.produtoDAO.alterar(produto);
    }
    
    public void deletarProduto(Produto produto) {
        this.produtoDAO.deletar(produto);
    }
    
    public List<Produto> buscarTodos() {
        return this.produtoDAO.buscarTodos();
    }
    
    public List<Produto> listarProdutoDisponivel() {
        return this.produtoDAO.listarProdutoDisponivel();
    }

}
