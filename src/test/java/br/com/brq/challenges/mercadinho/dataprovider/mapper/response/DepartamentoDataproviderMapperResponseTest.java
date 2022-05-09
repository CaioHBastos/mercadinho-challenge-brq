package br.com.brq.challenges.mercadinho.dataprovider.mapper.response;

import br.com.brq.challenges.mercadinho.annotations.Unitario;
import br.com.brq.challenges.mercadinho.mock.DepartamentoMock;
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
            final var departamentos = DepartamentoMock.mockDepartamentosResponse();

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

        @DisplayName("Converter uma lista de entidades de departamentos para uma lista de domínios com sucesso para departamentos")
        @Test
        void testConvertSucesso() {
            final var departamento = DepartamentoMock.mockDepartamentoResponse();

            final var departamentoEntity = DepartamentoDataproviderMapperResponse.convert(departamento);

            assertNotNull(departamentoEntity);
            assertAll(
                    () -> assertEquals(1L, departamentoEntity.getId()),
                    () -> assertEquals("Alimentos", departamentoEntity.getNome())
            );
        }
    }
}
