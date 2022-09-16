package br.com.financeiro.portfolio.domain.repository;

import java.util.Date;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;
import io.vavr.control.Either;

public interface PasswordResetTokenRepository {

//    Either<Exception, PasswordResetToken> salvar(final PasswordResetToken passwordResetToken);

    Either<Exception, PasswordResetToken> obterPasswordResetTokenPelo(final String token);

    Either<Exception, Integer> deletarTokensExpiradosDesde(final Date now);

//    Either<Exception, Integer> deletarTokensPor(final Long userId);

}
