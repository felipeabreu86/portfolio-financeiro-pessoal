package br.com.financeiro.portfolio.exterior.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.financeiro.portfolio.core.util.JsonUtil;
import br.com.financeiro.portfolio.core.util.StringUtil;
import br.com.financeiro.portfolio.exterior.rest.dto.ResponseExteriorApiDto;

@RestController
@RequestMapping(value = "/api/exterior")
public class AtivoExteriorApiRest {
        
    @Autowired
    private WebClient webClient;
    
    @Autowired
    private Environment appProperties;

    @Autowired
    @Qualifier("envProperties")
    private Properties envProperties;

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
                
        String responseBody = null;
        
        Integer periodoDias = periodo.isPresent() && periodo.get() > 0 
                ? periodo.get() 
                : 1;
        
        String exteriorApiUri = appProperties.getProperty("yahoo.api.uri");
        
        String[] schemes = { "https" };
        
        if (new UrlValidator(schemes).isValid(exteriorApiUri)) {
            responseBody = webClient
                    .post()
                    .uri(exteriorApiUri)
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("X-RapidAPI-Key", envProperties.getProperty("yahoo.api.key"))
                    .header("X-RapidAPI-Host", appProperties.getProperty("yahoo.api.host"))
                    .bodyValue("symbol=" + codigoAtivo + "&period=" + periodoDias + "d")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block()
                    .trim();
        }

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
