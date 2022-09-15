package br.com.financeiro.portfolio.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.financeiro.portfolio.domain.entity.type.PerfilUsuarioType;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @Column(name = "username", unique = true, length = 50)
    private String username;

    @Column(name = "authority", length = 50)
    @Enumerated(EnumType.STRING)
    private PerfilUsuarioType authority;

    // Construtores

    public Authorities() {
        super();
        this.authority = PerfilUsuarioType.ROLE_USER;
    }

    public Authorities(Usuario usuario) {
        this();
        this.usuario = Objects.requireNonNull(usuario);
        this.username = usuario.getNomeUsuario();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public PerfilUsuarioType getAuthority() {
        return authority;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void setAuthority(PerfilUsuarioType authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (usuario == null || !usuario.getNomeUsuario().equals(username)) {
            throw new IllegalArgumentException(
                    "O 'username' precisa ser idêntico ao do usuário mapeado para Authorities.");
        }
        this.username = username;
    }

    // Métodos

    @Override
    public int hashCode() {
        return Objects.hash(authority, id, username, usuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Authorities other = (Authorities) obj;
        return authority == other.authority &&
               Objects.equals(id, other.id) &&
               Objects.equals(username, other.username) &&
               Objects.equals(usuario, other.usuario);
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
            .append("Authorities [e-mail=").append(username).append("]")
            .append("[authority=").append(authority.toString()).append("]");
        return builder.toString();
    }

}
