package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DepartamentoModelRequest {

    @NotBlank
    @Size(max = 30)
    @JsonDeserialize(using = StringTrimModelRequest.class)
    private String nome;

    @NotBlank
    @Size(max = 256)
    @JsonDeserialize(using = StringTrimModelRequest.class)
    private String descricao;

    public DepartamentoModelRequest() {
    }

    public DepartamentoModelRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
