package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OfertaModelRequest {

    @NotBlank
    @Size(max = 36)
    private String idProduto;

    @NotNull
    private Integer porcentagemOferta;

    public OfertaModelRequest() {
    }

    public OfertaModelRequest(String idProduto, Integer porcentagemOferta) {
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
