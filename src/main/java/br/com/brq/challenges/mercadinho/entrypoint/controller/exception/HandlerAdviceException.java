package br.com.brq.challenges.mercadinho.entrypoint.controller.exception;

import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeEmUsoException;
import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeExistenteException;
import br.com.brq.challenges.mercadinho.usecase.exception.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerAdviceException extends ResponseEntityExceptionHandler {

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

    private MensagemExceptionModelResponse montarRespostaExcecao(HttpStatus httpStatus, Exception exception) {
         return MensagemExceptionModelResponse.builder()
                .codigo(String.valueOf(httpStatus.value()))
                .mensagem(exception.getMessage())
                .build();
    }
}
