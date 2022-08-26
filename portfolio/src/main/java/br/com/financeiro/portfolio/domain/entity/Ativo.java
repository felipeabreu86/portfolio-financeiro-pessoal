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

import br.com.financeiro.portfolio.core.type.AtivoType;

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

    public Ativo() {
        super();
    }

    public Ativo(AtivoType tipoDeAtivo, String codigoAtivo) {
        this();
        this.tipoAtivo = Objects.requireNonNull(tipoDeAtivo);
        this.codigoAtivo = this.getTipoAtivo().validar(codigoAtivo);
    }

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
        if (this.tipoAtivo == null || this.codigoAtivo == null) {
            return false;
        }
        return true;
    }

}