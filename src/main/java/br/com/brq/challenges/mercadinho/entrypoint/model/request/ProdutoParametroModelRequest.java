package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class ProdutoParametroModelRequest {

    private String nomeCategoria;

    @ConstructorProperties(value = {"nome_categoria"})
    public ProdutoParametroModelRequest(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
