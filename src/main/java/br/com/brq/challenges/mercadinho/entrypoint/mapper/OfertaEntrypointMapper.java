package br.com.brq.challenges.mercadinho.entrypoint.mapper;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.OfertaModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.OfertaModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.Oferta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OfertaEntrypointMapper {

    List<Oferta> map(List<OfertaModelRequest> ofertasModelRequest);

    List<OfertaModelResponse> mapOfertas(List<Oferta> ofertas);
}
