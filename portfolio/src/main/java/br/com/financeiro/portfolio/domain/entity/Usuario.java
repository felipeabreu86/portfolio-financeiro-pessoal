package br.com.financeiro.portfolio.domain.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private String email;

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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Set<PasswordResetToken> tokens;

    // Construtores

    public Usuario() {
        super();
        this.authorities = new Authorities(this);
        this.tokens = new HashSet<PasswordResetToken>();
    }

    public Usuario(String email, String nome, String sobrenome, String senha, Boolean status) {
        this();
        setEmail(email);
        setNome(nome);
        setSobrenome(sobrenome);
        setSenha(senha);
        setStatus(status);
    }

    // Getters e Setters
    
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.authorities.setEmail(email);
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
        return !StringUtil.isNullOrEmpty(this.email) && 
               !StringUtil.isNullOrEmpty(this.nome) &&
               !StringUtil.isNullOrEmpty(this.sobrenome) &&
               !StringUtil.isNullOrEmpty(this.senha) &&
               status != null &&
               authorities != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nome, this.email, this.senha, this.sobrenome, this.status);
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
               Objects.equals(this.email, other.email) &&
               Objects.equals(this.sobrenome, other.sobrenome) &&
               Objects.equals(this.status, other.status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
            .append("Usuário [e-mail=").append(email).append("]")
            .append("[status=").append(status ? "ativo" : "inativo").append("]");
        return builder.toString();
    }

    public PasswordResetToken generateNewUserToken() {
        PasswordResetToken token = new PasswordResetToken(UUID.randomUUID().toString(), this);
        tokens.add(token);
        return token;
    }

}