package br.com.brq.challenges.mercadinho.dataprovider.mappers.response;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import org.springframework.data.domain.Page;

public class ProdutoMapperResponse {

    private ProdutoMapperResponse() {}

    public static Page<ProdutoDomainResponse> toCollectionDomain(Page<ProdutoEntity> produtosEntity) {
        return produtosEntity.map(ProdutoMapperResponse::toDomain);
    }

    public static ProdutoDomainResponse toDomain(ProdutoEntity produtoSalvo) {
        return ProdutoDomainResponse.builder()
                .id(produtoSalvo.getId())
                .nome(produtoSalvo.getNome())
                .descricao(produtoSalvo.getDescricao())
                .marca(produtoSalvo.getMarca())
                .quantidade(produtoSalvo.getQuantidade())
                .preco(produtoSalvo.getPreco())
                .ativo(produtoSalvo.getAtivo())
                .ofertado(produtoSalvo.getOfertado())
                .porcentagem(produtoSalvo.getPorcentagemOfertado())
                .categoria(CategoriaMapperResponse.toDomain(produtoSalvo.getCategoria()))
                .build();
    }
}
