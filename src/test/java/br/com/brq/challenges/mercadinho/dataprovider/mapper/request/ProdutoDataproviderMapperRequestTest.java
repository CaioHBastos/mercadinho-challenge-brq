package br.com.brq.challenges.mercadinho.dataprovider.mapper.request;

import br.com.brq.challenges.mercadinho.annotations.Unitario;
import br.com.brq.challenges.mercadinho.mock.ProdutoMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Unitario
public class ProdutoDataproviderMapperRequestTest {

    @Nested
    class ProdutoDataproviderMapperRequestConvertProdutoTest {

        @DisplayName("Converter os dados de um domínio de produto para a entidade do produto com sucesso")
        @Test
        void testConvertSucesso() {
            final var produto = ProdutoMock.mockProdutoDomainRequest();

            final var produtoEntity = ProdutoDataproviderMapperRequest.convert(produto);

            assertNotNull(produtoEntity);
            assertAll(
                    () -> assertEquals("Refrigerante Coca-Cola 2L", produtoEntity.getNome()),
                    () -> assertEquals("Refrigerante Coca-Cola 2L em garrafa retornável", produtoEntity.getDescricao()),
                    () -> assertEquals("Coca-Cola", produtoEntity.getMarca()),
                    () -> assertEquals(5.99, produtoEntity.getPreco()),
                    () -> assertTrue(produtoEntity.getAtivo()),
                    () -> assertEquals(0, produtoEntity.getPorcentagemOferta())
            );
        }
    }
}
