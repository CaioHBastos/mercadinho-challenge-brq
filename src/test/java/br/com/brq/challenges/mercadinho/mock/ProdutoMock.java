/*package br.com.brq.challenges.mercadinho.mock;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;

public class ProdutoMock {

    public static Produto mockProdutoRequest() {
        return Produto.builder()
                .nome("Refrigerante Coca-Cola 2L")
                .descricao("Refrigerante Coca-Cola 2L em garrafa retornável")
                .marca("Coca-Cola")
                .preco(5.99)
                .build();
    }

    public static Produto mockProdutoDomainRequest() {
        return Produto.builder()
                .nome("Refrigerante Coca-Cola 2L")
                .descricao("Refrigerante Coca-Cola 2L em garrafa retornável")
                .marca("Coca-Cola")
                .preco(5.99)
                .ativo(true)
                .ofertado(false)
                .porcentagemOferta(0)
                .build();
    }

    public static Produto mockProdutoRequestInvalido(Double preco) {
        return Produto.builder()
                .nome("Refrigerante Coca-Cola 2L")
                .descricao("Refrigerante Coca-Cola 2L em garrafa retornável")
                .marca("Coca-Cola")
                .preco(preco)
                .build();
    }

    public static Produto mockProdutoResponse() {
        return Produto.builder()
                .id(1L)
                .nome("Refrigerante Coca-Cola 2L")
                .descricao("Refrigerante Coca-Cola 2L em garrafa retornável")
                .marca("Coca-Cola")
                .preco(5.99)
                .ativo(true)
                .ofertado(false)
                .porcentagemOferta(0)
                .build();
    }
}
*/