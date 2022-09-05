package br.com.financeiro.portfolio.domain.service;

import javax.servlet.http.HttpServletRequest;

import br.com.financeiro.portfolio.domain.entity.Usuario;
import io.vavr.control.Either;

public interface EmailService {

    Either<Exception, Boolean> enviarEmailRecuperacaoSenha(final HttpServletRequest request, final String token, final Usuario usuario);

}
