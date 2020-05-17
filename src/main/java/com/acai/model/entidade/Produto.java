package com.acai.model.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@ManagedBean(name = "produto")
@RequestScoped
public class Produto implements Serializable {
    private Integer codigoProduto;
    private String descricao;
    private BigDecimal preco;
    private boolean statusProduto;
    private String tipo;

    public Produto() {}

    public Produto(Integer codigoProduto, String descricao, BigDecimal preco, boolean statusProduto, String tipo) {
        this.codigoProduto = codigoProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.statusProduto = statusProduto;
        this.tipo = tipo;
    }
       
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigoProduto() {
        return this.codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    @NotEmpty(message = "O campo descrição é obrigatório")
    @Size(min = 3, max = 45, message = "Tamanha de caractere pada o campo é 5 a 45")
    @Column(length = 45, nullable = false)
    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @NotNull(message = "O campo preço é obrigatório")
    @DecimalMin(value = "0.00", message = "O preço minimo para o campo é R$0.00")
    @DecimalMax(value = "9999.99", message = "O preço maxímo para o campo é R$9999.99")
    @Column(nullable = false, precision = 4, scale = 2)
    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    public boolean isStatusProduto() {
        return this.statusProduto;
    }

    public void setStatusProduto(boolean statusProduto) {
        this.statusProduto = statusProduto;
    }

    @NotEmpty(message = "O Campo tipo é Obrigatório")
    @Column(name = "tipoProduto", length = 30, nullable = false)
    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.codigoProduto, other.codigoProduto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "codigoProduto=" + codigoProduto + ", descricao=" + descricao + 
                ", preco=" + preco + ", statusProduto=" + statusProduto + ", tipo=" + tipo + '}';
    }
    
}
