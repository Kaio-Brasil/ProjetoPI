
package com.acai.model.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario implements Serializable {
    private Integer idUsuario;
    private String nome;
    private String login;
    private String senha;

    public Usuario() {}

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @NotEmpty(message = "O campo nome é obrigatório")
    @Size(min = 3, max = 45, message = "Tamanho de caracteres invalido 3 a 45")
    @Column(length = 45, nullable = false)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotEmpty(message = "O campo login é obrigatório")
    @Size(min = 3, max = 45, message = "Tamanho de caracteres invalido 3 a 45")
    @Column(length = 45, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    @Size(min = 5, max = 45, message = "Tamanho de caracteres invalido 3 a 45")
    @Column(length = 45, nullable = false)
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
