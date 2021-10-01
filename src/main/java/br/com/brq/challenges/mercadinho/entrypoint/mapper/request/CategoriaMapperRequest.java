package br.com.brq.challenges.mercadinho.entrypoint.mapper.request;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.CategoriaIdModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.CategoriaModelRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;

import java.util.Objects;

public class CategoriaMapperRequest {

    private CategoriaMapperRequest() {}

    public static CategoriaDomainRequest toDomain(CategoriaModelRequest categoriaModelRequest) {
        return CategoriaDomainRequest.builder()
                .nome(categoriaModelRequest.getNome())
                .build();
    }

    public static CategoriaDomainRequest toDomainId(CategoriaIdModelRequest categoria) {
        if (Objects.isNull(categoria)) {
            return CategoriaDomainRequest.builder().build();
        }

        return CategoriaDomainRequest.builder()
                .id(categoria.getId())
                .build();
    }
}
