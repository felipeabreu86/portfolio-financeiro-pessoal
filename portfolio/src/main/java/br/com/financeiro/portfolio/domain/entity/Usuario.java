package br.com.financeiro.portfolio.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @Column(name = "username")
    private String nomeUsuario;

    @Column(name = "password")
    private String senha;

    @Column(name = "enabled")
    private Boolean ativo;

    public Usuario() {
        super();
    }

    public Usuario(String nomeUsuario, String senha, Boolean ativo) {
        this();
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void updateStatusUsuario(Boolean isAtivo) {
        this.ativo = isAtivo;
    }

}