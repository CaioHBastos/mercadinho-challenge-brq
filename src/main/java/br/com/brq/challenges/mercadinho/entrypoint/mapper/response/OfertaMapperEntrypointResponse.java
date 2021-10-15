package br.com.brq.challenges.mercadinho.entrypoint.mapper.response;

import br.com.brq.challenges.mercadinho.entrypoint.model.response.ProdutoResumidoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import org.springframework.data.domain.Page;

public class OfertaMapperEntrypointResponse {

    private OfertaMapperEntrypointResponse() {}

    public static Page<ProdutoResumidoModelResponse> toCollectionModel(Page<ProdutoDomainResponse> produtosDomainOfertado) {
        return produtosDomainOfertado.map(OfertaMapperEntrypointResponse::toDomain);
    }

    private static ProdutoResumidoModelResponse toDomain(ProdutoDomainResponse produtoDomainResponse) {
        return ProdutoResumidoModelResponse.builder()
                .id(produtoDomainResponse.getId())
                .nome(produtoDomainResponse.getNome())
                .marca(produtoDomainResponse.getMarca())
                .quantidade(produtoDomainResponse.getQuantidade())
                .preco(produtoDomainResponse.getPreco())
                .porcentagem(produtoDomainResponse.getPorcentagem())
                .build();
    }
}
