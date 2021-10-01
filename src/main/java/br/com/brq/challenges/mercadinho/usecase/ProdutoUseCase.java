package br.com.brq.challenges.mercadinho.usecase;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoParametroModelRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoDomainResponse cadastrarProduto(ProdutoDomainRequest produtoDomainRequest) {
        return produtoGateway.cadastrarProduto(produtoDomainRequest);
    }

    public List<ProdutoDomainResponse> buscarTodosProdutos(ProdutoParametroModelRequest produtoParametroModelRequest) {

        if (StringUtils.isNotBlank(produtoParametroModelRequest.getNomeCategoria())) {
            return produtoGateway.buscaProdutoPorCategoria(produtoParametroModelRequest.getNomeCategoria());
        }

        return produtoGateway.buscarTodosProdutos();
    }

    public ProdutoDomainResponse buscarProdutoPorId(Long idProduto) {
        return produtoGateway.buscarProdutoPorId(idProduto);
    }

    public void removerProdutoPorId(Long idProduto) {
        produtoGateway.removerProdutoPorId(idProduto);
    }

    public ProdutoDomainResponse atualizarTodosOsDadosProduto(Long idProduto, ProdutoDomainRequest novoProdutoDomain) {
        ProdutoDomainResponse produtoAtual = buscarProdutoPorId(idProduto);

        CategoriaDomainResponse categoriaAtual = CategoriaDomainResponse.builder()
                .id(novoProdutoDomain.getCategoria().getId())
                .build();

        produtoAtual = ProdutoDomainResponse.builder()
                .id(produtoAtual.getId())
                .nome(novoProdutoDomain.getNome())
                .descricao(novoProdutoDomain.getDescricao())
                .marca(novoProdutoDomain.getMarca())
                .quantidade(novoProdutoDomain.getQuantidade())
                .preco(novoProdutoDomain.getPreco())
                .ativo(novoProdutoDomain.getAtivo())
                .ofertado(novoProdutoDomain.getOfertado())
                .porcentagemOferta(novoProdutoDomain.getPorcentagemOferta())
                .categoria(categoriaAtual)
                .build();

        return produtoGateway.atualizarTodosOsDadosProduto(produtoAtual);
    }

    public ProdutoDomainResponse atualizarParcialmenteProduto(Long idProduto, Map<String, Object> novosCampoProduto) {
        ProdutoDomainResponse produtoAtual = buscarProdutoPorId(idProduto);
        atualizarCamposProduto(novosCampoProduto, produtoAtual);

        return produtoGateway.atualizarParcialmenteOsDadosProduto(produtoAtual);
    }

    private void atualizarCamposProduto(Map<String, Object> novosCampoProduto, ProdutoDomainResponse produtoAtual) {
        ObjectMapper mapper = new ObjectMapper();

        ProdutoDomainResponse produtoOrigem =  mapper.convertValue(novosCampoProduto, ProdutoDomainResponse.class);

        novosCampoProduto.forEach((nomePropriedade, valorPropriedade) -> {
            Field campo = ReflectionUtils.findField(ProdutoDomainResponse.class, nomePropriedade);
            campo.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(campo, produtoOrigem);

            ReflectionUtils.setField(campo, produtoAtual, novoValor);
        });
    }
}
