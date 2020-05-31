package com.acai.controller;

import com.acai.model.entidade.Cliente;
import com.acai.model.entidade.ItemPedido;
import com.acai.model.entidade.Frete;
import com.acai.model.regradenegocio.PedidoRN;
import com.acai.model.entidade.Produto;
import com.acai.model.entidade.Pedido;
import com.acai.model.regradenegocio.EntregaRN;
import com.acai.model.regradenegocio.FreteRN;
import com.acai.model.regradenegocio.ItemPedidoRN;
import com.acai.model.regradenegocio.ProdutoRN;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author kaio
 */

@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable {
    private PedidoRN pedidoRN = null;
    private Pedido selectedPedido;
    private List<Produto> listaProdutos;
    private List<Produto> listaProdutosFiltrados;
    private List<ItemPedido> listaItem;
    private Pedido cadastrarPedido;
    private Frete freteSelecionado;

    public PedidoBean() {
        this.pedidoRN = new PedidoRN(PedidoRN.HIBERNATE_PEDIDO_DAO);
    }
      
    public String adicionarPedidoAction() {
        Cliente cliente = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clienteLogado");
        
        new EntregaRN(EntregaRN.HIBERNATE_ENTREGA_DAO).salvarEntrega(this.cadastrarPedido.getEnderecoEntrega());
        
        this.cadastrarPedido.setCliente(cliente);
        Integer cod = this.pedidoRN.salvarPedido(cadastrarPedido);
        Pedido codPedido = this.pedidoRN.buscarPedido(cod);
        
        for(ItemPedido item : listaItem) {
            item.setPedido(codPedido);
            
            ItemPedidoRN itemRN = new ItemPedidoRN(ItemPedidoRN.HIBERNATE_ITEMPEDIDO_DAO);
            itemRN.salvarItemPedido(item);
        }
        
        this.cadastrarPedido = new Pedido();
        this.cadastrarPedido.setTotal(new BigDecimal("0.00"));
        this.listaItem = new ArrayList<>();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido cadastrado com sucesso!"));
        
        return "comofazer.xhtml?faces-redirect=true";
    }    
    
    public Pedido buscarPedidoAction(Integer codigo) {
        return (Pedido) this.pedidoRN.buscarPedido(codigo);
    }
    
    public void alterarPedidoAction() {
        this.pedidoRN.alterarPedido(this.selectedPedido);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido alterado com sucesso!"));
    }
    
    public void excluirPedidoAction() {
        this.pedidoRN.deletarPedido(this.selectedPedido);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido deletado com sucesso!"));
    }
    
    public List<Pedido> listarAction() {
        return this.pedidoRN.buscarTodos();
    }
    
    public List<Pedido> listarActionRevesa() {
        List lista = listarAction();
        Collections.reverse(lista);
        return lista;
    }
    
    public List<Pedido> listarActionPedidoCliente() {
        Cliente cliente = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clienteLogado");
        List<Pedido>lista = this.pedidoRN.buscarPedidoPorCliente(cliente);
        Collections.reverse(lista);
        return lista;
    }

    public PedidoRN getPedidoRN() {
        return pedidoRN;
    }

    public void setPedidoRN(PedidoRN pedidoRN) {
        this.pedidoRN = pedidoRN;
    }
    
    public Pedido getSelectedPedido() {
        return this.selectedPedido;
    }

    public void setSelectedPedido(Pedido selectedPedido) {
        this.selectedPedido = selectedPedido;
    }
    
    public Pedido getCadastrarPedido() {
        if(cadastrarPedido == null) {
            cadastrarPedido = new Pedido();
            cadastrarPedido.setTotal(new BigDecimal("0.00"));
        }
        return cadastrarPedido;
    }

    public void setCadastrarPedido(Pedido cadastrarPedido) {
        this.cadastrarPedido = cadastrarPedido;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<Produto> getListaProdutosFiltrados() {
        return listaProdutosFiltrados;
    }

    public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
        this.listaProdutosFiltrados = listaProdutosFiltrados;
    }

    public List<ItemPedido> getListaItem() {
        if(listaItem == null) {
            listaItem = new ArrayList<>();
        }
        return listaItem;
    }

    public void setListaItem(List<ItemPedido> listaItem) {
        this.listaItem = listaItem;
    }

    public Frete getFreteSelecionado() {
        return freteSelecionado;
    }

    public void setFreteSelecionado(Frete freteSelecionado) {
        this.freteSelecionado = freteSelecionado;
    }
    
    public void carregarProdutos() {
        ProdutoRN produtoRN = new ProdutoRN(ProdutoRN.HIBERNATE_PRODUTO_DAO);
        this.listaProdutos = produtoRN.listarProdutoDisponivel();
        FreteRN freteRN = new FreteRN(FreteRN.HIBERNATE_FRETE_DAO);
        this.freteSelecionado = freteRN.buscarFrete(1);
    }
    
    public void carrinhoAction(Produto produto) {
        int posicao = -1;
        
        for(int i=0; i < this.listaItem.size(); i++) {
           if(listaItem.get(i).getProduto().equals(produto)) {
               posicao = i;
           }
        }
        
        if(posicao < 0) {
            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(1);
            item.setValor(produto.getPreco());
            listaItem.add(item);
        } else {
            ItemPedido item = listaItem.get(posicao);
            item.setQuantidade(item.getQuantidade() + 1);
            item.setValor(item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())));
            this.listaItem.set(posicao, item);
        }
        
        cadastrarPedido.setTotal(cadastrarPedido.getTotal().add(produto.getPreco()));
        cadastrarPedido.setFrete(freteSelecionado);
        cadastrarPedido.setDataHora(new Date());
        
    }
    
    public void remover(ItemPedido item) {
        int posicao = -1;
        
        for(int i=0; i < this.listaItem.size(); i++) {
           if(listaItem.get(i).getProduto().equals(item.getProduto())) {
               posicao = i;
           }
        }
        
        if(posicao > -1) {
            listaItem.remove(posicao);
            cadastrarPedido.setTotal(cadastrarPedido.getTotal().subtract(item.getValor()));
        }

    }
    
    public BigDecimal calcularValor() {
        return cadastrarPedido.getTotal().add(freteSelecionado.getValor());
    }
    
    public List<ItemPedido> buscarProduto(Integer item) {
        ItemPedidoRN itemPedidoRN = new ItemPedidoRN(ItemPedidoRN.HIBERNATE_ITEMPEDIDO_DAO);
        List<ItemPedido> listar = itemPedidoRN.buscarTodos();
        List<ItemPedido> apresentar = new ArrayList<>();
                
        for(int i=0; i<listar.size(); i++) {
            if(listar.get(i).getPedido().getCodigoPedido().equals(item)) {
                apresentar.add(listar.get(i));
            }
        }
        return apresentar;
    }
    
    public String cancelarPedido() {
        this.cadastrarPedido = null;
        return "comofazer.xhtml?faces-redirect=true";
    }
    
}
