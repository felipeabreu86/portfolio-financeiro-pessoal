package br.com.financeiro.portfolio.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.financeiro.portfolio.domain.entity.type.AtivoType;

@Entity
@Table(name = "ativos")
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AtivoType tipoAtivo;

    @Column(nullable = false, unique = true)
    private String codigoAtivo;

    // Construtores

    public Ativo() {
        super();
    }

    public Ativo(AtivoType tipoDeAtivo, String codigoAtivo) {
        this();
        this.tipoAtivo = Objects.requireNonNull(tipoDeAtivo);
        this.codigoAtivo = this.getTipoAtivo().validar(codigoAtivo);
    }

    // Getters e Setters

    public long getId() {
        return id;
    }

    public AtivoType getTipoAtivo() {
        return tipoAtivo;
    }

    public String getCodigoAtivo() {
        return codigoAtivo;
    }

    public boolean isValido() {
        return (this.tipoAtivo == null || this.codigoAtivo == null) ? false : true;
    }

    // Métodos

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
            .append("Ativo [codigo=").append(this.codigoAtivo).append("]")
            .append("[tipo=").append(this.tipoAtivo.toString()).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigoAtivo, this.id, this.tipoAtivo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ativo other = (Ativo) obj;
        return Objects.equals(codigoAtivo, other.codigoAtivo) && tipoAtivo == other.tipoAtivo;
    }

}