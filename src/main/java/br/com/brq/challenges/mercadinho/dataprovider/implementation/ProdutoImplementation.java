package br.com.brq.challenges.mercadinho.dataprovider.implementation;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroProdutoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoImplementation implements ProdutoGateway {

    @Override
    public Produto criarProduto(Produto produto) throws CadastroProdutoException {
        return null;
    }

    @Override
    public Optional<Produto> buscarProdutoPorNome(String nomeProduto) {
        return Optional.empty();
    }
}
