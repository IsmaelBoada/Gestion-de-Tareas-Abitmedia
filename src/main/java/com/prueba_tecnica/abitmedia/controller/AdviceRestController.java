package com.prueba_tecnica.abitmedia.controller;

import com.prueba_tecnica.abitmedia.rest.models.ModelResponseDto;
import com.prueba_tecnica.abitmedia.services.exception.ApiException;
import com.prueba_tecnica.abitmedia.services.messages.ApiMessages;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@RestControllerAdvice
public class AdviceRestController extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ModelResponseDto response = ModelResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message(ApiMessages.ERROR_VALIDACIONES_REQUEST + String.join(", ", errors))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(value = ApiException.class)
    public ModelResponseDto handleApiAccess(ApiException exception) {
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.NOT_ACCEPTABLE)
                .message(exception.getMessage())
                .build();
    }


}
