package br.com.brq.challenges.mercadinho.entrypoint.controller.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MensagemExceptionModelResponse {

    private String codigo;
    private String mensagem;
    private List<Campos> campos;

    @Getter
    @Builder
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Campos {
        private String nome;
        private String mensagemUsuario;
    }
}
