package br.com.financeiro.portfolio.domain.service;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;
import br.com.financeiro.portfolio.domain.entity.Usuario;
import io.vavr.control.Either;

public interface UsuarioService {

    Either<Exception, Usuario> obterUsuarioPelo(String nomeUsuario);

    Either<Exception, Usuario> criarNovo(Usuario usuario);

    Either<Exception, Usuario> atualizar(Usuario usuario);

    Either<Exception, Boolean> deletar(Usuario usuario);

    Either<Exception, PasswordResetToken> criarTokenDeRecuperacaoDeSenha(Usuario usuario);

    Either<Exception, PasswordResetToken> validarTokenDeRecuperacaoDeSenha(String usuario, String token);

}
