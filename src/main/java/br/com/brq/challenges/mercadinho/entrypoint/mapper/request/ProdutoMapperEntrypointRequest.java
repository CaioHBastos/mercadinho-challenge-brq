package br.com.brq.challenges.mercadinho.entrypoint.mapper.request;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoModelResquest;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;

public class ProdutoMapperEntrypointRequest {

    private ProdutoMapperEntrypointRequest() {}

    public static ProdutoDomainRequest toDomainCadastro(ProdutoModelResquest produtoModelResquest) {
        return ProdutoDomainRequest.builder()
                .nome(produtoModelResquest.getNome())
                .descricao(produtoModelResquest.getDescricao())
                .marca(produtoModelResquest.getMarca())
                .quantidade(produtoModelResquest.getQuantidade())
                .preco(produtoModelResquest.getPreco())
                .ativo(Boolean.TRUE)
                .ofertado(Boolean.FALSE)
                .porcentagem(0)
                .categoria(CategoriaMapperEntrypointRequest.toDomainId(produtoModelResquest.getCategoria()))
                .build();
    }

    public static ProdutoDomainRequest toDomainAtualizado(ProdutoModelResquest produtoModelResquest) {
        return ProdutoDomainRequest.builder()
                .nome(produtoModelResquest.getNome())
                .descricao(produtoModelResquest.getDescricao())
                .marca(produtoModelResquest.getMarca())
                .quantidade(produtoModelResquest.getQuantidade())
                .preco(produtoModelResquest.getPreco())
                .ativo(produtoModelResquest.getAtivo())
                .ofertado(produtoModelResquest.getOfertado())
                .porcentagem(produtoModelResquest.getPorcentagem())
                .categoria(CategoriaMapperEntrypointRequest.toDomainId(produtoModelResquest.getCategoria()))
                .build();
    }
}
