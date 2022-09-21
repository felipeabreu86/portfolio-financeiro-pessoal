package br.com.financeiro.portfolio.core.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.financeiro.portfolio.core.annotation.validator.password.PasswordValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaCaracterEspecialValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaLetraMaiusculaValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaLetraMinusculaValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaNulaOuVaziaValidation;
import br.com.financeiro.portfolio.core.annotation.validator.password.SenhaTamanhoPermitido;
import br.com.financeiro.portfolio.domain.service.AtivoService;
import br.com.financeiro.portfolio.domain.service.implementation.AtivoB3ServiceImpl;
import br.com.financeiro.portfolio.domain.service.implementation.AtivoExteriorServiceImpl;

@Configuration
public class AppConfiguration {

    @Bean(name = "B3AtivoService")
    public AtivoService B3AtivoService() {
        return new AtivoB3ServiceImpl();
    }

    @Bean(name = "ExteriorAtivoService")
    public AtivoService ExteriorAtivoService() {
        return new AtivoExteriorServiceImpl();
    }

    @Bean
    public List<PasswordValidation> validacoesDeSenha() {
        List<PasswordValidation> validacoes = new ArrayList<PasswordValidation>();
        validacoes.add(new SenhaNulaOuVaziaValidation());
        validacoes.add(new SenhaTamanhoPermitido());
        validacoes.add(new SenhaLetraMaiusculaValidation());
        validacoes.add(new SenhaLetraMinusculaValidation());
        validacoes.add(new SenhaCaracterEspecialValidation());
        return validacoes;
    }

}