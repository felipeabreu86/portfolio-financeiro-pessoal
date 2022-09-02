package br.com.financeiro.portfolio.domain.service;

import br.com.financeiro.portfolio.domain.entity.Usuario;
import io.vavr.control.Either;

public interface UsuarioService {

    Either<Exception, Usuario> criarNovo(Usuario usuario);

    Either<Exception, Usuario> atualizar(Usuario usuario);

    Either<Exception, Boolean> deletar(Usuario usuario);

}