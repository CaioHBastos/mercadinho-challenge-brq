package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;

import java.util.List;

public interface DepartamentoUseCase {

    Departamento criarDepartamento(Departamento departamento);

    List<Departamento> buscarTodosDepartamentos(String nomeDepartamento);

    void buscarDepartamento(Integer id);

    void removerDepartamento(Integer id);
}
