package br.com.brq.challenges.mercadinho.usecase;

import br.com.brq.challenges.mercadinho.usecase.domain.request.CategoriaDomainRequest;
import br.com.brq.challenges.mercadinho.usecase.domain.response.CategoriaDomainResponse;
import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeEmUsoException;
import br.com.brq.challenges.mercadinho.usecase.gateway.CategoriaGateway;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.brq.challenges.mercadinho.usecase.utils.CategoriaUseCaseUtils.verificarSeCategoriaEstaPresente;
import static br.com.brq.challenges.mercadinho.usecase.utils.CategoriaUseCaseUtils.verificarSeCategoriaExisteDeAcordoComONome;

@AllArgsConstructor
@Service
public class CategoriaUseCase {

    private static final String MENSAGEM_ERRO_REMOVER_CATEGORIA_EM_USO =
            "A categoria com o código %s, não pode ser removida, porque está em uso";

    private final CategoriaGateway categoriaGateway;

    /**
     * Método responsável por buscar todo a coleção de categorias através
     * da comunicação com o gateway se comunica com a base de dados.
     *
     * @return {@code List<CategoriaDomainResponse>}
     *      - Retorna uma lista de categorias da base de dados, tendo ou não conteúdo.
     */
    public List<CategoriaDomainResponse> buscarCategorias() {
        return categoriaGateway.buscarCategorias();
    }

    /**
     * Método responsável por buscar uma categoria específica por ID na base de dados
     * com comunicação com o gateway que se comunica com o provider.
     * Após a busca, é realizada uma verificação para saber se a categoria existe ou não
     * na base de dados, de acordo com o retorno.
     *
     * @param idCategoria {@code Long}
     *      - Id da categoria para a busca na base de dados.
     *
     * @return {@code CategoriaDomainResponse}
     *      - Caso a categoria exista cadastrada, é retornado os dados de informação da categoria.
     */
    public CategoriaDomainResponse buscarCategoriaPorId(Long idCategoria) {

        CategoriaDomainResponse categoriaDomain = categoriaGateway.buscarCategoriaPorId(idCategoria);
        verificarSeCategoriaEstaPresente(categoriaDomain, idCategoria);

        return categoriaDomain;
    }

    /**
     * Método responsável por realizar o cadastro da categoria de acordo com os dados de entrada
     * na base de dados, com a comunicação com o gateway que se comunica com o provider.
     * Antes do cadastro é feito uma busca na base de acordo com o nome a se cadastrado, para saber
     * se aquele nome, já não existe um cadastro específico.
     *
     * @param categoriaDomainRequest {@code CategoriaDomainRequest}
     *      - Dados de entrada para um novo cadastro com relação a categoria.
     *
     * @return {@code CategoriaDomainResponse}
     *      - Em caso de sucesso no cadastro, retorna os dados de uma nova categoria cadastrada
     *      na base de dados.
     */
    public CategoriaDomainResponse cadastrarCategoria(CategoriaDomainRequest categoriaDomainRequest) {

        CategoriaDomainResponse categoriaDomainPorNome = buscarCategoriaPorNome(categoriaDomainRequest.getNome());
        verificarSeCategoriaExisteDeAcordoComONome(categoriaDomainRequest, categoriaDomainPorNome);

        return categoriaGateway.cadastrarCategoria(categoriaDomainRequest);
    }

    /**
     * Método responsável por atualizar uma categoria de acordo com o ID e os dados do corpo para a atualização.
     * É feito uma comunicação com o gateway que se comunica com o provider para a atualização. Antes é feito uma
     * verificação para saber se o recurso existe e se o nome para atualizar não é o mesmo que já existe na base.
     *
     * @param idCategoria {@code Long}
     *      - Id da categoria para a busca do recurso;
     * @param categoriaDomainNova {@code CategoriaDomainRequest}
     *      - Objeto novo para a atualização do recurso da categoria.
     *
     * @return {@code CategoriaDomainResponse}
     *      - Em caso de sucesso na atualização, retorna um objeto com os novos dados atualizados na base de dados.
     */
    public CategoriaDomainResponse atualizarCategoria(Long idCategoria, CategoriaDomainRequest categoriaDomainNova) {

        CategoriaDomainResponse categoriaAtual = buscarCategoriaPorId(idCategoria);
        CategoriaDomainResponse categoriaDomainPorNome = buscarCategoriaPorNome(categoriaDomainNova.getNome());
        verificarSeCategoriaExisteDeAcordoComONome(categoriaDomainNova, categoriaDomainPorNome);

        categoriaAtual = montarCategoriaAtualizada(categoriaAtual, categoriaDomainNova);

        return categoriaGateway.atualizarCategoria(categoriaAtual);
    }

    /**
     * Método responsável por remover uma categoria da base de dados, com a comunicação pelo gateway
     * que se comunica com o provider. Antes da remoção, é feita uma verificação para saber se categoria
     * existe na base. Caso exista é feita a remoção.
     * Caso ocorra algum erro de <b>DataIntegrityViolationException</b> é lançada uma exceção informando
     * que a entidade da categoria está em uso e não pode ser removida.
     *
     * @param idCategoria {@code Long}
     *      - Id da categoria para ser informada na busca do recurso.
     */
    public void removerCategoriaPorId(Long idCategoria) {

        buscarCategoriaPorId(idCategoria);

        try {
            categoriaGateway.removerCategoriaPorId(idCategoria);

        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(
                    String.format(MENSAGEM_ERRO_REMOVER_CATEGORIA_EM_USO, idCategoria));
        }
    }

    /**
     * Método responsável por buscar uma categoria por nome na base de dados.
     *
     * @param nomeCategoria {@code String}
     *      - Nome da categoria que será pesquisada.
     *
     * @return {@code CategoriaDomainResponse}
     *      - Objeto de retorno de acordo com a busca feita pelo nome.
     */
    private CategoriaDomainResponse buscarCategoriaPorNome(String nomeCategoria) {
        return categoriaGateway.buscarCategoriaPorNome(nomeCategoria);
    }

    /**
     * Método responsável por montar o objeto que será usado para a atualização do recurso de categoria.
     * É feito a montagem para a atualização do objeto por completo.
     *
     * @param categoriaAtual {@code CategoriaDomainResponse}
     *      - Objeto que está salvo atualmente na base de dados.
     * @param categoriaDomainNova {@code CategoriaDomainRequest}
     *      - Objeto que foi recebido na entrada para a atualização do recurso.
     *
     * @return {@code CategoriaDomainResponse}
     *      - Retorna o objeto atualizado para salvar na base.
     */
    private CategoriaDomainResponse montarCategoriaAtualizada(CategoriaDomainResponse categoriaAtual,
                                                              CategoriaDomainRequest categoriaDomainNova) {
        return CategoriaDomainResponse.builder()
                .id(categoriaAtual.getId())
                .nome(categoriaDomainNova.getNome())
                .build();
    }
}