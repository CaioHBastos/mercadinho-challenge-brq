package br.com.brq.challenges.mercadinho.entrypoint.controller;

import br.com.brq.challenges.mercadinho.entrypoint.mapper.OfertaEntrypointMapper;
import br.com.brq.challenges.mercadinho.entrypoint.model.request.OfertaModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.OfertaModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.Oferta;
import br.com.brq.challenges.mercadinho.usecase.service.OfertaUseCase;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/ofertas")
public class OfertaController {

    private final OfertaEntrypointMapper ofertaEntrypointMapper = Mappers.getMapper(OfertaEntrypointMapper.class);

    private OfertaUseCase ofertaUseCase;

    public OfertaController(OfertaUseCase ofertaUseCase) {
        this.ofertaUseCase = ofertaUseCase;
    }

    @PutMapping
    public ResponseEntity<Void> adicionarOfertaEmProduto(@Valid @RequestBody List<OfertaModelRequest> ofertasModelRequest) {
        List<Oferta> ofertas = ofertaEntrypointMapper.map(ofertasModelRequest);
        ofertaUseCase.adicionarOfertas(ofertas);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> removerOfertaEmProduto(@RequestBody List<String> idsProduto) {
        ofertaUseCase.removerOfertas(idsProduto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<OfertaModelResponse>> buscarProdutosOfertado() {
        List<Oferta> ofertas = ofertaUseCase.buscarOfertas();
        List<OfertaModelResponse> ofertasModelResponse = ofertaEntrypointMapper.mapOfertas(ofertas);

        return new ResponseEntity<>(ofertasModelResponse, HttpStatus.OK);
    }
}
