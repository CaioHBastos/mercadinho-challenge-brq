package br.com.brq.challenges.mercadinho.entrypoint.mapper;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.DepartamentoIdModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.DepartamentoModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.DepartamentoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DepartamentoEntrypointMapper {

    Departamento map(DepartamentoModelRequest departamentoModelRequest);
    DepartamentoModelResponse map(Departamento departamento);
    List<DepartamentoModelResponse> map(List<Departamento> departamentos);

    List<Departamento> mapId(List<DepartamentoIdModelRequest> departamentosIdModelRequest);
}
