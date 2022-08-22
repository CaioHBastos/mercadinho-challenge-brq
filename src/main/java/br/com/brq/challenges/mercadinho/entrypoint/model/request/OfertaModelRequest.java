package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OfertaModelRequest {

    @NotBlank
    private String idProduto;

    @NotNull
    private Integer porcentagemOferta;

    public OfertaModelRequest() {
    }

    public OfertaModelRequest(Integer String, Integer porcentagemOferta) {
        this.idProduto = idProduto;
        this.porcentagemOferta = porcentagemOferta;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getPorcentagemOferta() {
        return porcentagemOferta;
    }

    public void setPorcentagemOferta(Integer porcentagemOferta) {
        this.porcentagemOferta = porcentagemOferta;
    }
}
