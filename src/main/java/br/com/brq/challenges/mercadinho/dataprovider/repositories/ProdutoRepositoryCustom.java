package br.com.brq.challenges.mercadinho.dataprovider.repositories;

import br.com.brq.challenges.mercadinho.dataprovider.dto.ProdutoDtoRequest;
import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;

import java.util.List;

public interface ProdutoRepositoryCustom {

    List<ProdutoEntity> filterBy(ProdutoDtoRequest produtoDtoRequest);
}
