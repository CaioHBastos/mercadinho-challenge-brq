package br.com.brq.challenges.mercadinho.dataprovider.implementation;

import br.com.brq.challenges.mercadinho.dataprovider.entities.CategoriaEntity;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.request.CategoriaMapperRequest;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.response.CategoriaMapperResponse;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.CategoriaRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.CategoriaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Component
public class CategoriaImplementation implements CategoriaGateway {

    private final CategoriaRepository categoriaRepository;

    @Transactional
    @Override
    public CategoriaDomainResponse cadastrarCategoria(CategoriaDomainRequest categoriaDomainRequest) {
        CategoriaEntity novaCategoria = CategoriaMapperRequest.toEntity(categoriaDomainRequest);
        CategoriaEntity categoriaSalva = categoriaRepository.save(novaCategoria);

        return CategoriaMapperResponse.toDomain(categoriaSalva);
    }

    @Override
    public List<CategoriaDomainResponse> buscarCategorias() {
        List<CategoriaEntity> categoriasEntity = categoriaRepository.findAll();

        return CategoriaMapperResponse.toCollecationDomain(categoriasEntity);
    }

    @Override
    public CategoriaDomainResponse buscarCategoriaPorId(Long idCategoria) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(idCategoria)
                .orElse(CategoriaEntity.builder().build());

        return CategoriaMapperResponse.toDomain(categoriaEntity);
    }

    @Transactional
    @Override
    public void removerCategoriaPorId(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }

    @Transactional
    @Override
    public CategoriaDomainResponse atualizarCategoria(CategoriaDomainResponse categoriaAtual) {
        CategoriaEntity novaCategoria = CategoriaMapperRequest.toEntityUpdate(categoriaAtual);
        CategoriaEntity categoriaSalva = categoriaRepository.save(novaCategoria);

        return CategoriaMapperResponse.toDomain(categoriaSalva);
    }

    @Override
    public CategoriaDomainResponse buscarCategoriaPorNome(String nome) {
        CategoriaEntity categoriaExistente = categoriaRepository.findByNome(nome);

        return CategoriaMapperResponse.toDomain(categoriaExistente);
    }
}
