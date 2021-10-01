package br.com.brq.challenges.mercadinho.usecase.domain.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDomainResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String marca;
    private Integer quantidade;
    private BigDecimal preco;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagemOferta;
}
