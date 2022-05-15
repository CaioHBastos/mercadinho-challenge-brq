package br.com.brq.challenges.mercadinho.usecase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class Produto {

    private final Long id;
    private final String nome;
    private final String descricao;
    private final String marca;
    private final Double preco;

    @Setter
    private Boolean ativo;

    @Setter
    private Boolean ofertado;

    @Setter
    private Integer porcentagemOferta;

    private List<Departamento> departamentos;
}
