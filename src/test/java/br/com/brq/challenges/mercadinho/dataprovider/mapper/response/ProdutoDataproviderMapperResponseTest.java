package br.com.brq.challenges.mercadinho.dataprovider.mapper.response;

import br.com.brq.challenges.mercadinho.annotations.Unitario;
import br.com.brq.challenges.mercadinho.mock.ProdutoEntityMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Unitario
public class ProdutoDataproviderMapperResponseTest {

    @Nested
    class ProdutoDataproviderMapperResponseConvertTest {

        @DisplayName("Converter os dados de uma entidade de produto para os dados de domínio do produto")
        @Test
        void testeConvertProdutoSucesso() {
            var produtoEntity = ProdutoEntityMock.mockProdutoEntity();

            var produto = ProdutoDataproviderMapperResponse.convert(produtoEntity);

            assertNotNull(produto);
            assertAll(
                    () -> assertEquals(1L, produto.getId()),
                    () -> assertEquals("Refrigerante Coca Cola 2L", produto.getNome()),
                    () -> assertEquals("Refrigerante Coca Cola 2L retornável", produto.getDescricao()),
                    () -> assertEquals("Coca Cola", produto.getMarca()),
                    () -> assertEquals(5.99, produto.getPreco()),
                    () -> assertTrue(produto.getAtivo()),
                    () -> assertFalse(produto.getOfertado()),
                    () -> assertEquals(0, produto.getPorcentagemOferta())
            );
        }
    }
}
