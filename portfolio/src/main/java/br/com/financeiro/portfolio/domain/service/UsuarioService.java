package br.com.financeiro.portfolio.domain.service;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;
import br.com.financeiro.portfolio.domain.entity.Usuario;
import io.vavr.control.Either;

public interface UsuarioService {

    Either<Exception, Usuario> obterUsuarioPelo(final String nomeUsuario);

    Either<Exception, Usuario> criarNovo(final Usuario usuario);

    Either<Exception, Usuario> atualizar(final Usuario usuario);

    Either<Exception, Integer> deletar(final Usuario usuario);

    Either<Exception, Usuario> atualizarSenhaDoUsuario(final String emailCadastrado, final String novaSenha, final String token);

    Either<Exception, PasswordResetToken> criarTokenDeRecuperacaoDeSenha(final Usuario usuario);

    Either<Exception, PasswordResetToken> validarTokenDeRecuperacaoDeSenha(final String usuario, final String token);

}
