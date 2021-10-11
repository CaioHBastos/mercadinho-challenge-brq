package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.request.CategoriaMapperEntrypointRequest;
import br.com.brq.challenges.mercadinho.entrypoint.mapper.response.CategoriaMapperEntrypointResponse;
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

import static br.com.brq.challenges.mercadinho.entrypoint.controller.UrlApiConstants.URL_CATEGORIAS_BASE;
import static br.com.brq.challenges.mercadinho.entrypoint.controller.UrlApiConstants.URL_CATEGORIAS_ID;

/**
 * Classe responsável por ser a controladora do recurso de Categorias, onde contém
 * os endpoins e métodos https.
 *
 * @author Caio Henrique Bastos
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = URL_CATEGORIAS_BASE)
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;

    /**
     * Método responsável por realizar a busca da listagem de categorias na base de
     * dados e devolver um sucesso caso a busca tenha dados.
     *
     * @return {@code ResponseEntity<List<CategoriaModelResponse>>}
     *      - Caso tenha informações de categoria na base dados, a aplicação
     *      irá devolver uma lista de entidades de categorias com sucesso.
     *      - Caso não tenha categorias cadastrada, irá ser apresentado
     *      que não existe conteúdo para o recurso de categorias.
     */
    @GetMapping
    public ResponseEntity<List<CategoriaModelResponse>> buscar() {
        List<CategoriaDomainResponse> categoriasDomain = categoriaUseCase.buscarCategorias();
        List<CategoriaModelResponse> categoriasModel = CategoriaMapperEntrypointResponse.toCollectionModel(categoriasDomain);

        if (categoriasModel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categoriasModel);
    }

    /**
     * Método responsável por realizar a busca de dados de um recurso específico da categoria
     * na base de dados e devolver um sucesso.
     *
     * @param idCategoria {@code Long}
     *      - Recebe como parâmetro um path do id do recurso para a busca
     *
     * @return {@code ResponseEntity<CategoriaModelResponse>}
     *      - A aplicação devolve como resposta um singleton do recurso na busca com o status de sucesso.
     */
    @GetMapping(URL_CATEGORIAS_ID)
    public ResponseEntity<CategoriaModelResponse> buscarPorId(@PathVariable("id") Long idCategoria) {
        CategoriaDomainResponse categoriaDomainResponse = categoriaUseCase.buscarCategoriaPorId(idCategoria);
        CategoriaModelResponse categoriaModelResponse = CategoriaMapperEntrypointResponse.toModel(categoriaDomainResponse);

        return ResponseEntity.ok(categoriaModelResponse);
    }

    /**
     * Método responsável por realizar o cadastro de uma categoria na base de dados
     * e responder com o status de criado.
     *
     * @param categoriaModelRequest {@code CategoriaModelRequest}
     *      - Recebe como parâmetro os dados da categoria e realiza a validação do bean.
     *
     * @return {@code ResponseEntity<CategoriaModelResponse>}
     *      - A aplicação devolve como resposta uma entidade criada de categoria.
     */
    @PostMapping
    public ResponseEntity<CategoriaModelResponse> adicionar(@RequestBody CategoriaModelRequest categoriaModelRequest) {
        CategoriaDomainRequest categoriaDomainRequest = CategoriaMapperEntrypointRequest.toDomain(categoriaModelRequest);
        CategoriaDomainResponse categoriaDomainResponse = categoriaUseCase.cadastrarCategoria(categoriaDomainRequest);
        CategoriaModelResponse categoriaModelResponse = CategoriaMapperEntrypointResponse.toModel(categoriaDomainResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaModelResponse);
    }

    /**
     * Método responsável por realizar a atualização de um recurso de acordo com o Id e os
     * dados de entrada informados para a categoria.
     *
     * @param idCategoria {@code Long}
     *      - Recebe como parâmetro um path do id do recurso para a busca.
     *
     * @param categoriaModelRequest {@code CategoriaModelRequest}
     *      - Recebe como parâmetro os dados da categoria e realiza a validação do bean.
     *
     * @return {@code ResponseEntity<CategoriaModelResponse>}
     *      - A aplicação devolve como resposta um singleton do recurso que está sendo atualizado
     *      com o status de sucesso.
     */
    @PutMapping(URL_CATEGORIAS_ID)
    public ResponseEntity<CategoriaModelResponse> atualizar(@PathVariable("id") Long idCategoria,
                                                            @RequestBody CategoriaModelRequest categoriaModelRequest) {
        CategoriaDomainRequest categoriaDomainNova = CategoriaMapperEntrypointRequest.toDomain(categoriaModelRequest);
        CategoriaDomainResponse categoriaDomainAtualizada = categoriaUseCase.atualizarCategoria(idCategoria, categoriaDomainNova);
        CategoriaModelResponse categoriaModelSalva = CategoriaMapperEntrypointResponse.toModel(categoriaDomainAtualizada);

        return ResponseEntity.ok(categoriaModelSalva);
    }

    /**
     * Método responsável por realizar a remoção de um recurso da base de dados de acordo
     * com o id informado da categoria
     *
     * @param idCategoria {@code Long}
     *      - Recebe como parâmetro um path do id do recurso para a busca.
     *
     * @return {@code ResponseEntity<Void>}
     *      - A devolve em caso de sucesso da remoção do recurso que não existe mais conteúdo.
     */
    @DeleteMapping(URL_CATEGORIAS_ID)
    public ResponseEntity<Void> removerPorId(@PathVariable("id") Long idCategoria) {
        categoriaUseCase.removerCategoriaPorId(idCategoria);

        return ResponseEntity.noContent().build();
    }
}
