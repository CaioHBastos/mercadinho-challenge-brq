package br.com.brq.challenges.mercadinho.usecase.exception;

public class BadRequestPostException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestPostException(String mensagem) {
        super(mensagem);
    }
}
