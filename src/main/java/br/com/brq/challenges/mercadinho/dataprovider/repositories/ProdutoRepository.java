package br.com.brq.challenges.mercadinho.dataprovider.repositories;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    Page<ProdutoEntity> findByCategoriaNome(Pageable pageable, String nomeCategoria);

    Page<ProdutoEntity> findByMarcaContaining(Pageable pageable, String marca);
}
