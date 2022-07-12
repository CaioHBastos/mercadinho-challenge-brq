package br.com.brq.challenges.mercadinho.usecase.exception;

public class NenhumConteudoEncontradoException extends RuntimeException {

    public NenhumConteudoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
