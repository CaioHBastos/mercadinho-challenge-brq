package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.DepartamentoEntrypointMapper;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.DepartamentoIdModelRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import br.com.brq.challenges.mercadinho.usecase.service.ProdutoUseCase;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/produtos/{id_produto}/departamentos")
public class ProdutoDepartamentoController {

    private final DepartamentoEntrypointMapper departamentoEntrypointMapper = Mappers.getMapper(DepartamentoEntrypointMapper.class);

    private final ProdutoUseCase produtoUseCase;

    public ProdutoDepartamentoController(ProdutoUseCase produtoUseCase) {
        this.produtoUseCase = produtoUseCase;
    }

    @PutMapping
    public ResponseEntity<Void> adicionarDepartamentosEmProduto(
            @PathVariable("id_produto") String idProduto,
            @RequestBody @Valid List<DepartamentoIdModelRequest> departamentosIdModelRequest) {

        List<Departamento> departamentos = departamentoEntrypointMapper.mapId(departamentosIdModelRequest);
        produtoUseCase.adicionarDepartamentoEmProduto(idProduto, departamentos);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> removerDepartamentosEmProduto(
            @PathVariable("id_produto") String idProduto,
            @RequestBody @Valid List<DepartamentoIdModelRequest> departamentosIdModelRequest) {

        List<Departamento> departamentos = departamentoEntrypointMapper.mapId(departamentosIdModelRequest);
        produtoUseCase.removerDepartamentoEmProduto(idProduto, departamentos);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
