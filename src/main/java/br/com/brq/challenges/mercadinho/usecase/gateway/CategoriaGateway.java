package br.com.brq.challenges.mercadinho.usecase.gateway;

import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;

import java.util.List;

public interface CategoriaGateway {

    CategoriaDomainResponse cadastrarCategoria(CategoriaDomainRequest categoriaDomainRequest);

    List<CategoriaDomainResponse> buscarCategorias();

    CategoriaDomainResponse buscarCategoriaPorId(Long idCategoria);

    void removerCategoriaPorId(Long idCategoria);

    CategoriaDomainResponse atualizarCategoria(CategoriaDomainResponse categoriaAtual);
}
