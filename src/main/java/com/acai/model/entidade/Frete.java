package com.acai.model.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class Frete implements Serializable {
    private Integer codigoFrete;
    private BigDecimal valor;

    public Frete() {}

    public Frete(Integer codigoFrete, BigDecimal valor) {
        this.codigoFrete = codigoFrete;
        this.valor = valor;
    }
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigoFrete() {
        return this.codigoFrete;
    }

    public void setCodigoFrete(Integer codigoFrete) {
        this.codigoFrete = codigoFrete;
    }

    @NotNull(message = "O campo valor é obrigatório")
    @DecimalMin(value = "0.00", message = "O valor minimo para o campo é R$0.00")
    @DecimalMax(value = "99.99", message = "O valor maximo para o campo é R$99.99")
    @Column(name = "valorFrete", nullable = false)
    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
}
