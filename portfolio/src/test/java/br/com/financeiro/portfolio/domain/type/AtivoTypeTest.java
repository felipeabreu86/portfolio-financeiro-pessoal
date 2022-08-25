package br.com.financeiro.portfolio.domain.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.financeiro.portfolio.domain.AtivoTestUtil;
import br.com.financeiro.portfolio.domain.entity.Ativo;

public class AtivoTypeTest {

    @Test
    public void testarCriarUmTipoAcao() {
        // Arrange
        AtivoType acao = AtivoType.ACAO;

        // Act
        Ativo ativo = acao.obterInstancia("ABCD4");
        Ativo ativo2 = acao.obterInstancia("ABCD11");

        // Assert
        assertTrue(ativo instanceof Ativo);
        assertEquals("ABCD4", ativo.getCodigoAtivo());
        assertEquals(AtivoType.ACAO, ativo.getTipoAtivo());
        assertTrue(ativo2 instanceof Ativo);
        assertEquals("ABCD11", ativo2.getCodigoAtivo());
        assertEquals(AtivoType.ACAO, ativo2.getTipoAtivo());
    }

    @Test
    public void testarCriarUmTipoFundoImobiliario() {
        // Arrange
        AtivoType fundoImobiliario = AtivoType.FUNDO_IMOBILIARIO;

        // Act
        Ativo ativo = fundoImobiliario.obterInstancia("ABCD11");

        // Assert
        assertTrue(ativo instanceof Ativo);
        assertEquals("ABCD11", ativo.getCodigoAtivo());
        assertEquals(AtivoType.FUNDO_IMOBILIARIO, ativo.getTipoAtivo());
    }

    @Test
    public void testarCriarUmTipoStock() {
        // Arrange
        AtivoType stock = AtivoType.STOCK;

        // Act
        Ativo ativo = stock.obterInstancia("ABCD");

        // Assert
        assertTrue(ativo instanceof Ativo);
        assertEquals("ABCD", ativo.getCodigoAtivo());
        assertEquals(AtivoType.STOCK, ativo.getTipoAtivo());
    }

    @Test
    public void testarCriarUmTipoReit() {
        // Arrange
        AtivoType reit = AtivoType.REIT;

        // Act
        Ativo ativo = reit.obterInstancia("ABC");

        // Assert
        assertTrue(ativo instanceof Ativo);
        assertEquals("ABC", ativo.getCodigoAtivo());
        assertEquals(AtivoType.REIT, ativo.getTipoAtivo());
    }

    @Test
    public void testarCriarUmTipoInvalidoDeStock() {
        // Arrange
        AtivoType asset = AtivoType.STOCK;

        // Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> asset.obterInstancia(null),
                "Error message");
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCD12"), "Error message");
        IllegalArgumentException thrown3 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCD1"), "Error message");
        IllegalArgumentException thrown4 = assertThrows(IllegalArgumentException.class, () -> asset.obterInstancia(""),
                "Error message");
        IllegalArgumentException thrown5 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCDE"), "Error message");
        IllegalArgumentException thrown6 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABC1"), "Error message");

