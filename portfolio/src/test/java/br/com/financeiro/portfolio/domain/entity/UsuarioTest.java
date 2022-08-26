package br.com.financeiro.portfolio.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UsuarioTest {

    @Test
    public void testarCriarNovoUsuarioSemParametros() {
        // Arrange / Act / Assert
        new Usuario();
    }

    @Test
    public void testarCriarNovoUsuarioComParametros() {
        // Arrange
        Usuario usuario = null;
        Usuario usuario2 = null;

        // Act
        usuario = new Usuario("usuario", "senha", true);
        usuario2 = new Usuario("usuario", "senha", false);
        new Usuario(null, "senha", true);
        new Usuario("usuario", null, true);
        new Usuario(null, null, false);

        // Assert
        assertEquals("usuario", usuario.getNomeUsuario());
        assertEquals("senha", usuario.getSenha());
        assertTrue(usuario.isAtivo());
        assertFalse(usuario2.isAtivo());
    }

    @Test
    public void testarSeUsuarioTemStatusValido() {
        // Arrange
        Usuario usuario = new Usuario("usuario", "senha", false);

        // Act
        usuario.updateAtivo(true);

        // Assert
        assertTrue(usuario.isAtivo());
    }

    @Test
    public void testarSeUsuarioTemStatusInvalido() {
        // Arrange
        Usuario usuario = new Usuario("usuario", "senha", true);

        // Act
        usuario.updateAtivo(false);

        // Assert
        assertFalse(usuario.isAtivo());
    }

}
