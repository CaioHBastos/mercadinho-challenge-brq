package br.com.brq.challenges.mercadinho.dataprovider.repositories.filtros;

import br.com.brq.challenges.mercadinho.dataprovider.dto.ProdutoDtoRequest;
import br.com.brq.challenges.mercadinho.dataprovider.entities.DepartamentoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.ProdutoRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProdutoEntity> filterBy(ProdutoDtoRequest produtoDtoRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutoEntity> criteria = builder.createQuery(ProdutoEntity.class);
        Root<ProdutoEntity> root = criteria.from(ProdutoEntity.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.isNotBlank(produtoDtoRequest.getNome())) {
            predicates.add(builder.like(root.get("nomeProduto"), "%" + produtoDtoRequest.getNome() + "%"));
        }

        if (StringUtils.isNotBlank(produtoDtoRequest.getMarca())) {
            predicates.add(builder.equal(root.get("marcaProduto"), produtoDtoRequest.getMarca().toLowerCase(Locale.ROOT)));
        }

        if (Objects.nonNull(produtoDtoRequest.getPreco())) {
            predicates.add(builder.lessThanOrEqualTo(root.get("preco"), produtoDtoRequest.getPreco()));
        }

        if (Objects.nonNull(produtoDtoRequest.getAtivo())) {
            predicates.add(builder.equal(root.get("ativo"), produtoDtoRequest.getAtivo()));
        }

        if (Objects.nonNull(produtoDtoRequest.getIdDepartamento())) {
            Join<ProdutoEntity, DepartamentoEntity> join = root.join("departamentos", JoinType.INNER);
            predicates.add(builder.equal(join.get("id"), produtoDtoRequest.getIdDepartamento()));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<ProdutoEntity> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }
}
