package br.com.brq.challenges.mercadinho.usecase.mock;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProdutoMock {

    public static Produto mockProdutoRequest() {
        return Produto.builder()
                .nome("Refrigerante Coca-Cola 2L")
                .descricao("Refrigerante Coca-Cola 2L em garrafa retornável")
                .marca("Coca-Cola")
                .preco(5.99)
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
                .porcentagemOferta(0)
                .build();
    }
}
