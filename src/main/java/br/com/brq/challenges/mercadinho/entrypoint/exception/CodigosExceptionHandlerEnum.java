package br.com.brq.challenges.mercadinho.entrypoint.exception;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public enum CodigosExceptionHandlerEnum {

    INTERNAL_SERVER_ERROR("/erro-generico", 500),
    NOT_FOUND("/recurso-nao-encontrado", 404),
    CONFLICT("/entidade-em-uso", 409),
    UNPROCESSABLE_ENTITY("/regra-negocio-invalida", 422),
    BAD_REQUEST("/requisicao-invalida", 400);

    private final String url;
    private final Integer httpStatus;

    CodigosExceptionHandlerEnum(String url, Integer httpStatus) {
        this.url = url;
        this.httpStatus = httpStatus;
    }

    public String getUrl() {
        return url;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public static String buscaUrl(Integer httpStatusValue) {
        if (Objects.isNull(httpStatusValue)) {
            return CodigosExceptionHandlerEnum.INTERNAL_SERVER_ERROR.getUrl();
        }

        return Arrays.stream(values())
                .filter(url -> url.getHttpStatus().equals(httpStatusValue))
                .map(CodigosExceptionHandlerEnum::getUrl)
                .collect(Collectors.joining());
    }
}
