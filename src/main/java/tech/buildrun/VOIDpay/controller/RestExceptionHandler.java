package tech.buildrun.VOIDpay.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.tags.Param;
import tech.buildrun.VOIDpay.exception.VOIDpayException;
import org.springframework.validation.FieldError;

import java.security.InvalidParameterException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(VOIDpayException.class)
    public ProblemDetail handleVOIDpayException(VOIDpayException e){
        return  e.toProblemDetail();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var fieldErros = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pd= ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Your resquest parameters didnt validate.");
        pd.setProperty("Invalid-params", fieldErros);
        return pd;
    }
    private  record  InvalidParam(String name, String reason){};
}
