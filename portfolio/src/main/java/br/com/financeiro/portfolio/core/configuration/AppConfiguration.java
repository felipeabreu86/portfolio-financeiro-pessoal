package br.com.financeiro.portfolio.core.configuration;

import java.time.Duration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

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
        Http11SslContextSpec http11SslContextSpec = Http11SslContextSpec.forClient();
        
        HttpClient httpClient = HttpClient
                .create()
                .responseTimeout(Duration.ofSeconds(30))        
                .secure(spec -> spec
                        .sslContext(http11SslContextSpec)
                        .handshakeTimeout(Duration.ofSeconds(30))         
                        .closeNotifyFlushTimeout(Duration.ofSeconds(10))  
                        .closeNotifyReadTimeout(Duration.ofSeconds(10)));
        
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder()
                .clientConnector(connector)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}