package br.com.brq.challenges.mercadinho.usecase.domain.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProdutoDomainRequest {

    private String nome;
    private String descricao;
    private String marca;
    private Integer quantidade;
    private BigDecimal preco;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagemOferta;
    private CategoriaDomainRequest categoria;
}
