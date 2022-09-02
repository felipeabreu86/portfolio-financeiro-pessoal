package br.com.financeiro.portfolio.exterior.rest.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseExteriorApiDataDto {

    @JsonProperty("Close")
    private BigDecimal precoFechamento;

    public ResponseExteriorApiDataDto() {
        super();
    }

    public BigDecimal getPrecoFechamento() {
        return precoFechamento;
    }

    public void setPrecoFechamento(BigDecimal precoFechamento) {
        this.precoFechamento = precoFechamento;
    }

}