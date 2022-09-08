package br.com.financeiro.portfolio.infrastructure.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.financeiro.portfolio.domain.entity.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeUsuario(final String nomeUsuario);

    @Modifying
    @Query(value = "delete u, p from users u inner join password_reset_token p on u.username = p.username where u.username = ?1", nativeQuery = true)
    Optional<Integer> deletar(final String nomeUsuario);

}