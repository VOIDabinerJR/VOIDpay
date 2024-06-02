package tech.buildrun.VOIDpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class VOIDpayException extends RuntimeException{
    public ProblemDetail toProblemDetail(){
        var pb= ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setDetail("VOIDpay Internal server error");
        return pb;
    }
}

