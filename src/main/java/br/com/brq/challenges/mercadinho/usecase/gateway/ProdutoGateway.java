package br.com.brq.challenges.mercadinho.usecase.gateway;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroProdutoException;

import java.util.Optional;

public interface ProdutoGateway {

    Produto criarProduto(Produto produto) throws CadastroProdutoException;

    Optional<Produto> buscarProdutoPorNome(String nomeProduto);
}
