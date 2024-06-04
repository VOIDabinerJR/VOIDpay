package tech.buildrun.VOIDpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class transferNotAllowedException extends VOIDpayException{

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Tranfer Not Authorized");
        pb.setDetail("Authorization service not authorized this transfer.");
        return pb;
    }
}
