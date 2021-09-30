package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.request.CategoriaMapperRequest;
import br.com.brq.challenges.mercadinho.entrypoint.mapper.response.CategoriaMapperResponse;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.CategoriaModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.CategoriaModelResponse;
import br.com.brq.challenges.mercadinho.usecase.CategoriaUseCase;
import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;

    @PostMapping
    public ResponseEntity<CategoriaModelResponse> adicionar(@RequestBody CategoriaModelRequest categoriaModelRequest) {
        CategoriaDomainRequest categoriaDomainRequest = CategoriaMapperRequest.toDomain(categoriaModelRequest);
        CategoriaDomainResponse categoriaDomainResponse = categoriaUseCase.cadastrarCategoria(categoriaDomainRequest);
        CategoriaModelResponse categoriaModelResponse = CategoriaMapperResponse.toModel(categoriaDomainResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaModelResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaModelResponse>> buscar() {
        List<CategoriaDomainResponse> categoriasDomain = categoriaUseCase.buscarCategorias();
        List<CategoriaModelResponse> categoriasModel = CategoriaMapperResponse.toCollectionModel(categoriasDomain);

        return ResponseEntity.ok(categoriasModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModelResponse> buscarPorId(@PathVariable("id") Long idCategoria) {
        CategoriaDomainResponse categoriaDomainResponse = categoriaUseCase.buscarCategoriaPorId(idCategoria);
        CategoriaModelResponse categoriaModelResponse = CategoriaMapperResponse.toModel(categoriaDomainResponse);

        return ResponseEntity.ok(categoriaModelResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaModelResponse> atualizar(@PathVariable("id") Long idCategoria,
                                                            @RequestBody CategoriaModelRequest categoriaModelRequest) {
        CategoriaDomainRequest categoriaDomainNova = CategoriaMapperRequest.toDomain(categoriaModelRequest);
        CategoriaDomainResponse categoriaDomainAtualizada = categoriaUseCase.atualizarCategoria(idCategoria, categoriaDomainNova);
        CategoriaModelResponse categoriaModelSalva = CategoriaMapperResponse.toModel(categoriaDomainAtualizada);

        return ResponseEntity.ok(categoriaModelSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable("id") Long idCategoria) {
        categoriaUseCase.removerCategoriaPorId(idCategoria);

        return ResponseEntity.noContent().build();
    }
}
