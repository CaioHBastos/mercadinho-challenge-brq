package br.com.brq.challenges.mercadinho.usecase.exception;

public class BadBusyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadBusyException(String mensagem) {
        super(mensagem);
    }
}
