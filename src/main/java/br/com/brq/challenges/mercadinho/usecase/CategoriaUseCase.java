package br.com.brq.challenges.mercadinho.usecase;

import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeEmUsoException;
import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeExistenteException;
import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeNaoEncontradaException;
import br.com.brq.challenges.mercadinho.usecase.gateway.CategoriaGateway;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class CategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public CategoriaDomainResponse cadastrarCategoria(CategoriaDomainRequest categoriaDomainRequest) {
        verificarSeCategoriaExiste(categoriaDomainRequest);

        return categoriaGateway.cadastrarCategoria(categoriaDomainRequest);
    }

    public List<CategoriaDomainResponse> buscarCategorias() {
        return categoriaGateway.buscarCategorias();
    }

    public CategoriaDomainResponse buscarCategoriaPorId(Long idCategoria) {
        CategoriaDomainResponse categoriaDomain = categoriaGateway.buscarCategoriaPorId(idCategoria);

        verificarSeCategoriaEstaPresente(categoriaDomain, idCategoria);

        return categoriaDomain;
    }

    public void removerCategoriaPorId(Long idCategoria) {
        buscarCategoriaPorId(idCategoria);

        try {
            categoriaGateway.removerCategoriaPorId(idCategoria);

        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(
                    String.format("A categoria com o código %s, não pode ser removida, porque está em uso", idCategoria));
        }
    }

    public CategoriaDomainResponse atualizarCategoria(Long idCategoria, CategoriaDomainRequest categoriaDomainNova) {
        CategoriaDomainResponse categoriaAtual = buscarCategoriaPorId(idCategoria);
        verificarSeCategoriaExiste(categoriaDomainNova);

        categoriaAtual = CategoriaDomainResponse.builder()
                .id(categoriaAtual.getId())
                .nome(categoriaDomainNova.getNome())
                .build();

        return categoriaGateway.atualizarCategoria(categoriaAtual);
    }

    private void verificarSeCategoriaEstaPresente(CategoriaDomainResponse categoriaOptionalDomain,
                                                  Long idCategoria) {

        if (Objects.isNull(categoriaOptionalDomain.getId())) {
            throw new EntidadeNaoEncontradaException(
                    String.format("A categoria de id %s informada, não existe.", idCategoria));
        }
    }

    private void verificarSeCategoriaExiste(CategoriaDomainRequest categoriaDomainRequest) {
        CategoriaDomainResponse categoriaDomain =
                categoriaGateway.buscarCategoriaPorNome(categoriaDomainRequest.getNome());

        if (StringUtils.isNotBlank(categoriaDomain.getNome())) {
            throw new EntidadeExistenteException(
                    String.format("A categoria %s já está cadastrado na base de dados.", categoriaDomainRequest.getNome()));
        }
    }
}
