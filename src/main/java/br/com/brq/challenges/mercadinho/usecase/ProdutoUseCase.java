package br.com.brq.challenges.mercadinho.usecase;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoParametroModelRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.CategoriaGateway;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

import static br.com.brq.challenges.mercadinho.usecase.utils.ProdutoUseCaseUtils.*;

@AllArgsConstructor
@Service
public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;
    private final CategoriaGateway categoriaGateway;

    //TODO REMOVER OS IFS EM OUTRO MOMENTO
    public Page<ProdutoDomainResponse> buscarTodosProdutos(Pageable pageable, ProdutoParametroModelRequest produtoParametroModelRequest) {

        if (StringUtils.isNotBlank(produtoParametroModelRequest.getNomeCategoria())) {
            return produtoGateway.buscarProdutoPorCategoria(pageable, produtoParametroModelRequest.getNomeCategoria());
        }

        if (StringUtils.isNotBlank(produtoParametroModelRequest.getMarca())) {
            return produtoGateway.buscarProdutoPorMarca(pageable, produtoParametroModelRequest.getMarca());
        }

        return produtoGateway.buscarTodosProdutos(pageable);
    }

    /**
     * Método responsável pela busca de um produto através do seu ID na base de dados.
     * A comunicação é feita pelo gateway que se comunica com o provider de dados.
     * Após a busca é feita uma verificação para saber se o recurso existe ou não.
     *
     * @param idProduto {@code Long}
     *      - Id do recurso do produto para a busca dos dados.
     * @param expand {@code String}
     *      - Expand para definir se retorna o payload da tabela nutricional. Se for informado,
     *      ira retornar as informaçoes..
     *
     * @param expand
     * @return {@code ProdutoDomainResponse}
     *      - Retorna os dados de informação do dominio do produto após a busca.
     */
    public ProdutoDomainResponse buscarProdutoPorId(Long idProduto, String expand) {

        ProdutoDomainResponse produto = produtoGateway.buscarProdutoPorId(idProduto);
        verificarSeProdutoExiste(idProduto, produto);

        return produto;
    }

    public ProdutoDomainResponse cadastrarProduto(ProdutoDomainRequest produtoDomainRequest) {
        Long idCategoria = produtoDomainRequest.getCategoria().getId();
        Integer quantidadeProduto = produtoDomainRequest.getQuantidade();

        CategoriaDomainResponse categoriaDomainResponse = categoriaGateway.buscarCategoriaPorId(idCategoria);
        verificarSeCategoriaExisteParaCadastroProduto(idCategoria, categoriaDomainResponse);

        verificarSeQuantidadeProdutoEstaZero(quantidadeProduto);

        return produtoGateway.cadastrarProduto(produtoDomainRequest);
    }

    public void removerProdutoPorId(Long idProduto) {
        buscarProdutoPorId(idProduto, null);
        produtoGateway.removerProdutoPorId(idProduto);
    }

    public ProdutoDomainResponse atualizarParcialmenteProduto(Long idProduto, Map<String, Object> novosCampoProduto) {
        ProdutoDomainResponse produtoAtual = buscarProdutoPorId(idProduto, null);
        atualizarCamposProduto(novosCampoProduto, produtoAtual);

        return produtoGateway.atualizarParcialmenteOsDadosProduto(produtoAtual);
    }

    private void atualizarCamposProduto(Map<String, Object> novosCampoProduto, ProdutoDomainResponse produtoAtual) {
        ObjectMapper mapper = new ObjectMapper();

        ProdutoDomainResponse produtoOrigem =  mapper.convertValue(novosCampoProduto, ProdutoDomainResponse.class);

        verificarSeCategoriaIdEstaNuloParaBuscarCategoria(produtoOrigem);
        verificarSeProdutoAtivoNaoEstaNuloParaVerificarQuantidade(produtoOrigem, produtoAtual);
        vetificarSeProdutoEstaAtivoEPodeSerOfertado(produtoOrigem, produtoAtual);
        verificarSeProdutoOfertaEProdutoPorcentagemNaoEstaNulo(produtoOrigem, produtoAtual);

        novosCampoProduto.forEach((nomePropriedade, valorPropriedade) -> {
            Field campo = ReflectionUtils.findField(ProdutoDomainResponse.class, nomePropriedade);
            campo.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(campo, produtoOrigem);

            ReflectionUtils.setField(campo, produtoAtual, novoValor);
        });
    }

    private void verificarSeCategoriaIdEstaNuloParaBuscarCategoria(ProdutoDomainResponse produtoOrigem) {

        if (Objects.nonNull(produtoOrigem.getCategoria())) {
            if (Objects.nonNull(produtoOrigem.getCategoria().getId())) {
                CategoriaDomainResponse categoriaDomainResponse =
                        categoriaGateway.buscarCategoriaPorId(produtoOrigem.getCategoria().getId());

                verificarSeCategoriaExisteParaCadastroProduto(produtoOrigem.getCategoria().getId(), categoriaDomainResponse);
            }
        }
    }
}
