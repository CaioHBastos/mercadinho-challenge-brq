package br.com.brq.challenges.mercadinho.dataprovider.mapper;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.usecase.domain.Oferta;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;

@Mapper(uses = DepartamentoDataproviderMapper.class)
public interface ProdutoDataproviderMapper {

    @Mappings({
        @Mapping(source = "produto.id", target = "idProduto"),
        @Mapping(source = "produto.nome", target = "nomeProduto"),
        @Mapping(source = "produto.descricao", target = "descricaoProduto"),
        @Mapping(source = "produto.marca", target = "marcaProduto")
    })
    ProdutoEntity map(Produto produto);

    @Mappings({
        @Mapping(source = "produtoEntity.idProduto", target = "id"),
        @Mapping(source = "produtoEntity.nomeProduto", target = "nome"),
        @Mapping(source = "produtoEntity.descricaoProduto", target = "descricao"),
        @Mapping(source = "produtoEntity.marcaProduto", target = "marca"),
        @Mapping(source = "produtoEntity.departamentos", target = "departamentos")
    })
    Produto map(ProdutoEntity produtoEntity);

    @Mappings({
            @Mapping(source = "produtoEntity.idProduto", target = "id"),
            @Mapping(source = "produtoEntity.nomeProduto", target = "nome"),
            @Mapping(source = "produtoEntity.descricaoProduto", target = "descricao"),
            @Mapping(source = "produtoEntity.marcaProduto", target = "marca"),
            @Mapping(source = "produtoEntity.departamentos", target = "departamentos")
    })
    List<Produto> map(List<ProdutoEntity> produtosEntity);

    default Optional<Produto> wrapOptionalMap(ProdutoEntity objeto) {
        return Optional.of(map(objeto));
    }

    @Mappings({
            @Mapping(source = "id", target = "produtoEntity.idProduto"),
            @Mapping(source = "nome", target = "produtoEntity.nomeProduto"),
            @Mapping(source = "descricao", target = "produtoEntity.descricaoProduto"),
            @Mapping(source = "marca", target = "produtoEntity.marcaProduto"),
            @Mapping(source = "departamentos", target = "produtoEntity.departamentos")
    })
    List<ProdutoEntity> mapProdutosOferta(List<Produto> produtos);

    List<Oferta> mapOfertas(List<ProdutoEntity> produtoEntities);
}
