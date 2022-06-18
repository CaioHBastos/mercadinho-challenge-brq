package br.com.brq.challenges.mercadinho.entrypoint.mapper;

import br.com.brq.challenges.mercadinho.entrypoint.model.request.ProdutoModelRequest;
import br.com.brq.challenges.mercadinho.entrypoint.model.response.ProdutoModelResponse;
import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DepartamentoEntrypointMapper.class)
public interface ProdutoEntrypointMapper {

    Produto map(ProdutoModelRequest produtoModelRequest);

    @Mapping(
            source = "produto.departamentos", target = "departamentos"
    )
    ProdutoModelResponse map(Produto produto);
}
