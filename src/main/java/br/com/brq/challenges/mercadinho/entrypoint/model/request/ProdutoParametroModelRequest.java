package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class ProdutoParametroModelRequest {

    private String nomeCategoria;
    private String marca;

    @ConstructorProperties(value = {"nome_categoria", "marca"})
    public ProdutoParametroModelRequest(String nomeCategoria, String marca) {
        this.nomeCategoria = nomeCategoria;
        this.marca = marca;
    }
}
