package br.com.brq.challenges.mercadinho.usecase.service;

import br.com.brq.challenges.mercadinho.usecase.domain.Produto;
import br.com.brq.challenges.mercadinho.usecase.exception.*;
import br.com.brq.challenges.mercadinho.usecase.gateway.ProdutoGateway;
import br.com.brq.challenges.mercadinho.usecase.service.utils.MercadinhoServiceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    public List<Produto> buscarProdutos() {
        List<Produto> produtos = produtoGateway.buscarTodosProdutos();

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

        if (Objects.nonNull(novoProduto.getAtivo())) {

            if (!novoProduto.getAtivo()) {
                produtoAtual.setOfertado(Boolean.FALSE);
                produtoAtual.setPorcentagemOferta(0);
            }

            produtoAtual.setAtivo(novoProduto.getAtivo());
        }

        if (Objects.nonNull(novoProduto.getOfertado())) {

            if (!produtoAtual.getAtivo()) {
                throw new RegraProdutoException("Não é possível ofertar esse produto, porque o mesmo não está ativo.");
            }

            if (Objects.isNull(novoProduto.getPorcentagemOferta())) {
                throw new RegraProdutoException("Para ofertar um produto é obrigatório informar a porcentagem de oferta.");
            }

            if (produtoAtual.getOfertado() && novoProduto.getPorcentagemOferta() <= 0 ) {
                throw new RegraProdutoException("Não é possível ofertar o produto, sem informar a porcentagem de oferta.");
            }

            produtoAtual.setOfertado(novoProduto.getOfertado());
        }

        if (Objects.nonNull(novoProduto.getPorcentagemOferta())) {

            if (!produtoAtual.getOfertado()) {
                throw new RegraProdutoException("Não é possível adicionar uma porcentagem de oferta a esse produto, porque o mesmo não está sendo ofertado.");
            }

            if (novoProduto.getPorcentagemOferta() < 0) {
                throw new RegraProdutoException("A porcentagem da oferta tem que ser maior que 0.");
            }

            produtoAtual.setPorcentagemOferta(novoProduto.getPorcentagemOferta());
        }

        produtoAtual.setDataAtualizacao(MercadinhoServiceUtils.gerarData());

        return produtoGateway.atualizarProduto(produtoAtual);
    }

    private void validarCadastroProduto(Produto produto) {
        validarDuplicidade(produto);
        validarPreco(produto);
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
}
