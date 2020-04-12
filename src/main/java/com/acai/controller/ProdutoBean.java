package com.acai.controller;

import com.acai.model.regradenegocio.ProdutoRN;
import com.acai.model.entidade.Produto;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ProdutoBean {
    private ProdutoRN produtoRN = null;
    private Produto selectedProduto;
    private Produto cadastrarProduto;

    public ProdutoBean() {
        this.produtoRN = new ProdutoRN(ProdutoRN.HIBERNATE_PRODUTO_DAO);
        this.cadastrarProduto = new Produto();
    }
    
    public void adicionarProdutoAction() {
        this.produtoRN.salvarProduto(this.cadastrarProduto);
        this.cadastrarProduto = new Produto();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto cadastrado com sucesso!"));
    }
    
    public Produto buscarProdutoAction(Integer codigo) {
        return (Produto) this.produtoRN.buscarProduto(codigo);
    }
    
    public void alterarProdutoAction() {
        this.produtoRN.alterarProduto(this.selectedProduto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto alterado com sucesso!"));
    }
    
    public void excluirProdutoAction() {
        this.produtoRN.deletarProduto(this.selectedProduto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto deletado com sucesso!"));
    }
    
    public List<Produto> listarAction() {
        return this.produtoRN.buscarTodos();
    }

    public ProdutoRN getProdutoRN() {
        return this.produtoRN;
    }

    public void setProdutoRN(ProdutoRN produtoRN) {
        this.produtoRN = produtoRN;
    }
    
    public Produto getSelectedProduto() {
        return this.selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public Produto getCadastroProduto() {
        return this.cadastrarProduto;
    }

    public void setCadastroProduto(Produto cadastroProduto) {
        this.cadastrarProduto = cadastroProduto;
    }
    
}
