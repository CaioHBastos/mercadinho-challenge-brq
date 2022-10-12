package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.*;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import br.com.brq.challenges.mercadinho.usecase.service.utils.MercadinhoServiceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoGateway produtoGateway;
    private final DepartamentoUseCase departamentoUseCase;

    public ProdutoUseCaseImpl(ProdutoGateway produtoGateway, DepartamentoUseCase departamentoUseCase) {
        this.produtoGateway = produtoGateway;
        this.departamentoUseCase = departamentoUseCase;
    }

    @Override
    public Produto criarProduto(Produto produto) {

        try {
            validarCadastroProduto(produto);
            produto.setId(UUID.randomUUID().toString());
            produto.setDataCadastro(MercadinhoServiceUtils.gerarData());
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
    public List<Produto> buscarProdutos(Produto produto) {
        List<Produto> produtos = produtoGateway.buscarTodosProdutos(produto);

        if (produtos.isEmpty()) {
            throw new NenhumConteudoEncontradoException("Não existem cadastro de produtos");
        }

        return produtos;
    }

    @Override
    public Produto detalharProduto(String idProduto) {
        return produtoGateway.detalharProdutoPorId(idProduto)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoException(
                                String.format("O produto informado '%s' não existe.", idProduto)
                ));
    }

    @Override
    public void removerProduto(String idProduto) {
        Produto produtoPresente = detalharProduto(idProduto);

        if (produtoPresente.getAtivo()) {
            throw new RegraProdutoException("O produto não pode ser removido porque está ativo.");
        }

        produtoGateway.removerProdutoPorId(idProduto);
    }

    @Override
    public Produto atualiazarParcialmenteProduto(String idProduto, Produto novoProduto) {

        Produto produtoAtual = detalharProduto(idProduto);

        if (StringUtils.isNotBlank(novoProduto.getNome())) {

            Optional<Produto> produtoPresente = produtoGateway.buscarProdutoPorNome(novoProduto.getNome());

            if (produtoPresente.isPresent() && !StringUtils.equalsIgnoreCase(produtoAtual.getId(), idProduto)) {
                validarDuplicidade(novoProduto);
            }

            produtoAtual.setNome(novoProduto.getNome());
        }

        if (StringUtils.isNotBlank(novoProduto.getDescricao())) {
            produtoAtual.setDescricao(novoProduto.getDescricao());
        }

        if (StringUtils.isNotBlank(novoProduto.getMarca())) {
            produtoAtual.setMarca(novoProduto.getMarca());
        }

        if (Objects.nonNull(novoProduto.getPreco())) {
            validarPreco(novoProduto);
            produtoAtual.setPreco(novoProduto.getPreco());
        }

        if (Objects.nonNull(novoProduto.getDepartamentos())) {
            validarDepartamento(novoProduto);

            produtoAtual.setDepartamentos(novoProduto.getDepartamentos());
        }

        produtoAtual.setDataAtualizacao(MercadinhoServiceUtils.gerarData());

        return produtoGateway.atualizarProduto(produtoAtual);
    }

    @Override
    public void ativarProdutos(List<Produto> produtos) {
        List<Produto> produtosAtualizado = new ArrayList<>();

        produtos.forEach(produto -> {
            try {
                Produto produtoAtual = detalharProduto(produto.getId());
                produtoAtual.setAtivo(Boolean.TRUE);
                produtoAtual.setDataAtualizacao(MercadinhoServiceUtils.gerarData());

                produtosAtualizado.add(produtoAtual);

            } catch (RecursoNaoEncontradoException exception) {
                throw new RegraProdutoException(String.format(
                        "O produto com o ID '%s' não foi encontrado.", produto.getId()
                ));
            }
        });

        produtoGateway.atualizarAtivacaoProdutos(produtosAtualizado);
    }

    @Override
    public void inativarProdutos(List<Produto> produtos) {
        List<Produto> produtosAtualizado = new ArrayList<>();

        produtos.forEach(produto -> {
            try {
                Produto produtoAtual = detalharProduto(produto.getId());
                produtoAtual.setAtivo(Boolean.FALSE);
                produtoAtual.setOfertado(Boolean.FALSE);
                produtoAtual.setPorcentagemOferta(0);
                produtoAtual.setDataAtualizacao(MercadinhoServiceUtils.gerarData());

                produtosAtualizado.add(produtoAtual);

            } catch (RecursoNaoEncontradoException exception) {
                throw new RegraProdutoException(String.format(
                        "O produto com o ID '%s' não foi encontrado.", produto.getId()
                ));
            }
        });

        produtoGateway.atualizarAtivacaoProdutos(produtosAtualizado);
    }

    private void validarCadastroProduto(Produto produto) {
        validarDuplicidade(produto);
        validarPreco(produto);
        validarDepartamento(produto);
    }

    private void validarDuplicidade(Produto produto) {
        produtoGateway.buscarProdutoPorNome(produto.getNome())
                .ifPresent(nomeProduto -> {
                    throw new RegraProdutoException(
                            String.format("Erro ao realizar o cadastro do produto. Já existe um produto cadastrado " +
                                    "com o nome '%s'.", produto.getNome()));
                });
    }

    private void validarPreco(Produto produto) {
        if (produto.getPreco() <= 0) {
            throw new RegraProdutoException(String.format(
                    "Erro ao realizar o cadastro do produto. O produto não pode ser cadastrado com o valor menor " +
                            "ou igual zero e foi informado '%s'.", produto.getPreco()
            ));
        }
    }

    private void validarDepartamento(Produto produto) {
        try {
            produto.getDepartamentos().forEach(departamento -> departamentoUseCase.buscarDepartamento(departamento.getId()));

        } catch (RecursoNaoEncontradoException exception) {
            throw new RegraProdutoException(exception.getMessage());
        }
    }
}
