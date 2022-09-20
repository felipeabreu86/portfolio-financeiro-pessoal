package br.com.financeiro.portfolio.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.financeiro.portfolio.domain.service.AtivoService;
import br.com.financeiro.portfolio.domain.service.implementation.AtivoB3ServiceImpl;
import br.com.financeiro.portfolio.domain.service.implementation.AtivoExteriorServiceImpl;

@Configuration
public class ServiceConfiguration {

    @Bean(name = "ativoB3Service")
    public AtivoService obterAtivoB3Service() {
        return new AtivoB3ServiceImpl();
    }

    @Bean(name = "ativoExteriorService")
    public AtivoService obterAtivoExteriorService() {
        return new AtivoExteriorServiceImpl();
    }

}