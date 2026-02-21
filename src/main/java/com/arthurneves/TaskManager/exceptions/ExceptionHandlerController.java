package com.arthurneves.TaskManager.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {
    // Cria as mensagens
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message) {
        this.messageSource = message; // Apenas para que o message nao inice null
    };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidExcepetion(MethodArgumentNotValidException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        // e => E um objeto com varios campos
        e.getBindingResult().getFieldErrors().forEach(field -> {
            String message = messageSource.getMessage(field, LocaleContextHolder.getLocale());

            ErrorMessageDTO err = new ErrorMessageDTO(message, field.getField());
            dto.add(err);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
