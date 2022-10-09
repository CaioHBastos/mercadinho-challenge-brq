package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import javax.validation.constraints.NotBlank;

public class ProdutoAtivacaoModelRequest {

    @NotBlank
    private String id;

    public ProdutoAtivacaoModelRequest() {
    }

    public ProdutoAtivacaoModelRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
