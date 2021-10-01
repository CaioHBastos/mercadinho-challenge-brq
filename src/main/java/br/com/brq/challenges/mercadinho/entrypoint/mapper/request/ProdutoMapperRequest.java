package br.com.brq.challenges.mercadinho.entrypoint.mapper.request;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoModelResquest;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;

public class ProdutoMapperRequest {

    private ProdutoMapperRequest() {}

    public static ProdutoDomainRequest toDomainCadastro(ProdutoModelResquest produtoModelResquest) {
        return ProdutoDomainRequest.builder()
                .nome(produtoModelResquest.getNome())
                .descricao(produtoModelResquest.getDescricao())
                .marca(produtoModelResquest.getMarca())
                .quantidade(produtoModelResquest.getQuantidade())
                .preco(produtoModelResquest.getPreco())
                .ativo(Boolean.TRUE)
                .ofertado(Boolean.FALSE)
                .porcentagemOferta(0)
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
                .porcentagemOferta(produtoModelResquest.getPorcentagemOferta())
                .build();
    }
}
