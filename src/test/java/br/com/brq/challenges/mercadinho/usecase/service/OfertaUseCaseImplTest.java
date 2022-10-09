package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.annotations.Unitario;
import br.com.brq.challenges.mercadinho.usecase.domain.Oferta;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.gateway.OfertaGateway;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@Unitario
class OfertaUseCaseImplTest {

    @InjectMocks
    OfertaUseCaseImpl ofertaUseCase;

    @Mock
    OfertaGateway ofertaGateway;

    @Mock
    ProdutoUseCase produtoUseCase;

    @Nested
    class OfertaUseCaseImplAdicionarOfertTest {

        @Test
        void testAdicionarOferta_InformoUmProduto_Sucesso() {
            var produto = mockProdutoUm();
            var produtoOfertado = mockProdutoUmOfertado();
            var produtosOfertaRequest = mockProdutosUmOfertaRequest();

            given(produtoUseCase.detalharProduto("e3d9a467-5d9d-4de3-b7f8-e8222d76d0f5")).willReturn(produto);
            doNothing().when(ofertaGateway).ofertarProdutos(List.of(produtoOfertado));

            var isSuccess = ofertaUseCase.adicionarOfertas(produtosOfertaRequest);
            assertTrue(isSuccess);
        }

        @Test
        void testAdicionarOferta_InformoDoisProdutos_Sucesso() {
            var produtoUm = mockProdutoUm();
            var produtoDois = mockProdutoDois();
            var produtoUmOfertado = mockProdutoUmOfertado();
            var produtoDoisOfertado = mockProdutoDoisOfertado();
            var produtosOfertaRequest = mockProdutosDoisOfertaRequest();

            given(produtoUseCase.detalharProduto("e3d9a467-5d9d-4de3-b7f8-e8222d76d0f5")).willReturn(produtoUm);
            given(produtoUseCase.detalharProduto("bddcd8c9-82ac-4b85-a1aa-74294048ad6c")).willReturn(produtoDois);
            doNothing().when(ofertaGateway).ofertarProdutos(List.of(produtoUmOfertado, produtoDoisOfertado));

            var isSuccess = ofertaUseCase.adicionarOfertas(produtosOfertaRequest);
            assertTrue(isSuccess);
        }

        //ProdutoQueNaoExiste
        //ListaComOUltimoProdutoNaoExiste
        //ProdutoPorcentagemZerado
        //ProdutoPorcentagemNegativo
        //ListComProdutoValorIncorreto
        //ProdutoInativo
        //ListaComOUltimoProdutoInativo
    }

    private Produto mockProdutoUm() {
        var produto = new Produto();
        produto.setId("e3d9a467-5d9d-4de3-b7f8-e8222d76d0f5");
        produto.setNome("Coca-Cola 2L");
        produto.setMarca("Coca-Cola");
        produto.setDescricao("Coca-Cola 2L Pet");
        produto.setPreco(10.0);
        produto.setAtivo(true);
        produto.setOfertado(false);
        produto.setPorcentagemOferta(0);
        produto.setDataCadastro("2022-07-31T12:45:30.879881600-03:00");

        return produto;
    }

    private Produto mockProdutoDois() {
        var produto = new Produto();
        produto.setId("bddcd8c9-82ac-4b85-a1aa-74294048ad6c");
        produto.setNome("Arroz Camil 5kg");
        produto.setMarca("Camil");
        produto.setDescricao("Pacote de Arroz Camil 5KG");
        produto.setPreco(22.0);
        produto.setAtivo(true);
        produto.setOfertado(false);
        produto.setPorcentagemOferta(0);
        produto.setDataCadastro("2022-07-31T12:45:30.879881600-03:00");

        return produto;
    }

    private Produto mockProdutoUmOfertado() {
        var produto = new Produto();
        produto.setId("e3d9a467-5d9d-4de3-b7f8-e8222d76d0f5");
        produto.setNome("Coca-Cola 2L");
        produto.setMarca("Coca-Cola");
        produto.setDescricao("Coca-Cola 2L Pet");
        produto.setPreco(10.0);
        produto.setAtivo(true);
        produto.setOfertado(true);
        produto.setPorcentagemOferta(10);
        produto.setDataCadastro("2022-07-31T12:45:30.879881600-03:00");

        return produto;
    }

    private Produto mockProdutoDoisOfertado() {
        var produto = new Produto();
        produto.setId("bddcd8c9-82ac-4b85-a1aa-74294048ad6c");
        produto.setNome("Arroz Camil 5kg");
        produto.setMarca("Camil");
        produto.setDescricao("Pacote de Arroz Camil 5KG");
        produto.setPreco(22.0);
        produto.setAtivo(true);
        produto.setOfertado(true);
        produto.setPorcentagemOferta(5);
        produto.setDataCadastro("2022-07-31T12:45:30.879881600-03:00");

        return produto;
    }

    private List<Oferta> mockProdutosUmOfertaRequest() {
        var produtoOferta = new Oferta();
        produtoOferta.setIdProduto("e3d9a467-5d9d-4de3-b7f8-e8222d76d0f5");
        produtoOferta.setOfertado(true);
        produtoOferta.setPorcentagemOferta(10);

        return List.of(produtoOferta);
    }

    private List<Oferta> mockProdutosDoisOfertaRequest() {
        var produtoUmOferta = new Oferta();
        produtoUmOferta.setIdProduto("e3d9a467-5d9d-4de3-b7f8-e8222d76d0f5");
        produtoUmOferta.setOfertado(true);
        produtoUmOferta.setPorcentagemOferta(10);

        var produtoDoisOferta = new Oferta();
        produtoDoisOferta.setIdProduto("bddcd8c9-82ac-4b85-a1aa-74294048ad6c");
        produtoDoisOferta.setOfertado(true);
        produtoDoisOferta.setPorcentagemOferta(5);

        return List.of(produtoUmOferta, produtoDoisOferta);
    }
}
