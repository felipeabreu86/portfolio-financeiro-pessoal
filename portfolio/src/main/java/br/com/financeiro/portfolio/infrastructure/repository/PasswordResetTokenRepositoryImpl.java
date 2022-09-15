package br.com.financeiro.portfolio.infrastructure.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;
import br.com.financeiro.portfolio.domain.repository.PasswordResetTokenRepository;
import br.com.financeiro.portfolio.infrastructure.dao.PasswordResetTokenDao;
import io.vavr.control.Either;

@Component
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepository {

    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;

    @Override
    public Either<Exception, PasswordResetToken> salvar(final PasswordResetToken passwordResetToken) {

        try {
            return Either.right(passwordResetTokenDao.save(passwordResetToken));
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    @Override
    public Either<Exception, PasswordResetToken> obterPasswordResetTokenPelo(final String token) {

        Optional<PasswordResetToken> opt = passwordResetTokenDao.findByToken(token);

        return opt.isPresent() 
                ? Either.right(opt.get()) 
                : Either.left(new Exception("Token não encontrado."));
    }
    
    @Override
    public Either<Exception, Integer> deletarTokensExpiradosDesde(final Date now) {

        try {
            Optional<Integer> result = passwordResetTokenDao.deleteAllExpiredSince(now);
            return Either.right(result.isPresent() ? result.get() : 0);
        } catch (Exception e) {
            return Either.left(e);
        }
    }
    
    @Override
    public Either<Exception, Integer> deletarTokensPor(final Long userId) {

        try {
            Optional<Integer> result = passwordResetTokenDao.deleteTokensBy(userId);
            return Either.right(result.isPresent() ? result.get() : 0);
        } catch (Exception e) {
            return Either.left(e);
        }
    }

}
