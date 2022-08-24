package br.com.brq.challenges.mercadinho.entrypoint.validate;

import br.com.brq.challenges.mercadinho.entrypoint.validate.exception.RequisicaoInvalidaException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ValidacaoOfertaProduto {

    private ValidacaoOfertaProduto() {
    }

    public static void validate(List<String> idsProduto) {

        if (idsProduto.isEmpty()) {
            throw new RequisicaoInvalidaException(List.of("Vixi", "Erro"));
        }

        idsProduto.forEach(idProduto -> {
            if (StringUtils.isBlank(idProduto)) {
                throw new RequisicaoInvalidaException(List.of("Vixi", "Erro"));
            }
        });
    }
}
