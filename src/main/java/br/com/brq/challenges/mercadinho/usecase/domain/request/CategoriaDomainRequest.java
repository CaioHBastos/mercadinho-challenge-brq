package br.com.brq.challenges.mercadinho.usecase.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoriaDomainRequest {

    private String nome;
}
