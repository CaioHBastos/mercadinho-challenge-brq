package br.com.brq.challenges.mercadinho.dataprovider.implementation;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.exception.OfertaProdutoException;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.ProdutoDataproviderMapper;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.ProdutoRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.Oferta;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.gateway.OfertaGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class OfertaImplementation implements OfertaGateway {

    private final ProdutoDataproviderMapper produtoDataproviderMapper =
            Mappers.getMapper(ProdutoDataproviderMapper.class);

    private final ProdutoRepository produtoRepository;

    public OfertaImplementation(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    @Override
    public void ofertarProdutos(List<Produto> produtos) {
        List<ProdutoEntity> produtosEntities = produtoDataproviderMapper.mapProdutosOferta(produtos);

        try {
            produtosEntities.forEach(produtoRepository::save);
        } catch (Exception exception) {
            throw new OfertaProdutoException("Erro ao adicionar a oferta nos produtos informado.", exception);
        }
    }

    @Override
    public List<Oferta> buscarProdutosEmOferta() {
        List<ProdutoEntity> produtosEntities = produtoRepository.findByOfertadoTrue();

        return produtoDataproviderMapper.mapOfertas(produtosEntities);
    }
}
