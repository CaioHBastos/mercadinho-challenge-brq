package br.com.brq.challenges.mercadinho.usecase;

import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.CategoriaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public CategoriaDomainResponse cadastrarCategoria(CategoriaDomainRequest categoriaDomainRequest) {
        return categoriaGateway.cadastrarCategoria(categoriaDomainRequest);
    }

    public List<CategoriaDomainResponse> buscarCategorias() {
        return categoriaGateway.buscarCategorias();
    }

    public CategoriaDomainResponse buscarCategoriaPorId(Long idCategoria) {
        return categoriaGateway.buscarCategoriaPorId(idCategoria);
    }

    public void removerCategoriaPorId(Long idCategoria) {
        categoriaGateway.removerCategoriaPorId(idCategoria);
    }

    public CategoriaDomainResponse atualizarCategoria(Long idCategoria, CategoriaDomainRequest categoriaDomainNova) {
        CategoriaDomainResponse categoriaAtual = buscarCategoriaPorId(idCategoria);

        categoriaAtual = CategoriaDomainResponse.builder()
                .id(categoriaAtual.getId())
                .nome(categoriaDomainNova.getNome())
                .build();

        return categoriaGateway.atualizarCategoria(categoriaAtual);
    }
}
