package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import java.util.List;

public class ProdutoModelRequest {

    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private List<DepartamentoIdModelRequest> departamentos;

    public ProdutoModelRequest() {}

    public ProdutoModelRequest(String nome, String descricao, String marca, Double preco, List<DepartamentoIdModelRequest> departamentos) {
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
        this.departamentos = departamentos;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public List<DepartamentoIdModelRequest> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DepartamentoIdModelRequest> departamentos) {
        this.departamentos = departamentos;
    }
}
