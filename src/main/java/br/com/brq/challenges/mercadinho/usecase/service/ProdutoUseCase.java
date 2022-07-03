package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;

import java.util.List;

public interface ProdutoUseCase {

    Produto criarProduto(Produto produto);
    List<Produto> buscarProdutos();
    Produto detalharProduto(String idProduto);
}
