package br.com.brq.challenges.mercadinho.dataprovider.implementations;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.mappers.response.OfertaMapperResponse;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.OfertaRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.OfertaGateway;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OfertaImplementation implements OfertaGateway {

    private final OfertaRepository ofertaRepository;

    @Override
    public Page<ProdutoDomainResponse> buscarTodasOfertas(Pageable pageable) {
        Page<ProdutoEntity> produtosEmOferta = ofertaRepository.buscarProdutosEmOferta(pageable);

        return OfertaMapperResponse.toCollectionDomain(produtosEmOferta);
    }
}
