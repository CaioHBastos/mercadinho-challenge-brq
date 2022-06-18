package br.com.brq.challenges.mercadinho.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProdutoModelResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private Boolean ativo;
    private List<DepartamentoModelResponse> departamentos = new ArrayList<>();

    public ProdutoModelResponse() {}

    public ProdutoModelResponse(Long id, String nome, String descricao, String marca, Double preco,
                                Boolean ativo, List<DepartamentoModelResponse> departamentos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
        this.ativo = ativo;
        this.departamentos = departamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<DepartamentoModelResponse> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DepartamentoModelResponse> departamentos) {
        this.departamentos = departamentos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
