package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoUpdateModelRequest {

    @Size(max = 60)
    private String nome;

    @Size(max = 256)
    private String descricao;

    @Size(max = 40)
    private String marca;
    private Double preco;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagemOferta;
    private List<DepartamentoIdModelRequest> departamentos;

    public ProdutoUpdateModelRequest() {
    }

    public ProdutoUpdateModelRequest(String nome, String descricao, String marca, Double preco, Boolean ativo, Boolean ofertado, Integer porcentagemOferta, List<DepartamentoIdModelRequest> departamentos) {
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
        this.ativo = ativo;
        this.ofertado = ofertado;
        this.porcentagemOferta = porcentagemOferta;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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

    public List<DepartamentoIdModelRequest> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DepartamentoIdModelRequest> departamentos) {
        this.departamentos = departamentos;
    }
}
