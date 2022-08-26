package br.com.financeiro.portfolio.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.financeiro.portfolio.domain.entity.Ativo;
import br.com.financeiro.portfolio.domain.repository.AtivoRepository;

@Component
public class AtivoRepositoryImpl implements AtivoRepository {

    @Override
    public List<Ativo> obterTodosAtivos() {
        return null;
    }

    @Override
    public Optional<Ativo> salvar(Ativo ativo) {
        return null;
    }

}
