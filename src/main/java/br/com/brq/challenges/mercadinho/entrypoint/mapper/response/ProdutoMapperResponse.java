package br.com.brq.challenges.mercadinho.entrypoint.mapper.response;

import br.com.brq.challenges.mercadinho.entrypoint.model.response.ProdutoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapperResponse {

    private ProdutoMapperResponse() {}

    public static List<ProdutoModelResponse> toCollectionModel(List<ProdutoDomainResponse> produtosDomain) {
        return produtosDomain.stream()
                .map(ProdutoMapperResponse::toModel)
                .collect(Collectors.toList());
    }

    public static ProdutoModelResponse toModel(ProdutoDomainResponse produtoCadastradoDomain) {
        return ProdutoModelResponse.builder()
                .id(produtoCadastradoDomain.getId())
                .nome(produtoCadastradoDomain.getNome())
                .descricao(produtoCadastradoDomain.getDescricao())
                .marca(produtoCadastradoDomain.getMarca())
                .quantidade(produtoCadastradoDomain.getQuantidade())
                .preco(produtoCadastradoDomain.getPreco())
                .ativo(produtoCadastradoDomain.getAtivo())
                .ofertado(produtoCadastradoDomain.getOfertado())
                .porcentagemOferta(produtoCadastradoDomain.getPorcentagemOferta())
                .build();
    }
}
