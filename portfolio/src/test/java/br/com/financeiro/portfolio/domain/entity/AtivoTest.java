package br.com.financeiro.portfolio.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.com.financeiro.portfolio.domain.type.AtivoType;

class AtivoTest {

    @Test
    void testarCriarAtivoStock() {
        // Arrange
        Ativo asset = null;

        // Act
        asset = new Ativo(AtivoType.STOCK, "ABCD3");

        // Assert
        assertEquals(true, asset instanceof Ativo);
        assertEquals("ABCD3", asset.getCodigoAtivo());
        assertEquals(AtivoType.STOCK, asset.getTipoAtivo());
    }

    @Test
    void testarCriarAtivoReit() {
        // Arrange
        Ativo asset = null;

        // Act
        asset = new Ativo(AtivoType.REIT, "ABCD11");

        // Asset
        assertEquals(true, asset instanceof Ativo);
        assertEquals("ABCD11", asset.getCodigoAtivo());
        assertEquals(AtivoType.REIT, asset.getTipoAtivo());
    }

    @Test
    void testarCriarAtivoAcao() {
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
    void testarCriarAtivoFundoImobiliario() {
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
    void testarCriarAtivoComParametrosNulos() {
        // Arrange
        Ativo asset1 = null;
        Ativo asset2 = null;
        Ativo asset3 = null;
        Ativo asset4 = null;
        Ativo asset5 = null;
        Ativo asset6 = null;

        // Act
        asset1 = new Ativo(null, null);
        asset2 = new Ativo(null, "ABCD3");
        asset3 = new Ativo(AtivoType.REIT, null);
        asset4 = new Ativo(AtivoType.STOCK, null);
        asset5 = new Ativo(AtivoType.ACAO, null);
        asset6 = new Ativo(AtivoType.FUNDO_IMOBILIARIO, null);

        // Asset
        assertEquals(true, asset1 instanceof Ativo);
        assertNull(asset1.getCodigoAtivo());
        assertNull(asset1.getTipoAtivo());

        assertEquals(true, asset2 instanceof Ativo);
        assertEquals("ABCD3", asset2.getCodigoAtivo());
        assertNull(asset2.getTipoAtivo());

        assertEquals(true, asset3 instanceof Ativo);
        assertNull(asset3.getCodigoAtivo());
        assertEquals(AtivoType.REIT, asset3.getTipoAtivo());

        assertEquals(true, asset4 instanceof Ativo);
        assertNull(asset4.getCodigoAtivo());
        assertEquals(AtivoType.STOCK, asset4.getTipoAtivo());

        assertEquals(true, asset5 instanceof Ativo);
        assertNull(asset5.getCodigoAtivo());
        assertEquals(AtivoType.ACAO, asset5.getTipoAtivo());

        assertEquals(true, asset6 instanceof Ativo);
        assertNull(asset6.getCodigoAtivo());
        assertEquals(AtivoType.FUNDO_IMOBILIARIO, asset6.getTipoAtivo());
    }

}
