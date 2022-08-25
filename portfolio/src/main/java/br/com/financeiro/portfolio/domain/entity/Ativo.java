package br.com.financeiro.portfolio.domain.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.financeiro.portfolio.domain.type.AtivoType;

@MappedSuperclass
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected AtivoType tipoAtivo;

    @Column(nullable = false, unique = true)
    protected String codigoAtivo;

    public Ativo(AtivoType tipoDeAtivo, String codigoAtivo) {
        this.tipoAtivo = tipoDeAtivo;
        this.codigoAtivo = codigoAtivo;
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

}