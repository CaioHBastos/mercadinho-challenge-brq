package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Oferta;

import java.util.List;

public interface OfertaUseCase {

    Boolean adicionarOfertas(List<Oferta> ofertas);

    Boolean removerOfertas(List<String> idsProduto);

    List<Oferta> buscarOfertas();
}
