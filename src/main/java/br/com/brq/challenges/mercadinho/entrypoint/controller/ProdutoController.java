package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.request.ProdutoMapperEntrypointRequest;
import br.com.brq.challenges.mercadinho.entrypoint.mapper.response.ProdutoMapperEntrypointResponse;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoModelResquest;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoParametroModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.ProdutoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.ProdutoUseCase;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;

    @PostMapping
    public ResponseEntity<ProdutoModelResponse> salvar(@RequestBody ProdutoModelResquest produtoModelResquest) {
        ProdutoDomainRequest novoProdutoDomain = ProdutoMapperEntrypointRequest.toDomainCadastro(produtoModelResquest);
        ProdutoDomainResponse produtoCadastradoDomain = produtoUseCase.cadastrarProduto(novoProdutoDomain);
        ProdutoModelResponse produtoCadastradoModel = ProdutoMapperEntrypointResponse.toModel(produtoCadastradoDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCadastradoModel);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> buscarTodos(ProdutoParametroModelRequest produtoParametroModelRequest) {
        List<ProdutoDomainResponse> produtosDomain = produtoUseCase.buscarTodosProdutos(produtoParametroModelRequest);
        List<ProdutoModelResponse> produtosModel = ProdutoMapperEntrypointResponse.toCollectionModel(produtosDomain);

        return ResponseEntity.ok(produtosModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModelResponse> buscarPorId(@PathVariable("id") Long idProduto) {
        ProdutoDomainResponse produtoDomain = produtoUseCase.buscarProdutoPorId(idProduto);
        ProdutoModelResponse produtoModel = ProdutoMapperEntrypointResponse.toModel(produtoDomain);

        return ResponseEntity.ok(produtoModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoModelResponse> atualizarProdutoParcial(@PathVariable("id") Long idProduto,
                                                                        @RequestBody Map<String, Object> camposProduto) {

        ProdutoDomainResponse produtoAtualizado = produtoUseCase.atualizarParcialmenteProduto(idProduto, camposProduto);
        ProdutoModelResponse produtoModelAtualizado = ProdutoMapperEntrypointResponse.toModel(produtoAtualizado);

        return ResponseEntity.ok(produtoModelAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long idProduto) {
        produtoUseCase.removerProdutoPorId(idProduto);

        return ResponseEntity.noContent().build();
    }
}
