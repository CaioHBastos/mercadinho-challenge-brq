package br.com.brq.challenges.mercadinho.mock;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import lombok.experimental.UtilityClass;

import java.util.Collections;

@UtilityClass
public class ProdutoEntityMock {


    public static ProdutoEntity mockProdutoEntity() {
        return ProdutoEntity.builder()
                .id(1L)
                .nome("Refrigerante Coca Cola 2L")
                .descricao("Refrigerante Coca Cola 2L retornável")
                .marca("Coca Cola")
                .preco(5.99)
                .ativo(true)
                .ofertado(false)
                .porcentagemOferta(0)
                .departamentos(Collections.emptyList())
                .build();
    }
}
