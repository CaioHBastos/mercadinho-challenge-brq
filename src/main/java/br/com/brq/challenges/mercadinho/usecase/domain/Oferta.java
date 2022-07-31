package br.com.brq.challenges.mercadinho.usecase.domain;

public class Oferta {

    private String idProduto;
    private String nomeProduto;
    private String marcaProduto;
    private Double precoProduto;
    private Boolean ofertado;
    private Integer porcentagemOferta;

    public Oferta() {
    }

    public Oferta(String idProduto, String nomeProduto, String marcaProduto, Double precoProduto, Boolean ofertado, Integer porcentagemOferta) {
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
}
