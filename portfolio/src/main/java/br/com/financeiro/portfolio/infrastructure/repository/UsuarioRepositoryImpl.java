package br.com.financeiro.portfolio.infrastructure.repository;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.financeiro.portfolio.core.exception.UsuarioNaoEncontradoException;
import br.com.financeiro.portfolio.domain.entity.Usuario;
import br.com.financeiro.portfolio.domain.repository.UsuarioRepository;
import br.com.financeiro.portfolio.infrastructure.dao.UsuarioDao;
import io.vavr.control.Either;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Either<Exception, Usuario> obterUsuarioPelo(final String nomeUsuario) {

        try {
            return Either.right(usuarioDao.findByNomeUsuario(nomeUsuario).get());
        } catch (NoSuchElementException e) {
            return Either.left(new UsuarioNaoEncontradoException("Usuário não encontrado", e));
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    @Override
    public Either<Exception, Usuario> salvarOuAtualizar(final Usuario usuario) {

        try {
            return Either.right(usuarioDao.save(usuario));
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    @Override
    public Either<Exception, Integer> deletar(final Usuario usuario) {

        try {
            usuarioDao.delete(usuario);            
            return Either.right(1);
        } catch (Exception e) {
            return Either.left(e);
        }
    }

}
