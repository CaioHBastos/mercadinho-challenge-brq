package br.com.brq.challenges.mercadinho.dataprovider.implementation;

import br.com.brq.challenges.mercadinho.dataprovider.dto.ProdutoDtoRequest;
import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.ProdutoDataproviderMapper;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.ProdutoRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroProdutoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class ProdutoImplementation implements ProdutoGateway {

    private final ProdutoDataproviderMapper produtoDataproviderMapper =
            Mappers.getMapper(ProdutoDataproviderMapper.class);

    private final ProdutoRepository produtoRepository;

    public ProdutoImplementation(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    @Override
    public Produto criarProduto(Produto produto) throws CadastroProdutoException {
        try {
            ProdutoEntity produtoEntity = produtoDataproviderMapper.map(produto);
            ProdutoEntity produtoEntityCriado = produtoRepository.save(produtoEntity);

            return produtoDataproviderMapper.map(produtoEntityCriado);
        } catch (Exception exception) {
            throw new CadastroProdutoException("Erro ao realizar o cadastro do prduto", exception);
        }
    }

    @Override
    public Optional<Produto> buscarProdutoPorNome(String nomeProduto) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findByNomeProduto(nomeProduto);

        if (produtoEntity.isPresent()) {
            return produtoDataproviderMapper.wrapOptionalMap(produtoEntity.get());
        }

        return Optional.empty();
    }

    @Override
    public List<Produto> buscarTodosProdutos(Produto produto) {
        ProdutoDtoRequest produtoDtoRequest = produtoDataproviderMapper.mapDto(produto);
        List<ProdutoEntity> produtosEntity = produtoRepository.filterBy(produtoDtoRequest);
        return produtoDataproviderMapper.map(produtosEntity);
    }

    @Override
    public Optional<Produto> detalharProdutoPorId(String idProduto) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(idProduto);

        if (produtoEntity.isPresent()) {
            return produtoDataproviderMapper.wrapOptionalMap(produtoEntity.get());
        }

        return Optional.empty();
    }

    @Transactional
    @Override
    public void removerProdutoPorId(String idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Override
    public Produto atualizarProduto(Produto produtoAtual) {
        ProdutoEntity produtoEntity = produtoDataproviderMapper.map(produtoAtual);
        ProdutoEntity produtoEntityCriado = produtoRepository.save(produtoEntity);

        return produtoDataproviderMapper.map(produtoEntityCriado);
    }

    @Override
    public void atualizarAtivacaoProdutos(List<Produto> produtosAtualizado) {
        produtosAtualizado.forEach(produto -> {
            ProdutoEntity produtoEntity = produtoDataproviderMapper.map(produto);
            produtoRepository.save(produtoEntity);
        });
    }
}
