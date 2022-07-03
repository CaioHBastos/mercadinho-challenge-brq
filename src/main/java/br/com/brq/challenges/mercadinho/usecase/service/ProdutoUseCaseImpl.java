package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroProdutoException;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroRegraProdutoException;
import br.com.brq.challenges.mercadinho.usecase.exception.CadastroReprocessamentoException;
import br.com.brq.challenges.mercadinho.usecase.exception.RecursoNaoEncontradoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto criarProduto(Produto produto) {

        try {
            validarCadastroProduto(produto);
            produto.setId(UUID.randomUUID().toString());
            produto.setDataCadastro(gerarDataCadastro());
            produto.setAtivo(true);
            produto.setOfertado(false);
            produto.setPorcentagemOferta(0);

            return produtoGateway.criarProduto(produto);

        } catch (CadastroProdutoException exception) {
            throw new CadastroReprocessamentoException(
                    "Requisição aceita, porém está em processamento o cadastro do produto."
            );
        }
    }

    @Override
    public List<Produto> buscarProdutos() {
        return produtoGateway.buscarTodosProdutos();
    }

    @Override
    public Produto detalharProduto(String idProduto) {
        return produtoGateway.detalharProdutoPorId(idProduto)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoException(
                                String.format("O produto informado '%s' não existe.", idProduto)
                ));
    }

    private String gerarDataCadastro() {
        return OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).toString();
    }

    private void validarCadastroProduto(Produto produto) {
        validarDuplicidade(produto);
        validarPreco(produto);
    }

    private void validarDuplicidade(Produto produto) {
        produtoGateway.buscarProdutoPorNome(produto.getNome())
                .ifPresent(nomeProduto -> {
                    throw new CadastroRegraProdutoException(
                            String.format("Erro ao realizar o cadastro do produto. Já existe um produto cadastrado " +
                                    "com o nome '%s'.", produto.getNome()));
                });
    }

    private void validarPreco(Produto produto) {
        if (produto.getPreco() <= 0) {
            throw new CadastroRegraProdutoException(String.format(
                    "Erro ao realizar o cadastro do produto. O produto não pode ser cadastrado com o valor menor " +
                            "ou igual zero e foi informado '%s'.", produto.getPreco()
            ));
        }
    }
}
