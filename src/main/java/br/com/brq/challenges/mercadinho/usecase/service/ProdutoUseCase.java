package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;

import java.util.List;

public interface ProdutoUseCase {

    Produto criarProduto(Produto produto);

    List<Produto> buscarProdutos(Produto produto);

    Produto detalharProduto(String idProduto);

    void removerProduto(String idProduto);

    Produto atualiazarParcialmenteProduto(String idProduto, Produto produto);

    void ativarProdutos(List<Produto> produtos);

    void inativarProdutos(List<Produto> produtos);

    void adicionarDepartamentoEmProduto(String idProduto, List<Departamento> departamentos);

    void removerDepartamentoEmProduto(String idProduto, List<Departamento> departamentos);
}
