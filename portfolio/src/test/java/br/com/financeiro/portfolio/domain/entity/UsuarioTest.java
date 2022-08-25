package br.com.financeiro.portfolio.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UsuarioTest {

    @Test
    void testarCriarNovoUsuarioSemParametros() {
        new Usuario();
    }

    @Test
    void testarCriarNovoUsuarioComParametros() {
        Usuario usuario = new Usuario("usuario", "senha", true);
        Usuario usuario2 = new Usuario("usuario", "senha", false);
        new Usuario(null, "senha", true);
        new Usuario("usuario", null, true);
        new Usuario(null, null, false);

        assertEquals("usuario", usuario.getNomeUsuario());
        assertEquals("senha", usuario.getSenha());
        assertTrue(usuario.isAtivo());
        assertFalse(usuario2.isAtivo());
    }

}
