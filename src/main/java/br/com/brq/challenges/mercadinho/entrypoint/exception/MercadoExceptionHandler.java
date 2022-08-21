package br.com.brq.challenges.mercadinho.entrypoint.exception;

import br.com.brq.challenges.mercadinho.usecase.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MercadoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<MensagemExceptionHandler> handleErroGenerico(Exception exception) {
        HttpStatus httpErroGenerio = HttpStatus.INTERNAL_SERVER_ERROR;

        MensagemExceptionHandler mensagemExceptionHandler = montarSaidaException(
                httpErroGenerio, "Ocorreu um erro interno no serviço."
        );

        return new ResponseEntity<>(mensagemExceptionHandler, httpErroGenerio);
    }

    @ExceptionHandler(NenhumConteudoEncontradoException.class)
    public final ResponseEntity<?> handleNenhumConteudo(Exception exception) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public final ResponseEntity<MensagemExceptionHandler> handleRecursoNaoEncontrado(Exception exception) {
        HttpStatus httpRegistroNaoEncontrado = HttpStatus.NOT_FOUND;

        MensagemExceptionHandler mensagemExceptionHandler = montarSaidaException(
                httpRegistroNaoEncontrado, exception.getMessage()
        );

        return new ResponseEntity<>(mensagemExceptionHandler, httpRegistroNaoEncontrado);
    }

    @ExceptionHandler({ RegraDepartamentoException.class, RegraProdutoException.class})
    public final ResponseEntity<MensagemExceptionHandler> handleRegraNegocio(Exception exception) {
        HttpStatus httpRegraNegocio = HttpStatus.UNPROCESSABLE_ENTITY;

        MensagemExceptionHandler mensagemExceptionHandler = montarSaidaException(
                httpRegraNegocio, exception.getMessage()
        );

        return new ResponseEntity<>(mensagemExceptionHandler, httpRegraNegocio);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public final ResponseEntity<MensagemExceptionHandler> handleEntidadeEmUso(Exception exception) {
        HttpStatus httpEntidadeEmUso = HttpStatus.CONFLICT;

        MensagemExceptionHandler mensagemExceptionHandler = montarSaidaException(
                httpEntidadeEmUso, exception.getMessage()
        );

        return new ResponseEntity<>(mensagemExceptionHandler, httpEntidadeEmUso);
    }

    private MensagemExceptionHandler montarSaidaException(HttpStatus httpStatus, String mensagem) {
        MensagemExceptionHandler mensagemExceptionHandler = new MensagemExceptionHandler();
        mensagemExceptionHandler.setCodigo(String.valueOf(httpStatus.value()));
        mensagemExceptionHandler.setMensagem(mensagem);
        mensagemExceptionHandler.setUrlErro(CodigosExceptionHandlerEnum.buscaUrl(httpStatus.value()));

        return mensagemExceptionHandler;
    }
}
