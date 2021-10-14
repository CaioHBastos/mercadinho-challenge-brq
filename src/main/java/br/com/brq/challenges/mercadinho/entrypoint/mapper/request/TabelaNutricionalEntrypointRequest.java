package br.com.brq.challenges.mercadinho.entrypoint.mapper.request;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.TabelaNutricionalModelRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.request.TabelaNutricionalDomainRequest;

public class TabelaNutricionalEntrypointRequest {

    private TabelaNutricionalEntrypointRequest() {}

    public static TabelaNutricionalDomainRequest toDomain(TabelaNutricionalModelRequest tabelaNutricional) {
        return TabelaNutricionalDomainRequest.builder()
                .valorEnergetico(tabelaNutricional.getValorEnergetico())
                .valorGorduraSaturada(tabelaNutricional.getValorGorduraSaturada())
                .sodio(tabelaNutricional.getSodio())
                .acucar(tabelaNutricional.getAcucar())
                .proteinas(tabelaNutricional.getProteinas())
                .fibras(tabelaNutricional.getFibras())
                .build();
    }
}
