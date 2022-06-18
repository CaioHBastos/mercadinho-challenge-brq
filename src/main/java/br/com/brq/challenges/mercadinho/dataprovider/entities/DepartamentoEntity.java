package br.com.brq.challenges.mercadinho.dataprovider.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "departamento")
public class DepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartamento;

    @Column(nullable = false)
    private String nomeDepartamento;

    public DepartamentoEntity() {}

    public DepartamentoEntity(Long idDepartamento, String nomeDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nomeDepartamento = nomeDepartamento;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartamentoEntity that = (DepartamentoEntity) o;
        return idDepartamento.equals(that.idDepartamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepartamento);
    }
}
