package tech.buildrun.VOIDpay.controller;


import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.buildrun.VOIDpay.exception.VOIDpayException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(VOIDpayException.class)
    public ProblemDetail handleVOIDpayException(VOIDpayException e){
        return  e.toProblemDetail();
    }
}
