package br.com.brq.challenges.mercadinho.dataprovider.mapper.request;

import br.com.brq.challenges.mercadinho.dataprovider.entities.CategoriaEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;

public class CategoriaMapperRequest {

    private CategoriaMapperRequest() {}

    public static CategoriaEntity toEntity(CategoriaDomainRequest categoriaDomainRequest) {
        return CategoriaEntity.builder()
                .nome(categoriaDomainRequest.getNome())
                .build();
    }

    public static CategoriaEntity toEntityUpdate(CategoriaDomainResponse categoriaAtual) {
        return CategoriaEntity.builder()
                .id(categoriaAtual.getId())
                .nome(categoriaAtual.getNome())
                .build();
    }
}
