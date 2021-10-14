package br.com.brq.challenges.mercadinho.dataprovider.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TabelaNutricionalEntity {

    @Column(name = "tabela_nutricional_valor_energetico")
    private String valorEnergetico;

    @Column(name = "tabela_nutricional_valor_gordura_saturada")
    private String valorGorduraSaturada;

    @Column(name = "tabela_nutricional_sodio")
    private String sodio;

    @Column(name = "tabela_nutricional_acucar")
    private String acucar;

    @Column(name = "tabela_nutricional_proteinas")
    private String proteinas;

    @Column(name = "tabela_nutricional_fibras")
    private String fibras;
}
