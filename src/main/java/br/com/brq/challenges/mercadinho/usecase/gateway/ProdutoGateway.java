package br.com.brq.challenges.mercadinho.usecase.gateway;

import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoGateway {

    ProdutoDomainResponse cadastrarProduto(ProdutoDomainRequest produtoDomainRequest);

    Page<ProdutoDomainResponse> buscarTodosProdutos(Pageable pageable);

    ProdutoDomainResponse buscarProdutoPorId(Long idProduto, String expand);

    void removerProdutoPorId(Long idProduto);

    ProdutoDomainResponse atualizarParcialmenteOsDadosProduto(ProdutoDomainResponse produtoAtual);

    Page<ProdutoDomainResponse> buscarProdutoPorCategoria(Pageable pageable, String nomeCategoria);

    Page<ProdutoDomainResponse> buscarProdutoPorMarca(Pageable pageable, String marca);
}
