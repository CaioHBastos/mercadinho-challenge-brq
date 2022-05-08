package br.com.brq.challenges.mercadinho.usecase.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Departamento {

    private final Long id;
    private final String nome;
}
