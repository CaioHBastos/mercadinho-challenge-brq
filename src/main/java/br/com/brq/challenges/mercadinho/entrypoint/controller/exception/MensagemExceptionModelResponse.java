package br.com.brq.challenges.mercadinho.entrypoint.controller.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MensagemExceptionModelResponse {

    private String codigo;
    private String mensagem;
}
