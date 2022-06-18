package br.com.brq.challenges.mercadinho.usecase.domain;

import java.util.ArrayList;
import java.util.List;

public class Produto {

    private Long id;
    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagemOferta;
    private List<Departamento> departamentos = new ArrayList<>();

    public Produto() {}

    public Produto(Long id, String nome, String descricao, String marca, Double preco, Boolean ativo,
                   Boolean ofertado, Integer porcentagemOferta, List<Departamento> departamentos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
        this.ativo = ativo;
        this.ofertado = ofertado;
        this.porcentagemOferta = porcentagemOferta;
        this.departamentos = departamentos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public Double getPreco() {
        return preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Boolean getOfertado() {
        return ofertado;
    }

    public Integer getPorcentagemOferta() {
        return porcentagemOferta;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setOfertado(Boolean ofertado) {
        this.ofertado = ofertado;
    }

    public void setPorcentagemOferta(Integer porcentagemOferta) {
        this.porcentagemOferta = porcentagemOferta;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
