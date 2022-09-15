package br.com.financeiro.portfolio.infrastructure.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;

@Repository
public interface PasswordResetTokenDao extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(final String token);

    @Modifying
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    Optional<Integer> deleteAllExpiredSince(final Date now);

    @Modifying
    @Query("delete from PasswordResetToken t where t.user.id = ?1")
    Optional<Integer> deleteTokensBy(final Long userId);

}
