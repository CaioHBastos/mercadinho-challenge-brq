package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.ProdutoEntrypointMapper;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoAtivacaoModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoModelFilterRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoUpdateModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.ProdutoModelResponse;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.ProdutoResumidoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.service.ProdutoUseCase;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final ProdutoEntrypointMapper produtoEntrypointMapper = Mappers.getMapper(ProdutoEntrypointMapper.class);

    private final ProdutoUseCase produtoUseCase;

    public ProdutoController(ProdutoUseCase produtoUseCase) {
        this.produtoUseCase = produtoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProdutoModelResponse> cadastrarProduto(@Valid @RequestBody ProdutoModelRequest produtoModelRequest) {
        Produto produtoRequest = produtoEntrypointMapper.map(produtoModelRequest);
        Produto produtoCriado = produtoUseCase.criarProduto(produtoRequest);
        ProdutoModelResponse produtoModelResponse = produtoEntrypointMapper.map(produtoCriado);

        return new ResponseEntity<>(produtoModelResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResumidoModelResponse>> buscarTodosProdutos(ProdutoModelFilterRequest produtoModelFilterRequest) {
        Produto produtoFilter = produtoEntrypointMapper.map(produtoModelFilterRequest);
        List<Produto> produtos = produtoUseCase.buscarProdutos(produtoFilter);
        List<ProdutoResumidoModelResponse> produtosModelResponse = produtoEntrypointMapper.map(produtos);

        return new ResponseEntity<>(produtosModelResponse, HttpStatus.OK);
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<ProdutoModelResponse> detalharProduto(@PathVariable("id_produto") String idProduto) {
        Produto produto = produtoUseCase.detalharProduto(idProduto);
        ProdutoModelResponse produtoModelResponse = produtoEntrypointMapper.map(produto);

        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id_produto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProduto(@PathVariable("id_produto") String idProduto) {
        produtoUseCase.removerProduto(idProduto);
    }

    @PatchMapping("/{id_produto}")
    public ResponseEntity<ProdutoModelResponse> atualizarProduto(@PathVariable("id_produto") String idProduto,
                                                                 @RequestBody @Valid ProdutoUpdateModelRequest produtoUpdateModelRequest) {

        Produto produtoUpdate = produtoEntrypointMapper.map(produtoUpdateModelRequest);
        Produto produtoAtualizado = produtoUseCase.atualiazarParcialmenteProduto(idProduto, produtoUpdate);
        ProdutoModelResponse produtoModelResponse = produtoEntrypointMapper.map(produtoAtualizado);

        return ResponseEntity.ok(produtoModelResponse);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarProdutos(@RequestBody @Valid List<ProdutoAtivacaoModelRequest> produtosModelRequest) {
        List<Produto> produtos = produtoEntrypointMapper.mapAtivacao(produtosModelRequest);
        produtoUseCase.ativarProdutos(produtos);
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarProdutos(@RequestBody @Valid List<ProdutoAtivacaoModelRequest> produtosModelRequest) {
        List<Produto> produtos = produtoEntrypointMapper.mapAtivacao(produtosModelRequest);
        produtoUseCase.inativarProdutos(produtos);
    }
}
