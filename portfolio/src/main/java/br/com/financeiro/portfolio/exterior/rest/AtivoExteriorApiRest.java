package br.com.financeiro.portfolio.exterior.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.financeiro.portfolio.core.dto.ResponseExteriorApiDto;
import br.com.financeiro.portfolio.core.util.JsonUtil;
import br.com.financeiro.portfolio.core.util.StringUtil;

@RestController
@RequestMapping(value = "/api/exterior")
public class AtivoExteriorApiRest {
        
    @Autowired
    private WebClient webClient;
    
    @Autowired
    @Qualifier("chaveApiAtivoExterior")
    private String ativoExteriorApiKey;
        
    /**
     * 
     * @param codigoAtivo
     * @param periodo
     * @return
     * @throws IOException 
     */
    @GetMapping(
            value = { "/dados/{codigoAtivo}", "/dados/{codigoAtivo}/{periodo}" }, 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obterDadosDoAtivo(@PathVariable String codigoAtivo, @PathVariable Optional<Integer> periodo) {
        
        Integer periodoDias = periodo.isPresent() && periodo.get() > 0 
                ? periodo.get() 
                : 1;
        
        String responseBody = webClient
                .post()
                .uri("https://yahoo-finance97.p.rapidapi.com/price")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", ativoExteriorApiKey)
                .header("X-RapidAPI-Host", "yahoo-finance97.p.rapidapi.com")
                .bodyValue("symbol=" + codigoAtivo + "&period=" + periodoDias + "d")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return !StringUtil.isNullOrEmpty(responseBody) 
                ? ResponseEntity.ok(responseBody)
                : ResponseEntity.notFound().build();
    }
    
    /**
     * 
     * @param codigoAtivo
     * @return
     * @throws IOException 
     */
    @GetMapping(
            value = { "/cotacao/{codigoAtivo}" }, 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> obterUltimaCotacao(@PathVariable String codigoAtivo) {  
        
        ResponseExteriorApiDto responseExteriorApiDto = null;
        ResponseEntity<String> response = obterDadosDoAtivo(codigoAtivo, Optional.of(1));

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            Optional<ResponseExteriorApiDto> optExteriorApi = JsonUtil.readValue(response.getBody(), ResponseExteriorApiDto.class);
            
            if (optExteriorApi.isPresent()) {
                responseExteriorApiDto = optExteriorApi.get();
            }
        }

        return responseExteriorApiDto != null 
                ? ResponseEntity.ok(responseExteriorApiDto.getUltimaCotacao())
                : ResponseEntity.ok(BigDecimal.ZERO);
    }

}
