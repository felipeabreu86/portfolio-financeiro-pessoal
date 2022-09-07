package br.com.financeiro.portfolio.domain.service.implementation;

import java.util.Calendar;
import java.util.UUID;

import javax.transaction.Transactional;

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
    public Either<Exception, Usuario> obterUsuarioPelo(String nomeUsuario) {
        return usuarioRepository.obterUsuarioPelo(nomeUsuario);
    }

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
        if (obterUsuarioPelo(usuario.getNomeUsuario()).isRight()) {
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

        // Se o usuário não existir, não atualizar e retornar exceção
        Either<Exception, Usuario> usuarioCadastrado = obterUsuarioPelo(usuario.getNomeUsuario());

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
    public Either<Exception, Integer> deletar(Usuario usuario) {

        // Validar usuário passado por parâmetro
        if (usuario == null || !usuario.isValido()) {
            return Either.left(new IllegalArgumentException("Dados inválidos!"));
        }

        return usuarioRepository.deletar(usuario);
    }
    
    @Override
    @Transactional
    public Either<Exception, Usuario> atualizarSenhaDoUsuario(String emailCadastrado, String novaSenha,
            String token) {

        Either<Exception, PasswordResetToken> tokenResult = validarTokenDeRecuperacaoDeSenha(emailCadastrado, token);

        if (tokenResult.isLeft()) {
            return Either.left(tokenResult.getLeft());
        }

        Either<Exception, Usuario> usuarioResult = obterUsuarioPelo(emailCadastrado);

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
    @Transactional
    public Either<Exception, PasswordResetToken> criarTokenDeRecuperacaoDeSenha(Usuario usuario) {

        passwordResetTokenRepository.apagarTokensExpiradosDesde(Calendar.getInstance().getTime());

        PasswordResetToken myToken = new PasswordResetToken(UUID.randomUUID().toString(), usuario);

        return passwordResetTokenRepository.salvar(myToken);
    }

    /**
     * 
     */
    @Override
    public Either<Exception, PasswordResetToken> validarTokenDeRecuperacaoDeSenha(String usuario, String token) {

        if (StringUtil.isNullOrEmpty(usuario) || StringUtil.isNullOrEmpty(token)) {
            return Either.left(new IllegalArgumentException("Usuário ou Token inválidos."));
        }

        Either<Exception, PasswordResetToken> tokenResult = passwordResetTokenRepository.obterPasswordResetTokenPelo(token.trim());
        
        if (tokenResult.isLeft()) {
            return Either.left(tokenResult.getLeft());
        }

        if (tokenResult.get().getExpiryDate().before(Calendar.getInstance().getTime())) {
            return Either.left(new Exception("Token expirado."));
        }

        if (!tokenResult.get().getUser().getNomeUsuario().trim().equals(usuario.trim())) {
            return Either.left(new Exception("Token inválido."));
        }

        return tokenResult;
    }

}
