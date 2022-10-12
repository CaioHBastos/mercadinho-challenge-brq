package br.com.brq.challenges.mercadinho.usecase.gateway;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroProdutoException;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {

    Produto criarProduto(Produto produto) throws CadastroProdutoException;

    Optional<Produto> buscarProdutoPorNome(String nomeProduto);

    List<Produto> buscarTodosProdutos(Produto produto);

    Optional<Produto> detalharProdutoPorId(String idProduto);

    void removerProdutoPorId(String idProduto);

    Produto atualizarProduto(Produto produtoAtual);

    void atualizarAtivacaoProdutos(List<Produto> produtosAtualizado);
}
