package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CategoriaModelRequest {

    @NotBlank
    private String nome;
}
