package br.com.brq.challenges.mercadinho.dataprovider.implementation;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.request.ProdutoMapperRequest;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.response.ProdutoMapperResponse;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.ProdutoRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.request.ProdutoDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.ProdutoDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ProdutoImplementation implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Transactional
    @Override
    public ProdutoDomainResponse cadastrarProduto(ProdutoDomainRequest produtoDomainRequest) {
        ProdutoEntity novoProduto = ProdutoMapperRequest.toEntity(produtoDomainRequest);
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
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(idProduto);
        return ProdutoMapperResponse.toDomain(produtoEntity.get());
    }

    @Transactional
    @Override
    public void removerProdutoPorId(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Transactional
    @Override
    public ProdutoDomainResponse atualizarTodosOsDadosProduto(ProdutoDomainResponse produtoAtual) {
        ProdutoEntity novoProdutoAtualizado = ProdutoMapperRequest.toEntityAtualizado(produtoAtual);
        ProdutoEntity produtoAtualizadoSalvo = produtoRepository.save(novoProdutoAtualizado);

        return ProdutoMapperResponse.toDomain(produtoAtualizadoSalvo);
    }

    @Override
    public ProdutoDomainResponse atualizarParcialmenteOsDadosProduto(ProdutoDomainResponse produtoAtual) {
        ProdutoEntity novoProdutoAtualizado = ProdutoMapperRequest.toEntityAtualizado(produtoAtual);
        ProdutoEntity produtoAtualizadoSalvo = produtoRepository.save(novoProdutoAtualizado);

        return ProdutoMapperResponse.toDomain(produtoAtualizadoSalvo);
    }
}
