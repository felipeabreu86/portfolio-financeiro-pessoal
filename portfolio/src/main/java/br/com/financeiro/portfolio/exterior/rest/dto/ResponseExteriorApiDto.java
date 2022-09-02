package br.com.financeiro.portfolio.exterior.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseExteriorApiDto {

    @JsonProperty("data")
    private List<ResponseExteriorApiDataDto> dados;

    public List<ResponseExteriorApiDataDto> getDados() {
        return dados;
    }

    public void setDados(List<ResponseExteriorApiDataDto> dados) {
        this.dados = dados;
    }

    public BigDecimal getUltimaCotacao() {
        return (dados != null && dados.size() > 0)
                ? dados.get(0).getPrecoFechamento()
                : BigDecimal.ZERO;
    }

}