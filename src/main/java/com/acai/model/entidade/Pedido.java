package com.acai.model.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {
    private Integer codigoPedido;
    private Date dataHora;
    private StatusPedido status;
    private BigDecimal total;
    private Frete frete;
    private Cliente cliente;
    private EnderecoEntrega entrega;

    public Pedido() {this.entrega = new EnderecoEntrega();}

    public Pedido(StatusPedido status, BigDecimal total, Frete frete, Cliente cliente, EnderecoEntrega entrega) {
        this.dataHora = new Date();
        this.status = status;
        this.total = total;
        this.frete = frete;
        this.cliente = cliente;
        this.entrega = entrega;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigoPedido() {
        return this.codigoPedido;
    }

    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="data_hora", nullable = false)
    public Date getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Enumerated(EnumType.STRING)
    public StatusPedido getStatus() {
        return this.status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Column(nullable = false, precision = 4, scale = 2)
    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "frete_codigoFrete", referencedColumnName = "codigoFrete", nullable = false)
    public Frete getFrete() {
        return frete;
    }

    public void setFrete(Frete frete) {
        this.frete = frete;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_idCliente", referencedColumnName = "idCliente", nullable = false)
    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="entrega_codigoEntrega", referencedColumnName = "codigoEntrega", nullable = false)
    public EnderecoEntrega getEnderecoEntrega() {
        return this.entrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega entrega) {
        this.entrega = entrega;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.codigoPedido, other.codigoPedido)) {
            return false;
        }
        return true;
    }
    
}
