package br.com.brq.challenges.mercadinho.entrypoint.model.request;


import java.beans.ConstructorProperties;

public class ProdutoModelFilterRequest {

    private String nome;
    private String marca;
    private Double preco;
    private Boolean ativo;
    private Integer idDepartamento;

    @ConstructorProperties({"nome", "marca", "preco", "ativo", "id_departamento"})
    public ProdutoModelFilterRequest(String nome, String marca, Double preco, Boolean ativo, Integer idDepartamento) {
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.ativo = ativo;
        this.idDepartamento = idDepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
