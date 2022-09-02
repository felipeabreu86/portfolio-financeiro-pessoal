package br.com.financeiro.portfolio.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.netty.util.internal.StringUtil;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @Column(name = "username")
    private String nomeUsuario;

    @Column(name = "name")
    private String nome;

    @Column(name = "lastname")
    private String sobrenome;

    @Column(name = "password")
    private String senha;

    @Column(name = "enabled")
    private Boolean status;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(Objects.requireNonNull(senha));
    }

    public boolean isValido() {
        return !StringUtil.isNullOrEmpty(nomeUsuario) 
                && !StringUtil.isNullOrEmpty(nome)
                && !StringUtil.isNullOrEmpty(sobrenome) 
                && !StringUtil.isNullOrEmpty(senha) 
                && status != null;
    }

}