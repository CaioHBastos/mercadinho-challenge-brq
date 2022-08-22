package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import javax.validation.constraints.NotNull;

public class DepartamentoIdModelRequest {

    @NotNull
    private Integer id;

    public DepartamentoIdModelRequest() {
    }

    public DepartamentoIdModelRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
