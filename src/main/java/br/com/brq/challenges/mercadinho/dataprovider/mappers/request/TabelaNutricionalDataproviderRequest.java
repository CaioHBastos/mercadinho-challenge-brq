package br.com.brq.challenges.mercadinho.dataprovider.mappers.request;

import br.com.brq.challenges.mercadinho.dataprovider.entities.TabelaNutricionalEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.request.TabelaNutricionalDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.TabelaNutricionalDomainResponse;

import java.util.Objects;

public class TabelaNutricionalDataproviderRequest {

    private TabelaNutricionalDataproviderRequest() {}

    public static TabelaNutricionalEntity toEntity(TabelaNutricionalDomainRequest tabelaNutricional) {
        if (Objects.isNull(tabelaNutricional)) {
            return null;
        }

        return TabelaNutricionalEntity.builder()
                .valorEnergetico(tabelaNutricional.getValorEnergetico())
                .valorGorduraSaturada(tabelaNutricional.getValorGorduraSaturada())
                .sodio(tabelaNutricional.getSodio())
                .acucar(tabelaNutricional.getAcucar())
                .proteinas(tabelaNutricional.getProteinas())
                .fibras(tabelaNutricional.getFibras())
                .build();
    }

    public static TabelaNutricionalEntity toEntityIdAtualizado(TabelaNutricionalDomainResponse tabelaNutricional) {
        if (Objects.isNull(tabelaNutricional)) {
            return null;
        }

        return TabelaNutricionalEntity.builder()
                .valorEnergetico(tabelaNutricional.getValor_energetico())
                .valorGorduraSaturada(tabelaNutricional.getValor_gordura_saturada())
                .sodio(tabelaNutricional.getSodio())
                .acucar(tabelaNutricional.getAcucar())
                .proteinas(tabelaNutricional.getProteinas())
                .fibras(tabelaNutricional.getFibras())
                .build();
    }
}
