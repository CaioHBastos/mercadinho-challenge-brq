package br.com.brq.challenges.mercadinho.usecase.domain.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoriaDomainResponse {

    private Long id;
    private String nome;
}
