package br.com.brq.challenges.mercadinho.usecase.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CategoriaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
