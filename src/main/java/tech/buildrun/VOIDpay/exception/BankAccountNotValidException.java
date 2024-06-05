package tech.buildrun.VOIDpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;


public class BankAccountNotValidException extends VOIDpayException{

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Bank Account Not Valid");
        pb.setDetail("Account not valid or not acepted.");
        return pb;
    }
}
