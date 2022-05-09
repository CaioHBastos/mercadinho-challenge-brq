package br.com.brq.challenges.mercadinho.dataprovider.implementation;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.request.ProdutoDataproviderMapperRequest;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.response.ProdutoDataproviderMapperResponse;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.ProdutoRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroProdutoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoImplementation implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoImplementation(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto criarProduto(Produto produto) throws CadastroProdutoException {
        try {
            ProdutoEntity produtoEntity = ProdutoDataproviderMapperRequest.convert(produto);
            ProdutoEntity produtoEntityCriado = produtoRepository.save(produtoEntity);

            return ProdutoDataproviderMapperResponse.convert(produtoEntityCriado);
        } catch (Exception exception) {
            throw new CadastroProdutoException("Erro ao realizar o cadastro do prduto", exception);
        }
    }

    @Override
    public Optional<Produto> buscarProdutoPorNome(String nomeProduto) {
        return produtoRepository.findByNome(nomeProduto)
                .map(ProdutoDataproviderMapperResponse::convert);
    }
}
