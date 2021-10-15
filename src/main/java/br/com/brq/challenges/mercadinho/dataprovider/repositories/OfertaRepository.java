package br.com.brq.challenges.mercadinho.dataprovider.repositories;

import br.com.brq.challenges.mercadinho.dataprovider.entities.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query("select produto from ProdutoEntity produto where produto.ofertado = 1")
    Page<ProdutoEntity> buscarProdutosEmOferta(Pageable pageable);
}
