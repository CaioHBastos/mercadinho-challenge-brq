package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TabelaNutricionalModelRequest {

    private String valorEnergetico;
    private String valorGorduraSaturada;
    private String sodio;
    private String acucar;
    private String proteinas;
    private String fibras;
}
