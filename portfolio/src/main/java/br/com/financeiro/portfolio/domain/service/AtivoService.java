package br.com.financeiro.portfolio.domain.service;

import java.util.List;

import br.com.financeiro.portfolio.domain.entity.Ativo;

public interface AtivoService {

    List<Ativo> obterTodosAtivos();

}
