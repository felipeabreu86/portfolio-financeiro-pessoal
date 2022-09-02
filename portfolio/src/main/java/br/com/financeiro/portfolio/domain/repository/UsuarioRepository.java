package br.com.financeiro.portfolio.domain.repository;

import br.com.financeiro.portfolio.domain.entity.Usuario;
import io.vavr.control.Either;

public interface UsuarioRepository {

    Either<Exception, Usuario> obterUsuarioPelo(String nomeUsuario);

    Either<Exception, Usuario> salvarOuAtualizar(Usuario usuario);

    Either<Exception, Boolean> deletar(Usuario usuario);

}
