package br.com.brq.challenges.mercadinho.entrypoint.validate.exception;

import java.util.List;

public class RequisicaoInvalidaException extends RuntimeException {

    public RequisicaoInvalidaException(List<String> mensagens) {
    }
}
