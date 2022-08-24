package br.com.brq.challenges.mercadinho.entrypoint.exception;

import br.com.brq.challenges.mercadinho.usecase.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MercadoExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

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

    @ExceptionHandler({ RegraDepartamentoException.class, RegraProdutoException.class, RegraOfertaException.class })
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        HttpStatus httpStatusBadRequest = HttpStatus.BAD_REQUEST;
        MensagemExceptionHandler mensagemExceptionHandler = new MensagemExceptionHandler();
        List<CampoMensagemExceptionHandler> camposMensagem = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(field -> {
            String mensagem = messageSource.getMessage(field, LocaleContextHolder.getLocale());
            CampoMensagemExceptionHandler campoMensagem = new CampoMensagemExceptionHandler();
            campoMensagem.setCampo(field.getField());
            campoMensagem.setMensagem(mensagem);

            camposMensagem.add(campoMensagem);
        });

        mensagemExceptionHandler.setCodigo(String.valueOf(httpStatusBadRequest.value()));
        mensagemExceptionHandler.setMensagem("Houve um erro no momento da requisição, é necessário corrigir.");
        mensagemExceptionHandler.setUrlErro(CodigosExceptionHandlerEnum.buscaUrl(httpStatusBadRequest.value()));
        mensagemExceptionHandler.setCampos(camposMensagem);

        return new ResponseEntity<>(mensagemExceptionHandler, httpStatusBadRequest);
    }

    private MensagemExceptionHandler montarSaidaException(HttpStatus httpStatus, String mensagem) {
        MensagemExceptionHandler mensagemExceptionHandler = new MensagemExceptionHandler();
        mensagemExceptionHandler.setCodigo(String.valueOf(httpStatus.value()));
        mensagemExceptionHandler.setMensagem(mensagem);
        mensagemExceptionHandler.setUrlErro(CodigosExceptionHandlerEnum.buscaUrl(httpStatus.value()));

        return mensagemExceptionHandler;
    }
}
