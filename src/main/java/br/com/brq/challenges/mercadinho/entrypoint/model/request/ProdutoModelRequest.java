package br.com.brq.challenges.mercadinho.entrypoint.model.request;

public class ProdutoModelRequest {

    private String nome;
    private String descricao;
    private String marca;
    private Double preco;

    public ProdutoModelRequest() {}

    public ProdutoModelRequest(String nome, String descricao, String marca, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
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
}
