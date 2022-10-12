package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoUpdateModelRequest {

    @Size(max = 60)
    @JsonDeserialize(using = StringTrimModelRequest.class)
    private String nome;

    @Size(max = 256)
    @JsonDeserialize(using = StringTrimModelRequest.class)
    private String descricao;

    @Size(max = 40)
    @JsonDeserialize(using = StringTrimModelRequest.class)
    private String marca;
    private Double preco;
    private List<DepartamentoIdModelRequest> departamentos;

    public ProdutoUpdateModelRequest() {
    }

    public ProdutoUpdateModelRequest(String nome, String descricao, String marca, Double preco, Integer porcentagemOferta, List<DepartamentoIdModelRequest> departamentos) {
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

    public List<DepartamentoIdModelRequest> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DepartamentoIdModelRequest> departamentos) {
        this.departamentos = departamentos;
    }
}
