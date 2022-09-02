package br.com.financeiro.portfolio.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioTest {

    @Test
    public void testarCriarNovoUsuarioSemParametros() {
        // Arrange / Act / Assert
        Usuario usuario = new Usuario();
        assertFalse(usuario.isValido());
    }

    @Test
    public void testarCriarNovoUsuarioComParametros() {
        // Arrange
        Usuario usuario = new Usuario();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Act
        usuario.setNome("nome");
        usuario.setNomeUsuario("usuario");
        usuario.setSenha("S3nh@S3nh@");
        usuario.setSobrenome("sobrenome");
        usuario.setStatus(false);

        // Assert
        assertEquals("usuario", usuario.getNomeUsuario());
        assertEquals("nome", usuario.getNome());
        assertTrue(encoder.matches("S3nh@S3nh@", usuario.getSenha()));
        assertEquals("sobrenome", usuario.getSobrenome());
        assertFalse(usuario.getStatus());
        assertTrue(usuario.isValido());
    }

}
