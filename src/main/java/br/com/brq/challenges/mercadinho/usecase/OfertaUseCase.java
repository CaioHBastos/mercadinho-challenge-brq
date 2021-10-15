package br.com.brq.challenges.mercadinho.usecase;

import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.OfertaGateway;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OfertaUseCase {

    private final OfertaGateway ofertaGateway;

    public Page<ProdutoDomainResponse> buscarTodasOfertas(Pageable pageable) {
        return ofertaGateway.buscarTodasOfertas(pageable);
    }
}
