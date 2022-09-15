package br.com.financeiro.portfolio.domain.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.netty.util.internal.StringUtil;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String nomeUsuario;

    @Column(name = "name")
    private String nome;

    @Column(name = "lastname")
    private String sobrenome;

    @Column(name = "password")
    private String senha;

    @Column(name = "enabled")
    private Boolean status;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Authorities authorities;

    // Construtores

    public Usuario() {
        super();
        this.authorities = new Authorities(this);
    }

    public Usuario(String nomeUsuario, String nome, String sobrenome, String senha, Boolean status) {
        this();
        setNomeUsuario(nomeUsuario);
        setNome(nome);
        setSobrenome(sobrenome);
        setSenha(senha);
        setStatus(status);
    }

    // Getters e Setters
    
    public Long getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.authorities.setUsername(nomeUsuario);
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

    // Métodos

    public boolean isValido() {
        return !StringUtil.isNullOrEmpty(this.nomeUsuario) && 
               !StringUtil.isNullOrEmpty(this.nome) &&
               !StringUtil.isNullOrEmpty(this.sobrenome) &&
               !StringUtil.isNullOrEmpty(this.senha) &&
               status != null &&
               authorities != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nome, this.nomeUsuario, this.senha, this.sobrenome, this.status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(this.nome, other.nome) && 
               Objects.equals(this.nomeUsuario, other.nomeUsuario) &&
               Objects.equals(this.sobrenome, other.sobrenome) &&
               Objects.equals(this.status, other.status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
            .append("Usuário [e-mail=").append(nomeUsuario).append("]")
            .append("[status=").append(status ? "ativo" : "inativo").append("]");
        return builder.toString();
    }

}