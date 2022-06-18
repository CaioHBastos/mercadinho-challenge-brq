package br.com.brq.challenges.mercadinho.entrypoint.mapper;

import br.com.brq.challenges.mercadinho.dataprovider.entities.DepartamentoEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DepartamentoEntrypointMapper {

    Departamento map(DepartamentoEntity departamentoEntity);
    List<Departamento> map (List<DepartamentoEntity> departamentosEntity);
}
