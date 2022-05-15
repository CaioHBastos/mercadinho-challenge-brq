package br.com.brq.challenges.mercadinho.dataprovider.mapper.response;

import br.com.brq.challenges.mercadinho.dataprovider.entities.DepartamentoEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class DepartamentoDataproviderMapperResponse {

    public static List<Departamento> convert(List<DepartamentoEntity> departamentos) {
        return departamentos.stream()
                .map(DepartamentoDataproviderMapperResponse::convert)
                .collect(Collectors.toList());
    }

    public static Departamento convert(DepartamentoEntity departamento) {

        if (Objects.isNull(departamento)) {
            return null;
        }

        return Departamento.builder()
                .id(departamento.getId())
                .nome(departamento.getNome())
                .build();
    }
}
