package br.com.brq.challenges.mercadinho.dataprovider.mappers.response;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import org.springframework.data.domain.Page;

public class OfertaMapperResponse {

    private OfertaMapperResponse() {}

    public static Page<ProdutoDomainResponse> toCollectionDomain(Page<ProdutoEntity> produtosEmOferta) {
        return produtosEmOferta.map(OfertaMapperResponse::toDomain);
    }

    private static ProdutoDomainResponse toDomain(ProdutoEntity produtoEntity) {
        return ProdutoDomainResponse.builder()
                .id(produtoEntity.getId())
                .nome(produtoEntity.getNome())
                .marca(produtoEntity.getMarca())
                .quantidade(produtoEntity.getQuantidade())
                .preco(produtoEntity.getPreco())
                .porcentagem(produtoEntity.getPorcentagemOfertado())
                .build();
    }
}
