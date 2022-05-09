package br.com.brq.challenges.mercadinho.usecase.exception;

public class CadastroProdutoException extends Exception {

    public CadastroProdutoException(String message, Exception exception) {
        super(message, exception);
    }
}
