package br.com.financeiro.portfolio.domain.repository;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;
import io.vavr.control.Either;

public interface PasswordResetTokenRepository {

    Either<Exception, Boolean> salvar(PasswordResetToken passwordResetToken);

    Either<Exception, PasswordResetToken> obterPasswordResetTokenPelo(String token);

}
