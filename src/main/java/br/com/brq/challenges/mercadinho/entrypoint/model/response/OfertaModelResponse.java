package br.com.brq.challenges.mercadinho.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OfertaModelResponse {

    private String idProduto;
    private String nomeProduto;
    private String marcaProduto;
    private Double precoProduto;
    private Boolean ofertado;
    private Integer porcentagemOferta;

    public OfertaModelResponse() {
    }

    public OfertaModelResponse(String idProduto, String nomeProduto, String marcaProduto, Double precoProduto, Boolean ofertado, Integer porcentagemOferta) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.marcaProduto = marcaProduto;
        this.precoProduto = precoProduto;
        this.ofertado = ofertado;
        this.porcentagemOferta = porcentagemOferta;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(String marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Boolean getOfertado() {
        return ofertado;
    }

    public void setOfertado(Boolean ofertado) {
        this.ofertado = ofertado;
    }

    public Integer getPorcentagemOferta() {
        return porcentagemOferta;
    }

    public void setPorcentagemOferta(Integer porcentagemOferta) {
        this.porcentagemOferta = porcentagemOferta;
    }
}
