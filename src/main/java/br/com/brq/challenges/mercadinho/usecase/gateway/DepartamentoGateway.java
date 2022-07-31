package br.com.brq.challenges.mercadinho.usecase.gateway;

import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoGateway {

    Departamento criarDepartamento(Departamento departamento);

    Optional<String> consultarDepartamentoPorNome(String nomeDepartamento);

    List<Departamento> buscarTodosDepartamentos(String nomeDepartamento);

    Optional<String> consultarDepartamentoPorId(Long id);

    void removerDepartamento(Long id);
}
