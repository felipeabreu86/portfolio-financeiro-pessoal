package br.com.financeiro.portfolio.infrastructure.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financeiro.portfolio.domain.entity.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeUsuario(final String nomeUsuario);

}