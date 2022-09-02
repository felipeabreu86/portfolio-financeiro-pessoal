package br.com.financeiro.portfolio.infrastructure.repository;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
    public Either<Exception, Usuario> obterUsuarioPelo(String nomeUsuario) {
        try {
            Usuario usuario = usuarioDao.findByNomeUsuario(nomeUsuario).get();
            return Either.right(usuario);
        } catch (NoSuchElementException e) {
            return Either.left(new UsuarioNaoEncontradoException("Usuário não encontrado", e));
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    @Override
    public Either<Exception, Usuario> salvarOuAtualizar(Usuario usuario) {
        try {
            usuario = usuarioDao.save(usuario);
            return Either.right(usuario);
        } catch (IllegalArgumentException | InvalidDataAccessApiUsageException e) {
            return Either.left(e);
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    @Override
    public Either<Exception, Boolean> deletar(Usuario usuario) {
        try {
            usuarioDao.delete(usuario);
            return Either.right(true);
        } catch (Exception e) {
            return Either.left(e);
        }
    }

}
