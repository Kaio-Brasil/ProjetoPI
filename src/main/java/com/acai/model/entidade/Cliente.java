package com.acai.model.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente implements Serializable {
    private Integer idCliente;
    private String nome;
    private Date DataNasc;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;

    public Cliente() {}

    public Cliente(String nome, Date DataNasc, String cpf, String telefone, String email, String senha) {
        this.nome = nome;
        this.DataNasc = DataNasc;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @NotEmpty(message = "O campo nome é obrigatório")
    @Column(length = 45, nullable = false)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Past(message = "A data de nascimento não pode ser futura!")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getDataNasc() {
        return DataNasc;
    }

    public void setDataNasc(Date DataNasc) {
        this.DataNasc = DataNasc;
    }
    
    @NotNull(message = "O campo cpf deve ser informado!")
    @Size(max = 14, message = "O cpf deve possui 11 digitos!")
    @CPF(message = "O cpf deve ser valido!")
    @Column(length = 14, nullable = false, unique = true)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Size(min = 11, max = 20, message = "Numero de telefone invalido")
    @Column(length = 20)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    @NotEmpty(message = "O campo email é obrigatório")
    @Size(min = 12, max = 45, message = "Tamanho de caractere é 12 a 45")
    @Email(message = "O Email deve ser valido!")
    @Column(length = 45, nullable = false)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "O campo email é obrigatório")
    @Size(min = 4, max = 45, message = "Tamanho de caractere é 4 a 45")
    @Column(length = 45, nullable = false)
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        return true;
    }
    
}
