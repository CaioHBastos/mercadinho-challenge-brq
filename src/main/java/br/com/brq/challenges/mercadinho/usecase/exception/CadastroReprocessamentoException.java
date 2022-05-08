package br.com.brq.challenges.mercadinho.usecase.exception;

public class CadastroReprocessamentoException extends RuntimeException {

    public CadastroReprocessamentoException(String mensagem) {
        super(mensagem);
    }
}
