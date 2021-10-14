package br.com.brq.challenges.mercadinho.usecase.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TabelaNutricionalDomainResponse {

    private String valor_energetico;
    private String valor_gordura_saturada;
    private String sodio;
    private String acucar;
    private String proteinas;
    private String fibras;
}
