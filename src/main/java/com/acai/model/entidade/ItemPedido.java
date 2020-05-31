package com.acai.model.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido implements Serializable {
    private Integer codigoItem;
    private Integer quantidade;
    private BigDecimal valor;
    private Produto produto;
    private Pedido pedido;

    public ItemPedido() {
        this.produto = new Produto();
        this.pedido = new Pedido();
    }

    public ItemPedido(Integer quantidade, BigDecimal valor, Produto produto, Pedido pedido) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.produto = produto;
        this.pedido = pedido;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigoItem() {
        return this.codigoItem;
    }

    public void setCodigoItem(Integer codigoItem) {
        this.codigoItem = codigoItem;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Column(name = "valorItem")
    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="produto_codigoProduto", referencedColumnName = "codigoProduto", nullable = false)
    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pedido_codigoPedido", referencedColumnName = "codigoPedido", nullable = false)
    public Pedido getPedido() {
        return this.pedido;
    }
        
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        
        if(obj == null) {
            return false;
        }
        
        if(getClass() != obj.getClass()) {
            return false;
        }
        
        final ItemPedido other = (ItemPedido) obj;
        
        if(!Objects.equals(this.codigoItem, other.codigoItem)) {
            return false;
        }
        return true;
    }
    
}
