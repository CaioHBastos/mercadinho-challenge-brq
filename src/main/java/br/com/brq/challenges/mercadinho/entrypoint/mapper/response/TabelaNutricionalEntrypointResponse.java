package br.com.brq.challenges.mercadinho.entrypoint.mapper.response;

import br.com.brq.challenges.mercadinho.entrypoint.model.response.TabelaNutricionalModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.response.TabelaNutricionalDomainResponse;

import java.util.Objects;

public class TabelaNutricionalEntrypointResponse {

    private TabelaNutricionalEntrypointResponse() {}

    public static TabelaNutricionalModelResponse toModel(TabelaNutricionalDomainResponse tabelaNutricional) {
        if (Objects.isNull(tabelaNutricional)) {
            return null;
        }

        return TabelaNutricionalModelResponse.builder()
                .valorEnergetico(tabelaNutricional.getValor_energetico())
                .valorGorduraSaturada(tabelaNutricional.getValor_gordura_saturada())
                .sodio(tabelaNutricional.getSodio())
                .acucar(tabelaNutricional.getAcucar())
                .proteinas(tabelaNutricional.getProteinas())
                .fibras(tabelaNutricional.getFibras())
                .build();
    }
}
