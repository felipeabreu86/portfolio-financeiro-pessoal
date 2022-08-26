package br.com.financeiro.portfolio.core.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseB3ApiDto {

    @JsonProperty("vl_fechamento")
    private BigDecimal ultimaCotacao;

    public BigDecimal getUltimaCotacao() {
        return ultimaCotacao;
    }

    public void setUltimaCotacao(BigDecimal ultimaCotacao) {
        this.ultimaCotacao = ultimaCotacao;
    }

}