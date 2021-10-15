package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoModelResquest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
    private String marca;

    @NotNull
    private Integer quantidade;

    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @NotNull
    @Valid
    private CategoriaIdModelRequest categoria;

    @Valid
    private TabelaNutricionalModelRequest tabelaNutricional;
}
