package br.com.financeiro.portfolio.domain.service.implementation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.financeiro.portfolio.core.util.StringUtil;
import br.com.financeiro.portfolio.domain.entity.Ativo;
import br.com.financeiro.portfolio.domain.repository.AtivoRepository;
import br.com.financeiro.portfolio.domain.service.AtivoService;

@Service
public class AtivoServiceImpl implements AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    @Value("${b3.api}")
    private String b3ApiUri;

    @Override
    public String obterUriDaApi(String codigoAtivo, int qtdPregoes) {
        String b3ApiUriComParams = "";

        if (!StringUtil.isNullOrEmpty(this.b3ApiUri) && qtdPregoes > 0 && qtdPregoes <= 2700) {
            b3ApiUriComParams = String.format("%s/%s",
                    StringUtil.combineStringsToPath(this.b3ApiUri, Objects.requireNonNull(codigoAtivo).trim()),
                    qtdPregoes);
        }

        return b3ApiUriComParams;
    }

    @Override
    public List<Ativo> obterTodosAtivosDaB3() {
        return ativoRepository
                .obterTodosAtivos()
                .stream()
                .filter(a -> a.getTipoAtivo().isB3())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Ativo> obterTodosAtivosDoExterior() {
        return ativoRepository
                .obterTodosAtivos()
                .stream()
                .filter(a -> a.getTipoAtivo().isInvestimentoExterior())
                .collect(Collectors.toList());
    }

}
