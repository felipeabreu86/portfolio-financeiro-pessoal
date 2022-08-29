package br.com.financeiro.portfolio.domain.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financeiro.portfolio.domain.entity.Ativo;
import br.com.financeiro.portfolio.domain.repository.AtivoRepository;
import br.com.financeiro.portfolio.domain.service.AtivoService;

@Service
public class AtivoB3ServiceImpl implements AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    @Override
    public List<Ativo> obterTodosAtivos() {
        return ativoRepository
                .obterTodosAtivos()
                .stream()
                .filter(a -> a.getTipoAtivo().isB3())
                .collect(Collectors.toList());
    }

}
