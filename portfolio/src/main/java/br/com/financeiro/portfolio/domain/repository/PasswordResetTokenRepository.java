package br.com.financeiro.portfolio.domain.repository;

import java.util.Date;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;
import io.vavr.control.Either;

public interface PasswordResetTokenRepository {

    Either<Exception, PasswordResetToken> salvar(PasswordResetToken passwordResetToken);

    Either<Exception, PasswordResetToken> obterPasswordResetTokenPelo(String token);

    Either<Exception, Integer> apagarTokensExpiradosDesde(Date now);

}
