package br.com.financeiro.portfolio.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.financeiro.portfolio.domain.entity.Ativo;
import br.com.financeiro.portfolio.domain.repository.AtivoRepository;
import br.com.financeiro.portfolio.infrastructure.dao.AtivoDao;

@Component
public class AtivoRepositoryImpl implements AtivoRepository {

    @Autowired
    private AtivoDao ativoDao;

    @Override
    public List<Ativo> obterTodosAtivos() {
        return ativoDao.findAll();
    }

    @Override
    public Optional<Ativo> salvar(Ativo ativo) {
        Ativo ativoSalvo = null;

        if (ativo != null && ativo.isValido()) {
            ativoSalvo = ativoDao.save(ativo);
        }

        return Optional.ofNullable(ativoSalvo);
    }

}
