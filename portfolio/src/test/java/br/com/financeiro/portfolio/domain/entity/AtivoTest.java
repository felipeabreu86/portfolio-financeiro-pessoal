package br.com.financeiro.portfolio.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.financeiro.portfolio.domain.entity.type.AtivoType;

public class AtivoTest {

    @Test
    public void testarCriarAtivoStock() {
        // Arrange
        Ativo asset = null;

        // Act
        asset = new Ativo(AtivoType.STOCK, "ABCD");

        // Assert
        assertEquals(true, asset instanceof Ativo);
        assertEquals("ABCD", asset.getCodigoAtivo());
        assertEquals(AtivoType.STOCK, asset.getTipoAtivo());
    }

    @Test
    public void testarCriarAtivoReit() {
        // Arrange
        Ativo asset = null;

        // Act
        asset = new Ativo(AtivoType.REIT, "ABC");

        // Asset
        assertEquals(true, asset instanceof Ativo);
        assertEquals("ABC", asset.getCodigoAtivo());
        assertEquals(AtivoType.REIT, asset.getTipoAtivo());
    }

    @Test
    public void testarCriarAtivoAcao() {
        // Arrange
        Ativo asset = null;

        // Act
        asset = new Ativo(AtivoType.ACAO, "ABCD3");

        // Assert
        assertEquals(true, asset instanceof Ativo);
        assertEquals("ABCD3", asset.getCodigoAtivo());
        assertEquals(AtivoType.ACAO, asset.getTipoAtivo());
    }

    @Test
    public void testarCriarAtivoFundoImobiliario() {
        // Arrange
        Ativo asset = null;

        // Act
        asset = new Ativo(AtivoType.FUNDO_IMOBILIARIO, "ABCD11");

        // Asset
        assertEquals(true, asset instanceof Ativo);
        assertEquals("ABCD11", asset.getCodigoAtivo());
        assertEquals(AtivoType.FUNDO_IMOBILIARIO, asset.getTipoAtivo());
    }

    @Test
    public void testarCriarAtivoComParametrosNulos() {
        // Arrange / Act
        NullPointerException thrown1 = assertThrows(NullPointerException.class, () -> new Ativo(null, null),
                "Error message");
        NullPointerException thrown2 = assertThrows(NullPointerException.class, () -> new Ativo(null, "ABCD3"),
                "Error message");
        NullPointerException thrown3 = assertThrows(NullPointerException.class, () -> new Ativo(AtivoType.REIT, null),
                "Error message");
        NullPointerException thrown4 = assertThrows(NullPointerException.class, () -> new Ativo(AtivoType.STOCK, null),
                "Error message");
        NullPointerException thrown5 = assertThrows(NullPointerException.class, () -> new Ativo(AtivoType.ACAO, null),
                "Error message");
        NullPointerException thrown6 = assertThrows(NullPointerException.class,
                () -> new Ativo(AtivoType.FUNDO_IMOBILIARIO, null), "Error message");

        // Assert
        assertEquals(NullPointerException.class, thrown1.getClass());
        assertEquals(NullPointerException.class, thrown2.getClass());
        assertEquals(NullPointerException.class, thrown3.getClass());
        assertEquals(NullPointerException.class, thrown4.getClass());
        assertEquals(NullPointerException.class, thrown5.getClass());
        assertEquals(NullPointerException.class, thrown6.getClass());
    }

    @Test
    public void testarSeAtivoEstaComStatusValido() {
        // Arrange
        Ativo asset = null;

        // Act
        asset = new Ativo(AtivoType.ACAO, "ABCD3");

        // Assert
        assertTrue(asset.isValido());
    }

    @Test
    public void testarSeAtivoEstaComStatusInvalido() {
        // Arrange
        Ativo asset = null;

        // Act
        asset = new Ativo();

        // Assert
        assertFalse(asset.isValido());
    }

}
