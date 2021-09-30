package br.com.brq.challenges.mercadinho.entrypoint.mapper.request;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.CategoriaModelRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;

public class CategoriaMapperRequest {

    private CategoriaMapperRequest() {}

    public static CategoriaDomainRequest toDomain(CategoriaModelRequest categoriaModelRequest) {
        return CategoriaDomainRequest.builder()
                .nome(categoriaModelRequest.getNome())
                .build();
    }
}
