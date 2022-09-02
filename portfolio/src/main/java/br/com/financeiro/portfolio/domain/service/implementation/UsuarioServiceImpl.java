package br.com.financeiro.portfolio.domain.service.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financeiro.portfolio.core.exception.UsuarioExistenteException;
import br.com.financeiro.portfolio.domain.entity.Usuario;
import br.com.financeiro.portfolio.domain.repository.UsuarioRepository;
import br.com.financeiro.portfolio.domain.service.UsuarioService;
import io.vavr.control.Either;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * 
     */
    @Override
    @Transactional
    public Either<Exception, Usuario> criarNovo(Usuario usuario) {

        // Validar usuário passado por parâmetro
        if (usuario == null || !usuario.isValido()) {
            return Either.left(new IllegalArgumentException("Dados inválidos!"));
        }

        // Se o usuário já existir, não criar novo usuário e retornar exceção
        if (usuarioRepository.obterUsuarioPelo(usuario.getNomeUsuario()).isRight()) {
            return Either.left(new UsuarioExistenteException("Conta já cadastrada."));
        }

        return usuarioRepository.salvarOuAtualizar(usuario);
    }

    /**
     * 
     */
    @Override
    @Transactional
    public Either<Exception, Usuario> atualizar(Usuario usuario) {

        // Validar usuário passado por parâmetro
        if (usuario == null || !usuario.isValido()) {
            return Either.left(new IllegalArgumentException("Dados inválidos!"));
        }

        // Validar se o usuário não existir, não atualizar e retornar exceção
        Either<Exception, Usuario> usuarioCadastrado = usuarioRepository.obterUsuarioPelo(usuario.getNomeUsuario());
        if (usuarioCadastrado.isLeft()) {
            return Either.left(usuarioCadastrado.getLeft());
        }

        return usuarioRepository.salvarOuAtualizar(usuario);
    }

    /**
     * 
     */
    @Override
    @Transactional
    public Either<Exception, Boolean> deletar(Usuario usuario) {

        // Validar usuário passado por parâmetro
        if (usuario == null || !usuario.isValido()) {
            return Either.left(new IllegalArgumentException("Dados inválidos!"));
        }

        return usuarioRepository.deletar(usuario);
    }

}
