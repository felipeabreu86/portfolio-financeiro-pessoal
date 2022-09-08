package br.com.financeiro.portfolio.domain.repository;

import br.com.financeiro.portfolio.domain.entity.Usuario;
import io.vavr.control.Either;

public interface UsuarioRepository {

    Either<Exception, Usuario> obterUsuarioPelo(final String nomeUsuario);

    Either<Exception, Usuario> salvarOuAtualizar(final Usuario usuario);

    Either<Exception, Integer> deletar(final Usuario usuario);

}
