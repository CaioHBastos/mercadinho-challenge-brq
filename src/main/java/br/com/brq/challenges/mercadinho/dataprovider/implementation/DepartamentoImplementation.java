package br.com.brq.challenges.mercadinho.dataprovider.implementation;

import br.com.brq.challenges.mercadinho.dataprovider.entities.DepartamentoEntity;
import br.com.brq.challenges.mercadinho.dataprovider.mapper.DepartamentoDataproviderMapper;
import br.com.brq.challenges.mercadinho.dataprovider.repositories.DepartamentoRepository;
import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import br.com.brq.challenges.mercadinho.usecase.gateway.DepartamentoGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class DepartamentoImplementation implements DepartamentoGateway {

    private final DepartamentoDataproviderMapper departamentoDataproviderMapper =
            Mappers.getMapper(DepartamentoDataproviderMapper.class);

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoImplementation(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Transactional
    @Override
    public Departamento criarDepartamento(Departamento departamento) {
        DepartamentoEntity departamentoEntity = departamentoDataproviderMapper.map(departamento);
        DepartamentoEntity departamentoCriado = departamentoRepository.save(departamentoEntity);

        return departamentoDataproviderMapper.map(departamentoCriado);
    }

    @Override
    public Optional<String> consultarDepartamentoPorNome(String nomeDepartamento) {
        Optional<DepartamentoEntity> departamentoEntity = departamentoRepository.findByNome(nomeDepartamento);

        if (departamentoEntity.isPresent()) {
            return Optional.of("Departamento presente");
        }

        return Optional.empty();
    }

    @Override
    public List<Departamento> buscarTodosDepartamentos(String nomeDepartamento) {
        List<DepartamentoEntity> departamentos = departamentoRepository.findAllWithFilter(nomeDepartamento);

        return departamentoDataproviderMapper.map(departamentos);
    }

    @Override
    public Optional<String> consultarDepartamentoPorId(Integer id) {
        Optional<DepartamentoEntity> departamento = departamentoRepository.findById(id);

        if (departamento.isPresent()) {
            return Optional.of("Departamento presente");
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void removerDepartamento(Integer id) {
        departamentoRepository.deleteById(id);
    }
}
