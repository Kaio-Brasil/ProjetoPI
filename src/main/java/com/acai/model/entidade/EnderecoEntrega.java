package com.acai.model.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Entrega")
public class EnderecoEntrega implements Serializable {
    private Integer codigoEntrega;
    private String rua;
    private String numero;
    private String bairro;
    private String referencia;

    public EnderecoEntrega() {}

    public EnderecoEntrega(String rua, String numero, String bairro) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigoEntrega() {
        return this.codigoEntrega;
    }

    public void setCodigoEntrega(Integer codigoEntrega) {
        this.codigoEntrega = codigoEntrega;
    }
    
    @Column(length = 50)
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Column(length = 10)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(length = 50)
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(length = 75)
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
}
