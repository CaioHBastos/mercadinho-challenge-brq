package br.com.brq.challenges.mercadinho.dataprovider.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe responsável por ser a entidade de produto para carregar os dados
 * para a tabela de <b>produto</b> na base de dados do mercadinho.
 *
 * @author Caio Henrique Bastos
 * @since 01/10/2021
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = false)
    private Boolean ofertado;

    @Column(nullable = false)
    private Integer porcentagemOfertado;

    @ManyToOne(targetEntity=CategoriaEntity.class)
    @JoinColumn(nullable = false)
    private CategoriaEntity categoria;

    @Embedded
    private TabelaNutricionalEntity tabelaNutricional;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProdutoEntity that = (ProdutoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
