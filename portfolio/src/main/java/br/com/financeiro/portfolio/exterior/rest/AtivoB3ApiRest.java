package br.com.financeiro.portfolio.exterior.rest;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.financeiro.portfolio.exterior.rest.dto.ResponseB3ApiDto;

@RestController
@RequestMapping(value = "/api/b3")
public class AtivoB3ApiRest {
        
    @Autowired
    private WebClient webClient;
    
    @Autowired
    private Environment env;

    /**
     * 
     * @param codigoAtivo
     * @param periodo
     * @return
     */
    @GetMapping(
            value = { "/dados/{codigoAtivo}", "/dados/{codigoAtivo}/{periodo}" }, 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obterDadosDoAtivo(@PathVariable String codigoAtivo, @PathVariable Optional<Integer> periodo) {

        String responseBody = null;
        
        Integer periodoDias = (periodo.isPresent() && periodo.get() > 0)
                ? periodo.get() 
                : 1;
        
        String b3ApiUri = String.format("%s/%s",
                StringUtil.combineStringsToPath(env.getProperty("b3.api.uri"), codigoAtivo.trim()), periodoDias);        
        
        String[] schemes = { "https" };
        
        if (new UrlValidator(schemes).isValid(b3ApiUri)) {
            responseBody = webClient
                    .get()
                    .uri(b3ApiUri)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block()
                    .trim();
        }
        
        return (!StringUtil.isNullOrEmpty(responseBody) && !responseBody.equals("[]"))
                ? ResponseEntity.ok(responseBody)
                : ResponseEntity.notFound().build();
    }
    
    /**
     * 
     * @param codigoAtivo
     * @return
     */
    @GetMapping(
            value = { "/cotacao/{codigoAtivo}" }, 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> obterUltimaCotacao(@PathVariable String codigoAtivo) {  
        
        ResponseB3ApiDto responseB3ApiDto = null;
        ResponseEntity<String> response = obterDadosDoAtivo(codigoAtivo, Optional.of(1));

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            Optional<ResponseB3ApiDto[]> optB3Api = JsonUtil.readValue(response.getBody(), ResponseB3ApiDto[].class);
            
            if (optB3Api.isPresent() && optB3Api.get().length > 0) {
                responseB3ApiDto = optB3Api.get()[0];
            }
        }

        return responseB3ApiDto != null 
                ? ResponseEntity.ok(responseB3ApiDto.getUltimaCotacao())
                : ResponseEntity.ok(BigDecimal.ZERO);
    }

}
