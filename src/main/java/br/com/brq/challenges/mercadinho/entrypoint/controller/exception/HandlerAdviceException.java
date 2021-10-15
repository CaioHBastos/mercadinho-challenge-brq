package br.com.brq.challenges.mercadinho.entrypoint.controller.exception;

import br.com.brq.challenges.mercadinho.usecase.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerAdviceException extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public final ResponseEntity<?> handlerEntidadeNaoEncontrada(Exception exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        MensagemExceptionModelResponse mensagemException = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(mensagemException);
    }

    @ExceptionHandler(EntidadeExistenteException.class)
    public final ResponseEntity<?> handlerEntidadeExistente(Exception exception) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        MensagemExceptionModelResponse mensagemException = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(mensagemException);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public final ResponseEntity<?> handlerEntidadeEmUso(Exception exception) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        MensagemExceptionModelResponse mensagemException = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(mensagemException);
    }

    @ExceptionHandler(BadRequestPostException.class)
    public final ResponseEntity<?> handlerBadRequestPost(Exception exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        MensagemExceptionModelResponse mensagemException = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(mensagemException);
    }

    @ExceptionHandler(BadBusyException.class)
    public final ResponseEntity<?> handlerBadBusy(Exception exception) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        MensagemExceptionModelResponse mensagemException = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(mensagemException);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleValidationInternal(exception, exception.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request) {

        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<MensagemExceptionModelResponse.Campos> problemaCampos = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return MensagemExceptionModelResponse.Campos.builder()
                            .nome(name)
                            .mensagemUsuario(message)
                            .build();
                })
                .collect(Collectors.toList());

        MensagemExceptionModelResponse mensagemExceptionModelResponse = MensagemExceptionModelResponse.builder()
                .codigo(String.valueOf(status.value()))
                .mensagem(detail)
                .campos(problemaCampos)
                .build();

        return handleExceptionInternal(ex, mensagemExceptionModelResponse, headers, status, request);
    }

    private MensagemExceptionModelResponse montarRespostaExcecao(HttpStatus httpStatus, Exception exception) {
        return MensagemExceptionModelResponse.builder()
                .codigo(String.valueOf(httpStatus.value()))
                .mensagem(exception.getMessage())
                .build();
    }
}
