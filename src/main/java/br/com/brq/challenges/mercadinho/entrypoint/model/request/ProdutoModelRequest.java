package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoModelRequest {

    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotBlank
    @Size(max = 256)
    private String descricao;

    @NotBlank
    @Size(max = 50)
    private String marca;

    @NotNull
    private Double preco;

    @Valid
    @NotEmpty
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
