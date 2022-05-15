package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.annotations.Aceitacao;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroProdutoException;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroRegraProdutoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import br.com.brq.challenges.mercadinho.mock.ProdutoMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Aceitacao
public class ProdutoUseCaseImplTest {

    @InjectMocks
    private ProdutoUseCaseImpl produtoUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ProdutoUseCaseImplCriarProdutoTest {

        @DisplayName("Criar um produto com sucesso informando os dados válidos para cadastro")
        @Test
        void testCriarProdutoSucesso() throws CadastroProdutoException {
            final var produtoRequest = ProdutoMock.mockProdutoRequest();
            final var produtoResponse = ProdutoMock.mockProdutoResponse();

            BDDMockito.given(produtoGateway.buscarProdutoPorNome(produtoRequest.getNome())).willReturn(Optional.empty());
            BDDMockito.given(produtoGateway.criarProduto(produtoRequest)).willReturn(produtoResponse);

            final var produtoCriado = produtoUseCase.criarProduto(produtoRequest);

            assertNotNull(produtoCriado);
            assertAll(
                    () -> assertEquals(1L, produtoCriado.getId()),
                    () -> assertEquals("Refrigerante Coca-Cola 2L", produtoCriado.getNome()),
                    () -> assertEquals("Refrigerante Coca-Cola 2L em garrafa retornável", produtoCriado.getDescricao()),
                    () -> assertEquals("Coca-Cola", produtoCriado.getMarca()),
                    () -> assertEquals(5.99, produtoCriado.getPreco()),
                    () -> assertTrue(produtoCriado.getAtivo()),
                    () -> assertFalse(produtoCriado.getOfertado()),
                    () -> assertEquals(0, produtoCriado.getPorcentagemOferta())
            );
        }

        @DisplayName("Criar um produto com erro informando os dados válidos para cadastro mas com um nome já existente gerando duplicidade")
        @Test
        void testCriarProdutoDuplicado() {
            final var produtoRequest = ProdutoMock.mockProdutoRequest();
            final var produtoResponse = ProdutoMock.mockProdutoResponse();

            BDDMockito.given(produtoGateway.buscarProdutoPorNome(produtoRequest.getNome())).willReturn(Optional.of(produtoResponse));

            CadastroRegraProdutoException exception = assertThrows(CadastroRegraProdutoException.class,
                    () -> produtoUseCase.criarProduto(produtoRequest));

            assertEquals(String.format("Erro ao realizar o cadastro do produto. Já existe um produto cadastrado " +
                    "com o nome '%s'.", produtoRequest.getNome()), exception.getMessage());
        }

        @DisplayName("Criar um produto com erro informando os dados válidos para cadastro mas com um valor zerado no cadastro")
        @ParameterizedTest
        @MethodSource("getMockProdutoRequest")
        void testCriarProdutoValorZerado(Produto produtoRequest) {

            BDDMockito.given(produtoGateway.buscarProdutoPorNome(produtoRequest.getNome())).willReturn(Optional.empty());

            CadastroRegraProdutoException exception = assertThrows(CadastroRegraProdutoException.class,
                    () -> produtoUseCase.criarProduto(produtoRequest));

            assertEquals(String.format("Erro ao realizar o cadastro do produto. O produto não pode ser cadastrado com o valor menor " +
                    "ou igual zero e foi informado '%s'.", produtoRequest.getPreco()), exception.getMessage());
        }

        private Stream<Produto> getMockProdutoRequest() {
            return Stream.of(
                    ProdutoMock.mockProdutoRequestInvalido(0.0),
                    ProdutoMock.mockProdutoRequestInvalido(Double.valueOf("-5.99"))
            );
        }
    }
}
