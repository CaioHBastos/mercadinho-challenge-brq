package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import br.com.brq.challenges.mercadinho.usecase.exception.RegraDepartamentoException;
import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeEmUsoException;
import br.com.brq.challenges.mercadinho.usecase.exception.NenhumConteudoEncontradoException;
import br.com.brq.challenges.mercadinho.usecase.exception.RecursoNaoEncontradoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.DepartamentoGateway;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoUseCaseImpl implements DepartamentoUseCase {

    private final DepartamentoGateway departamentoGateway;

    public DepartamentoUseCaseImpl(DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = departamentoGateway;
    }

    @Override
    public Departamento criarDepartamento(Departamento departamento) {
        validarDuplicidadeDepartamento(departamento);

        return departamentoGateway.criarDepartamento(departamento);
    }

    @Override
    public List<Departamento> buscarTodosDepartamentos(String nomeDepartamento) {
        List<Departamento> departamentos = departamentoGateway.buscarTodosDepartamentos(nomeDepartamento);

        if (departamentos.isEmpty()) {
            throw new NenhumConteudoEncontradoException("Não existem cadastro de departamentos");
        }

        return departamentos;
    }

    @Override
    public void buscarDepartamento(Integer id) {
        departamentoGateway.consultarDepartamentoPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        String.format("O departamento informado '%s' não existe", id)
                ));
    }

    @Override
    public void removerDepartamento(Integer id) {
        buscarDepartamento(id);

        try {
            departamentoGateway.removerDepartamento(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException("A entidade de departamento está em uso e não pode ser removida.");
        }
    }

    private void validarDuplicidadeDepartamento(Departamento departamento) {
        departamentoGateway.consultarDepartamentoPorNome(departamento.getNome())
                .ifPresent(nomeDepartamento -> {
                    throw new RegraDepartamentoException(
                            String.format("Já tem um cadastro para o nome do departamento '%s' informado",
                                    departamento.getNome()
                            )
                    );
                });
    }
}
