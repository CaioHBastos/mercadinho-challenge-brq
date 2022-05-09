package br.com.brq.challenges.mercadinho.dataprovider.mapper.response;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProdutoDataproviderMapperResponse {

    public static Produto convert(ProdutoEntity produtoEntity) {
        return Produto.builder()
                .id(produtoEntity.getId())
                .nome(produtoEntity.getNome())
                .descricao(produtoEntity.getDescricao())
                .marca(produtoEntity.getMarca())
                .preco(produtoEntity.getPreco())
                .ativo(produtoEntity.getAtivo())
                .porcentagemOferta(produtoEntity.getPorcentagemOferta())
                .departamentos(DepartamentoDataproviderMapperResponse.convert(produtoEntity.getDepartamento()))
                .build();
    }
}
