package tech.buildrun.VOIDpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DepositNotAllowedException  extends VOIDpayException{

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Deposit Not Authorized");
        pb.setDetail("Authorization service not authorized this deposit.");
        return pb;
    }
}
