package br.com.brq.challenges.mercadinho.usecase.gateway;

import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfertaGateway {

    Page<ProdutoDomainResponse> buscarTodasOfertas(Pageable pageable);
}
