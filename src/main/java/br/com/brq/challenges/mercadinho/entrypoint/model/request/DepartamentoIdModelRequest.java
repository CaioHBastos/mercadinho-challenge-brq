package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DepartamentoIdModelRequest {

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
