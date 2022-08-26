package br.com.financeiro.portfolio.domain.service;

public interface AtivoService {

    String obterUriApiB3();

    String obterUriApiB3(String codigoAtivo, int quantidadePregoes);

}
