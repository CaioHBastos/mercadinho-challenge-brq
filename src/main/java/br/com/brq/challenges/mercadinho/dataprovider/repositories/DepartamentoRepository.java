package br.com.brq.challenges.mercadinho.dataprovider.repositories;

import br.com.brq.challenges.mercadinho.dataprovider.entities.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Long> {

    Optional<DepartamentoEntity> findByNome(String nomeDepartamento);

    @Query("FROM DepartamentoEntity WHERE (:nome is null or nome like %:nome%)")
    List<DepartamentoEntity> findAllWithFilter(@Param("nome") String nomeDepartamento);
}
