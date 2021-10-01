package br.com.brq.challenges.mercadinho.dataprovider.mapper.response;

import br.com.brq.challenges.mercadinho.dataprovider.entities.CategoriaEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CategoriaMapperResponse {

    private CategoriaMapperResponse() {}

    public static List<CategoriaDomainResponse> toCollecationDomain(List<CategoriaEntity> categoriasEntity) {
        return categoriasEntity.stream()
                .map(CategoriaMapperResponse::toDomain)
                .collect(Collectors.toList());
    }

    public static CategoriaDomainResponse toDomain(CategoriaEntity categoriaSalva) {
        if (Objects.isNull(categoriaSalva)) {
            return CategoriaDomainResponse.builder().build();
        }

        return CategoriaDomainResponse.builder()
                .id(categoriaSalva.getId())
                .nome(categoriaSalva.getNome())
                .build();
    }
}
