package br.com.brq.challenges.mercadinho.dataprovider.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @Column(name = "id", length=36)
    private String idProduto;

    @Column(name = "nome", nullable = false, length=100)
    private String nomeProduto;

    @Column(name = "descricao", nullable = false, length=256)
    private String descricaoProduto;

    @Column(name = "marca", nullable = false, length=50)
    private String marcaProduto;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "ofertado", nullable = false)
    private Boolean ofertado;

    @Column(name = "porcentagem_oferta", nullable = false)
    private Integer porcentagemOferta;

    @Column(name = "data_cadastro", nullable = false, length=36)
    private String dataCadastro;

    @Column(name = "data_atualizacao", length=36)
    private String dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "produto_departamento",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    private Set<DepartamentoEntity> departamentos;

    public ProdutoEntity() {}

    public ProdutoEntity(String idProduto, String nomeProduto, String descricaoProduto, String marcaProduto,
                         Double preco, Boolean ativo, Boolean ofertado, Integer porcentagemOferta,
                         String dataCadastro, String dataAtualizacao, Set<DepartamentoEntity> departamentos) {

        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.marcaProduto = marcaProduto;
        this.preco = preco;
        this.ativo = ativo;
        this.ofertado = ofertado;
        this.porcentagemOferta = porcentagemOferta;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.departamentos = departamentos;
    }

    public String getIdProduto() {
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

    public Set<DepartamentoEntity> getDepartamentos() {
        return departamentos;
    }

    public void setIdProduto(String idProduto) {
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

    public void setDepartamentos(Set<DepartamentoEntity> departamentos) {
        this.departamentos = departamentos;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
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
