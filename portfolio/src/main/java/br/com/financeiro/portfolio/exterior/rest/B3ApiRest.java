package br.com.financeiro.portfolio.exterior.rest;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.financeiro.portfolio.core.dto.ResponseB3ApiDto;
import br.com.financeiro.portfolio.core.util.JsonUtil;
import br.com.financeiro.portfolio.core.util.StringUtil;

@RestController
@RequestMapping(value = "/api/b3")
public class B3ApiRest {
        
    @Autowired
    private WebClient webClient;

    @Value("${b3.api}")
    private String b3ApiUri;

    @GetMapping(
            value = "/dados/{codigoAtivo}", 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obterDadosDoAtivoPelo(@PathVariable String codigoAtivo) {
        
        String responseBody = webClient
                .get()
                .uri(getB3ApiUri(codigoAtivo))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return !StringUtil.isNullOrEmpty(responseBody) 
                ? ResponseEntity.ok(responseBody)
                : ResponseEntity.notFound().build();
    }
    
    @GetMapping(
            value = "/cotacao/{codigoAtivo}", 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> obterUltimaCotacaoPelo(@PathVariable String codigoAtivo) {  
        
        ResponseB3ApiDto responseB3ApiDto = null;
        ResponseEntity<String> response = obterDadosDoAtivoPelo(codigoAtivo);

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

    private String getB3ApiUri(String codigoAtivo) {
        return b3ApiUri.replace("{ticker}", codigoAtivo.trim());
    }

}