        // Assert
        assertEquals(NullPointerException.class, thrown.getClass());
        assertEquals(IllegalArgumentException.class, thrown2.getClass());
        assertEquals(IllegalArgumentException.class, thrown3.getClass());
        assertEquals(IllegalArgumentException.class, thrown4.getClass());
        assertEquals(IllegalArgumentException.class, thrown5.getClass());
        assertEquals(IllegalArgumentException.class, thrown6.getClass());
    }

    @Test
    public void testarCriarUmTipoInvalidoDeReit() {
        // Arrange
        AtivoType asset = AtivoType.REIT;

        // Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> asset.obterInstancia(null),
                "Error message");
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCD12"), "Error message");
        IllegalArgumentException thrown3 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCD1"), "Error message");
        IllegalArgumentException thrown4 = assertThrows(IllegalArgumentException.class, () -> asset.obterInstancia(""),
                "Error message");
        IllegalArgumentException thrown5 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCDE"), "Error message");
        IllegalArgumentException thrown6 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABC1"), "Error message");

        // Assert
        assertEquals(NullPointerException.class, thrown.getClass());
        assertEquals(IllegalArgumentException.class, thrown2.getClass());
        assertEquals(IllegalArgumentException.class, thrown3.getClass());
        assertEquals(IllegalArgumentException.class, thrown4.getClass());
        assertEquals(IllegalArgumentException.class, thrown5.getClass());
        assertEquals(IllegalArgumentException.class, thrown6.getClass());
    }

    @Test
    public void testarCriarUmTipoInvalidoDeAcao() {
        // Arrange
        AtivoType asset = AtivoType.ACAO;

        // Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> asset.obterInstancia(null),
                "Error message");
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCD12"), "Error message");
        IllegalArgumentException thrown3 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCD1"), "Error message");
        IllegalArgumentException thrown4 = assertThrows(IllegalArgumentException.class, () -> asset.obterInstancia(""),
                "Error message");
        IllegalArgumentException thrown5 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCDE"), "Error message");
        IllegalArgumentException thrown6 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABC1"), "Error message");

        // Assert
        assertEquals(NullPointerException.class, thrown.getClass());
        assertEquals(IllegalArgumentException.class, thrown2.getClass());
        assertEquals(IllegalArgumentException.class, thrown3.getClass());
        assertEquals(IllegalArgumentException.class, thrown4.getClass());
        assertEquals(IllegalArgumentException.class, thrown5.getClass());
        assertEquals(IllegalArgumentException.class, thrown6.getClass());
    }

    @Test
    public void testarCriarUmTipoInvalidoDeFundoImobiliario() {
        // Arrange
        AtivoType asset = AtivoType.FUNDO_IMOBILIARIO;

        // Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> asset.obterInstancia(null),
                "Error message");
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCD12"), "Error message");
        IllegalArgumentException thrown3 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCD1"), "Error message");
        IllegalArgumentException thrown4 = assertThrows(IllegalArgumentException.class, () -> asset.obterInstancia(""),
                "Error message");
        IllegalArgumentException thrown5 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABCDE"), "Error message");
        IllegalArgumentException thrown6 = assertThrows(IllegalArgumentException.class,
                () -> asset.obterInstancia("ABC1"), "Error message");

        // Assert
        assertEquals(NullPointerException.class, thrown.getClass());
        assertEquals(IllegalArgumentException.class, thrown2.getClass());
        assertEquals(IllegalArgumentException.class, thrown3.getClass());
        assertEquals(IllegalArgumentException.class, thrown4.getClass());
        assertEquals(IllegalArgumentException.class, thrown5.getClass());
        assertEquals(IllegalArgumentException.class, thrown6.getClass());
    }

    @Test
    public void testarValidacaoDeAtivosComCodigoDoAtivoNulo() {
        // Arrange
        AtivoType asset = AtivoType.STOCK;
        AtivoType asset2 = AtivoType.REIT;
        AtivoType asset3 = AtivoType.ACAO;
        AtivoType asset4 = AtivoType.FUNDO_IMOBILIARIO;

        // Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> asset.validar(null),
                "Error message");
        NullPointerException thrown2 = assertThrows(NullPointerException.class, () -> asset2.validar(null),
                "Error message");
        NullPointerException thrown3 = assertThrows(NullPointerException.class, () -> asset3.validar(null),
                "Error message");
        NullPointerException thrown4 = assertThrows(NullPointerException.class, () -> asset4.validar(null),
                "Error message");

        // Assert
        assertEquals(NullPointerException.class, thrown.getClass());
        assertEquals(NullPointerException.class, thrown2.getClass());
        assertEquals(NullPointerException.class, thrown3.getClass());
        assertEquals(NullPointerException.class, thrown4.getClass());
    }

    @Test
    public void testarValidacaoDeAtivosComCodigoDoAtivoInvalido() {
        // Arrange
        AtivoType stock = AtivoType.STOCK;
        AtivoType reit = AtivoType.REIT;
        AtivoType acao = AtivoType.ACAO;
        AtivoType fundoImobiliario = AtivoType.FUNDO_IMOBILIARIO;

        // Act
        for (String ticker : AtivoTestUtil.codigosInvalidosParaAcoes) {
            // Assert
            assertThrows(IllegalArgumentException.class, () -> acao.validar(ticker));
        }
        for (String ticker : AtivoTestUtil.codigosInvalidosParaFundosImobiliarios) {
            // Assert
            assertThrows(IllegalArgumentException.class, () -> fundoImobiliario.validar(ticker));
        }
        for (String ticker : AtivoTestUtil.codigosInvalidosParaAtivosNoExterior) {
            // Assert
            assertThrows(IllegalArgumentException.class, () -> stock.validar(ticker));
            assertThrows(IllegalArgumentException.class, () -> reit.validar(ticker));
        }
    }

    @Test
    public void testarValidacaoDeAtivosComCodigoDoAtivoValido() {
        // Arrange
        AtivoType asset = AtivoType.STOCK;
        AtivoType asset2 = AtivoType.REIT;
        AtivoType asset3 = AtivoType.ACAO;
        AtivoType asset4 = AtivoType.FUNDO_IMOBILIARIO;

        // Act
        String result = asset.validar("ABCD");
        String result2 = asset2.validar("ABC");
        String result3 = asset3.validar("ABCD3");
        String result4 = asset4.validar("ABCD11");

        // Assert
        assertEquals("ABCD", result);
        assertEquals("ABC", result2);
        assertEquals("ABCD3", result3);
        assertEquals("ABCD11", result4);
    }

    @Test
    public void testarTipoDeAtivoB3() {
        // Arrange
        AtivoType acao = AtivoType.ACAO;
        AtivoType fii = AtivoType.FUNDO_IMOBILIARIO;

        // Act
        boolean result1 = acao.isB3();
        boolean result2 = fii.isB3();
        boolean result3 = acao.isInvestimentoExterior();
        boolean result4 = fii.isInvestimentoExterior();

        // Assert
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    public void testarTipoDeInvestimentoNoExterior() {
        // Arrange
        AtivoType stock = AtivoType.STOCK;
        AtivoType reit = AtivoType.REIT;

        // Act
        boolean result1 = stock.isInvestimentoExterior();
        boolean result2 = reit.isInvestimentoExterior();
        boolean result3 = stock.isB3();
        boolean result4 = reit.isB3();

        // Assert
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

}
