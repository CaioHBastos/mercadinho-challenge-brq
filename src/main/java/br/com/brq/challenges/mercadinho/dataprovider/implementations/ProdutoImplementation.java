package br.com.brq.challenges.mercadinho.dataprovider.implementations;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.mappers.request.ProdutoMapperDataproviderRequest;
import br.com.brq.challenges.mercadinho.dataprovider.mappers.response.ProdutoMapperResponse;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.ProdutoRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe responsável por implementar o contrato com os métodos da produto e com isso
 * a classe de implementação <b>ProdutoImplementation</b> irá implementar os métodos
 * e escrever quais os serviços que serão feitos na base de dados.
 *
 * @author Caio Henrique Bastos
 * @since 01/10/2021
 */
@AllArgsConstructor
@Component
public class ProdutoImplementation implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Transactional
    @Override
    public ProdutoDomainResponse cadastrarProduto(ProdutoDomainRequest produtoDomainRequest) {
        ProdutoEntity novoProduto = ProdutoMapperDataproviderRequest.toEntity(produtoDomainRequest);
        ProdutoEntity produtoSalvo = produtoRepository.save(novoProduto);

        return ProdutoMapperResponse.toDomain(produtoSalvo);
    }

    @Override
    public List<ProdutoDomainResponse> buscarTodosProdutos() {
        List<ProdutoEntity> produtosEntity = produtoRepository.findAll();
        return ProdutoMapperResponse.toCollectionDomain(produtosEntity);
    }

    @Override
    public ProdutoDomainResponse buscarProdutoPorId(Long idProduto) {
        ProdutoEntity produtoEntity = produtoRepository.findById(idProduto)
                .orElse(ProdutoEntity.builder().build());;
        return ProdutoMapperResponse.toDomain(produtoEntity);
    }

    @Transactional
    @Override
    public void removerProdutoPorId(Long idProduto) {
        produtoRepository.deleteById(idProduto);
        produtoRepository.flush();
    }

    @Override
    public ProdutoDomainResponse atualizarParcialmenteOsDadosProduto(ProdutoDomainResponse produtoAtual) {
        ProdutoEntity novoProdutoAtualizado = ProdutoMapperDataproviderRequest.toEntityAtualizado(produtoAtual);
        ProdutoEntity produtoAtualizadoSalvo = produtoRepository.save(novoProdutoAtualizado);

        return ProdutoMapperResponse.toDomain(produtoAtualizadoSalvo);
    }

    @Override
    public List<ProdutoDomainResponse> buscarProdutoPorCategoria(String nomeCategoria) {
        List<ProdutoEntity> produtosPorCategoria = produtoRepository.findByCategoriaNome(nomeCategoria);

        return ProdutoMapperResponse.toCollectionDomain(produtosPorCategoria);
    }

    @Override
    public List<ProdutoDomainResponse> buscarProdutoPorMarca(String marca) {
        List<ProdutoEntity> produtosPorMarca = produtoRepository.findByMarcaContaining(marca);

        return ProdutoMapperResponse.toCollectionDomain(produtosPorMarca);
    }
}
