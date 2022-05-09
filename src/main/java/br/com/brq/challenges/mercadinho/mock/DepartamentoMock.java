package br.com.brq.challenges.mercadinho.mock;

import br.com.brq.challenges.mercadinho.dataprovider.entities.DepartamentoEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DepartamentoMock {

    public static List<DepartamentoEntity> mockDepartamentosResponse() {
        return List.of(
                DepartamentoEntity.builder()
                        .id(1L)
                        .nome("Alimentos")
                        .build(),
                DepartamentoEntity.builder()
                        .id(2L)
                        .nome("Limpeza")
                        .build()
        );
    }

    public static DepartamentoEntity mockDepartamentoResponse() {
        return DepartamentoEntity.builder()
                        .id(1L)
                        .nome("Alimentos")
                        .build();
    }
}
