    package br.com.financeiro.portfolio.core.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.financeiro.portfolio.core.annotation.validator.password.PasswordValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaCaracterEspecialValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaLetraMaiusculaValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaLetraMinusculaValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaNulaOuVaziaValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaTamanhoPermitido;
import br.com.financeiro.portfolio.domain.service.AtivoService;
import br.com.financeiro.portfolio.domain.service.implementation.AtivoB3ServiceImpl;
import br.com.financeiro.portfolio.domain.service.implementation.AtivoExteriorServiceImpl;
import reactor.netty.http.Http11SslContextSpec;
import reactor.netty.http.client.HttpClient;

@Configuration
public class AppConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:/messages/error_messages", 
                "classpath:/messages/response_messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;   
    }

    @Bean
    public WebClient getWebClient() {        
        HttpClient httpClient = HttpClient
                .create()
                .responseTimeout(Duration.ofSeconds(30))        
                .secure(spec -> spec
                        .sslContext(Http11SslContextSpec.forClient())
                        .handshakeTimeout(Duration.ofSeconds(30))         
                        .closeNotifyFlushTimeout(Duration.ofSeconds(10))  
                        .closeNotifyReadTimeout(Duration.ofSeconds(10)));

        return WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    
    @Bean(name = "envProperties")
    public Properties obterVariaveisDeAmbiente() throws IOException {
        try (InputStream input = new FileInputStream("env/env.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        }
    }
    
    @Bean(name = "ativoB3Service")
    public AtivoService obterAtivoB3Service() {
        return new AtivoB3ServiceImpl();
    }

    @Bean(name = "ativoExteriorService")
    public AtivoService obterAtivoExteriorService() {
        return new AtivoExteriorServiceImpl();
    }
    
    @Bean
    public List<PasswordValidation> obterValidacoesDeSenha() {
        List<PasswordValidation> validacoes = new ArrayList<PasswordValidation>();
        validacoes.add(new SenhaNulaOuVaziaValidation());
        validacoes.add(new SenhaTamanhoPermitido());
        validacoes.add(new SenhaLetraMaiusculaValidation());
        validacoes.add(new SenhaLetraMinusculaValidation());
        validacoes.add(new SenhaCaracterEspecialValidation());
        return validacoes;
    }

}