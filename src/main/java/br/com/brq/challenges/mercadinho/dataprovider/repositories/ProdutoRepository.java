package br.com.brq.challenges.mercadinho.dataprovider.repositories;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findByNomeProduto(String nome);
}
