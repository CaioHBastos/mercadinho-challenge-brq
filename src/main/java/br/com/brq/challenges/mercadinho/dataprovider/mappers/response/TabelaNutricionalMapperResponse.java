package br.com.brq.challenges.mercadinho.dataprovider.mappers.response;

import br.com.brq.challenges.mercadinho.dataprovider.entities.TabelaNutricionalEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.response.TabelaNutricionalDomainResponse;

import java.util.Objects;

public class TabelaNutricionalMapperResponse {

    private TabelaNutricionalMapperResponse() {}

    public static TabelaNutricionalDomainResponse toDomain(TabelaNutricionalEntity tabelaNutricional) {
        if (Objects.isNull(tabelaNutricional)) {
            return null;
        }

        return TabelaNutricionalDomainResponse.builder()
                .valor_energetico(tabelaNutricional.getValorEnergetico())
                .valor_gordura_saturada(tabelaNutricional.getValorGorduraSaturada())
                .sodio(tabelaNutricional.getSodio())
                .acucar(tabelaNutricional.getAcucar())
                .proteinas(tabelaNutricional.getProteinas())
                .fibras(tabelaNutricional.getFibras())
                .build();
    }
}
