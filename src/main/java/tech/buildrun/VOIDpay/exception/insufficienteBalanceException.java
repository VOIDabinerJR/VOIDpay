package tech.buildrun.VOIDpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class insufficienteBalanceException  extends VOIDpayException{
    @Override
    public ProblemDetail toProblemDetail() {
        var pb= ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Insufficient Balance");
        pb.setDetail("Cannot transfer values bigger than your balance.");
        return pb;
    }
}
