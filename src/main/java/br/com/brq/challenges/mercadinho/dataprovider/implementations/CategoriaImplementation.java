package br.com.brq.challenges.mercadinho.dataprovider.implementations;

import br.com.brq.challenges.mercadinho.dataprovider.entities.CategoriaEntity;
import br.com.brq.challenges.mercadinho.dataprovider.mappers.request.CategoriaMapperDataproviderRequest;
import br.com.brq.challenges.mercadinho.dataprovider.mappers.response.CategoriaMapperResponse;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.CategoriaRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.gateway.CategoriaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe responsável por implementar o contrato com os métodos da categoria e com isso
 * a classe de implementação <b>CategoriaImplementation</b> irá implementar os métodos
 * e escrever quais os serviçoc que serão feitos na base de dados.
 *
 * @author Caio Henrique Bastos
 * @since 01/10/2021
 */
@AllArgsConstructor
@Component
public class CategoriaImplementation implements CategoriaGateway {

    private final CategoriaRepository categoriaRepository;

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
    public CategoriaDomainResponse cadastrarCategoria(CategoriaDomainRequest categoriaDomainRequest) {
        CategoriaEntity novaCategoria = CategoriaMapperDataproviderRequest.toEntity(categoriaDomainRequest);
        CategoriaEntity categoriaSalva = categoriaRepository.save(novaCategoria);

        return CategoriaMapperResponse.toDomain(categoriaSalva);
    }

    @Transactional
    @Override
    public void removerCategoriaPorId(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
        categoriaRepository.flush();
    }

    @Transactional
    @Override
    public CategoriaDomainResponse atualizarCategoria(CategoriaDomainResponse categoriaAtual) {
        CategoriaEntity novaCategoria = CategoriaMapperDataproviderRequest.toEntityUpdate(categoriaAtual);
        CategoriaEntity categoriaSalva = categoriaRepository.save(novaCategoria);

        return CategoriaMapperResponse.toDomain(categoriaSalva);
    }

    @Override
    public CategoriaDomainResponse buscarCategoriaPorNome(String nome) {
        CategoriaEntity categoriaExistente = categoriaRepository.findByNome(nome);

        return CategoriaMapperResponse.toDomain(categoriaExistente);
    }
}
