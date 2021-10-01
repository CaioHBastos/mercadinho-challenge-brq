package br.com.brq.challenges.mercadinho.usecase.gateway;

import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;

import java.util.List;

public interface ProdutoGateway {

    ProdutoDomainResponse cadastrarProduto(ProdutoDomainRequest produtoDomainRequest);

    List<ProdutoDomainResponse> buscarTodosProdutos();

    ProdutoDomainResponse buscarProdutoPorId(Long idProduto);

    void removerProdutoPorId(Long idProduto);

    ProdutoDomainResponse atualizarTodosOsDadosProduto(ProdutoDomainResponse produtoAtual);

    ProdutoDomainResponse atualizarParcialmenteOsDadosProduto(ProdutoDomainResponse produtoAtual);

    List<ProdutoDomainResponse> buscarProdutoPorCategoria(String nomeCategoria);

    List<ProdutoDomainResponse> buscarProdutoPorMarca(String marca);
}
