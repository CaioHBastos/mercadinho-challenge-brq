package br.com.brq.challenges.mercadinho.entrypoint.model.response;

public class DepartamentoModelResponse {

    private Long id;
    private String nome;

    public DepartamentoModelResponse() {}

    public DepartamentoModelResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
