package br.com.brq.challenges.mercadinho.dataprovider.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long idProduto;

    @Column(name = "nome", nullable = false)
    private String nomeProduto;

    @Column(name = "descricao", nullable = false)
    private String descricaoProduto;

    @Column(name = "marca", nullable = false)
    private String marcaProduto;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "ofertado", nullable = false)
    private Boolean ofertado;

    @Column(name = "porcentagem_oferta", nullable = false)
    private Integer porcentagemOferta;

    @ManyToMany
    @JoinTable(name = "produto_departamento",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    private List<DepartamentoEntity> departamentos = new ArrayList<>();

    public ProdutoEntity() {}

    public ProdutoEntity(Long idProduto, String nomeProduto, String descricaoProduto, String marcaProduto,
                         Double preco, Boolean ativo, Boolean ofertado, Integer porcentagemOferta,
                         List<DepartamentoEntity> departamentos) {

        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.marcaProduto = marcaProduto;
        this.preco = preco;
        this.ativo = ativo;
        this.ofertado = ofertado;
        this.porcentagemOferta = porcentagemOferta;
        this.departamentos = departamentos;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public String getMarcaProduto() {
        return marcaProduto;
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

    public List<DepartamentoEntity> getDepartamentos() {
        return departamentos;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public void setMarcaProduto(String marcaProduto) {
        this.marcaProduto = marcaProduto;
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

    public void setDepartamentos(List<DepartamentoEntity> departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoEntity that = (ProdutoEntity) o;
        return idProduto.equals(that.idProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto);
    }
}
