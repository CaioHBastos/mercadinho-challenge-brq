package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.DepartamentoEntrypointMapper;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.DepartamentoModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.DepartamentoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import br.com.brq.challenges.mercadinho.usecase.service.DepartamentoUseCase;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/departamentos")
public class DepartamentoController {

    private final DepartamentoEntrypointMapper departamentoEntrypointMapper =
            Mappers.getMapper(DepartamentoEntrypointMapper.class);

    private final DepartamentoUseCase departamentoUseCase;

    public DepartamentoController(DepartamentoUseCase departamentoUseCase) {
        this.departamentoUseCase = departamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<DepartamentoModelResponse> cadastrarDepartamento(
            @RequestBody DepartamentoModelRequest departamentoModelRequest) {

        Departamento novoDepartamento = departamentoEntrypointMapper.map(departamentoModelRequest);
        Departamento departamentoCriado = departamentoUseCase.criarDepartamento(novoDepartamento);
        DepartamentoModelResponse departamentoModelResponse = departamentoEntrypointMapper.map(departamentoCriado);

        return new ResponseEntity<>(departamentoModelResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoModelResponse>> buscarTodosDepartamento() {
        List<Departamento> departamentos = departamentoUseCase.buscarTodosDepartamentos();
        List<DepartamentoModelResponse> departamentoModelResponses = departamentoEntrypointMapper.map(departamentos);

        return new ResponseEntity<>(departamentoModelResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{id_departamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerDepartamento(@PathVariable("id_departamento") Long idDepartamento) {
        departamentoUseCase.removerDepartamento(idDepartamento);
    }
}
