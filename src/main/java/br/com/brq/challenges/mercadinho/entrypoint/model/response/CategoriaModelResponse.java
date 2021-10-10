package br.com.brq.challenges.mercadinho.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaModelResponse {

    private Long id;
    private String nome;
}
