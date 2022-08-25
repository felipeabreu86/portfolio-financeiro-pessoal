package br.com.financeiro.portfolio.domain.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Usuario {

    @Id
    @Column(name = "username")
    protected String nomeUsuario;

    @Column(name = "password")
    protected String senha;

    @Column(name = "enabled")
    protected Boolean ativo;

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

}