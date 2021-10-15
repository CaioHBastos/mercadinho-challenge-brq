package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TabelaNutricionalModelRequest {

    @NotBlank
    private String valorEnergetico;

    @NotBlank
    private String valorGorduraSaturada;

    @NotBlank
    private String sodio;

    @NotBlank
    private String acucar;

    @NotBlank
    private String proteinas;

    @NotBlank
    private String fibras;
}
