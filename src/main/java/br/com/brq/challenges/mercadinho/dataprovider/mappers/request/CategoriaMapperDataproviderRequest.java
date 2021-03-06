package br.com.brq.challenges.mercadinho.dataprovider.mappers.request;

import br.com.brq.challenges.mercadinho.dataprovider.entities.CategoriaEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;

import java.util.Objects;


public class CategoriaMapperDataproviderRequest {

    private CategoriaMapperDataproviderRequest() {}

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

    public static CategoriaEntity toEntityId(CategoriaDomainRequest categoria) {
        if (Objects.isNull(categoria)) {
            return CategoriaEntity.builder().build();
        }

        return CategoriaEntity.builder()
                .id(categoria.getId())
                .build();
    }

    public static CategoriaEntity toEntityIdAtualizado(CategoriaDomainResponse categoria) {
        if (Objects.isNull(categoria)) {
            return CategoriaEntity.builder().build();
        }

        return CategoriaEntity.builder()
                .id(categoria.getId())
                .build();
    }
}
