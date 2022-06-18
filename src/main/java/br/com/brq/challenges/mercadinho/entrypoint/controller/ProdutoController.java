package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.ProdutoEntrypointMapper;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.ProdutoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.service.ProdutoUseCase;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final ProdutoEntrypointMapper produtoEntrypointMapper = Mappers.getMapper(ProdutoEntrypointMapper.class);

    private final ProdutoUseCase produtoUseCase;

    public ProdutoController(ProdutoUseCase produtoUseCase) {
        this.produtoUseCase = produtoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProdutoModelResponse> cadastrarProduto(@RequestBody ProdutoModelRequest produtoModelRequest) {
        Produto produtoRequest = produtoEntrypointMapper.map(produtoModelRequest);
        Produto produtoCriado = produtoUseCase.criarProduto(produtoRequest);
        ProdutoModelResponse produtoModelResponse = produtoEntrypointMapper.map(produtoCriado);

        return new ResponseEntity<>(produtoModelResponse, HttpStatus.CREATED);
    }
}
