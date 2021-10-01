package br.com.brq.challenges.mercadinho.dataprovider.mapper.request;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;

public class ProdutoMapperRequest {

    private ProdutoMapperRequest() {}

    public static ProdutoEntity toEntity(ProdutoDomainRequest produtoDomainRequest) {
        return ProdutoEntity.builder()
                .nome(produtoDomainRequest.getNome())
                .descricao(produtoDomainRequest.getDescricao())
                .marca(produtoDomainRequest.getMarca())
                .quantidade(produtoDomainRequest.getQuantidade())
                .preco(produtoDomainRequest.getPreco())
                .ativo(produtoDomainRequest.getAtivo())
                .ofertado(produtoDomainRequest.getOfertado())
                .porcentagemOfertado(produtoDomainRequest.getPorcentagemOferta())
                .categoria(CategoriaMapperRequest.toEntityId(produtoDomainRequest.getCategoria()))
                .build();
    }

    public static ProdutoEntity toEntityAtualizado(ProdutoDomainResponse produtoAtual) {
        return ProdutoEntity.builder()
                .id(produtoAtual.getId())
                .nome(produtoAtual.getNome())
                .descricao(produtoAtual.getDescricao())
                .marca(produtoAtual.getMarca())
                .quantidade(produtoAtual.getQuantidade())
                .preco(produtoAtual.getPreco())
                .ativo(produtoAtual.getAtivo())
                .ofertado(produtoAtual.getOfertado())
                .porcentagemOfertado(produtoAtual.getPorcentagemOferta())
                .categoria(CategoriaMapperRequest.toEntityIdAtualizado(produtoAtual.getCategoria()))
                .build();
    }
}
