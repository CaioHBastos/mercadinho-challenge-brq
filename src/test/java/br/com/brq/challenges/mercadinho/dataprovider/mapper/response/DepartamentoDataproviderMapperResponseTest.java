package br.com.brq.challenges.mercadinho.dataprovider.mapper.response;

import br.com.brq.challenges.mercadinho.annotations.Unitario;
import br.com.brq.challenges.mercadinho.dataprovider.entities.DepartamentoEntity;
import br.com.brq.challenges.mercadinho.mock.DepartamentoEntityMock;
import br.com.brq.challenges.mercadinho.usecase.domain.Departamento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Unitario
public class DepartamentoDataproviderMapperResponseTest {

    @Nested
    class DepartamentoDataproviderMapperResponseConvertDepartamentosTest {

        @DisplayName("Converter uma lista de entidades de departamentos para uma lista de domínios com sucesso para departamentos")
        @Test
        void testConvertSucesso() {
            final var departamentos = DepartamentoEntityMock.mockDepartamentosResponse();

            final var departamentosEntity = DepartamentoDataproviderMapperResponse.convert(departamentos);

            assertNotNull(departamentosEntity);
            assertFalse(departamentosEntity.isEmpty());
            assertAll(
                    () -> assertEquals(1L, departamentosEntity.get(0).getId()),
                    () -> assertEquals("Alimentos", departamentosEntity.get(0).getNome()),
                    () -> assertEquals(2L, departamentosEntity.get(1).getId()),
                    () -> assertEquals("Limpeza", departamentosEntity.get(1).getNome())
            );
        }

        @DisplayName("Converter uma lista de entidades de departamentos vazio para uma lista de domínios vazio com sucesso")
        @Test
        void testConvertSemDepartamentosSucesso() {
            final var departamentosEntity = DepartamentoDataproviderMapperResponse.convert(Collections.emptyList());
            assertTrue(departamentosEntity.isEmpty());
        }
    }

    @Nested
    class DepartamentoDataproviderMapperResponseConvertDepartamentoTest {

        @DisplayName("Converter uma entidade de departamento para o domínio com sucesso para departamentos")
        @Test
        void testConvertSucesso() {
            final var departamentoEntity = DepartamentoEntityMock.mockDepartamentoResponse();

            final var departamento = DepartamentoDataproviderMapperResponse.convert(departamentoEntity);

            assertNotNull(departamento);
            assertAll(
                    () -> assertEquals(1L, departamento.getId()),
                    () -> assertEquals("Alimentos", departamento.getNome())
            );
        }

        @DisplayName("Converter uma entidade de departamento que está nulo retornando nulo para evitar nullPointer")
        @Test
        void testConvertNull() {
            DepartamentoEntity departamentoEntity = null;
            final var departamento = DepartamentoDataproviderMapperResponse.convert(departamentoEntity);

            assertNull(departamento);
        }
    }
}
