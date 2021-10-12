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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static br.com.brq.challenges.mercadinho.entrypoint.controller.UrlApiConstants.URL_PRODUTOS_BASE;
import static br.com.brq.challenges.mercadinho.entrypoint.controller.UrlApiConstants.URL_PRODUTOS_ID;

/**
 * Classe responsável por ser a controladora do recurso de Produtos, onde contém
 * os endpoins e métodos https.
 *
 * @author Caio Henrique Bastos
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = URL_PRODUTOS_BASE)
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;

    /**
     * Método responsável por buscar todos os dados da coleção de produtos. Pode buscar sem ter informado algum filtro da busca
     * ou com algum filtro inforado. Caso não seja informado o filtro, retorna a coleção completa da base de dados.
     *
     * @param produtoParametroModelRequest {@Code ProdutoParametroModelRequest}
     *      - Objeto no qual contém os parâmetros que são opcionais para o filtro na busca.
     *
     * @return {@code ResponseEntity<Page<ProdutoModelResponse>>}
     *      - Caso tenha informações de produtos na base dados, a aplicação
     *      irá devolver uma lista paginada de entidades de produtos com sucesso.
     *      - Caso não tenha produtos cadastrado, irá ser apresentado
     *      que não existe conteúdo para o recurso de produtos.
     */
    @GetMapping
    public ResponseEntity<Page<ProdutoModelResponse>> buscarTodos(Pageable pageable, ProdutoParametroModelRequest produtoParametroModelRequest) {
        Page<ProdutoDomainResponse> produtosDomain = produtoUseCase.buscarTodosProdutos(pageable, produtoParametroModelRequest);

        if (produtosDomain.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Page<ProdutoModelResponse> produtosModel = ProdutoMapperEntrypointResponse.toCollectionModel(produtosDomain);

        return ResponseEntity.ok(produtosModel);
    }

    /**
     * Método responsável por realizar a busca de dados de um recurso específico do produto
     * na base de dados e devolver um sucesso.
     *
     * @param idProduto {@code Long}
     *      - Recebe como parâmetro um path do id do recurso para a busca
     *
     * @return {@code ResponseEntity<ProdutoModelResponse>}
     *      - A aplicação devolve como resposta um singleton do recurso na busca com o status de sucesso.
     */
    @GetMapping(URL_PRODUTOS_ID)
    public ResponseEntity<ProdutoModelResponse> buscarPorId(@PathVariable("id") Long idProduto) {
        ProdutoDomainResponse produtoDomain = produtoUseCase.buscarProdutoPorId(idProduto);
        ProdutoModelResponse produtoModel = ProdutoMapperEntrypointResponse.toModel(produtoDomain);

        return ResponseEntity.ok(produtoModel);
    }

    /**
     * Método responsável por realizar o cadastro de um produto na base de dados
     * e responder com o status de criado.
     *
     * @param produtoModelResquest {@code ProdutoModelResquest}
     *      - Recebe como parâmetro os dados do produto e realiza a validação do bean.
     *
     * @return {@code ResponseEntity<ProdutoModelResponse>}
     *      - A aplicação devolve como resposta uma entidade criada de produto.
     */
    @PostMapping
    public ResponseEntity<ProdutoModelResponse> salvar(@RequestBody ProdutoModelResquest produtoModelResquest) {
        ProdutoDomainRequest novoProdutoDomain = ProdutoMapperEntrypointRequest.toDomainCadastro(produtoModelResquest);
        ProdutoDomainResponse produtoCadastradoDomain = produtoUseCase.cadastrarProduto(novoProdutoDomain);
        ProdutoModelResponse produtoCadastradoModel = ProdutoMapperEntrypointResponse.toModel(produtoCadastradoDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCadastradoModel);
    }

    /**
     * Método responsável por realizar a atualização forma parcial ou completa de um recurso
     * de acordo com o Id e os dados de entrada informados para a categoria.
     *
     * @param idProduto {@code Long}
     *      - Recebe como parâmetro um path do id do recurso para a busca.
     *
     * @param camposProduto {@code Map<String, Object>}
     *      - Recebe como parâmatro um Mapa de string e objeto, porque os objetos
     *      são opcionais e o cliente pode informar 1 ou N e para deixar a atualziação dinâmica.
     *
     * @return {@code ResponseEntity<ProdutoModelResponse>}
     *      - A aplicação devolve como resposta um singleton do recurso que está sendo atualizado
     *      com o status de sucesso.
     */
    @PatchMapping(URL_PRODUTOS_ID)
    public ResponseEntity<ProdutoModelResponse> atualizarProdutoParcial(@PathVariable("id") Long idProduto,
                                                                        @RequestBody Map<String, Object> camposProduto) {

        ProdutoDomainResponse produtoAtualizado = produtoUseCase.atualizarParcialmenteProduto(idProduto, camposProduto);
        ProdutoModelResponse produtoModelAtualizado = ProdutoMapperEntrypointResponse.toModel(produtoAtualizado);

        return ResponseEntity.ok(produtoModelAtualizado);
    }

    /**
     * Método responsável por realizar a remoção de um recurso da base de dados de acordo
     * com o id informado do produto
     *
     * @param idProduto {@code Long}
     *      - Recebe como parâmetro um path do id do recurso para a busca.
     *
     * @return {@code ResponseEntity<Void>}
     *      - A devolve em caso de sucesso da remoção do recurso que não existe mais conteúdo.
     */
    @DeleteMapping(URL_PRODUTOS_ID)
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long idProduto) {
        produtoUseCase.removerProdutoPorId(idProduto);

        return ResponseEntity.noContent().build();
    }
}
