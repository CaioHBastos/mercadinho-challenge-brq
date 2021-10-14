package br.com.brq.challenges.mercadinho.usecase.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TabelaNutricionalDomainRequest {

    private String valorEnergetico;
    private String valorGorduraSaturada;
    private String sodio;
    private String acucar;
    private String proteinas;
    private String fibras;
}
