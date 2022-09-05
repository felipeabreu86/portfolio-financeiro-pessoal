package br.com.financeiro.portfolio.infrastructure.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;

@Repository
public interface PasswordResetTokenDao extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

}
