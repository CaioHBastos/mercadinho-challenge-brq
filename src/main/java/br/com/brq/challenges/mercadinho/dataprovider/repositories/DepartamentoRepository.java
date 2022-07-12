package br.com.brq.challenges.mercadinho.dataprovider.repositories;

import br.com.brq.challenges.mercadinho.dataprovider.entities.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Long> {

    Optional<DepartamentoEntity> findByNome(String nomeDepartamento);
}
