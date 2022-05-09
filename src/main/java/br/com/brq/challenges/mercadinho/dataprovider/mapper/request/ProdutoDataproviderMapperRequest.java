package br.com.brq.challenges.mercadinho.dataprovider.mapper.request;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProdutoDataproviderMapperRequest {

    public static ProdutoEntity convert(Produto produto) {
        return ProdutoEntity.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .ativo(produto.getAtivo())
                .porcentagemOferta(produto.getPorcentagemOferta())
                .build();
    }
}
