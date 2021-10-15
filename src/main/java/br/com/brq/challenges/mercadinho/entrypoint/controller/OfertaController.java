package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.response.OfertaMapperEntrypointResponse;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.ProdutoResumidoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.OfertaUseCase;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.brq.challenges.mercadinho.entrypoint.controller.constants.UrlApiConstants.URL_OFERTAS_BASE;

/**
 * Classe responsável por ser a controladora do recurso de Ofertas, onde contém
 * os endpoins e métodos https.
 *
 * @author Caio Henrique Bastos
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = URL_OFERTAS_BASE)
public class OfertaController {

    private final OfertaUseCase ofertaUseCase;

    @GetMapping
    public ResponseEntity<Page<ProdutoResumidoModelResponse>> buscarTodos(Pageable pageable) {
        Page<ProdutoDomainResponse> produtosDomainOfertado = ofertaUseCase.buscarTodasOfertas(pageable);
        Page<ProdutoResumidoModelResponse> produtosModel = OfertaMapperEntrypointResponse.toCollectionModel(produtosDomainOfertado);

        return ResponseEntity.ok(produtosModel);
    }
}
