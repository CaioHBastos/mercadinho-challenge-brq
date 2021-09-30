package br.com.brq.challenges.mercadinho.dataprovider.repository;

import br.com.brq.challenges.mercadinho.dataprovider.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
