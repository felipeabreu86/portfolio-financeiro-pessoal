    package br.com.financeiro.portfolio.core.configuration;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.Http11SslContextSpec;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {

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

}