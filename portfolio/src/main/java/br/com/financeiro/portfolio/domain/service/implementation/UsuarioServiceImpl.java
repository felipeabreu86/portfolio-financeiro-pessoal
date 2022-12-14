package br.com.financeiro.portfolio.domain.service.implementation;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financeiro.portfolio.core.exception.UsuarioExistenteException;
import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;
import br.com.financeiro.portfolio.domain.entity.Usuario;
import br.com.financeiro.portfolio.domain.repository.PasswordResetTokenRepository;
import br.com.financeiro.portfolio.domain.repository.UsuarioRepository;
import br.com.financeiro.portfolio.domain.service.UsuarioService;
import io.netty.util.internal.StringUtil;
import io.vavr.control.Either;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    
    /**
     * 
     */
    @Override
    public Either<Exception, Usuario> obterUsuarioPelo(final String emailUsuario) {
        return usuarioRepository.obterUsuarioPelo(emailUsuario);
    }

    /**
     * 
     */
    @Override
    @Transactional
    public Either<Exception, Usuario> criarNovo(final Usuario usuario) {

        // Validar usuário passado por parâmetro
        if (usuario == null || !usuario.isValido()) {
            return Either.left(new IllegalArgumentException("Dados inválidos!"));
        }

        // Se o usuário já existir, não criar novo usuário e retornar exceção
        if (obterUsuarioPelo(usuario.getEmail()).isRight()) {
            return Either.left(new UsuarioExistenteException("Conta já cadastrada."));
        }

        return usuarioRepository.salvarOuAtualizar(usuario);
    }

    /**
     * 
     */
    @Override
    @Transactional
    public Either<Exception, Usuario> atualizar(final Usuario usuario) {

        // Validar usuário passado por parâmetro
        if (usuario == null || !usuario.isValido()) {
            return Either.left(new IllegalArgumentException("Dados inválidos!"));
        }

        // Se o usuário não existir, não atualizar e retornar exceção
        Either<Exception, Usuario> usuarioCadastrado = obterUsuarioPelo(usuario.getEmail());

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
    public Either<Exception, Integer> deletar(final Usuario usuario) {

        // Validar usuário passado por parâmetro
        if (usuario == null || !usuario.isValido()) {
            return Either.left(new IllegalArgumentException("Dados inválidos!"));
        }
                
        return usuarioRepository.deletar(usuario);
    }
    
    @Override
    @Transactional
    public Either<Exception, Usuario> atualizarSenhaDoUsuario(
            final String emailUsuario, 
            final String novaSenha,
            final String token) {

        Either<Exception, PasswordResetToken> tokenResult = validarTokenDeRecuperacaoDeSenha(emailUsuario, token);

        if (tokenResult.isLeft()) {
            return Either.left(tokenResult.getLeft());
        }

        Either<Exception, Usuario> usuarioResult = obterUsuarioPelo(emailUsuario);

        if (usuarioResult.isLeft()) {
            return Either.left(usuarioResult.getLeft());
        }

        Usuario usuario = usuarioResult.get();
        usuario.setSenha(novaSenha);

        return usuarioRepository.salvarOuAtualizar(usuario);
    }

    /**
     * 
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public Either<Exception, PasswordResetToken> criarTokenDeRecuperacaoDeSenha(final Usuario usuario) {

        final Date dataAtual = Calendar.getInstance().getTime();
        
        passwordResetTokenRepository.deletarTokensExpiradosDesde(dataAtual);
        
        PasswordResetToken token = usuario.generateNewUserToken();        
        
        return usuarioRepository.salvarOuAtualizar(usuario).isRight() 
                ? Either.right(token)
                : Either.left(new Exception("Erro ao salvar o Token"));
    }

    /**
     * 
     */
    @Override
    public Either<Exception, PasswordResetToken> validarTokenDeRecuperacaoDeSenha(
            final String emailUsuario,
            final String token) {

        if (StringUtil.isNullOrEmpty(emailUsuario) || StringUtil.isNullOrEmpty(token)) {
            return Either.left(new IllegalArgumentException("Usuário ou Token inválidos."));
        }

        Either<Exception, PasswordResetToken> tokenResult = passwordResetTokenRepository
                .obterPasswordResetTokenPelo(token);

        if (tokenResult.isLeft()) {
            return Either.left(tokenResult.getLeft());
        }

        if (tokenResult.get().getExpiryDate().before(Calendar.getInstance().getTime())) {
            return Either.left(new Exception("Token expirado."));
        }

        if (!tokenResult.get().getUsuario().getEmail().equals(emailUsuario)) {
            return Either.left(new Exception("Token inválido."));
        }

        return tokenResult;
    }

}
