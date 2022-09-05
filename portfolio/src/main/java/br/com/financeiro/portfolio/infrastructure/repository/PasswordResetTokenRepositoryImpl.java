package br.com.financeiro.portfolio.infrastructure.repository;

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
    public Either<Exception, Boolean> salvar(PasswordResetToken passwordResetToken) {
        try {
            passwordResetTokenDao.save(passwordResetToken);
            return Either.right(true);
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    @Override
    public Either<Exception, PasswordResetToken> obterPasswordResetTokenPelo(String token) {
        try {
            Optional<PasswordResetToken> opt = passwordResetTokenDao.findByToken(token);
            if (opt.isPresent()) {
                return Either.right(opt.get());
            } else {
                return Either.left(new Exception("Token não encontrado."));
            }
        } catch (Exception e) {
            return Either.left(e);
        }
    }

}
