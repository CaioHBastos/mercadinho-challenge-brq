package br.com.brq.challenges.mercadinho.usecase.utils;

import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.exception.BadBusyException;
import br.com.brq.challenges.mercadinho.usecase.exception.BadRequestPostException;
import br.com.brq.challenges.mercadinho.usecase.exception.ProdutoNaoEncontradoException;

import java.util.Objects;

public final class ProdutoUseCaseUtils {

    private static final String MENSAGEM_PRODUTO_NAO_EXISTE = "O produto de id %s informado, não existe.";
    private static final String MENSAGEM_NAO_EXISTE_CADASTRO_PRODUTO = "A categoria com o id %s informada, não existe para continuar com o cadastro do produto.";
    private static final String MENSAGEM_PRODUTO_NAO_PODE_CADASTRAR = "Um produto não pode ser cadastrado com a quantidade %s.";
    private static final String MENSAGEM_PRODUTO_OFERTADO_PORCENTAGEM = "O produto '%s' não pode ser ofertado com a porcentagem igual a 0.";
    private static final String MENSAGEM_PRODUTO_ATIVO_QUANTIDADE_ZERO = "O produto '%s' não pode ser ativado porque está com a quantidade 0";

    private ProdutoUseCaseUtils() {}

    public static void verificarSeProdutoExiste(Long idProduto, ProdutoDomainResponse produto) {

        if (Objects.isNull(produto.getId())) {
            throw new ProdutoNaoEncontradoException(
                    String.format(MENSAGEM_PRODUTO_NAO_EXISTE, idProduto));
        }
    }

    public static void verificarSeCategoriaExisteParaCadastroProduto(Long idCategoria,
                                                               CategoriaDomainResponse categoriaDomainResponse) {

        if (Objects.isNull(categoriaDomainResponse.getId())) {
            throw new BadRequestPostException(String.format(MENSAGEM_NAO_EXISTE_CADASTRO_PRODUTO, idCategoria));
        }
    }

    public static void verificarSeQuantidadeProdutoEstaZero(Integer quantidadeProduto) {

        if (verificarSeValorIgualAZero(quantidadeProduto)) {
            throw new BadBusyException(
                    String.format(MENSAGEM_PRODUTO_NAO_PODE_CADASTRAR, quantidadeProduto));
        }
    }

    public static void vetificarSePorcentagemOfertEstaDiferenteDeZeroParaAtivarOferta(Integer porcentagem, Boolean ofertado,
                                                                                ProdutoDomainResponse produtoAtual) {
        if (verificarSeValorIgualAZero(porcentagem)) {
            if (produtoEstaOferta(ofertado)) {
                throw new BadBusyException(String.format(MENSAGEM_PRODUTO_OFERTADO_PORCENTAGEM, produtoAtual.getNome()));
            }
        }
    }

    public static void verificarQuantidadeProdutoParaAtivar(ProdutoDomainResponse produtoAtual, Boolean produtoAtivo) {

        if (verificarSeValorIgualAZero(produtoAtual.getQuantidade())) {
            if (produtoEstaAtivo(produtoAtivo)) {
                throw new BadBusyException(
                        String.format(MENSAGEM_PRODUTO_ATIVO_QUANTIDADE_ZERO, produtoAtual.getNome()));
            }
        }
    }

    public static void verificarSeProdutoOfertaEProdutoPorcentagemNaoEstaNulo(ProdutoDomainResponse produtoOrigem,
                                                                        ProdutoDomainResponse produtoAtual) {

        if (Objects.nonNull(produtoOrigem.getOfertado()) && Objects.nonNull(produtoOrigem.getPorcentagem())) {
            vetificarSePorcentagemOfertEstaDiferenteDeZeroParaAtivarOferta(produtoOrigem.getPorcentagem(),
                    produtoOrigem.getOfertado(), produtoAtual);
        }
    }

    public static void verificarSeProdutoAtivoNaoEstaNuloParaVerificarQuantidade(ProdutoDomainResponse produtoOrigem,
                                                                           ProdutoDomainResponse produtoAtual) {

        if (Objects.nonNull(produtoOrigem.getAtivo())) {
            verificarQuantidadeProdutoParaAtivar(produtoAtual, produtoOrigem.getAtivo());
        }
    }

    private static boolean verificarSeValorIgualAZero(Integer valor) {
        return valor.equals(0);
    }

    private static boolean produtoEstaOferta(Boolean estaOfertado) {
        return estaOfertado;
    }

    private static boolean produtoEstaAtivo(Boolean estaAtivo) {
        return estaAtivo;
    }
}
