package br.com.brq.challenges.mercadinho.usecase.utils;

import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.exception.CategoriaNaoEncontradaException;
import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeExistenteException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CategoriaUseCaseUtils {

    private static final String MENSAGEM_ERRO_CATEGORIA_NAO_EXISTE = "A categoria com id %s informada, não existe.";
    private static final String MENSAGEM_ERRO_CATEGORIA_CADASTRO_REPETIDO = "A categoria de '%s' já está cadastrado(a).";

    private CategoriaUseCaseUtils() {}

    public static void verificarSeCategoriaEstaPresente(CategoriaDomainResponse categoriaOptionalDomain, Long idCategoria) {

        if (Objects.isNull(categoriaOptionalDomain.getId())) {
            throw new CategoriaNaoEncontradaException(
                    String.format(MENSAGEM_ERRO_CATEGORIA_NAO_EXISTE, idCategoria));
        }
    }

    public static void verificarSeCategoriaExisteDeAcordoComONome(CategoriaDomainRequest categoriaDomainRequest,
                                                                  CategoriaDomainResponse categoriaDomain) {

        if (StringUtils.isNotBlank(categoriaDomain.getNome())) {
            throw new EntidadeExistenteException(
                    String.format(MENSAGEM_ERRO_CATEGORIA_CADASTRO_REPETIDO, categoriaDomainRequest.getNome()));
        }
    }
}
