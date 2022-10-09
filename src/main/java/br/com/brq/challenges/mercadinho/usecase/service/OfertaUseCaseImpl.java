package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Oferta;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.NenhumConteudoEncontradoException;
import br.com.brq.challenges.mercadinho.usecase.exception.RecursoNaoEncontradoException;
import br.com.brq.challenges.mercadinho.usecase.exception.RegraOfertaException;
import br.com.brq.challenges.mercadinho.usecase.exception.RegraProdutoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.OfertaGateway;
import br.com.brq.challenges.mercadinho.usecase.service.utils.MercadinhoServiceUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfertaUseCaseImpl implements OfertaUseCase {

    private final OfertaGateway ofertaGateway;
    private final ProdutoUseCase produtoUseCase;

    public OfertaUseCaseImpl(OfertaGateway ofertaGateway, ProdutoUseCase produtoUseCase) {
        this.ofertaGateway = ofertaGateway;
        this.produtoUseCase = produtoUseCase;
    }

    @Override
    public Boolean adicionarOfertas(List<Oferta> ofertas) {
        List<Produto> produtos = new ArrayList<>();

        ofertas.forEach(oferta -> {
            Produto produto = produtoUseCase.detalharProduto(oferta.getIdProduto());

            if (oferta.getPorcentagemOferta() <= 0) {
                throw new RegraProdutoException("A porcentagem da oferta está inválida, tem que ser maior que 0.");
            }

            if (!produto.getAtivo()) {
                throw new RegraProdutoException("O produto não pode ser ofertado porque está inativo.");
            }

            produto.setOfertado(true);
            produto.setPorcentagemOferta(oferta.getPorcentagemOferta());
            produto.setDataAtualizacao(MercadinhoServiceUtils.gerarData());

            produtos.add(produto);
        });

        ofertaGateway.ofertarProdutos(produtos);
        return true;
    }

    @Override
    public Boolean removerOfertas(List<String> idsProduto) {
        List<Produto> produtos = new ArrayList<>();

        try {
            idsProduto.forEach(idProduto -> {
                Produto produto = produtoUseCase.detalharProduto(idProduto);

                produto.setOfertado(false);
                produto.setPorcentagemOferta(0);
                produto.setDataAtualizacao(MercadinhoServiceUtils.gerarData());

                produtos.add(produto);
            });
        } catch (RecursoNaoEncontradoException exception) {
            throw new RegraOfertaException(exception.getMessage());
        }

        ofertaGateway.ofertarProdutos(produtos);
        return true;
    }

    @Override
    public List<Oferta> buscarOfertas() {
        List<Oferta> ofertas = ofertaGateway.buscarProdutosEmOferta();

        if (ofertas.isEmpty()) {
            throw new NenhumConteudoEncontradoException("Não existem ofertas cadastradas.");
        }

        return ofertas;
    }
}
