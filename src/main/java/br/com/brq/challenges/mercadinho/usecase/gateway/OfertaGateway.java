package br.com.brq.challenges.mercadinho.usecase.gateway;

import br.com.brq.challenges.mercadinho.usecase.domain.Oferta;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;

import java.util.List;

public interface OfertaGateway {

    void ofertarProdutos(List<Produto> ofertas);

    List<Oferta> buscarProdutosEmOferta();
}
