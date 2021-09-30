package br.com.brq.challenges.mercadinho.entrypoint.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoriaModelResponse {

    private Long id;
    private String nome;
}
