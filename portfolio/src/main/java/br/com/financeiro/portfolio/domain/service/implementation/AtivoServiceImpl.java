package br.com.financeiro.portfolio.domain.service.implementation;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.financeiro.portfolio.core.util.StringUtil;
import br.com.financeiro.portfolio.domain.service.AtivoService;

@Service
public class AtivoServiceImpl implements AtivoService {

    @Value("${b3.api}")
    private String b3ApiUri;

    @Override
    public String obterUriApiB3() {
        return this.b3ApiUri;
    }

    @Override
    public String obterUriApiB3(String codigoAtivo, int qtdPregoes) {
        String b3ApiUriComParams = "";

        if (!StringUtil.isNullOrEmpty(this.b3ApiUri) && qtdPregoes > 0 && qtdPregoes <= 2700) {
            b3ApiUriComParams = String.format("%s/%s",
                    StringUtil.combineStringsToPath(this.b3ApiUri, Objects.requireNonNull(codigoAtivo).trim()),
                    qtdPregoes);
        }

        return b3ApiUriComParams;
    }

}
