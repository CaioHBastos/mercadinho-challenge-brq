package br.com.brq.challenges.mercadinho.entrypoint.mapper.response;

import br.com.brq.challenges.mercadinho.entrypoint.model.response.CategoriaModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CategoriaMapperResponse {

    private CategoriaMapperResponse() {}

    public static List<CategoriaModelResponse> toCollectionModel(List<CategoriaDomainResponse> categoriasDomain) {
        return categoriasDomain.stream()
                .map(CategoriaMapperResponse::toModel)
                .collect(Collectors.toList());
    }

    public static CategoriaModelResponse toModel(CategoriaDomainResponse categoriaDomainResponse) {
        if (Objects.isNull(categoriaDomainResponse)) {
            return CategoriaModelResponse.builder().build();
        }

        return CategoriaModelResponse.builder()
                .id(categoriaDomainResponse.getId())
                .nome(categoriaDomainResponse.getNome())
                .build();
    }
}
