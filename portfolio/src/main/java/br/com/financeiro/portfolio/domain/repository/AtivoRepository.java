package br.com.financeiro.portfolio.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.financeiro.portfolio.domain.entity.Ativo;

public interface AtivoRepository {

    List<Ativo> obterTodosAtivos();

    Optional<Ativo> salvar(final Ativo ativo);

}
