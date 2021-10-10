package br.com.brq.challenges.mercadinho.usecase;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoParametroModelRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.exception.BadBusyException;
import br.com.brq.challenges.mercadinho.usecase.exception.BadRequestPostException;
import br.com.brq.challenges.mercadinho.usecase.exception.ProdutoNaoEncontradoException;
import br.com.brq.challenges.mercadinho.usecase.exception.ProdutoSemConteudoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.CategoriaGateway;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;
    private final CategoriaGateway categoriaGateway;

    public ProdutoDomainResponse cadastrarProduto(ProdutoDomainRequest produtoDomainRequest) {
        Long idCategoria = produtoDomainRequest.getCategoria().getId();
        Integer quantidadeProduto = produtoDomainRequest.getQuantidade();

        CategoriaDomainResponse categoriaDomainResponse = categoriaGateway.buscarCategoriaPorId(idCategoria);

        verificarSeCategoriaExisteParaCadastroProduto(idCategoria, categoriaDomainResponse);
        verificarSeQuantidadeProdutoEstaZero(quantidadeProduto);

        return produtoGateway.cadastrarProduto(produtoDomainRequest);
    }

    public List<ProdutoDomainResponse> buscarTodosProdutos(ProdutoParametroModelRequest produtoParametroModelRequest) {

        if (StringUtils.isNotBlank(produtoParametroModelRequest.getNomeCategoria())) {
            List<ProdutoDomainResponse> produtosDomain =
                    produtoGateway.buscarProdutoPorCategoria(produtoParametroModelRequest.getNomeCategoria());

            verificarProdutosEstaVazio(produtosDomain);

            return produtosDomain;
        }

        if (StringUtils.isNotBlank(produtoParametroModelRequest.getMarca())) {
            List<ProdutoDomainResponse> produtosDomain =
                    produtoGateway.buscarProdutoPorMarca(produtoParametroModelRequest.getMarca());

            verificarProdutosEstaVazio(produtosDomain);

            return produtosDomain;
        }

        List<ProdutoDomainResponse> produtosDomain = produtoGateway.buscarTodosProdutos();

        verificarProdutosEstaVazio(produtosDomain);

        return produtosDomain;
    }

    public ProdutoDomainResponse buscarProdutoPorId(Long idProduto) {
        ProdutoDomainResponse produto = produtoGateway.buscarProdutoPorId(idProduto);

        verificarSeProdutoExiste(idProduto, produto);

        return produto;
    }

    public void removerProdutoPorId(Long idProduto) {
        buscarProdutoPorId(idProduto);
        produtoGateway.removerProdutoPorId(idProduto);
    }

    private void vetificarSePorcentagemOfertEstaDiferenteDeZeroParaAtivarOferta(Integer porcentagem, Boolean ofertado,
                                                                                ProdutoDomainResponse produtoAtual) {
        if (porcentagem.equals(0)) {
            if (ofertado) {
                throw new BadBusyException(String.format("O produto '%s' não pode ser ofertado com " +
                        "a porcentagem igual a 0.", produtoAtual.getNome()));
            }
        }
    }

    public ProdutoDomainResponse atualizarParcialmenteProduto(Long idProduto, Map<String, Object> novosCampoProduto) {
        ProdutoDomainResponse produtoAtual = buscarProdutoPorId(idProduto);
        atualizarCamposProduto(novosCampoProduto, produtoAtual);

        return produtoGateway.atualizarParcialmenteOsDadosProduto(produtoAtual);
    }

    private void atualizarCamposProduto(Map<String, Object> novosCampoProduto, ProdutoDomainResponse produtoAtual) {
        ObjectMapper mapper = new ObjectMapper();

        ProdutoDomainResponse produtoOrigem =  mapper.convertValue(novosCampoProduto, ProdutoDomainResponse.class);

        if (Objects.nonNull(produtoOrigem.getCategoria())) {
            if (Objects.nonNull(produtoOrigem.getCategoria().getId())) {
                CategoriaDomainResponse categoriaDomainResponse =
                        categoriaGateway.buscarCategoriaPorId(produtoOrigem.getCategoria().getId());

                verificarSeCategoriaExisteParaCadastroProduto(produtoOrigem.getCategoria().getId(), categoriaDomainResponse);
            }
        }

        if (Objects.nonNull(produtoOrigem.getAtivo())) {
            verificarQuantidadeProdutoParaAtivar(produtoAtual, produtoOrigem.getAtivo());
        }

        if (Objects.nonNull(produtoOrigem.getOfertado()) && Objects.nonNull(produtoOrigem.getPorcentagem())) {
            vetificarSePorcentagemOfertEstaDiferenteDeZeroParaAtivarOferta(produtoOrigem.getPorcentagem(),
                    produtoOrigem.getOfertado(), produtoAtual);
        }

        novosCampoProduto.forEach((nomePropriedade, valorPropriedade) -> {
            Field campo = ReflectionUtils.findField(ProdutoDomainResponse.class, nomePropriedade);
            campo.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(campo, produtoOrigem);

            ReflectionUtils.setField(campo, produtoAtual, novoValor);
        });
    }

    private void verificarProdutosEstaVazio(List<ProdutoDomainResponse> produtosDomain) {
        if (produtosDomain.isEmpty()) {
            throw new ProdutoSemConteudoException();
        }
    }

    private void verificarSeProdutoExiste(Long idProduto, ProdutoDomainResponse produto) {
        if (Objects.isNull(produto.getId())) {
            throw new ProdutoNaoEncontradoException(
                    String.format("O produto de id %s informado, não existe.", idProduto));
        }
    }

    private void verificarSeCategoriaExisteParaCadastroProduto(Long idCategoria,
                                                               CategoriaDomainResponse categoriaDomainResponse) {
        if (Objects.isNull(categoriaDomainResponse.getId())) {
            throw new BadRequestPostException(String.format("A categoria com o id %s informada, " +
                    "não existe para continuar com o cadastro do produto.", idCategoria));
        }
    }

    private void verificarSeQuantidadeProdutoEstaZero(Integer quantidadeProduto) {
        if (quantidadeProduto.equals(0)) {
            throw new BadBusyException(
                    String.format("Um produto não pode ser cadastrado com a quantidade %s.", quantidadeProduto));
        }
    }

    private void verificarQuantidadeProdutoParaAtivar(ProdutoDomainResponse produtoAtual, Boolean produtoAtivo) {
        if (produtoAtual.getQuantidade().equals(0)) {
            if (produtoAtivo) {
                throw new BadBusyException(
                        String.format("O produto '%s' não pode ser ativado porque está com a quantidade 0",
                                produtoAtual.getNome()));
            }
        }
    }
}